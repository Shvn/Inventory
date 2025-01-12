package com.example.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class ItemEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private double price;

        @ManyToOne
        @JoinColumn(name = "category_id")
        private CategoryEntity category;

        @ManyToOne
        @JoinColumn(name = "brand_id")
        private BrandEntity brand;

        public ItemEntity(Long id) {
                this.id = id;
        }
}
