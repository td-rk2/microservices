package com.Tz.CategoryService;

import com.Tz.Models.Category;
import com.Tz.dto.SalonDTO;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getCategoriesBySalon(Long salonId);
    Category getCategoryById(Long categoryId);
    void deleteCategory(Long categoryId, Long salonId) throws Exception;

}
