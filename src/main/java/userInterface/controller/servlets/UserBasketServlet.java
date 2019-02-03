package userInterface.controller.servlets;

import model.facade.SimpleFacade;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lenovo on 03.02.2019.
 */
public class UserBasketServlet extends HttpServlet {
    private SimpleFacade simpleFacade = SimpleFacade.getInstance();

    public UserBasketServlet() throws IllegalAccessException, SQLException, InstantiationException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> pizzas = simpleFacade.getBasketItems();
        req.setAttribute("pizzaNames", pizzas);
        RequestDispatcher view = req.getRequestDispatcher("/BasketPage.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        simpleFacade.confirmOrder(address, phone);
        resp.sendRedirect("/orders");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        simpleFacade.removePizzaFromBasket(req.getParameterValues("removablePizza")[0]);
        doGet(req, resp);
    }
}
