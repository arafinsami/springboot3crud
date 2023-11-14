package com.springboot3crud.dto;


import com.springboot3crud.model.NotePriority;
import com.springboot3crud.model.NoteStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Builder
public class NoteDto {

    private Long id;

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    private NoteStatus noteStatus;

    @NonNull
    private NotePriority notePriority;

    @NonNull
    @NotBlank
    private String description;
}
