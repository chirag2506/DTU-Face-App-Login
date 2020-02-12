package com.example.dtufaceapplogin.Model;

public class User {

    private String email;
    private String password;
    private String rollno;
    private String phone;

    public User(){

    }

    public User(String email, String password, String rollno, String phone) {
        this.email = email;
        this.password = password;
        this.rollno = rollno;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
