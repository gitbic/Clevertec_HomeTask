package ru.clevertec.observer.listeners;

import ru.clevertec.mailer.IMailService;
import ru.clevertec.observer.entities.State;

public class EmailListener implements EventListener{
    private final IMailService mailService;

    public EmailListener(IMailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void update(State eventType, String message) {
        System.out.println(message);
        mailService.createEmail(eventType.getCashReceiptType());
        mailService.prepareServer();
        mailService.sendMail();
    }
}
