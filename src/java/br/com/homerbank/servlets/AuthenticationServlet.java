package br.com.homerbank.servlets;

import br.com.homerbank.dao.DAOFactory;
import br.com.homerbank.dao.core.AccountDAO;
import br.com.homerbank.locales.Locales;
import br.com.homerbank.model.Account;
import br.com.homerbank.util.Configs;
import br.com.homerbank.util.MD5;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AuthLogin")
public class AuthenticationServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        final String agencyNumber = request.getParameter("agency");
        final String accountNumber = request.getParameter("account");
        final String accountPassword = !request.getParameter("password").isEmpty() ? MD5.encrypt(request.getParameter("password")) : null;
        
        AccountDAO accountDAO = DAOFactory.getDAOFactory(Configs.FACTORY).getAccountDAO();
        
        Account account = null;
        RequestDispatcher rd = null;
        
        if ((account = accountDAO.authenticate(agencyNumber, accountNumber, accountPassword)) != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", account);
            
            rd = request.getRequestDispatcher("homer.jsp");
        } else {
            request.setAttribute("status", "error");
            request.setAttribute("message", Locales.get("message.error.authenticate"));
            
            rd = request.getRequestDispatcher("authenticate.jsp");
        }
        
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
