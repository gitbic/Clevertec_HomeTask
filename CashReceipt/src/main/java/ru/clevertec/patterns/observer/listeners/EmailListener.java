package ru.clevertec.patterns.observer.listeners;

import ru.clevertec.services.mailer.IMailService;
import ru.clevertec.patterns.observer.entities.State;

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
