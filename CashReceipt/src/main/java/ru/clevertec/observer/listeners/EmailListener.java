package ru.clevertec.observer.listeners;

import ru.clevertec.mailer.CashReceiptType;
import ru.clevertec.mailer.IMailService;
import ru.clevertec.observer.entities.State;

public class EmailListener implements EventListener{
    private IMailService mailService;

    public EmailListener(IMailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void update(State eventType, String message) {
        mailService.createEmail(CashReceiptType.TXT);
        mailService.prepareServer();
        mailService.sendMail();
    }
}
