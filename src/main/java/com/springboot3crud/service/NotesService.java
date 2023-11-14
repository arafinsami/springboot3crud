package com.springboot3crud.service;


import com.springboot3crud.dto.NoteDto;
import com.springboot3crud.entity.Notes;
import com.springboot3crud.exception.ResourceNotFoundException;
import com.springboot3crud.handlar.NoteStoreHandler;
import com.springboot3crud.mapper.NoteModelMapper;
import com.springboot3crud.repository.NoteRepository;
import com.springboot3crud.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.springboot3crud.model.Action.*;
import static com.springboot3crud.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final ActionLogService actionLogService;

    private final NoteRepository noteRepository;

    private final ActiveUserContext context;

    @Transactional
    public Notes save(NoteDto dto) {
        NoteStoreHandler handler = NoteModelMapper.to(dto);
        Notes notes = NoteModelMapper.to(handler);
        Notes savedNotes = noteRepository.save(notes);
        NoteStoreHandler auditNoteStoreHandler = NoteModelMapper.from(savedNotes);
        NoteDto auditDto = NoteModelMapper.from(auditNoteStoreHandler);
        actionLogService.publishActivity(
                SAVE,
                String.valueOf(auditDto.getId()),
                objectToJson(auditDto)
        );
        return savedNotes;
    }

    @Transactional
    public Notes update(NoteDto dto) {
        Notes notes = noteRepository.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
        NoteStoreHandler noteStoreHandler = NoteModelMapper.update(dto);
        NoteModelMapper.update(noteStoreHandler, notes);
        Notes savedNotes = noteRepository.save(notes);
        NoteStoreHandler auditNoteStoreHandler = NoteModelMapper.from(savedNotes);
        NoteDto auditDto = NoteModelMapper.from(auditNoteStoreHandler);
        actionLogService.publishActivity(
                UPDATE,
                String.valueOf(auditDto.getId()),
                objectToJson(auditDto)
        );
        return savedNotes;
    }

    @Transactional(readOnly = true)
    public Optional<Notes> findById(Long id) {
        return noteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Notes> findByAppUserAndTitle(String title) {
        return noteRepository.findByAppUserAndTitle(context.getLoggedInUser(), title);
    }

    @Transactional
    public void delete(Notes notes) {
        noteRepository.delete(notes);
        NoteStoreHandler auditNoteStoreHandler = NoteModelMapper.from(notes);
        NoteDto auditDto = NoteModelMapper.from(auditNoteStoreHandler);
        actionLogService.publishActivity(
                DELETE,
                String.valueOf(auditDto.getId()),
                objectToJson(auditDto)
        );
    }

}
