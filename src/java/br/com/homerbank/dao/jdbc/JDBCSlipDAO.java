package br.com.homerbank.dao.jdbc;

import br.com.homerbank.dao.core.SlipDAO;
import br.com.homerbank.model.Account;
import br.com.homerbank.model.Slip;
import br.com.homerbank.model.names.ClientNames;
import br.com.homerbank.model.names.SlipNames;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class JDBCSlipDAO extends JDBCGenericDAO implements SlipDAO {

    @Override
    public boolean create(Slip t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Slip slip) {
        String sql = "update " + SlipNames.TABLE + " SET " + SlipNames.FROM_ACCOUNT + " = ? WHERE " + SlipNames.CODE + " = ?";

        boolean updated = false;

        try {
            set(sql);
            set(1, slip.getCode());

            updated = execute();
        } catch (SQLException e) {;
        } finally {
            closeConnection();
        }
        return updated;
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Slip read(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Slip> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Slip read(String barCode, double amount, Date dateOfPayment) {
        String sql = "select * from " + SlipNames.TABLE + " WHERE " + SlipNames.CODE + " = ? ";

        Slip slip = null;

        try {
            set(sql);
            set(1, barCode);

            if (results().next()) {
                slip = new Slip();
                slip.setAmount(getDouble(SlipNames.AMOUNT.toString()));
                slip.setCode(getString(SlipNames.CODE.toString()));
                slip.setDateOfPayment(getDate(SlipNames.DATE_OF_PAYMENT.toString()));

                Account toAccount = new Account();
                toAccount.setId(getLong(SlipNames.TO_ACCOUNT.toString()));
                slip.setToAccount(toAccount);

                Account fromAccount = new Account();
                toAccount.setId(getLong(SlipNames.FROM_ACCOUNT.toString()));
                slip.setFromAccount(fromAccount);
            }

        } catch (SQLException e) {;
        } finally {
            closeConnection();
        }

        return slip;
    }

}
