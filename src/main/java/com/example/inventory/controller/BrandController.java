package com.example.inventory.controller;

import com.example.inventory.service.BrandService;
import com.example.inventory.entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<BrandEntity> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandEntity createBrand(@RequestBody BrandEntity brand) {
        return brandService.createBrand(brand);
    }

    @GetMapping("/{id}")
    public BrandEntity getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @PutMapping("/{id}")
    public BrandEntity updateBrand(@PathVariable Long id, @RequestBody BrandEntity brand) {
        return brandService.updateBrand(id, brand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}
