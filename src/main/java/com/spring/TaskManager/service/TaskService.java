package com.spring.TaskManager.service;

import com.spring.TaskManager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int tasksId = 1;
    private final SimpleDateFormat deadlineDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public  TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(tasksId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineDateFormat.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        tasksId++;
        return task;
    }
    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }
    public TaskEntity getTaskById(int id){
        for(TaskEntity task : tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id, String description, String deadline, boolean completed) throws Exception{
        TaskEntity task = getTaskById(id);
        if(task == null){
            throw new Exception("Task not found");
        }
        if(description != null){
        task.setDescription(description);
        }
        if(deadline!= null){
        task.setDeadline(deadlineDateFormat.parse(deadline));
        }
        task.setCompleted(completed);
        return task;
    }
}
