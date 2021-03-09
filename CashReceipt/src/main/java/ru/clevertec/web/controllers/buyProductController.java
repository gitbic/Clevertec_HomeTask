package ru.clevertec.web.controllers;

import ru.clevertec.beans.Product;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.web.constants.Constant;
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
public class buyProductController extends HttpServlet {
    DBService dbService;
    MainOrderService mainOrderService;

    @Override
    public void init() throws ServletException {
        dbService = (DBService) getServletContext().getAttribute(Constant.DB_SERVICE_ATTRIBUTE_NAME);
        mainOrderService = (MainOrderService) getServletContext().getAttribute(Constant.MAIN_ORDER_SERVICE_ATTRIBUTE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = dbService.getProducts();

        req.setAttribute(Constant.PRODUCTS_ATTRIBUTE_NAME, products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.BUY_PRODUCT_PAGE_URL);
        requestDispatcher.forward(req, resp);
    }
}
