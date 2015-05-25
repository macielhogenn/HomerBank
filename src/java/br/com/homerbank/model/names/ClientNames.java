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
public enum ClientNames {
    
    TABLE("clients"),
    ID("client_id"),
    NAME("name"),
    ACCOUNT("account_id");
    
    private String name;
    
    private ClientNames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
