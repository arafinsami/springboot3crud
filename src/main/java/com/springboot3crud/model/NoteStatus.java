package com.springboot3crud.model;

import lombok.Getter;

@Getter
public enum NoteStatus {

    ACTIVE("Active"),
    IN_ACTIVE("In Active");


    private String label;

    NoteStatus(String label) {
        this.label = label;
    }

}
