package com.technical.sprinter.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands", schema = "sprinter")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class BrandEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemEntity> items;
}
