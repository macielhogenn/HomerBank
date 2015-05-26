/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao.jdbc;

import br.com.homerbank.dao.core.AccountDAO;
import br.com.homerbank.model.Account;
import br.com.homerbank.model.Agency;
import br.com.homerbank.model.Client;
import br.com.homerbank.model.names.AccountNames;
import br.com.homerbank.model.names.AgencyNames;
import br.com.homerbank.model.names.ClientNames;
import br.com.homerbank.model.names.SlipNames;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Maciel
 */
public class JDBCAccountDAO extends JDBCGenericDAO implements AccountDAO {

    @Override
    public boolean create(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Account read(long id) {
        Account account = null;
        if (verify(id)) {
            String sql = "select * from accounts "
                    + "join clients on (accounts.client_id = clients.client_id) "
                    + "join agencies on (agencies.agency_id = accounts.agency_id) "
                    + "where accounts.account_id = ?";

            try {
                set(sql);

                set(1, id);

                if (results().next()) {

                    account = new Account();
                    Client client = new Client();
                    client.setId(getLong("client_id"));
                    client.setName(getString("name"));
                    client.setAccount(account);

                    Agency agency = new Agency();
                    agency.setId(getLong("agency_id"));
                    agency.setNumber(getString("number_agency"));

                    account.setId(getLong("account_id"));
                    account.setAgency(agency);
                    account.setOwner(client);
                    account.setPassword(null);
                    account.setNumber(getString("number_account"));
                    account.setType(getShort("type_account"));
                    account.setDateOfCreation(getDate("date_of_creation"));
                    account.setBalance(getDouble("balance"));
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                closeConnection();
            }

        }
        return account;
    }

    @Override
    public List<Account> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account authenticate(String agencyNumber, String accountNumber, String accountPassword) {

        Account account = null;
        if (verify(agencyNumber) && verify(accountNumber) && verify(accountPassword)) {
            String sql = "select * from accounts "
                    + "join clients on (accounts.client_id = clients.client_id) "
                    + "join agencies on (agencies.agency_id = accounts.agency_id) "
                    + "where (agencies.number_agency = ?) and (accounts.number_account = ?) and (upper(accounts.password_account) = upper(?))";

            try {
                set(sql);

                set(1, agencyNumber);
                set(2, accountNumber);
                set(3, accountPassword);

                if (results().next()) {

                    account = new Account();
                    Client client = new Client();
                    client.setId(getLong("client_id"));
                    client.setName(getString("name"));
                    client.setAccount(account);

                    Agency agency = new Agency();
                    agency.setId(getLong("agency_id"));
                    agency.setNumber(getString("number_agency"));

                    account.setId(getLong("account_id"));
                    account.setAgency(agency);
                    account.setOwner(client);
                    account.setPassword(null);
                    account.setNumber(getString("number_account"));
                    account.setType(getShort("type_account"));
                    account.setDateOfCreation(getDate("date_of_creation"));
                    account.setBalance(getDouble("balance"));
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                closeConnection();
            }

        }
        return account;
    }

    @Override
    public Account read(String agencyNumber, String accountNumber) {

        Account account = null;

        if (verify(agencyNumber) && verify(accountNumber)) {
            String sql = "select * from accounts "
                    + "join clients on (accounts.client_id = clients.client_id) "
                    + "join agencies on (agencies.agency_id = accounts.agency_id) "
                    + "where (agencies.number_agency = ?) and (accounts.number_account = ?)";

            try {
                set(sql);

                set(1, agencyNumber);
                set(2, accountNumber);

                if (results().next()) {

                    account = new Account();
                    Client client = new Client();
                    client.setId(getLong("client_id"));
                    client.setName(getString("name"));
                    client.setAccount(account);

                    Agency agency = new Agency();
                    agency.setId(getLong("agency_id"));
                    agency.setNumber(getString("number_agency"));

                    account.setId(getLong("account_id"));
                    account.setAgency(agency);
                    account.setOwner(client);
                    account.setPassword(null);
                    account.setNumber(getString("number_account"));
                    account.setType(getShort("type_account"));
                    account.setDateOfCreation(getDate("date_of_creation"));
                    account.setBalance(getDouble("balance"));
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                closeConnection();
            }
        }

        return account;

    }

}
