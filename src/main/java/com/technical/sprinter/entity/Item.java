package com.technical.sprinter.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(callSuper = true)
public class Item extends BaseEntity {

    @Column(name = "reference_code")
    private String referenceCode;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "size")
    private String size;

    @Column(name = "material")
    private String material;

    @Column(name = "color")
    private String color;

    @Column(name = "sport")
    private String sport;
}
