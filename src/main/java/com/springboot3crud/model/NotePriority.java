package com.springboot3crud.model;

import lombok.Getter;

@Getter
public enum NotePriority {

    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("HIGH");


    private String label;

    NotePriority(String label) {
        this.label = label;
    }

}
