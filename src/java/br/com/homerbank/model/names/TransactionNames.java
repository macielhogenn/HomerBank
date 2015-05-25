/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.model.names;

/**
 *
 * @author Maciel
 */
public enum TransactionNames {
    
    TABLE("transactions"),
    ID("transaction_id"),
    AC_CREDIT("account_id_credit"),
    AC_DEBIT("account_id_debit"),
    DATE("date_transaction"),
    AMOUNT("amount");
    
    private String name;
    
    private TransactionNames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
