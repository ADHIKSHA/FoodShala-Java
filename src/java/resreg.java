/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
import java.sql.Blob;
/**
 *
 * @author Adhiksha
 */
@WebServlet(urlPatterns = {"/resreg"})
public class resreg extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String resname=request.getParameter("resname");
        String ownname=request.getParameter("ownname");
        String country=request.getParameter("country");
        String add1=request.getParameter("add1");
        String add2=request.getParameter("add2");
        String city=request.getParameter("city");
        String postcode=request.getParameter("postcode");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        String location=request.getParameter("location");
        String read=request.getParameter("read");
        String password=request.getParameter("password");
        long phonenum= Long.parseLong(phone);
        int postcodenum= Integer.parseInt(postcode);
        int id=0;
            
        List dataList = new ArrayList();
           
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("class laoded");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodshala?zeroDateTimeBehavior=convertToNull&useSSL=false","root","root");
            Statement stmt = con.createStatement();
            String query="INSERT INTO restaurants (res_name, owner_name, country,streetadd1,streetadd2,city,postcode,phone,email,location,resid,password) \n" +
"              VALUES(?, ?, ?,?, ?,?,?,?,?,?, 0,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, resname);
            preparedStmt.setString(2, ownname);
            preparedStmt.setString(3, country);
            preparedStmt.setString(4, add1);
            preparedStmt.setString(5, add2);
            preparedStmt.setString(6, city);
            preparedStmt.setInt(7, postcodenum);
            preparedStmt.setLong(8, phonenum);
            preparedStmt.setString(9, email);
            preparedStmt.setString(10, location);
            preparedStmt.setString(11, password);
            preparedStmt.execute();
            
            String s = "select * from restaurants where email=? and res_name=? and owner_name=? and phonenum=? and country=?";
            PreparedStatement prep = con.prepareStatement(s);
            prep.setString(1,email);
            prep.setString(2,resname);
            prep.setString(3,ownname);
            prep.setLong(4,phonenum);
            prep.setString(5,country);
            ResultSet r =prep.executeQuery();
            while(r.next()){
                id = r.getInt("resid");
                System.out.println(id);
            }
            
            String sql = "select * from menu where resid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt (1, id);
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
           rs.close();
           con.close();
           }catch (Exception e) {
               System.err.println("Got an exception!");
               System.err.println(e.getMessage());
           // Logger.getLogger(AddData.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("data",dataList);
        request.setAttribute("resid",id);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
           ServletContext sc = getServletContext();
            sc.getRequestDispatcher("/addmenu.jsp").forward(request, response);
        }
    }

}