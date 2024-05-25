package br.com.fiap.traskBackAPI.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_trash_category")
public class TrashCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRASH_CATEGORY")
    @SequenceGenerator(name = "SEQ_TRASH_CATEGORY", sequenceName = "SEQ_TRASH_CATEGORY", allocationSize = 1)
    private Long id;
    private String name;
}

