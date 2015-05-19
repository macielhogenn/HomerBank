/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao.jdbc;

import br.com.homerbank.dao.core.AccountDAO;
import br.com.homerbank.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Maciel
 */
public class JDBCAccountDAO extends JDBCGenericDAO implements AccountDAO {

    @Override
    public boolean create(Account t) {
        Connection conn = getJDBCUtil().openConnection();
        PreparedStatement pst = null;
        
        boolean success = false;
        
        try {
            String sql = "insert into account (number_agency, "
                    + "number_account, "
                    + "password_account, "
                    + "type_account, "
                    + "date_of_creation, "
                    + "balance, "
                    + "client_account_id) "
                    + " VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?)";
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, t.getAgency().getNumber());
            pst.setString(2, t.getNumber());
            pst.setString(3, t.getPassword());
            pst.setInt(4, t.getType());
            pst.setDate(5, t.getDateOfCreation());
            pst.setDouble(6, t.getBalance());
            pst.setLong(7, t.getOwner().getId());
            
            success = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            getJDBCUtil().close(conn, pst);
        }
        
        return success;
    }

    @Override
    public boolean update(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean read(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
