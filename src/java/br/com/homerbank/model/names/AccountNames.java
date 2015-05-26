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
public enum AccountNames {

    TABLE("accounts"),
    ID("account_id"),
    AGENCY("agency_id"),
    NUMBER("number_account"),
    PASSWORD("password"),
    TYPE("type_account"),
    DATE_OF_CREATION("date_of_creation"),
    CLIENT("client_id"),
    BALANCE("balance");

    private String name;

    private AccountNames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
