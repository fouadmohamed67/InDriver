package com.example.advSoft.Client;

import com.example.advSoft.connection.DataBaseConnect;
import com.example.advSoft.connection.ILoginDataBaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class ClientDatabaseConnect implements ILoginDataBaseConnect, DataBaseConnect {

    @Override
    public Connection establish_connection() throws SQLException, ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/sprint2";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,"root","root");
        return connection;
    }

    @Override
    public void set(JSONObject Client) throws SQLException, ClassNotFoundException {

        String query = " insert into Client (username,email,pass,mobileNumber,status,birthdate) values (?,?,?,?,?,?)";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.setString (1, (String) Client.get("username"));
        preparedStmt.setString (2, (String) Client.get("email"));
        preparedStmt.setString (3, (String) Client.get("pass"));
        preparedStmt.setString (4, (String) Client.get("mobileNumber"));
        preparedStmt.setString (5, "UnVerified");
        preparedStmt.setString (6, (String) Client.get("birthdate"));
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("one Client created");
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public JSONObject get(int id) {
        return null;
    }

    @Override
    public JSONArray listAll() throws SQLException, ClassNotFoundException {
        JSONArray allClients = new JSONArray();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from client");
        while(rs.next())
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",rs.getInt("id"));
            jsonObject.put("username",rs.getString("username"));
            jsonObject.put("pass",rs.getString("pass"));
            jsonObject.put("email",rs.getString("email"));
            jsonObject.put("mobileNumber",rs.getString("mobileNumber"));
            jsonObject.put("birthdate",rs.getString("birthdate"));
            jsonObject.put("status",rs.getString("status"));
            allClients.put(jsonObject);
        }
        return allClients;
    }


    @Override
    public void update(JSONObject Client, int id) throws SQLException, ClassNotFoundException {

        String query = "UPDATE client SET username =? , email =? , pass = ? , mobileNumber = ? , status = ? , birthdate = ? where id="+id+" ";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.setString (1, (String) Client.get("username"));
        preparedStmt.setString (2, (String) Client.get("email"));
        preparedStmt.setString (3, (String) Client.get("pass"));
        preparedStmt.setString (4, (String) Client.get("mobileNumber"));
        preparedStmt.setString (5, (String) Client.get("status"));
        preparedStmt.setString (6, (String) Client.get("birthdate"));
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("one client updated");
    }

    @Override
    public JSONObject login(String client) throws SQLException, ClassNotFoundException {

         JSONObject jsonObject = new JSONObject(client);
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from client where username='"+ (String) jsonObject.get("username") +"' and pass='"+ (String) jsonObject.get("pass") + "'" );
        JSONObject newjsonObject = new JSONObject();
        if(rs.next())
        {
            newjsonObject.put("username",rs.getString("username"));
            newjsonObject.put("email",rs.getString("email"));
            newjsonObject.put("mobileNumber",rs.getString("mobileNumber"));
            newjsonObject.put("status",rs.getString("status"));
            return  newjsonObject;
        }
        else
        {
            newjsonObject.put("message","notfound");
            return  newjsonObject;
        }
    }


}
