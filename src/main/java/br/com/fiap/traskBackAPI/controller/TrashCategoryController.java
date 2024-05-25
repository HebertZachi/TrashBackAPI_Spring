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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TrashCategory> getListOfTrashCategoryService() {
        return trashCategoryService.getAllTrashCategories();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrashCategory getTrashCategoryById(@RequestParam() Long id) {
        return trashCategoryService.getTrashCategoryById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TrashCategory createTrashCategory(@RequestBody() TrashCategory trashCategory) {
        return trashCategoryService.createTrashCategory(trashCategory);
    }

//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public String getIndex() {
//        return "Hello World";
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public String getIndexByID(@PathVariable int id) {
//        return "Hello World" + id;
//    }
//
//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public String saveIndex(@RequestBody String body){
//        return "Hello World";
//    };
}
