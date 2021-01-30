package ru.clevertec.runners;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import ru.clevertec.enums.Arguments;

import javax.activation.FileDataSource;


public class MailRunner {

    public static void main(String[] args) {

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

        MailerBuilder.withSMTPServer(MailProperties.SMTP_HOST,
                MailProperties.SMTP_PORT,
                MailProperties.SMTP_USERNAME,
                MailProperties.SMTP_PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTPS)
                .buildMailer()
                .sendMail(email);

    }
}

enum CashReceiptSource {
    TXT(Arguments.CHECK_TXT_OUTPUT_PATH_FILE.getValue()),
    PDF(Arguments.CHECK_PDF_OUTPUT_PATH_FILE.getValue());

    private final String sourceFilePath;

    CashReceiptSource(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }
}
