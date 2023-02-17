package com.gnanig.spring.services;


import com.gnanig.spring.database.Singleton;
import com.gnanig.spring.model.TodoItem;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoServices implements TodoInterface {
    Singleton data = Singleton.getInstance();
    List<TodoItem> list = data.getToDo();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public String create(String date, String task, boolean done) {
        TodoItem todoItem = new TodoItem();
        if (task != null && date != null) {
            todoItem.setDate(date);
            todoItem.setTask(task);
            todoItem.setDone(done);
        } else if (date != null) {
            todoItem.setDate(date);
            todoItem.setTask("not provided");
            todoItem.setDone(done);
        } else if (task != null) {
            todoItem.setDate("not provided");
            todoItem.setTask(task);
            todoItem.setDone(done);
        }
        list.add(todoItem);
        return "The task has been successfully added\n" + list;
    }

    public String display() {
        return gson.toJson(list);
    }

    public boolean delete(int index) {
        if (index <= list.size()) {
            list.remove(index - 1);
            return true;
        }
        else return false;
    }

    public boolean update(int index, String date, String task, Boolean done){
        if(index > list.size()){
            return true;
        }
        else {
            if(done){
                list.get(index-1).setDone(done);
            }
            if(date!=null && task!=null){
                list.get(index-1).setDate(date);
                list.get(index-1).setTask(task);
            }
            else if(date!=null){
                list.get(index-1).setDate(date);
            }
            else if(task!=null){
                list.get(index-1).setTask(task);
            }
        }
        return false;
    }
}

