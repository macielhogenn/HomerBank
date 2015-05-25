package br.com.homerbank.dao.core;

import br.com.homerbank.dao.GenericDAO;
import br.com.homerbank.model.Slip;
import java.util.Date;

public interface SlipDAO extends GenericDAO<Slip> {
    
    Slip read(String barCode, double amount, Date dateOfPayment);
}
