package ru.clevertec.mailer;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.activation.FileDataSource;


public class MailService implements IMailService {
    Email email;
    Mailer mailer;


    @Override
    public void createEmail(CashReceiptType cashReceiptType) {
        String sourceFilePath = cashReceiptType.getSourceFilePath();

        email = EmailBuilder.startingBlank()
                .from(MailPropertiesStorage.FROM_ADDRESS)
                .to(MailPropertiesStorage.TO_ADDRESS)
                .withSubject(MailPropertiesStorage.MAIL_TITLE)
                .withPlainText(MailPropertiesStorage.MAIL_BODY)
                .withAttachment(MailPropertiesStorage.MAIL_ATTACHMENT_TITLE, new FileDataSource(sourceFilePath))
                .buildEmail();

    }

    @Override
    public void prepareServer() {
        mailer = MailerBuilder.withSMTPServer(
                MailPropertiesStorage.SMTP_HOST,
                MailPropertiesStorage.SMTP_PORT,
                MailPropertiesStorage.SMTP_USERNAME,
                MailPropertiesStorage.SMTP_PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTPS)
                .buildMailer();
    }

    @Override
    public void sendMail() {
        mailer.sendMail(email);
    }


}

