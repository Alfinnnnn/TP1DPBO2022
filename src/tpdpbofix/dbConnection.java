/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpdpbofix;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author dell
 */
public class dbConnection {
    public static Connection con;
    public static Statement stm;
    
    public void connect (){
        try{
            String url = "jdbc:mysql://localhost/db_tpdpbo";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();
            System.out.println("Koneksi Berhasil!");
  
        } catch(Exception e){
            System.err.println("Koneksi Gagal" + e.getMessage());
        }
    }
    
    public DefaultTableModel readTable(){
        DefaultTableModel dataTabel = null;
        try{
            Object [] column = {"Nama", "Jumlah_Buku", "Bio_Singkat"};
            connect();
            dataTabel = new DefaultTableModel(null, column);
            String sql = "Select Nama,Jumlah_Buku,Bio_Singkat from author";
            ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                Object [] hasil = new Object[3];
                hasil[0] = res.getString("Nama");
                hasil[1] = res.getString("Jumlah_Buku");
                hasil[2] = res.getString("Bio_Singkat");
//                System.out.println(hasil[1]);
                dataTabel.addRow(hasil);
            }
        } catch(Exception e){
            System.err.println("Read Gagal21" + e.getMessage());
        }
        return dataTabel;
    }
    public DefaultTableModel readTable2(){
        DefaultTableModel dataTabel = null;
        try{
            Object [] column = {"Judul", "Author", "Penerbit", "Desc"};
            connect();
            dataTabel = new DefaultTableModel(null, column);
            String sql = "Select Judul,Author,Penerbit,Desc from buku";
            ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                Object [] hasil = new Object[4];
                hasil[0] = res.getString("Judul");
                hasil[1] = res.getString("Author");
                hasil[2] = res.getString("Penerbit");
                hasil[3] = res.getString("Desc");
//                System.out.println(hasil[1]);
                dataTabel.addRow(hasil);
            }
        } catch(Exception e){
            System.err.println("Read Gagal21" + e.getMessage());
        }
        return dataTabel;
    }
    
    public void Query(String inputan){
        try{
            connect();
            String sql = inputan;
            stm.execute(sql);
        }catch(Exception e){
            System.err.println("Read Gagal!!" + e.getMessage());
        }
    }
}