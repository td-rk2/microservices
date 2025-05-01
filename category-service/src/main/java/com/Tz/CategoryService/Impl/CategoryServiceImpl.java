package com.Tz.CategoryService.Impl;

import com.Tz.CategoryService.CategoryService;
import com.Tz.Models.Category;
import com.Tz.Repositories.CategoryRepository;
import com.Tz.dto.SalonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDTO salonDTO) {

        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setSalonId(salonDTO.getId());
        category1.setImage(category.getImage());

        return categoryRepository.save(category1);

    }

    @Override
    public Set<Category> getCategoriesBySalon(Long salonId) {

        return categoryRepository.findBySalonId(salonId);

    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public void deleteCategory(Long categoryId, Long salonId) throws Exception {

        Category category = getCategoryById(categoryId);
        if(!category.getSalonId().equals(salonId)){
            throw new Exception("Category does not belong to this salon");
        }
        categoryRepository.deleteById(categoryId);

    }
}
