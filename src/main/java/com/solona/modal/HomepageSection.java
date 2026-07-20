package com.solona.modal;

import com.solona.domain.SectionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Entity
@Data
public class HomepageSection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SectionType sectionType;

    private String title;
    private Integer displayOrder;

    private Boolean enabled;
    @Column(columnDefinition = "TEXT")
    private String config;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
