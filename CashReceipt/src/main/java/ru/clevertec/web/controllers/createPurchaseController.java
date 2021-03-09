package ru.clevertec.web.controllers;

import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.web.constants.Constant;
import ru.clevertec.web.constants.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {URL.CREATE_PURCHASE_URL_PATTERN})
public class createPurchaseController extends HttpServlet {
    DBService dbService;
    MainOrderService mainOrderService;

    @Override
    public void init() throws ServletException {
        dbService = (DBService) getServletContext().getAttribute(Constant.DB_SERVICE_ATTRIBUTE_NAME);
        mainOrderService = (MainOrderService) getServletContext().getAttribute(Constant.MAIN_ORDER_SERVICE_ATTRIBUTE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Product product = new Product();
//        String productName = req.getParameter("productName");
        int productId = Integer.parseInt(req.getParameter("productName"));
        int productNumber = Integer.parseInt(req.getParameter("productNumber"));

        System.out.println("productName = " + productId);
        System.out.println("productNumber = " + productNumber);
    }
}
