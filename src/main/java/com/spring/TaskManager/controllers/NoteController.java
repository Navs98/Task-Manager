package com.spring.TaskManager.controllers;

import com.spring.TaskManager.dto.CreateNoteDTO;
import com.spring.TaskManager.dto.CreateNoteResponseDTO;
import com.spring.TaskManager.entities.NoteEntity;
import com.spring.TaskManager.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes = noteService.getNotesForTask(taskId);
        if(notes == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body){
        var note = noteService.addNoteTask(taskId, body.getTitle(), body.getBody());

        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }

}
