/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.servlets;

import br.com.homerbank.dao.DAOFactory;
import br.com.homerbank.dao.core.AccountDAO;
import br.com.homerbank.dao.core.SlipDAO;
import br.com.homerbank.locales.Locales;
import br.com.homerbank.model.Account;
import br.com.homerbank.model.Slip;
import br.com.homerbank.model.Transaction;
import br.com.homerbank.model.names.AccountNames;
import br.com.homerbank.model.names.SlipNames;
import br.com.homerbank.util.Configs;
import br.com.homerbank.util.MD5;
import br.com.homerbank.web.captcha.Captcha;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felipe
 */
@WebServlet("/Slip")
public class SlipServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkCaptcha(request, response);

        final String password = request.getParameter(AccountNames.PASSWORD.toString());
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDAO accountDAO = DAOFactory.getDAOFactory(Configs.FACTORY).getAccountDAO();
        SlipDAO slipDAO = DAOFactory.getDAOFactory(Configs.FACTORY).getSlipDAO();

        checkPassword(account.getPassword(), password, request, response);

        final String barCode = request.getParameter("bar-code");
        final String paramDateOfPayment = request.getParameter(SlipNames.DATE_OF_PAYMENT.toString());
        Date dateOfPayment = new Date();
        
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (paramDateOfPayment != null && !paramDateOfPayment.isEmpty()) {
            try {
                dateOfPayment = format.parse(paramDateOfPayment);
            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }
        }

        final String amount = request.getParameter(SlipNames.AMOUNT.toString());

        double convertedValue = convertValue(amount);

        Slip slip = slipDAO.read(barCode, convertedValue, dateOfPayment);
        
        if (slip != null) {
            if (account.getBalance() >= slip.getAmount()) {

                    slip.setFromAccount(account);
                    if (writeTransaction(slip)) {
                        session.setAttribute("account", accountDAO.read(slip.getFromAccount().getId()));
                        request.setAttribute("status", "success");
                        request.setAttribute("message", Locales.get("app.slip.message.success"));

                        slipDAO.update(slip);
                    }

            } else {
                request.setAttribute("status", "error");
                request.setAttribute("message", Locales.get("app.transfer.message.error.insufficientFunds"));
            }
        } else {
            request.setAttribute("status", "error");
            request.setAttribute("message", "app.slip.error.barCode");
        }

        RequestDispatcher rd = request.getRequestDispatcher("slip.jsp");
        rd.forward(request, response);
    }

    private boolean writeTransaction(Slip slip) {
        
        System.out.println(slip.getToAccount() + "\n" + slip.getFromAccount() + "\n" + slip.getAmount());
        Transaction transaction = new Transaction();
        transaction.setAcCredit(slip.getToAccount());
        transaction.setAcDebit(slip.getFromAccount());
        transaction.setAmount(slip.getAmount());
        transaction.setDate(new Date());

        return DAOFactory.getDAOFactory(Configs.FACTORY).getTransactionDAO().create(transaction);
    }

    private void checkCaptcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Captcha.check(request)) {
            RequestDispatcher rd = request.getRequestDispatcher("slip.jsp");
            rd.forward(request, response);
        }
    }

    private double convertValue(String amount) {
        double convertedValue = 0;
        System.out.println("AAAAAAAAAAAAAAA: " + amount);
        if (amount != null) {
            if (!amount.isEmpty()) {
                try {
                    convertedValue = Double.parseDouble(amount);
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return convertedValue;
    }

    private void checkPassword(String password, String password2, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!MD5.encrypt(password2).toUpperCase().equals(password.toUpperCase())) {

            request.setAttribute("status", "error");
            request.setAttribute("message", Locales.get("message.error.authenticate"));
            RequestDispatcher rd = request.getRequestDispatcher("slip.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
