package com.springboot3crud.controller;


import com.springboot3crud.dto.NoteDto;
import com.springboot3crud.entity.Notes;
import com.springboot3crud.exception.ResourceNotFoundException;
import com.springboot3crud.handlar.NoteStoreHandler;
import com.springboot3crud.mapper.NoteModelMapper;
import com.springboot3crud.service.NotesService;
import com.springboot3crud.validation.NoteValidator;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import static com.springboot3crud.exception.ApiError.fieldError;
import static com.springboot3crud.utils.ResponseBuilder.error;
import static com.springboot3crud.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Tag(name = "Note api")
@RequestMapping(path = "notes")
public class NotesController {

    private final NotesService notesService;

    private final NoteValidator noteValidator;

    @PostMapping
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoteDto.class))
    })
    public ResponseEntity<JSONObject> createNote(@Valid @RequestBody NoteDto dto,
                                                 BindingResult bindingResult) {
        ValidationUtils.invokeValidator(noteValidator, dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Notes notes = notesService.save(dto);
        NoteStoreHandler noteStoreHandler = NoteModelMapper.from(notes);
        return ok(success(NoteModelMapper.from(noteStoreHandler)).getJson());
    }

    @PutMapping
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoteDto.class))
    })
    public ResponseEntity<JSONObject> updateNote(@Valid @RequestBody NoteDto dto,
                                                 BindingResult bindingResult) {
        ValidationUtils.invokeValidator(noteValidator, dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Notes notes = notesService.update(dto);
        NoteStoreHandler noteStoreHandler = NoteModelMapper.from(notes);
        return ok(success(NoteModelMapper.from(noteStoreHandler)).getJson());
    }

    @GetMapping("/{noteId}")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoteDto.class))
    })
    public ResponseEntity<JSONObject> noteDetails(@PathVariable Long noteId) {
        Notes notes = notesService.findById(noteId).orElseThrow(ResourceNotFoundException::new);
        NoteStoreHandler noteStoreHandler = NoteModelMapper.from(notes);
        return ok(success(NoteModelMapper.from(noteStoreHandler)).getJson());
    }

    @DeleteMapping("/{noteId}")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NoteDto.class))
    })
    public ResponseEntity<JSONObject> deleteNote(@PathVariable Long noteId) {
        Notes notes = notesService.findById(noteId).orElseThrow(ResourceNotFoundException::new);
        notesService.delete(notes);
        return ok(success("note delete with id:" + noteId).getJson());
    }
}
