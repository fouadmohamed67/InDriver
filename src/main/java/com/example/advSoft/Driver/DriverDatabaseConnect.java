package com.example.advSoft.Driver;

import com.example.advSoft.connection.DataBaseConnect;
import com.example.advSoft.connection.ILoginDataBaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class DriverDatabaseConnect implements DataBaseConnect, ILoginDataBaseConnect {
    @Override
    public Connection establish_connection() throws SQLException, ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/sprint2";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,"root","root");
        return connection;
    }

    @Override
    public void set(JSONObject Driver) throws SQLException, ClassNotFoundException {
        String query = " insert into driver (username,email,pass,mobileNumber,nationalID,drive_license,status) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.setString (1, (String) Driver.get("username"));
        preparedStmt.setString (2, (String) Driver.get("email"));
        preparedStmt.setString (3, (String) Driver.get("pass"));
        preparedStmt.setString (4, (String) Driver.get("mobileNumber"));
        preparedStmt.setString (5, (String) Driver.get("nationalID"));
        preparedStmt.setString (6, (String) Driver.get("drive_license"));
        preparedStmt.setString (7, "UnVerified");
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("one driver created");
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public JSONObject get(int id) {
return  null;
    }


    @Override
    public JSONArray listAll() throws SQLException, ClassNotFoundException {
        JSONArray allDrivers = new JSONArray();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from driver");
        while(rs.next())
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",rs.getInt("id"));
            jsonObject.put("username",rs.getString("username"));
            jsonObject.put("pass",rs.getString("pass"));
            jsonObject.put("email",rs.getString("email"));
            jsonObject.put("mobileNumber",rs.getString("mobileNumber"));
            jsonObject.put("nationalID",rs.getString("nationalID"));
            jsonObject.put("drive_license",rs.getString("drive_license"));
            jsonObject.put("status",rs.getString("status"));
            allDrivers.put(jsonObject);
        }
        return allDrivers;
    }

    @Override
    public void update(JSONObject Driver,int id) throws SQLException, ClassNotFoundException {

        String query = "UPDATE driver SET username =? , email =? , pass = ? , mobileNumber = ? , nationalID = ? , drive_license = ? , status= ?  where id="+id+" ";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.setString (1, (String) Driver.get("username"));
        preparedStmt.setString (2, (String) Driver.get("email"));
        preparedStmt.setString (3, (String) Driver.get("pass"));
        preparedStmt.setString (4, (String) Driver.get("mobileNumber"));
        preparedStmt.setString (5, (String) Driver.get("nationalID"));
        preparedStmt.setString (6, (String) Driver.get("drive_license"));
        preparedStmt.setString (7, (String) Driver.get("status"));
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("one Driver updated");

    }

    public JSONObject login(String person) throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject(person);
        JSONObject newjsonObject = new JSONObject();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from driver where username ='" + jsonObject.get("username") +"' and pass = '" +  jsonObject.get("pass") + "'");
        if(rs.next())
        {
            newjsonObject.put("username",rs.getString("username"));
            newjsonObject.put("pass",rs.getString("pass"));
            newjsonObject.put("email",rs.getString("email"));
            newjsonObject.put("mobileNumber",rs.getString("mobileNumber"));
            newjsonObject.put("nationalID",rs.getString("nationalID"));
            newjsonObject.put("drive_license",rs.getString("drive_license"));
            newjsonObject.put("status",rs.getString("status"));
        }
        else
            newjsonObject.put("message","notfound");
        return newjsonObject;
    }
}
