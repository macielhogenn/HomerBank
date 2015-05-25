package br.com.homerbank.dao.listeners;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        System.out.println("ENCERROU");
        RequestDispatcher rd = hse.getSession().getServletContext().getNamedDispatcher("index.jsp");
        try {
            rd.forward(null, null);
        } catch (ServletException | IOException e) { ; }
    }
    
    
}
