package br.com.homerbank.web.captcha;

import javax.servlet.http.HttpServletRequest;


public class Captcha {
    
    public static boolean check(HttpServletRequest request) {
        boolean checked = false;
        
        CaptchasDotNet captchas = new CaptchasDotNet(request.getSession(true), "demo", "secret");
        
        String captcha = request.getParameter("captcha");
        
        String body = "";
        
        switch (captchas.check(captcha)) {
            case 's':
                body = "Session seems to be timed out or broken. ";
                body += "Please try again or report error to administrator.";
                break;
            case 'm':
                body = "Every CAPTCHA can only be used once. ";
                body += "The current CAPTCHA has already been used. ";
                body += "Please use back button and reload";
                break;
            case 'w':
                body = "You entered the wrong password. ";
                body += "Please use back button and try again. ";
                break;
            default:
                checked = true;
                break;
        }
        
        if (!checked) {
            request.setAttribute("status", "error");
            request.setAttribute("message", body);
        }
        
        return checked;
    }
}
