/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Maciel
 */
public class JDBCUtil {
    
    protected Connection openConnection() {
        
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
    
    protected void close(Connection conn) {
        this.close(conn, null);
    }
    
    protected void close(Connection conn, PreparedStatement pst) {
        this.close(conn, pst, null);
    }
    
    protected void close(Connection conn, PreparedStatement pst, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) { ; }
            conn = null;
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) { ; }
            pst = null;
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) { ; }
            rs = null;
        }
    }
}
