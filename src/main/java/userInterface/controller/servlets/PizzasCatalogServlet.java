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
 * Created by Lenovo on 02.02.2019.
 */
public class PizzasCatalogServlet extends HttpServlet {
    private SimpleFacade facade = SimpleFacade.getInstance();

    public PizzasCatalogServlet() throws IllegalAccessException, SQLException, InstantiationException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> pizzaNames = facade.getPizzaNames();
        req.setAttribute("pizzaNames", pizzaNames);
        RequestDispatcher view = req.getRequestDispatcher("/Catalog.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pizzaName = req.getParameterValues("pizza")[0];
        facade.addPizzaToBasket(pizzaName);
        doGet(req,resp);
    }
}
