package ru.clevertec.observer.listeners;

import ru.clevertec.mailer.IMailService;
import ru.clevertec.observer.entities.State;

public class EmailListener implements EventListener{
    private IMailService mailService;

    public EmailListener(IMailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void update(State eventType) {
        mailService.createEmail();
        mailService.prepareServer();
        mailService.sendMail();
    }
}
