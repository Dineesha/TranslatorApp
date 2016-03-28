package loginmongo;

/**
 * Created by hsenid on 3/14/16.
 */

import com.mongodb.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;

public class Login extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)  {

        response.setContentType("text/html");

        String username = request.getParameter("form-username"); // get the name entered by user's input
        String password = request.getParameter("form-password"); //get the password entered by user's input


        try {

            /**** Connect to MongoDB ****/

            MongoClient mongo = new MongoClient("localhost", 27017);

            /**** Get database ****/

            DB db = mongo.getDB("logindata");

            /**** Get collection / table from 'testdb' ****/

            DBCollection table = db.getCollection("userinfo");

            /**** Insert ****/

           /* BasicDBObject document = new BasicDBObject();
            document.put("username", "dineesha");
            document.put("password", "1234");

            table.insert(document);*/

            /**** Find and display ****/
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("username", username);
            searchQuery.put("password", password);

            DBCursor cursor = table.find(searchQuery);

            if (cursor.hasNext()) {


                response.sendRedirect("success.jsp?name="+username);
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
            }
        } catch (UnknownHostException ex) {
            String er1=ex.getMessage();
            System.out.println("Exception thrown  :\" + ex");
            System.out.println("exception :\" +er1");
            //ex.printStackTrace();

        } catch (Exception e) {
            //e.printStackTrace();
            String er1=e.getMessage();
            System.out.println("Exception thrown  :\" + e");
            System.out.println("exception :\" +er1");

        }
    }
}