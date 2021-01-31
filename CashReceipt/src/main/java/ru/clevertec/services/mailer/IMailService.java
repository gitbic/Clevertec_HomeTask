package ru.clevertec.services.mailer;

public interface IMailService {
    void createEmail(CashReceiptType cashReceiptType);

    void prepareServer();

    void sendMail();

}
