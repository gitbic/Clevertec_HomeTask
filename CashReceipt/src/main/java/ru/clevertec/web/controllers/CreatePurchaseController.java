package ru.clevertec.web.controllers;

import ru.clevertec.beans.Product;
import ru.clevertec.beans.Purchase;
import ru.clevertec.factories.PurchaseFactory;
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

@WebServlet(urlPatterns = {URL.CREATE_PURCHASE_URL_PATTERN})
public class CreatePurchaseController extends HttpServlet {
    private DBService dbService;
    private MainOrderService mainOrderService;

    @Override
    public void init(){
        dbService = DBServiceBuilder.getInstance();
        mainOrderService = MainOrderServiceBuilder.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter(AttributeName.PRODUCT_NAME));
        int productNumber = Integer.parseInt(req.getParameter(AttributeName.PRODUCT_NUMBER));

        Product product = dbService.getProductById(productId);
        Purchase purchase = PurchaseFactory.createPurchase(product, productNumber);
        mainOrderService.addPurchaseToMainOrder(purchase);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.MAIN_URL_PATTERN);
        requestDispatcher.forward(req, resp);

    }
}
