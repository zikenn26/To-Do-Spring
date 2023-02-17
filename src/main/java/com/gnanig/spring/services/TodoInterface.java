package com.gnanig.spring.services;

public interface TodoInterface {
    String display();
    String create(String date, String task, boolean done);
    boolean delete(int index);
    boolean update(int index, String date, String task, Boolean done);
}
