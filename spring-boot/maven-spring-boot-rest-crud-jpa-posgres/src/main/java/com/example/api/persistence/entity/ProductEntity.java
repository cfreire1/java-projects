package com.example.api.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_demo_category")
    private Integer idCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "state")
    private boolean state;

    @ManyToOne
    @JoinColumn(name = "id_demo_category",insertable = false,updatable = false)
    private CategoryEntity demoCategory;
}