package com.ali.quizapp;



public class User  {
    private String name;
    private String email;
    private String pass;
    private String profile;
    private String referCode;
    private String updateBtn;

    public String getAboutBtn() {
        return aboutBtn;
    }

    public void setAboutBtn(String aboutBtn) {
        this.aboutBtn = aboutBtn;
    }

    public User(String aboutBtn) {
        this.aboutBtn = aboutBtn;
    }

    private String aboutBtn;
    private String profileImage;
    private String forgotPass;
    private long coins = 25;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUpdateBtn() {
        return updateBtn;
    }

    public void setUpdateBtn(String updateBtn) {
        this.updateBtn = updateBtn;
    }

    public String getForgotPass() {
        return forgotPass;
    }

    public void setForgotPass(String forgotPass) {
        this.forgotPass = forgotPass;
    }

    public User(String name, String email, String pass, String profile, String referCode, String updateBtn, String profileImage, String forgotPass, long coins) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.profile = profile;
        this.referCode = referCode;
        this.updateBtn = updateBtn;
        this.profileImage = profileImage;
        this.forgotPass = forgotPass;
        this.coins = coins;
    }

    public User(){
    }

    public User(String name, String email, String pass, String referCode) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.referCode = referCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
