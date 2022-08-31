package com.example.entities;

import javax.persistence.*;

@Entity
@Table(
        name = "logins",
        uniqueConstraints = @UniqueConstraint(name = "LoginTableUniqueConstraints", columnNames = {"username"})
)
public class Login {

    @Id
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Login() {

    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login(String username, String password, User user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public Login(Long userId, String username, String password, User user) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}