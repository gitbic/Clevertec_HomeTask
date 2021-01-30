package ru.clevertec.runners;

import java.util.ResourceBundle;

public class MailProperties {
    public static final String MAIL_PROPERTIES = "send_mail";
    public static final ResourceBundle properties = ResourceBundle.getBundle(MAIL_PROPERTIES);

    public static final String SMTP_HOST = properties.getString("smtp.host");
    public static final int SMTP_PORT = Integer.parseInt(properties.getString("smtp.port"));
    public static final String SMTP_USERNAME = properties.getString("smtp.username");
    public static final String SMTP_PASSWORD = properties.getString("smtp.password");
    public static final String TO_ADDRESS = properties.getString("send.to_address");
    public static final String FROM_ADDRESS = properties.getString("send.from_address");
    public static final String MAIL_TITLE = properties.getString("mail.title");
    public static final String MAIL_BODY = properties.getString("mail.body");
    public static final String MAIL_ATTACHMENT_TITLE = properties.getString("mail.attachment.title");
    public static final String MAIL_ATTACHMENT_SOURCE = properties.getString("mail.attachment.source");

}
