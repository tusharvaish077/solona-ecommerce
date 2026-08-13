package com.solona.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    private String logo;

    private String banner;

    @Column(length = 2000)
    private String description;

    private String website;

    private Boolean enabled = true;

    private Integer displayOrder = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}