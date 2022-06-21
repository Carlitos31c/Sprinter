package com.technical.sprinter.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "items", schema = "sprinter")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class ItemEntity extends BaseEntity {

    @Column(name = "reference_code")
    private String referenceCode;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @Column(name = "size")
    private String size;

    @Column(name = "material")
    private String material;

    @Column(name = "color")
    private String color;

    @Column(name = "sport")
    private String sport;
}
