package br.com.fiap.traskBackAPI.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_collect_point")
public class CollectPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COLLECT_POINT")
    @SequenceGenerator(name = "SEQ_COLLECT_POINT", sequenceName = "SEQ_COLLECT_POINT", allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    private String trashType;
    private Double latitude;
    private Double longitude;
}

