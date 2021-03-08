package ru.clevertec.web.controllers;

import ru.clevertec.web.constants.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {URL.MAIN_URL})
public class mainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> elements = new ArrayList<>();
        elements.add("First element");
        elements.add("Second element");

        req.setAttribute("elements", elements);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.BUY_PRODUCT);
        requestDispatcher.forward(req, resp);



//        String productName =req.getParameter("productName");
//        String productNumber =req.getParameter("productNumber");
//        System.out.println("productName = " + productName);
    }


}
