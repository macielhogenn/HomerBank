/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.model;

import java.util.Date;

/**
 *
 * @author Maciel
 */
public class Transaction {
    
    private long id;
    private Account acCredit;
    private Account acDebit;
    private Date date;
    private double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAcCredit() {
        return acCredit;
    }

    public void setAcCredit(Account acCredit) {
        this.acCredit = acCredit;
    }

    public Account getAcDebit() {
        return acDebit;
    }

    public void setAcDebit(Account acDebit) {
        this.acDebit = acDebit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
