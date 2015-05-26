package br.com.homerbank.web.filters;

import br.com.homerbank.model.Account;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationAccount implements Filter {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AuthenticationAccount.class.getName());
    private String AUTHENTICATION_PAGE = null;
    private String[] IGNORE_URLS = null;
    private static final String SESSION_ACCOUNT_ATRIBUTE_NAME = "account";

    public void init(FilterConfig config) throws ServletException {
        String tmp = config.getInitParameter("AUTHENTICATION_PAGE");
        if (tmp == null || tmp.equals("")) {
            throw new ServletException("Authentication default page (AUTHENTICATION_PAGE) was not defined.");
        }
        AUTHENTICATION_PAGE = tmp;

        String ignore_urls = config.getInitParameter("IGNORE_URLS");
        ignore_urls = ignore_urls != null ? ignore_urls : "";
        IGNORE_URLS = ignore_urls.split(";");
    }

    public boolean applyFiltro(HttpServletRequest request, HttpServletResponse response) throws ApplyFilterException {
        boolean authenticated = authenticateAccount(request);

        if (!authenticated && !ignoreUrl(request)) {
            RequestDispatcher disp = request.getRequestDispatcher(AUTHENTICATION_PAGE);
            try {
                disp.forward(request, response);
                return false;
            } catch (Exception e) {
                logger.severe("" + e);
                throw new ApplyFilterException(e);
            }
        }
        return true;
    }

    public String getName() {
        return this.getClass().getName();
    }

    private boolean ignoreUrl(HttpServletRequest request) {
        for (int i = 0; i < IGNORE_URLS.length; i++) {
            String comp = request.getContextPath() + IGNORE_URLS[i];
            if (request.getRequestURI().startsWith(comp)) {
                return true;
            }
        }
        return false;
    }

    public void destroy() {
        AUTHENTICATION_PAGE = null;
        IGNORE_URLS = null;
    }

    public boolean authenticateAccount(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            try {
                if (session.getAttribute(SESSION_ACCOUNT_ATRIBUTE_NAME) != null) {
                    Account account = (Account) session.getAttribute(SESSION_ACCOUNT_ATRIBUTE_NAME);
                    if (account != null) {
                        request.setAttribute(SESSION_ACCOUNT_ATRIBUTE_NAME, account);
                        return true;
                    }
                } else {
                    return false;
                }
            } catch (IllegalStateException e) {
                logger.severe("" + e);
                return false;
            }
        }
        return false;
    }
}
