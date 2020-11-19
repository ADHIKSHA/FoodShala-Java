
import static com.mysql.cj.MysqlType.BLOB;
import static java.sql.JDBCType.BLOB;
import static java.sql.Types.BLOB;
import java.sql.Blob;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adhiksha
 */
public class menu {
    private String category;
    private String dish_name;
    private String res_name;
    private int price;
    private Blob image;
    private int dishid;
    private int resid;
    
    public menu(int dishid, String category, String dish_name, String res_name,int price, Blob image , int resid) 
    { 
        this.dishid=dishid;
        this.category=category;
        this.dish_name=dish_name;
        this.res_name=res_name;
        this.price=price;
        this.image=image;
        this.resid=resid;
        
    } 
    public int getdishid(){ return dishid;}
    public String getcategory(){ return category;}
    public String getdishname(){ return dish_name;}
    public String getresname(){ return res_name;}
    public int getprice(){ return price;}
    public Blob getimage(){ return image;}
    public int resid(){ return resid;}
    
}
