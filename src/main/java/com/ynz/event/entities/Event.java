package com.ynz.event.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "EVENT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "CREATION_TIME")
    //@Builder.Default
    private LocalDateTime createdAt;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "ACTION")
    private String action;

}
