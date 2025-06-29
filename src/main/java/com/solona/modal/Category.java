package com.solona.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    @NotNull
    @Column(unique = true)
    private String categoryId; // this is changed from CategoryId to categoryId after facing issue on restart

    @ManyToOne
    private Category perentCategory;

    @NotNull
    private Integer level;
}
