package com.springboot3crud.validation;

import com.springboot3crud.dto.NoteDto;
import com.springboot3crud.entity.Notes;
import com.springboot3crud.exception.ResourceNotFoundException;
import com.springboot3crud.service.NotesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

import static com.springboot3crud.utils.Constants.ALREADY_EXIST;
import static com.springboot3crud.utils.StringUtils.isNotEmpty;

@Component
@RequiredArgsConstructor
public class NoteValidator implements Validator {


    private final NotesService service;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return NoteDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors error) {
        NoteDto dto = (NoteDto) target;

        if (Objects.isNull(dto.getId())) {
            Optional<Notes> notes = service.findByAppUserAndTitle(dto.getTitle());
            if (notes.isPresent()) {
                error.rejectValue("title", null, ALREADY_EXIST);
            }
        }

        if (isNotEmpty(dto.getId())) {
            Notes notes = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!notes.getTitle().equals(dto.getTitle())) {
                Optional<Notes> appItemName = service.findByAppUserAndTitle(dto.getTitle());
                if (appItemName.isPresent()) {
                    error.rejectValue("title", null, ALREADY_EXIST);
                }
            }
        }
    }

}
