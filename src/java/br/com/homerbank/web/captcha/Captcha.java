package br.com.homerbank.web.captcha;

import br.com.homerbank.locales.Locales;
import javax.servlet.http.HttpServletRequest;


public class Captcha {
    
    public static boolean check(HttpServletRequest request) {
        boolean checked = false;
        
        CaptchasDotNet captchas = new CaptchasDotNet(request.getSession(true), "demo", "secret");
        
        String captcha = request.getParameter("captcha");
        
        String body = "";
        
        switch (captchas.check(captcha)) {
            case 's':
                body = Locales.get("app.captcha.sessionIsBroken");
                break;
            case 'm':
                body = Locales.get("app.captcha.currentCaptchaHasBeenUsed");
                break;
            case 'w':
                body = Locales.get("app.captcha.wrongCaptcha");
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
