package com.example.inventory.service;

import com.example.inventory.repository.BrandRepository;
import com.example.inventory.entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public BrandEntity createBrand(BrandEntity brand) {
        if (brandRepository.existsByName(brand.getName())) {
            throw new IllegalArgumentException("Brand name must be unique");
        }
        return brandRepository.save(brand);
    }

    public List<BrandEntity> getAllBrands() {
        return brandRepository.findAll();
    }

    public BrandEntity getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    public BrandEntity updateBrand(Long id, BrandEntity brand) {
        BrandEntity existingBrand = getBrandById(id);
        existingBrand.setName(brand.getName());
        return brandRepository.save(existingBrand);
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}

