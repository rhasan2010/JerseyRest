/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imhasan.beingjava.messenger.api.exception;

/**
 *
 * @author Uzzal
 */


public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6328286661536343936L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
