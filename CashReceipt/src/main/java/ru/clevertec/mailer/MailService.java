package ru.clevertec.mailer;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import ru.clevertec.enums.CashReceiptSource;

import javax.activation.FileDataSource;


public class MailService {

    public static void sendCashReceiptToMail() {

        String sourceFilePath = CashReceiptSource
                .valueOf(MailProperties.MAIL_ATTACHMENT_SOURCE.toUpperCase())
                .getSourceFilePath();

        Email email = EmailBuilder.startingBlank()
                .from(MailProperties.FROM_ADDRESS)
                .to(MailProperties.TO_ADDRESS)
                .withSubject(MailProperties.MAIL_TITLE)
                .withPlainText(MailProperties.MAIL_BODY)
                .withAttachment(MailProperties.MAIL_ATTACHMENT_TITLE, new FileDataSource(sourceFilePath))
                .buildEmail();

        MailerBuilder.withSMTPServer(
                MailProperties.SMTP_HOST,
                MailProperties.SMTP_PORT,
                MailProperties.SMTP_USERNAME,
                MailProperties.SMTP_PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTPS)
                .buildMailer()
                .sendMail(email);

    }
}

