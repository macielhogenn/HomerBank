/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao.jdbc;

import br.com.homerbank.dao.core.ClientDAO;
import br.com.homerbank.model.Client;
import br.com.homerbank.model.names.ClientNames;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maciel
 */
public class JDBCClientDAO extends JDBCGenericDAO implements ClientDAO {

    @Override
    public boolean create(Client t) {
        
        String sql = "insert into " + ClientNames.TABLE + " (" + ClientNames.NAME + ") values (?)";
        boolean created = false;
        
        try {
            set(sql);
            set(1, t.getName());
            created = execute();
        } catch (SQLException e) { ; } finally {
            closeConnection();
        }
        
        return created;
    }

    @Override
    public boolean update(Client t) {
        String sql = "update " + ClientNames.TABLE + " SET " + ClientNames.NAME + " = ? WHERE " + ClientNames.ID + " = ?";
        
        boolean updated = false;
        
        try {
            set(sql);
            set(1, t.getName());
            set(2, t.getId());
            updated = execute();
        } catch (SQLException e) { ; } finally {
            closeConnection();
        }
        
        return updated;
    }

    @Override
    public boolean delete(long id) {
         String sql = "delete from " + ClientNames.TABLE + " WHERE " + ClientNames.ID + " = ?";
        
        boolean updated = false;
        
        try {
            set(sql);
            set(1, id);
            updated = execute();
        } catch (SQLException e) { ; } finally {
            closeConnection();
        }
        
        return updated;
    }

    @Override
    public Client read(long id) {
        String sql = "select * from " + ClientNames.TABLE + " WHERE " + ClientNames.ID + " = ?";
        
        Client client = null;
        
         try {
            set(sql);
            set(1, id);
            
            if (results().next()) {
                client = new Client();
                client.setId(getInt(ClientNames.ID.toString()));
                client.setName(getString(ClientNames.NAME.toString()));
            }
            
        } catch (SQLException e) { ; } finally {
            closeConnection();
        }
         
         return client;
    }

    @Override
    public List<Client> readAll() {
        String sql = "select * from " + ClientNames.TABLE;
        
        List<Client> clients = new ArrayList<>();
        
         try {
            set(sql);
            
            while (results().next()) {
                Client client = new Client();
                
                client.setId(getInt(ClientNames.ID.toString()));
                client.setName(getString(ClientNames.NAME.toString()));
                
                clients.add(client);
            }
            
        } catch (SQLException e) { ; } finally {
            closeConnection();
        }
         
        return clients;
    }
    
    
}
