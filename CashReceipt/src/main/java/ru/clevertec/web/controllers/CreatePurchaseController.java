package ru.clevertec.web.controllers;

import ru.clevertec.beans.Product;
import ru.clevertec.beans.Purchase;
import ru.clevertec.factories.PurchaseFactory;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBService;
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
    DBService dbService;
    MainOrderService mainOrderService;

    @Override
    public void init() throws ServletException {
        dbService = (DBService) getServletContext().getAttribute(AttributeName.DB_SERVICE);
        mainOrderService = (MainOrderService) getServletContext().getAttribute(AttributeName.MAIN_ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter(AttributeName.PRODUCT_NAME));
        int productNumber = Integer.parseInt(req.getParameter(AttributeName.PRODUCT_NUMBER));

        Product product = dbService.getProductById(productId);
        Purchase purchase = PurchaseFactory.createPurchase(product, productNumber);
        mainOrderService.addPurchaseToMainOrder(purchase);

//        List<Purchase> purchases = mainOrderService.getPurchases();
//
//        req.setAttribute(AttributeName.PURCHASES, purchases);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.INDEX_PAGE_URL);
//        requestDispatcher.forward(req, resp);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.MAIN_URL_PATTERN);
        requestDispatcher.forward(req, resp);

    }
}
