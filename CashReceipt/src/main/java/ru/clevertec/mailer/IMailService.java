package ru.clevertec.mailer;

public interface IMailService {
    void createEmail();

    void prepareServer();

    void sendMail();

}
