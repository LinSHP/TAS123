package com.se.user.password.web;

//import packages
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import com.se.global.domain.User;
import com.se.user.email.domain.EmailContext;
import com.se.user.email.service.EmailService;
import com.se.user.password.service.PasswordUpdateService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class PasswordController {
    private PasswordUpdateService passwordUpdateService;
    private EmailService emailService;
    private static final String DEFAULT_PASSWORD = "123456";

    @Autowired
    public void setPasswordUpdateService(PasswordUpdateService passwordUpdateService) {
        this.passwordUpdateService = passwordUpdateService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) { this.emailService = emailService; }

    /**
     * 显示密码修改界面
     *
     * @param session 当前会话
     * @return 若用户未设置邮箱则返回邮箱设置界面逻辑视图名，否则返回密码修改界面逻辑视图名
     */
    @RequestMapping("/user/password-modify")
    public String passwordModifyPage(HttpSession session) {
        User user = SessionService.getUser(session);
        String email = user.getEmail();

        if (email == null || email.isEmpty()) {
            return "user/email/email_bind";
        } else {
            return "user/password/password_modify";
        }
    }

    /**
     * 修改用户密码
     *
     * @param session 当前会话
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param model Model对象
     * @return 若修改失败则返回密码修改界面逻辑视图名，否则返回修改成功界面逻辑视图名
     */
    @RequestMapping("/user/password-modify/modify")
    public String modifyPassword(HttpSession session, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword1") String newPassword, Model model) {
        User user = SessionService.getUser(session);

        if (!passwordUpdateService.identifyPassword(user.getId(), oldPassword)) {
            ModelService.setError(model, "密码错误!");
            return "user/password/password_modify";
        }

        if (!passwordUpdateService.updatePassword(session, newPassword)) {
            ModelService.setError(model, "更新密码出错!");
            return "user/password/password_modify";
        }

        SessionService.removeUser(session);
        return "user/password/password_modify_success";
    }

    /**
     * 显示密码重置界面
     *
     * @return 密码重置界面逻辑视图名
     */
    @RequestMapping("/user/password-reset")
    public String passwordResetPage() {
        return "user/password/password_reset";
    }

    /**
     * 响应重置密码请求
     *
     * @param id 用户学号或工号
     * @param email 用户邮箱
     * @param model Model对象
     * @return 响应成功则返回邮件发送成功界面逻辑视图名，否则返回密码重置界面逻辑视图名
     */
    @RequestMapping("/user/password-reset/to-reset")
    public String toResetPassword(HttpSession session, @RequestParam("id") String id, @RequestParam("email") String email, Model model) {
        int type = passwordUpdateService.identifyEmail(id, email);

        if (type == 0) {
            ModelService.setError(model, "学号或邮箱不正确!");
            return "/user/password/password_reset";
        }

        User user = new User();
        user.setId(id);
        user.setType(type);

        EmailContext emailContext = new EmailContext();
        emailContext.setText("<html><body><a href=\"http://localhost:8080/user/password-reset/reset?id=" + emailContext.getUuid() + "\">点击该链接即可成功重置密码！</a></body></html>");

        if (emailService.sendEmail(session, email, emailContext)) {
            return "user/email/email_send_success";
        } else {
            ModelService.setError(model, "发送邮件失败!");
            return "user/password/password_reset";
        }
    }

    /**
     * 重置密码
     *
     * @param uuid 用户id对应的随机字符串
     * @param model Model对象
     * @return 密码重置完成界面逻辑视图名
     */
    @RequestMapping("/user/password-reset/reset")
    public String resetPassword(HttpSession session, @RequestParam("id") String uuid, Model model) {
        User user = emailService.getUser(uuid);

        if (user != null && passwordUpdateService.updatePassword(session, DEFAULT_PASSWORD)) {
            ModelService.setInfo(model, "密码重置成功!");
        } else {
            ModelService.setInfo(model, "密码重置失败!");
        }

        return "/user/password/password_reset_finish";
    }
}
