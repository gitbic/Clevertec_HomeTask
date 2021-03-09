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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBService dbService = (DBService) getServletContext().getAttribute(Constant.DB_SERVICE_ATTRIBUTE_NAME);
        MainOrderService mainOrderService = (MainOrderService) getServletContext().getAttribute(Constant.MAIN_ORDER_SERVICE_ATTRIBUTE_NAME);

        List<Product> products = dbService.getProducts();

        req.setAttribute("products", products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.BUY_PRODUCT_PAGE_URL);
        requestDispatcher.forward(req, resp);
    }
}
