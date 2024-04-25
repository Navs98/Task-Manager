package com.spring.TaskManager.service;

import com.spring.TaskManager.entities.NoteEntity;
import com.spring.TaskManager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {
    private TaskService taskService;
    private HashMap<Integer, TaskNotesHolder> taskNotesHolders = new HashMap<>();
    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNotesHolder{
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }
    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }
        if(taskNotesHolders.get(taskId) == null){
            taskNotesHolders.put(taskId, new TaskNotesHolder());
        }
        return taskNotesHolders.get(taskId).notes;
    }

    public NoteEntity addNoteTask(int taskId, String title, String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }
        if(taskNotesHolders.get(taskId) == null){
            taskNotesHolders.put(taskId, new TaskNotesHolder());
        }
        NoteEntity note = new NoteEntity();
        note.setId(taskNotesHolders.get(taskId).noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolders.get(taskId).notes.add(note);
        taskNotesHolders.get(taskId).noteId++;
        return note;
    }
}
