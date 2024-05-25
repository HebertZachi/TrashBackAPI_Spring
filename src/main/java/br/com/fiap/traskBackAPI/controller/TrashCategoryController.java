package br.com.fiap.traskBackAPI.controller;

import br.com.fiap.traskBackAPI.model.TrashCategory;
import br.com.fiap.traskBackAPI.service.TrashCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/trash-category")
public class TrashCategoryController {

    @Autowired
    private TrashCategoryService trashCategoryService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TrashCategory> getListOfTrashCategoryService() {
        return trashCategoryService.getAllTrashCategories();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrashCategory getTrashCategoryById(@PathVariable Long id) {
        return trashCategoryService.getTrashCategoryById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TrashCategory createTrashCategory(@RequestBody() TrashCategory trashCategory) {
        return trashCategoryService.createTrashCategory(trashCategory);
    }
}
