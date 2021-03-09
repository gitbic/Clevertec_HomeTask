package ru.clevertec.web.controllers;

import ru.clevertec.web.constants.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {URL.CREATE_PURCHASE_URL_PATTERN})
public class createPurchaseController extends HttpServlet {
//    List<Purchase> purchases = new ThreadSafeCustomLinkedList<>();
//    IMainOrder mainOrder = MainOrderFactory.NO_PROXY.createMainOrder(purchases);
//    MainOrderService mainOrderService = new MainOrderService(dbService, mainOrder);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
