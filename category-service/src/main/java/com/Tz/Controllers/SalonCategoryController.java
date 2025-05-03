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

    //localhost:5003/api/categories/salon-owner
    //{
    //    "name" : "Hair color",
    //    "image" : "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fpeace%2F&psig=AOvVaw1Snk69aq8KZ7QlzbwQqJa5&ust=1739460295216000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOilneC4vosDFQAAAAAdAAAAABAE"
    //}
    @PostMapping()
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        Category savedCategory = categoryService.saveCategory(category, salonDTO);
        return ResponseEntity.ok(savedCategory);
    }

    //localhost:5003/api/categories/salon-owner/1
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) throws Exception {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        categoryService.deleteCategory(categoryId, salonDTO.getId());
        return ResponseEntity.ok("Category deleted successfully");
    }
}
