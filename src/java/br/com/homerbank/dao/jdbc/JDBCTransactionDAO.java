package br.com.homerbank.dao.jdbc;

import br.com.homerbank.dao.DAOFactory;
import br.com.homerbank.dao.core.AccountDAO;
import br.com.homerbank.dao.core.TransactionDAO;
import br.com.homerbank.model.Account;
import br.com.homerbank.model.Agency;
import br.com.homerbank.model.Client;
import br.com.homerbank.model.Transaction;
import br.com.homerbank.model.names.TransactionNames;
import br.com.homerbank.util.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JDBCTransactionDAO extends JDBCGenericDAO implements TransactionDAO {

    @Override
    public boolean create(Transaction t) {
        boolean created = false;
        
        if (t != null) {
            String sql = "insert into " + TransactionNames.TABLE + " ( " + 
                    TransactionNames.AC_CREDIT + ", " + 
                    TransactionNames.AC_DEBIT + ", " +
                    TransactionNames.AMOUNT + ", " + 
                    TransactionNames.DATE + " ) values (?, ?, ?, ?)";
            
            try {
                set(sql);
                
                set(1, t.getAcCredit().getId());
                set(2, t.getAcDebit().getId());
                set(3, t.getAmount());
                set(4, t.getDate());
                
                created = execute();
                
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                closeConnection();
            }
        }
        
        return created;
    }

    @Override
    public boolean update(Transaction t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaction read(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> readByPeriod(Account account, Date dateStart, Date dateEnd) {
        List<Transaction> transactions = new ArrayList<>();
        
        if (verify(dateStart) && verify(dateEnd)) {
            
            AccountDAO accountDAO = DAOFactory.getDAOFactory(Configs.FACTORY).getAccountDAO();
            
            String sql = "select * from transactions "
                    + "join accounts on (transactions.account_id_debit = accounts.account_id OR transactions.account_id_credit = accounts.account_id)"
                    + "join clients on (accounts.client_id = clients.client_id) "
                    + "join agencies on (agencies.agency_id = accounts.agency_id) "
                    + "where accounts.account_id = ? "
                    + "and agencies.agency_id = ? "
                    + "and transactions.date_transaction between ? and ? "
                    + "order by date_transaction ASC";
            
            try {
                set(sql);
                
                set(1, account.getId());
                set(2, account.getAgency().getId());
                set(3, dateStart);
                set(4, dateEnd);
                
                while (results().next()) {
                    Transaction transaction = new Transaction();
                    
                    transaction.setId(getLong("transaction_id"));
                    transaction.setDate(getDate("date_transaction"));
                    transaction.setAmount(getDouble("amount"));
                    transaction.setAcCredit(accountDAO.read(getLong("account_id_credit")));
                    transaction.setAcDebit(accountDAO.read(getLong("account_id_debit")));
                    
                    transactions.add(transaction);
                }
                        
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                closeConnection();
            }
        }
        
        return transactions;
    }
    
    
    
}
