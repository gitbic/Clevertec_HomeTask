package ru.clevertec.web.controllers;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.web.builders.DBServiceBuilder;
import ru.clevertec.web.builders.MainOrderServiceBuilder;
import ru.clevertec.web.constants.AttributeName;
import ru.clevertec.web.constants.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {URL.SETUP_CARD_URL_PATTERN})
public class SetupCardController extends HttpServlet {
    private DBService dbService;
    private MainOrderService mainOrderService;

    @Override
    public void init(){
        dbService = DBServiceBuilder.getInstance();
        mainOrderService = MainOrderServiceBuilder.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cardNumber = req.getParameter(AttributeName.CARD_NUMBER);
        DiscountCard discountCard = dbService.getCardByNumber(cardNumber);

        mainOrderService.setupDiscountCard(discountCard);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.MAIN_URL_PATTERN);
        requestDispatcher.forward(req, resp);
    }
}
