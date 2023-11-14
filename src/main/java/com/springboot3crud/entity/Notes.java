package com.springboot3crud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot3crud.model.NotePriority;
import com.springboot3crud.model.NoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@DynamicUpdate
@NoArgsConstructor
public class Notes implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private NoteStatus noteStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private NotePriority notePriority;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    AppUser appUser;
}
