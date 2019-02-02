package userInterface.controller.servlets;

import model.facade.SimpleFacade;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Lenovo on 02.02.2019.
 */
public class SignOutServlet extends HttpServlet {
    private SimpleFacade facade = SimpleFacade.getInstance();

    public SignOutServlet() throws IllegalAccessException, SQLException, InstantiationException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        facade.logOut();
        resp.sendRedirect("/main");
    }
}
