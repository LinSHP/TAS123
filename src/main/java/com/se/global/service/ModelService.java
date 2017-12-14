package com.se.global.service;

//import packages
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Date;
import com.se.course.announcement.domain.Announcement;
import com.se.course.resource.domain.Resource;
import com.se.global.domain.User;
import com.se.global.domain.Course;
import com.se.notice.domain.CourseNotice;
import com.se.notice.domain.DetailNotice;
import com.se.notice.domain.Notice;
import com.se.notice.service.NoticeService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class ModelService {
    public static void setUser(Model model, User user) {
        model.addAttribute("user", user);
    }
    public static void setError(Model model, String error) {
        model.addAttribute("error", error);
    }

    public static void setInfo(Model model, String info) {
        model.addAttribute("info", info);
    }

    public static void setEmail(Model model, String email) {
        model.addAttribute("email", email);
    }

    public static void setAnnouncementList(Model model, ArrayList<Announcement> announcementList) {
        model.addAttribute("announcementList", announcementList);
    }

    public static void setMaterialList(Model model, ArrayList<Resource> materialList) {
        model.addAttribute("materialList", materialList);
    }

    public static void setVideoList(Model model, ArrayList<Resource> videoList) {
        model.addAttribute("videoList", videoList);
    }

    public static void setCourseList(Model model, ArrayList<Course> courseList) {
        model.addAttribute("courseList", courseList);
    }

    public static void setNotice(Model model, Notice notice) {
        model.addAttribute("notice", notice);
    }

    public static void setCourseNoticeList(Model model, ArrayList<CourseNotice> courseNoticeList) {
        model.addAttribute("courseNoticeList", courseNoticeList);
    }

    public static void setDetailNoticeList(Model model, ArrayList<DetailNotice> detailNoticeList) {
        model.addAttribute("detailNoticeList", detailNoticeList);
    }

    public static void setDetailNoticeType(Model model) {
        model.addAttribute("noticeType", NoticeService.DETAIL_NOTICE_TYPE);
    }

    public static void setMessageList(Model model, ArrayList<String> messageList) {
        model.addAttribute("messageList", messageList);
    }

    public static void setMessageDateList(Model model, ArrayList<Date> messageDateList) {
        model.addAttribute("messageDateList", messageDateList);
    }

    public static void setMessageURL(Model model, String messageURL) {
        model.addAttribute("messageURL", messageURL);
    }
}
