package ru.clevertec.web.controllers;

import ru.clevertec.beans.Product;
import ru.clevertec.constants.Constants;
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
import java.util.List;

@WebServlet(urlPatterns = {URL.BUY_PRODUCT_URL_PATTERN})
public class BuyProductController extends HttpServlet {
    DBService dbService;
    MainOrderService mainOrderService;

    @Override
    public void init() throws ServletException {
        dbService = (DBService) getServletContext().getAttribute(AttributeName.DB_SERVICE);
        mainOrderService = (MainOrderService) getServletContext().getAttribute(AttributeName.MAIN_ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = dbService.getProducts();

        req.setAttribute(AttributeName.PRODUCTS, products);
        req.setAttribute("quantityForDiscount", Constants.QUANTITY_FOR_DISCOUNT);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.BUY_PRODUCT_PAGE_URL);
        requestDispatcher.forward(req, resp);
    }
}
