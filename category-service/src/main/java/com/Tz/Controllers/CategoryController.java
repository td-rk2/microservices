package com.Tz.Controllers;

import com.Tz.CategoryService.CategoryService;
import com.Tz.Models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    //localhost:5003/api/categories
    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<Category>> getCategoriesBySalon(@PathVariable Long salonId) {
        Set<Category> category1 = categoryService.getCategoriesBySalon(salonId);
        return ResponseEntity.ok(category1);
    }

    //localhost:5003/api/categories/1
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId){
        Category category1 = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category1);
    }

}
