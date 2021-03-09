package ru.clevertec.web.controllers;

import ru.clevertec.services.jdbc.DBController;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.web.constants.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {URL.BUY_PRODUCT})
public class buyProductController extends HttpServlet {
    DBController dbController = new DBController();
    DBService dbService = new DBService(dbController);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName =req.getParameter("productName");
        String productNumber =req.getParameter("productNumber");
        System.out.println("productName = " + productName);

        System.out.println(productName);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.MAIN_URL);
        requestDispatcher.forward(req, resp);

    }
}
