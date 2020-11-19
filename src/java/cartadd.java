/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adhiksha
 */
@WebServlet(urlPatterns = {"/cartadd"})
public class cartadd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String click = request.getParameter("id");
      // System.out.println("clicked"+click);
         HttpSession session=request.getSession(false);
        String email=null;
               email= (String)session.getAttribute("email");
        if(email==null)
        {
            ServletContext sc = getServletContext();
            request.setAttribute("email",email);
            sc.getRequestDispatcher("/userlogin.jsp").forward(request, response);
        }
        else{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("class laoded");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodshala?zeroDateTimeBehavior=convertToNull&useSSL=false","root","root");
            Statement stmt = con.createStatement();
            String query="INSERT INTO cart (email, dishid) \n" +
"              VALUES(?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.setInt(2, Integer.parseInt(click));
            preparedStmt.execute();
            
            con.close();
           }catch (Exception e) {
               System.err.println("Got an exception!");
               System.err.println(e.getMessage());
           // Logger.getLogger(AddData.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("email",email);
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher("/cart.jsp").forward(request, response);
        }
    }

}