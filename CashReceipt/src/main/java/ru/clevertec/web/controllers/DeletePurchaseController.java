package ru.clevertec.web.controllers;

import ru.clevertec.beans.Product;
import ru.clevertec.beans.Purchase;
import ru.clevertec.services.MainOrderService;
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

@WebServlet(urlPatterns = {URL.DELETE_PURCHASE_URL_PATTERN})
public class DeletePurchaseController extends HttpServlet {
    private MainOrderService mainOrderService;

    @Override
    public void init() {
        mainOrderService = MainOrderServiceBuilder.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter(AttributeName.DELETE_PURCHASE));
        Product product = new Product(productId, null, null, false);
        Purchase purchase = new Purchase(product, 0);

        if (mainOrderService.isExistPurchaseInMainOrder(purchase)) {
            mainOrderService.deletePurchaseFromMainOrder(purchase);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.MAIN_URL_PATTERN);
        requestDispatcher.forward(req, resp);
    }
}
