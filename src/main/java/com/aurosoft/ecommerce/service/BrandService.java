package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> listAllBrand();
    Brand getBrandById(int id);
    Brand insertBrand(Brand brand);
    Brand updateBrand(Brand brand);
    int deleteBrand(int id);
}
