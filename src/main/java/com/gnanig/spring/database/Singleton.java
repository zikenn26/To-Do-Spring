package com.gnanig.spring.database;

import com.gnanig.spring.model.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    static Singleton obj = new Singleton();

    List<TodoItem> toDo;

    public List<TodoItem> getToDo() {
        return toDo;
    }

    private Singleton() {
        toDo = new ArrayList<>();
    }

    public static Singleton getInstance() {
        return obj;
    }
}
