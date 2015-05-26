/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.model.names;

/**
 *
 * @author Felipe
 */
public enum SlipNames {
    TABLE("slips"),
    FROM_ACCOUNT("from_account"),
    TO_ACCOUNT("to_account"),
    CODE("bar_code"),
    DATE_OF_PAYMENT("date_of_payment"),
    AMOUNT("amount"),
    ID("slip_id");

    SlipNames(String name) {
        this.name = name;
    }
    private final String name;

    @Override
    public String toString() {
        return name;
    }

}
