/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao.core;

import br.com.homerbank.dao.GenericDAO;
import br.com.homerbank.model.Account;
import br.com.homerbank.model.Transaction;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Maciel
 */
public interface TransactionDAO extends GenericDAO<Transaction>{
    
    List<Transaction> readByPeriod(Account account, Date dateStart, Date dateEnd);
}
