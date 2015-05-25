package br.com.homerbank.dao;

import br.com.homerbank.dao.core.AccountDAO;
import br.com.homerbank.dao.core.AgencyDAO;
import br.com.homerbank.dao.core.ClientDAO;
import br.com.homerbank.dao.core.SlipDAO;
import br.com.homerbank.dao.core.TransactionDAO;
import br.com.homerbank.dao.jdbc.JDBCAccountDAO;
import br.com.homerbank.dao.jdbc.JDBCAgencyDAO;
import br.com.homerbank.dao.jdbc.JDBCClientDAO;
import br.com.homerbank.dao.jdbc.JDBCSlipDAO;
import br.com.homerbank.dao.jdbc.JDBCTransactionDAO;

public class JDBCDAOFactory extends DAOFactory {

    @Override
    public ClientDAO getClientDAO() {
        return new JDBCClientDAO();
    }

    @Override
    public AccountDAO getAccountDAO() {
        return new JDBCAccountDAO();
    }

    @Override
    public AgencyDAO getAgencyDAO() {
        return new JDBCAgencyDAO();
    }

    @Override
    public TransactionDAO getTransactionDAO() {
        return new JDBCTransactionDAO();
    }

    @Override
    public SlipDAO getSlipDAO() {
        return new JDBCSlipDAO();
    }
    
    
}
