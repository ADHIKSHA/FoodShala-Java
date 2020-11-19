/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yushant Tyagi
 */
@WebServlet(urlPatterns = {"/user_login"})
public class user_login extends HttpServlet {


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
        return count;
    }
    static int check2(Connection con,String email,String pass){
        int f=0;
        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select password from users where email='"+email+"'");
            rs.next();
            String d=rs.getString(1);
            if(d.equals(pass)){
                f=1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out=response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            try{
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("driver loaded");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodshala?zeroDateTimeBehavior=convertToNull&useSSL=false","root","root");
                System.out.println("connection established");
                Scanner sc=new Scanner(System.in);
                int a=check(con,email);
                int b=check2(con,email,password);
                if(a==1&&b==1){
                    //landing page
                    out.println("correct credentials");
                }else{
                    out.println("wrong email or password");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(user_login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(user_login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


}
