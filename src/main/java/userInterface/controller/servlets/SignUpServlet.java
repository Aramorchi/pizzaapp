package userInterface.controller.servlets;

import model.facade.SimpleFacade;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpServlet extends HttpServlet {
    private SimpleFacade facade = SimpleFacade.getInstance();

    public SignUpServlet() throws IllegalAccessException, SQLException, InstantiationException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/RegistrationPage.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repeatedPassword = req.getParameter("passwordRepeat");

        if(repeatedPassword.equals(password)) {
            facade.registerNewUser(username, password);
            resp.sendRedirect("/main");
        } else {
            req.setAttribute("errorMessage", "Passwords don't match");
            doGet(req, resp);
        }
    }
}
