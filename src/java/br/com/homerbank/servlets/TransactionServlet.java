package br.com.homerbank.servlets;

import br.com.homerbank.dao.DAOFactory;
import br.com.homerbank.dao.core.AccountDAO;
import br.com.homerbank.dao.core.TransactionDAO;
import br.com.homerbank.locales.Locales;
import br.com.homerbank.model.Account;
import br.com.homerbank.model.Transaction;
import br.com.homerbank.util.Configs;
import br.com.homerbank.web.captcha.Captcha;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Transfer")
public class TransactionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (!Captcha.check(request)) {
            RequestDispatcher rd = request.getRequestDispatcher("transfer.jsp");
            rd.forward(request, response);
        }
        
        final String toAgencyNumber = request.getParameter("agency");
        final String toAccountNumber = request.getParameter("account");
        final String amount = request.getParameter("amount");
        
        AccountDAO accountDAO = DAOFactory.getDAOFactory(Configs.FACTORY).getAccountDAO();
        
        Account toAccount = null;
        RequestDispatcher rd = null;
        boolean validAmount = false;
        double convertedAmount = 0;
        if (amount != null && !amount.isEmpty()) {
            try {
                convertedAmount = Double.parseDouble(amount);
                if (convertedAmount > 0) {
                    validAmount = true;
                }
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(toAgencyNumber + "\n" + toAccountNumber);
        if ((toAccount = accountDAO.read(toAgencyNumber, toAccountNumber)) != null && validAmount) {
            HttpSession session = request.getSession();
            Account fromAccount = (Account) session.getAttribute("account");
            fromAccount = accountDAO.read(fromAccount.getId());
            
            if (fromAccount.getBalance() >= convertedAmount) {
                Transaction transaction = new Transaction();
                transaction.setAcCredit(toAccount);
                transaction.setAcDebit(fromAccount);
                transaction.setAmount(convertedAmount);
                transaction.setDate(new Date());

                TransactionDAO transactionDAO = DAOFactory.getDAOFactory(Configs.FACTORY).getTransactionDAO();

                if (transactionDAO.create(transaction)) {
                    session.setAttribute("account", accountDAO.read(fromAccount.getId()));

                    request.setAttribute("status", "success");
                    request.setAttribute("message", Locales.get("app.transfer.message.success"));
                }
            } else {
                request.setAttribute("status", "error");
                request.setAttribute("message", Locales.get("app.transfer.message.error.insufficientFunds"));
            }
        } else {
            request.setAttribute("status", "error");
            request.setAttribute("message", Locales.get("app.transfer.message.error.invalidData"));
        }
        
        rd = request.getRequestDispatcher("transfer.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    
}
