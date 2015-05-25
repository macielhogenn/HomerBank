/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Maciel
 */
public abstract class JDBCGenericDAO {
    
    protected Connection conn;
    protected PreparedStatement pst;
    protected ResultSet rs;
    
    private JDBCUtil getJDBCUtil() {
        return new JDBCUtil();
    }
    
    protected void openConnection() {
        conn = getJDBCUtil().openConnection();
    }
    
    private void prepareStatement(String sql) throws SQLException {
        
        if (conn == null) {
            openConnection();
        }
        
        if (conn != null) {
            pst = conn.prepareStatement(sql);
        }
    }
    
    protected boolean execute() throws SQLException {
        if (pst != null) {
            return pst.executeUpdate() > 0;
        } else {
            return false;
        }
    }
    
    protected ResultSet results() throws SQLException {
        if (rs == null) { 
            if (pst != null) {
                rs = pst.executeQuery();
            }
        }
        return rs;
    }
    
    protected void closeConnection() {
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
    
    protected void set(String sql) throws SQLException {
        if (pst == null) {
            prepareStatement(sql);
        }
    }
    
    protected void set(int index, Object value) throws SQLException {
        
        switch (value.getClass().getTypeName()) {
            case "java.lang.Byte" :
                setByte(index, (byte)value);
                break;
            case "java.lang.Short" :
                setShort(index, (short)value);
                break;
            case "java.lang.Integer" :
                setInt(index, (int)value);
                break;
            case "java.lang.Long" :
                setLong(index, (long)value);
                break;
            case "java.lang.Float" :
                setFloat(index, (float)value);
                break;
            case "java.lang.Double" :
                setDouble(index, (double)value);
                break;
            case "java.lang.Boolean" :
                setBoolean(index, (boolean)value);
                break;
            case "java.sql.Date" :
                setDate(index, (Date)value);
                break;
            case "java.util.Date" :
                setDate(index, (java.util.Date)value);
                break;
            case "java.lang.String" :
                setString(index, (String)value);
                break;
            default :
        }
    }
    
    
    protected void setByte(int index, byte value) throws SQLException {
        pst.setByte(index, value);
    }
    
    protected void setShort(int index, short value) throws SQLException {
        pst.setShort(index, value);
    }
    
    protected void setInt(int index, int value) throws SQLException {
        pst.setInt(index, value);
    }
    
    protected void setLong(int index, long value) throws SQLException {
        pst.setLong(index, value);
    }
    
    protected void setFloat(int index, float value) throws SQLException {
        pst.setFloat(index, value);
    }
    
    protected void setDouble(int index, double value) throws SQLException {
        pst.setDouble(index, value);
    }
    
    protected void setBoolean(int index, boolean value) throws SQLException {
        pst.setBoolean(index, value);
    }
    
    protected void setDate(int index, Date value) throws SQLException {
        pst.setDate(index, value);
    }
    
    protected void setDate(int index, java.util.Date value) throws SQLException {
        pst.setDate(index, new Date(((java.util.Date)value).getTime()));
    }
    
    protected void setString(int index, String value) throws SQLException {
        pst.setString(index, value);
    }   
    
    protected byte getByte(String column) throws SQLException {
        return rs.getByte(column);
    }
    
    protected short getShort(String column) throws SQLException {
        return rs.getShort(column);
    }
    
    protected int getInt(String column) throws SQLException {
        return rs.getInt(column);
    }
    
    protected long getLong(String column) throws SQLException {
        return rs.getLong(column);
    }
    
    protected float getFloat(String column) throws SQLException {
        return rs.getFloat(column);
    }
        
    protected double getDouble(String column) throws SQLException {
        return rs.getDouble(column);
    }
    
    protected boolean getBoolean(String column) throws SQLException {
        return rs.getBoolean(column);
    }
    
    protected java.util.Date getDate(String column) throws SQLException {
        return rs.getDate(column);
    }
    
    protected String getString(String column) throws SQLException {
        return rs.getString(column);
    }
    
    protected boolean verify(Object value) {
        boolean ok = false;
        
        if (value != null) {
            if (value instanceof String) {
                ok = !((String)value).isEmpty();
            } else if (value instanceof Long) {
                ok = ((long)value) > 0;
            } else if (value instanceof java.util.Date) {
                ok = true;
            }
        }
        
        return ok;
    }
}
