package br.com.homerbank.servlets;

import br.com.homerbank.dao.DAOFactory;
import br.com.homerbank.dao.core.TransactionDAO;
import br.com.homerbank.locales.Locales;
import br.com.homerbank.model.Account;
import br.com.homerbank.model.Transaction;
import br.com.homerbank.util.Configs;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Statement")
public class StatementServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String paramDateStart = request.getParameter("date-start");
        final String paramDateEnd = request.getParameter("date-end");
        
        DateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
        
        Date dateStart = null;
        Date dateEnd = null;
        
        if ((paramDateStart == null || paramDateStart.isEmpty()) || (paramDateEnd == null || paramDateEnd.isEmpty())) {
            Calendar calendarStart = Calendar.getInstance(Locale.getDefault());
            Calendar calendarEnd = Calendar.getInstance(Locale.getDefault());
            
            calendarStart.add(Calendar.DATE, -30);
            
            dateStart = calendarStart.getTime();
            dateEnd = calendarEnd.getTime();
        } else {
            try {
                dateStart = dateParser.parse(paramDateStart);
                dateEnd = dateParser.parse(paramDateEnd);
            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }
        }
        
        TransactionDAO transactionDAO = DAOFactory.getDAOFactory(Configs.FACTORY).getTransactionDAO();
        
        if (dateStart != null && dateEnd != null) {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            List<Transaction> transactions = transactionDAO.readByPeriod(account, dateStart, dateEnd);
            
            if(transactions != null && !transactions.isEmpty()) {
                request.setAttribute("transactions", transactions);
            } else {
                request.setAttribute("status", "error");
                request.setAttribute("message", Locales.get("app.statement.message.error.transactionsNotFound"));
            }
            
        } else {
            request.setAttribute("status", "error");
            request.setAttribute("message", Locales.get("app.statement.message.error.invalidDates"));
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("statement.jsp");
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
