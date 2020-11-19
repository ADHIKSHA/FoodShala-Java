/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yushant Tyagi
 */
@WebServlet(urlPatterns = {"/user_register"})
public class user_register extends HttpServlet {

    static int check(Connection con,String email){
        int count=0;
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) from users where email='"+email+"'");
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(user_login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(count>0){return 1;}
        return 0;
    }
    static void insert(Connection con,String a,String b,String c,String d,String e){
        try{    
            Scanner sc=new Scanner(System.in);
            String query = " insert into users (password, fullname, email, phone,location,userid)"
            + " values (?, ?, ?, ?, ?,0)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, a);
            preparedStmt.setString (2, b);
            preparedStmt.setString (3, c);
            preparedStmt.setString (4,d);
            preparedStmt.setString(5,e);
            preparedStmt.execute();
            System.out.println("\ninserted successfully\n\n");
        } catch (SQLException ex) {
            Logger.getLogger(user_register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session=request.getSession(false);
            PrintWriter out=response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            String name=request.getParameter("fullname");
            String phone=request.getParameter("phone");
            String email=request.getParameter("email");
            String location=request.getParameter("location");
            String password=request.getParameter("password");
            int id;
            session.setAttribute("email", email);
            out.println(name+phone+email+location+password);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("driver loaded");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodshala?zeroDateTimeBehavior=convertToNull&useSSL=false","root","root");
                System.out.println("connection established");
                Scanner sc=new Scanner(System.in);
                if(check(con,email)==1){
                    out.println("email already exists");
                }
                else{
                    insert(con,password,name,email,phone,location);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(user_register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(user_register.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("email", email);
            ServletContext sc = getServletContext();
        sc.getRequestDispatcher("/shop.jsp").forward(request, response);
    }


}
