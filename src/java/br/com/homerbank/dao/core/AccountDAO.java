/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao.core;

import br.com.homerbank.dao.GenericDAO;
import br.com.homerbank.model.Account;

/**
 *
 * @author Maciel
 */
public interface AccountDAO extends GenericDAO<Account>{
    
    Account authenticate(String agencyNumber, String accountNumber, String accountPassword);
    Account read(String agencyNumber, String accountNumber);
}
