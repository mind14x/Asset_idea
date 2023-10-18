package com.asset.model;

public class User {

    private int userId;
    private String username;
    private String password; // 注意: 在实际应用中, 我们通常不会直接存储明文密码. 使用哈希+盐的方式更为安全.
    private String role;
    private String status;

    // Getter, Setter, Constructors, and toString() methods

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
