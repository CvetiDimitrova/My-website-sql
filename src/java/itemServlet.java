import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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

@WebServlet(urlPatterns = {"/itemServlet"})
public class itemServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = null ;
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies) {
            if (c.getName().equals("name")) {
                name = c.getValue();
            }
        }
       
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/mmc");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM artikuli"));
            conn.close();
            Object items = null;
            
            while(rs.next()) {
                int id = rs.getInt(1);
                String vid = rs.getString(2);
                String marka = rs.getString(3);
                String snimka = rs.getString(4);
                String opisanie = rs.getString(5);
                int cena = rs.getInt(6);
                String garancia = rs.getString(7);
                items += """
                       
                               <div>
                                 <article class="card">
                                   <img src="%s">
                                   <footer>
                                     <h3>%s</h3>
                                         <p>%s</p>
                                         <p>%s</p>
                                     <button>Like</button>
                                   </footer>
                                 </article>
                               </div>
                         """.formatted(snimka,marka,opisanie,cena,garancia);
            }
            
            String webpage = """
                             <!DOCTYPE html>
                             <html>
                             	<head>
                                           <meta charset="utf8">
                             	<title>Shtori i dograma</title>
                             	<link rel="icon" type="image/x-icon" href="photos/1.jpg">
                             	<meta name="viewport" content="width=device-width" initial-scale="1">
                             	<link rel="stylesheet" href="picnic.css">
                             	<link rel="stylesheet" href="style.css">
                             </head>
                             	<body>
                             		<nav>
                             	<a href="index.html" class="brand">
                             		<img class="logo" src="photos/1.jpg" />
                             		<span>ММC klima</span>
                             	</a>
                             
                             	<!-- responsive-->
                             	<input id="bmenub" type="checkbox" class="show">
                             	<label for="bmenub" class="burger pseudo button">menu</label>
                             
                             	<div class="menu">
                             		
                             		  <a href="index.html" class="pseudo button icon-picture">Начало</a>
                             		
                             		
                             		<a href="klimatici.html" class="pseudo button icon-picture">Климатици</a>
                             		<a href="vrati.html" class="button icon-puzzle">Врати</a>
                             		<a href="shtoriidograma.html" class="pseudo button icon-picture">Щори и Дограма</a>
                             	</div>
                                    </nav>
                                        <div class="flex two">
                                                %s
                                         </div>
                                    </body>
                            </html>                         
                             """.formatted(items);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(itemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(itemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                         
        response.getWriter().println("");
        
    }
}