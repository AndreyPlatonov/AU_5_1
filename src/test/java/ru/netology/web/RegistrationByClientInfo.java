package ru.netology.web;

public class RegistrationByClientInfo {
    private final String city;
    private final String fullName;
    private final String phone;

    public RegistrationByClientInfo(String city, String fullname, String phone) {
        this.city = city;
        this.fullName = fullname;
        this.phone = phone;
    }

    public String getCity() {
        return this.city;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPhone() {
        return this.phone;
    }
}
