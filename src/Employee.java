import com.sun.istack.internal.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Employee {
    private Connection conn;
    private String tableName = "employee";

    public Employee(Connection connection){
        this.conn = connection;
    }

    @Nullable
    public ResultSet getAll(Integer limit, Integer offset){
        if(limit == null){
            limit = 10;
        }
        if(offset == null){
            offset = 0;
        }
        try{
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM " + tableName + " LIMIT " + limit.toString() + " OFFSET " + offset.toString() + ";");
            return result;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int add(String name, Integer phone){
        String insertStmt = "INSERT INTO " + tableName + " (name,phone) VALUES (?,?)";
        int rowsAffected = 0;
        try{
            PreparedStatement preStm = conn.prepareStatement(insertStmt);
            preStm.setString(1,name);
            preStm.setInt(2,phone);
            rowsAffected = preStm.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  rowsAffected;
    }

    public int update(Integer id,String name, Integer phone){
        String insertStmt = "UPDATE " + tableName + " SET name=?, phone=? WHERE id=?";
        int rowsAffected = 0;
        try{
            PreparedStatement preStm = conn.prepareStatement(insertStmt);
            preStm.setInt(1,id);
            preStm.setString(2,name);
            preStm.setInt(3,phone);
            rowsAffected = preStm.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  rowsAffected;
    }


    public int delete(Integer id){
        String insertStmt = "DELETE FROM " + tableName + " WHERE id=?";
        int rowsAffected = 0;
        try{
            PreparedStatement preStm = conn.prepareStatement(insertStmt);
            preStm.setInt(1,id);
            rowsAffected = preStm.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  rowsAffected;
    }

    @Nullable
    public ResultSet getByName(String name){
        try{
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM " + tableName + " WHERE name='"+name+"'");
            return result;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
