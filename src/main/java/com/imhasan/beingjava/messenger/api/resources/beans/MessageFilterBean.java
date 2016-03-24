/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imhasan.beingjava.messenger.api.resources.beans;

import javax.ws.rs.QueryParam;

/**
 *
 * @author Uzzal
 */
public class MessageFilterBean {

    private @QueryParam("year")
    int year;
    private @QueryParam("start")
    int start;
    private @QueryParam("size")
    int size;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
