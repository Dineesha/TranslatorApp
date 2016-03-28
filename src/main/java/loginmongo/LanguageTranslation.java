package loginmongo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by hsenid on 3/16/16.
 */
public class LanguageTranslation extends HttpServlet {

    DomXmlParser parser;

    public void doPost(HttpServletRequest request, HttpServletResponse response)  {
        String translated = null;
        parser = new DomXmlParser();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
            String er1=e.getMessage();
            System.out.println("Exception thrown  :\" + e");
            System.out.println("exception :\" +er1");
        }
        String fromLanguage = request.getParameter("fromLang");
        String toLanguage = request.getParameter("toLang");
        String toTranslateText = request.getParameter("txttranslatefrom");

        String fromTranslateText = request.getParameter("txttranslateto");

        String apiUrl = "https://translate.yandex.net/api/v1.5/tr/translate?key=trnsl.1.1.20160314T055210Z.609ee89149686a99.22e2a4c8f48279cba64fe814237ef790791fd2c5&text="
                + toTranslateText + "&lang=" + fromLanguage + "-" + toLanguage;


        HttpClientCall submitTxt = new HttpClientCall();
        try {
            translated = parser.readResponse(submitTxt.sendGet(apiUrl));
        } catch (Exception e) {
            System.out.print("return value no");
            try {
                throw new ServletException(e);
            } catch (ServletException e1) {
               // e1.printStackTrace();
                String er1=e1.getMessage();
                System.out.println("Exception thrown  :\" + e1");
                System.out.println("exception :\" +er1");
            }

        }

        request.setAttribute("fromLang", fromLanguage);
        request.setAttribute("toLang", toLanguage);
        request.setAttribute("txttranslatefrom", toTranslateText);
        request.setAttribute("passValue", translated);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/success.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            //e.printStackTrace();
            String er1=e.getMessage();
            System.out.println("Exception thrown  :\" + e");
            System.out.println("exception :\" +er1");
        } catch (IOException e) {
           // e.printStackTrace();
            String er1=e.getMessage();
            System.out.println("Exception thrown  :\" + e");
            System.out.println("exception :\" +er1");
        }

    }

}
