package ru.clevertec.mailer;

public interface IMailService {
    void createEmail(CashReceiptType cashReceiptType);

    void prepareServer();

    void sendMail();

}
