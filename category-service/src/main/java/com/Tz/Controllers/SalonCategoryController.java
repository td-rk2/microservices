package com.Tz.Controllers;

import com.Tz.CategoryService.CategoryService;
import com.Tz.Models.Category;
import com.Tz.dto.SalonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        Category savedCategory = categoryService.saveCategory(category, salonDTO);
        return ResponseEntity.ok(savedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) throws Exception {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        categoryService.deleteCategory(categoryId, salonDTO.getId());
        return ResponseEntity.ok("Category deleted successfully");
    }
}
