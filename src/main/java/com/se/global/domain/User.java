package com.se.global.domain;

/**
 * 封装用户共有的属性
 *
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class User {
    private String id;
    private String name;
    private String college;
    private String imageLocation;
    private String signature;
    private String profile;
    private String email;
    private int type;
    public static final int STUDENT_TYPE = 1;
    public static final int TEACHER_TYPE = 2;
    public static final String STUDENT_ID = "student_id";
    public static final String TEACHER_ID = "teacher_id";
    public static final String ID = "id";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String COLLEGE = "college";
    public static final String EMAIL = "email";
    public static final String IMAGE_LOCATION = "image_position";
    public static final String SIGNATURE = "signature";
    public static final String PROFILE = "profile";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
