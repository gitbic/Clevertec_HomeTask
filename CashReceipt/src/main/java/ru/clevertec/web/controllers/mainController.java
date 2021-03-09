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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {URL.MAIN_URL})
public class mainController extends HttpServlet {
    DBController dbController;
    DBService dbService;

    @Override
    public void init() throws ServletException {
        dbController = new DBController();
        dbService = new DBService(dbController);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("product id 1 = " + dbService.getProductById(28));
        System.out.println("product111");

        List<String> elements = new ArrayList<>();
        elements.add("First element");
        elements.add("Second element");


        req.setAttribute("elements", elements);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);


    }


}
