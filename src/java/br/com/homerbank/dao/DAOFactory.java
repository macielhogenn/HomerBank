package br.com.homerbank.dao;

import br.com.homerbank.dao.core.AccountDAO;
import br.com.homerbank.dao.core.AgencyDAO;
import br.com.homerbank.dao.core.ClientDAO;
import br.com.homerbank.dao.core.SlipDAO;
import br.com.homerbank.dao.core.TransactionDAO;
import br.com.homerbank.util.Configs;

public abstract class DAOFactory {
    
    private static final int JDBC = Configs.FACTORY;
    
    public static DAOFactory getDAOFactory(int factory) {
        switch (factory) {
            case DAOFactory.JDBC :
                return new JDBCDAOFactory();
            default :
                return null;
        }
    }
    
    public abstract ClientDAO getClientDAO();
    public abstract AccountDAO getAccountDAO();
    public abstract AgencyDAO getAgencyDAO();
    public abstract TransactionDAO getTransactionDAO();
    public abstract SlipDAO getSlipDAO();
}
