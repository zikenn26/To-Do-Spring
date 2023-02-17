package com.gnanig.spring.controller;

import com.gnanig.spring.model.TodoItem;
import com.gnanig.spring.services.TodoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RequestMapping(path= "todo")
@RestController
public class TodoController{

    @Autowired
    private TodoInterface todoList;

    @RequestMapping(path = "display", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> findAll(){
        if (Objects.equals(todoList.display(), null))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("List is empty, " +
                    "nothing to display.\n" + todoList.display());
        else
            return ResponseEntity.ok(todoList.display());
    }

    @RequestMapping(path = "create", method = {RequestMethod.POST},consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createToDoList(@RequestBody TodoItem todoItem) {
        if (todoItem.getDate() == null && todoItem.getTask() == null)
            return ResponseEntity.ok("No data provided.\n" + todoList.display());
        else {
            todoList.create(todoItem.getDate(), todoItem.getTask(), todoItem.isDone());
            return ResponseEntity.ok(todoList.display());
        }
    }


    @RequestMapping(path = "delete/{index}", method = {RequestMethod.DELETE})
    public ResponseEntity<String> deleteToDoList(@PathVariable Integer index) {
        if(todoList.delete(index))
            return ResponseEntity.ok(todoList.display());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid index!\n");
    }

    @RequestMapping(path = "update/{index}", method = {RequestMethod.PUT},consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateTodoList(@PathVariable Integer index, @RequestBody TodoItem todoItem){
        if(todoList.update(index, todoItem.getDate(), todoItem.getTask(),todoItem.isDone())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid serial number\n" + todoList.display());
        }
        if(Objects.equals(todoList.display(), "[]")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo List is empty, Please create a task first!\n");
        }
        String tempDate = todoItem.getDate();
        String tempTask = todoItem.getTask();
        Boolean tempDone = todoItem.isDone();
        if(tempDate==null && tempTask==null && !tempDone){
            return ResponseEntity.ok("No modification done in the To-Do.\n" + todoList.display());
        }
        else{
            todoList.update(index,tempDate,tempTask,tempDone);
            return ResponseEntity.ok("Successfully updated ToDo List\n" +todoList.display());
        }
    }

}
