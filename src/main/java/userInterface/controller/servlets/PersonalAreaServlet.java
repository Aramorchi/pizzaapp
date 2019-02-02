package userInterface.controller.servlets;

import model.facade.SimpleFacade;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Lenovo on 02.02.2019.
 */
public class PersonalAreaServlet extends HttpServlet {
    private SimpleFacade facade = SimpleFacade.getInstance();

    public PersonalAreaServlet() throws IllegalAccessException, SQLException, InstantiationException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = facade.getCurrentUsername();

        req.setAttribute("username", username);
        RequestDispatcher view = req.getRequestDispatcher("/PersonalArea.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
