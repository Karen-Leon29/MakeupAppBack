package com.dorysoft.mackeupApp.service;

public interface IServiceEmail {
    void sendEmail(String to, String subject, String text);
}
