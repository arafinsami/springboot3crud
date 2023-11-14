package com.springboot3crud.entity;


import com.springboot3crud.model.Action;
import com.springboot3crud.utils.DateUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class ActionLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Action action;

    private String userName;

    private String documentId;

    private String comments;

    private String ipAddress;

    public String getCreatedStr() {
        return DateUtils.format(this.created, DateUtils.DATE_TIME_FORMAT);
    }
}
