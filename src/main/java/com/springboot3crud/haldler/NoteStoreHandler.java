package com.springboot3crud.haldler;


import com.springboot3crud.model.NotePriority;
import com.springboot3crud.model.NoteStatus;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class NoteStoreHandler {

    private Long id;

    private String title;

    private NoteStatus noteStatus;

    private NotePriority notePriority;

    private String description;
}
