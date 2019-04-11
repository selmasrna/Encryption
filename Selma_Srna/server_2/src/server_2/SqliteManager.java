package server_2;


import java.sql.*;
import java.util.HashMap;

public class SqliteManager {
    Connection c=null;
    Statement stat=null;

    public void Manager(String pathToDataBase){
        try{
            Class.forName("org.sqlite.JDBC");
            this.c= DriverManager.getConnection("jdbc:sqlite:"+pathToDataBase);
        }catch (Exception e) {
            System.out.println("Error: hjbzcxv ");
        }
    }

    public void closeConnection(){
        try{
            c.close();
        }catch(Exception e){
            System.out.println("Error za close");
        }
    }


    public void executeQuery(String query){
        try{
            this.stat=c.createStatement();
            stat.executeUpdate(query);
            stat.close();
        }catch (SQLException e){
            System.out.println("Error: stat ");
        }

    }

    public HashMap<String,String> executeQ(){
        HashMap<String,String> hmap = new HashMap<String, String>();
        try{
            this.stat = c.createStatement();
            ResultSet rs = stat.executeQuery("select * from AES_KEYS;");
            while (rs.next()){
                hmap.put(rs.getString("FILE"),rs.getString("KEY"));

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return hmap;
    }
}
