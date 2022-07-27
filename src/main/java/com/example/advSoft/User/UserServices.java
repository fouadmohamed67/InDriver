package com.example.advSoft.User;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;

public interface UserServices {
    User user = new User();
    public String getAll() throws ClassNotFoundException, SQLException;
    public void register( @RequestBody String request) throws SQLException, ClassNotFoundException, SQLException;
    public String  login(@RequestBody String request)throws SQLException, ClassNotFoundException, SQLException;
 }
