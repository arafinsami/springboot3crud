package com.springboot3crud.mapper;

import com.springboot3crud.dto.NoteDto;
import com.springboot3crud.entity.Notes;
import com.springboot3crud.handlar.NoteStoreHandler;

public class NoteModelMapper {

    public static NoteStoreHandler to(NoteDto dto) {
        return NoteStoreHandler.builder()
                .title(dto.getTitle())
                .noteStatus(dto.getNoteStatus())
                .notePriority(dto.getNotePriority())
                .description(dto.getDescription())
                .build();
    }

    public static Notes to(NoteStoreHandler model) {
        return Notes.builder()
                .title(model.getTitle())
                .noteStatus(model.getNoteStatus())
                .notePriority(model.getNotePriority())
                .description(model.getDescription())
                .build();
    }

    public static NoteStoreHandler update(NoteDto dto) {
        return NoteStoreHandler.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .noteStatus(dto.getNoteStatus())
                .notePriority(dto.getNotePriority())
                .description(dto.getDescription())
                .build();
    }

    public static void update(NoteStoreHandler appUserStoreModel, Notes notes) {
        notes.setId(appUserStoreModel.getId());
        notes.setTitle(appUserStoreModel.getTitle());
        notes.setNoteStatus(appUserStoreModel.getNoteStatus());
        notes.setNotePriority(appUserStoreModel.getNotePriority());
        notes.setDescription(appUserStoreModel.getDescription());
    }

    public static NoteStoreHandler from(Notes notes) {
        return NoteStoreHandler.builder()
                .id(notes.getId())
                .title(notes.getTitle())
                .noteStatus(notes.getNoteStatus())
                .notePriority(notes.getNotePriority())
                .description(notes.getDescription())
                .build();
    }

    public static NoteDto from(NoteStoreHandler model) {
        return NoteDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .noteStatus(model.getNoteStatus())
                .notePriority(model.getNotePriority())
                .description(model.getDescription())
                .build();
    }
}
