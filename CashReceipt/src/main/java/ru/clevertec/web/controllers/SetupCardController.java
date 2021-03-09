package ru.clevertec.web.controllers;

import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.web.constants.AttributeName;
import ru.clevertec.web.constants.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {URL.SETUP_CARD_URL_PATTERN})
public class SetupCardController extends HttpServlet {
    DBService dbService;
    MainOrderService mainOrderService;

    @Override
    public void init(){
        dbService = (DBService) getServletContext().getAttribute(AttributeName.DB_SERVICE);
        mainOrderService = (MainOrderService) getServletContext().getAttribute(AttributeName.MAIN_ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cardNumber = req.getParameter(AttributeName.CARD_NUMBER);
        mainOrderService.findDiscountCardForOrder();
    }
}
