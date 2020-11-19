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
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.servlet.http.Part;
/**
 *
 * @author Adhiksha
 */
@WebServlet(urlPatterns = {"/save"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class save extends HttpServlet {
    
    public static final String UPLOAD_DIR = "images";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String dishname=request.getParameter("ndish");
        String price=request.getParameter("dprice");
        String category=request.getParameter("category");
        Part filePart = request.getPart("img");
        File fileSaveDir1 = null;
        HttpSession session=request.getSession(false);
        Integer resid=(Integer)session.getAttribute("resid");
        System.out.println("ses id-");
        System.out.println(Integer.toString(resid));
        int pricenum= Integer.parseInt(price);
        List dataList = new ArrayList();
        
        InputStream inputStream = null; // input stream of the upload file
        if (filePart != null) {
            // prints out some information for debugging
            String fileName = extractFileName(filePart);//file name
             String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        System.out.println("applicationPath:" + applicationPath);
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);
        String sRootPath = new File(savePath).getAbsolutePath();
        sRootPath = sRootPath.replace("\\", "\\\\");
       
        System.out.println("sRootPath: " + sRootPath);
        //filePart.write(savePath + File.separator);
          fileSaveDir1 = new File(sRootPath);
            // obtains input stream of the upload file
            inputStream = new FileInputStream(fileSaveDir1);
        }
           
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("class laoded");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodshala?zeroDateTimeBehavior=convertToNull&useSSL=false","root","root");
            Statement stmt = con.createStatement();
            String s="select * from restaurants where resid=?";
            PreparedStatement prep= con.prepareStatement(s);
            prep.setInt(1, resid);
            ResultSet rt = prep.executeQuery();
            String resname="";
            while(rt.next()){
                resname = rt.getString("res_name");
            }
            String query="INSERT INTO menu (category,dish_name,res_name,price,image,resid) \n" +
"              VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, category);
            preparedStmt.setString(2, dishname);
            preparedStmt.setString(3, resname);
            preparedStmt.setInt(4, pricenum);
            preparedStmt.setBinaryStream(5, (InputStream) inputStream, (int)(fileSaveDir1.length()));
            preparedStmt.setInt(6, resid);
            preparedStmt.execute();
            
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
            
            
           rt.close();
           con.close();
           }catch (Exception e) {
               System.err.println("Got an exception!");
               System.err.println(e.getMessage());
           // Logger.getLogger(AddData.class.getName()).log(Level.SEVERE, null, ex);
        }
         try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
           ServletContext sc = getServletContext();
            sc.getRequestDispatcher("/addmenu.jsp").forward(request, response);
        }
    }
    private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}