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
public enum AgencyNames {
    
    TABLE("agencies"),
    ID("agency_id"),
    NUMBER("number");
    
    private String name;
    
    private AgencyNames(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
