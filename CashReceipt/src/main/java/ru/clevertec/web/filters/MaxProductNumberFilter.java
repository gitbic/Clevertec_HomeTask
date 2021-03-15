package ru.clevertec.web.filters;

import ru.clevertec.beans.Product;
import ru.clevertec.beans.Purchase;
import ru.clevertec.factories.PurchaseFactory;
import ru.clevertec.services.MainOrderService;
import ru.clevertec.services.jdbc.DBService;
import ru.clevertec.web.builders.DBServiceBuilder;
import ru.clevertec.web.builders.MainOrderServiceBuilder;
import ru.clevertec.web.constants.AttributeName;
import ru.clevertec.web.constants.Constant;
import ru.clevertec.web.constants.URL;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = URL.CREATE_PURCHASE_URL_PATTERN)
public class MaxProductNumberFilter implements Filter {

    DBService dbService;
    MainOrderService mainOrderService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        int productId = Integer.parseInt(request.getParameter(AttributeName.PRODUCT_NAME));
        int productNumber = Integer.parseInt(request.getParameter(AttributeName.PRODUCT_NUMBER));

        Product product = dbService.getProductById(productId);
        Purchase purchase = PurchaseFactory.createPurchase(product, productNumber);
        Purchase oldPurchase = mainOrderService.findPurchaseInMainOrder(purchase);

        if (oldPurchase != null) {
            productNumber += purchase.getNumber();
        }

        if (productNumber > Constant.MAX_PRODUCT_NUMBER) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(URL.MAIN_URL_PATTERN);
            requestDispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        dbService = DBServiceBuilder.getInstance();
        mainOrderService = MainOrderServiceBuilder.getInstance();
    }

    @Override
    public void destroy() {

    }
}
