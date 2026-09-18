package com.smartrail.model;

public class Admin extends User {
    private String adminId;

    public Admin(String adminId, String name, String phone, String email) {
        super(name, phone, email);
        this.adminId = adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}
