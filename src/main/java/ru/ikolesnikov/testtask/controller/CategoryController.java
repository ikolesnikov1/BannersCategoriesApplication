package ru.ikolesnikov.testtask.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ikolesnikov.testtask.model.Category;
import ru.ikolesnikov.testtask.services.CategoryService;
import static org.springframework.http.HttpStatus.OK;
import org.json.JSONObject;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(@RequestParam(required = false) String name) {
        if (name != null)
            return ResponseEntity.status(OK)
                    .body(categoryService.getCategoriesByName(name));

        return ResponseEntity.status(OK)
                .body(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.status(OK)
                .body(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.status(OK)
                .body(categoryService.createCategory(category));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.status(OK)
                .body(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        return new JSONObject().put("message", categoryService.deleteCategory(categoryId)).toString();
    }
}
