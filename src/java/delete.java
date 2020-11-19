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
@WebServlet(urlPatterns = {"/delete"})
public class delete extends HttpServlet {
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] dishes=request.getParameterValues("dishid");
        HttpSession session=request.getSession(false);
        Integer resid=(Integer)session.getAttribute("resid");
         List dataList = new ArrayList();
       
         
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("class laoded");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodshala?zeroDateTimeBehavior=convertToNull&useSSL=false","root","root");
            Statement stmt = con.createStatement();
            
            for (String s : dishes){
            String query="DELETE FROM menu where dishid=? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, Integer.parseInt(s));
            preparedStmt.executeUpdate();
            }
            String sql = "select * from menu where resid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt (1, resid);
            ResultSet rs = ps.executeQuery();
            while (rs.next ()){
            dataList.add(rs.getString("category"));
            dataList.add(rs.getString("dish_name"));
            dataList.add(rs.getInt("price"));
            byte b[];
            Blob blob;
            blob=rs.getBlob("image");
            b=blob.getBytes(1,(int)blob.length());
            String encode = Base64.getEncoder().encodeToString(b);
            dataList.add(encode);
        }
            request.setAttribute("data",dataList);
            request.setAttribute("resid",resid);
            con.close();
           }catch (Exception e) {
               System.err.println("Got an exception!");
               System.err.println(e.getMessage());
           // Logger.getLogger(AddData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ServletContext sc = getServletContext();
            sc.getRequestDispatcher("/addmenu.jsp").forward(request, response);
        
    }

}