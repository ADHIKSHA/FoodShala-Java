/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 *
 * @author Adhiksha
 */
@WebServlet(urlPatterns = {"/reslogin"})
public class reslogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email=request.getParameter("email");
        String pass=request.getParameter("password");
        int id=0;
            
        boolean st = false;
         List dataList = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("class laoded");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodshala?zeroDateTimeBehavior=convertToNull&useSSL=false","root","root");
            Statement stmt = con.createStatement();
            String query="SELECT * from restaurants where email=? and password=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.setString(2, pass);
            ResultSet rs= preparedStmt.executeQuery();
            st=rs.next();
            if(st==true){
            String s = "select * from restaurants where email=?";
            PreparedStatement prep = con.prepareStatement(s);
            prep.setString(1,email);
            ResultSet r =prep.executeQuery();
            while(r.next()){
                id = r.getInt("resid");
                System.out.println(id);
            }
            
            String sql = "select * from menu where resid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt (1, id);
            ResultSet rst = ps.executeQuery();
            while (rst.next ()){
            dataList.add(rst.getString("category"));
            dataList.add(rst.getString("dish_name"));
            dataList.add(rst.getInt("price"));
            byte b[];
            Blob blob;
            blob=rst.getBlob("image");
            b=blob.getBytes(1,(int)blob.length());
            String encode = Base64.getEncoder().encodeToString(b);
            dataList.add(encode);
            
        }
           rs.close();
           con.close();
           request.setAttribute("data",dataList);
           request.setAttribute("resid",id);
             ServletContext sc = getServletContext();
            sc.getRequestDispatcher("/addmenu.jsp").forward(request, response);
        }
        else{
              
             PrintWriter out = response.getWriter();  
             out.println("<script type=\"text/javascript\">");
             out.println("alert('User or password incorrect');");
             out.println("location='reslogin.html';");
             out.println("</script>");
            
        }
            con.close();
           }catch (Exception e) {
               System.err.println("Got an exception!");
               System.err.println(e.getMessage());
           // Logger.getLogger(AddData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}