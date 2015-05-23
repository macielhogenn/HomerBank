/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.homerbank.dao;

import java.util.List;

/**
 *
 * @author Maciel
 */
public interface GenericDAO<T> {
    
    boolean create(T t);
    boolean update(T t);
    boolean delete(long id);
    T read(long id);
    List<T> readAll();
}
