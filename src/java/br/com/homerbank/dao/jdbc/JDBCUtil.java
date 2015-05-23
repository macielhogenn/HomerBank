/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Maciel
 */
public class JDBCUtil {
    
    public Connection openConnection() {
        Connection conn = openRemoteConnection();
        
        if (conn == null) {
            conn = openLocalConnection();
        }
        
        return conn;
    }
    
    private Connection openRemoteConnection () {
        Connection conn = null;
        
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/MySqlHomerBank");
            conn = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            System.err.println(e.getMessage());
        } 
        
        return conn;
    }
    
    private Connection openLocalConnection () {
        
        Connection conn = null;
        
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "tor", "tor");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return conn;
    }
}
