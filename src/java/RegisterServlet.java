
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name= request.getParameter("name");
        String pass = request.getParameter("pass");
        String email = request.getParameter("mail");
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/mmc");
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery(String.format("SELECT COUNT(*) FROM potrebiteli WHERE name=\"%s\"", name));
            rs1.next();
            if(rs1.getString(1).equals("1")) {
                response.getWriter().println("User exists, registration failed");
            } else {
                String q = String.format("INSERT INTO potrebiteli VALUES(\"%s\", \"%s\", \"%s\")", name, pass, email);
                ResultSet rs = stmt.executeQuery(q);
                response.getWriter().println("Registration successful");
            }
            conn.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}