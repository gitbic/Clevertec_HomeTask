package ru.clevertec.web.controllers;

import ru.clevertec.beans.DiscountCard;
import ru.clevertec.beans.Purchase;
import ru.clevertec.constants.Constants;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.web.builders.MainOrderServiceBuilder;
import ru.clevertec.web.constants.AttributeName;
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

@WebServlet(urlPatterns = {URL.MAIN_URL_PATTERN})
public class MainController extends HttpServlet {
    private MainOrderService mainOrderService;

    @Override
    public void init(){
        mainOrderService = MainOrderServiceBuilder.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Purchase> purchases = mainOrderService.getPurchases();
        DiscountCard discountCard = mainOrderService.getDiscountCard();
        String[] purchasesCost = mainOrderService.getPurchasesCost();

        req.setAttribute(AttributeName.PURCHASES, purchases);
        req.setAttribute(AttributeName.DISCOUNT_CARD, discountCard);
        req.setAttribute(AttributeName.QUANTITY_FOR_DISCOUNT, Constants.QUANTITY_FOR_DISCOUNT);
        req.setAttribute(AttributeName.DISCOUNT_FOR_PRODUCT, Constants.DEFAULT_DISCOUNT_PERCENT);
        req.setAttribute(AttributeName.TOTAL_COST, purchasesCost[Constant.TOTAL_COST_INDEX]);
        req.setAttribute(AttributeName.FINAL_COST, purchasesCost[Constant.FINAL_COST_INDEX]);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(URL.INDEX_PAGE_URL);
        requestDispatcher.forward(req, resp);


    }


}
