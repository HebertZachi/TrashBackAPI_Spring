package br.com.fiap.traskBackAPI.service;

import br.com.fiap.traskBackAPI.model.TrashCategory;
import br.com.fiap.traskBackAPI.repository.TrashCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrashCategoryService {

    @Autowired
    private TrashCategoryRepository trashCategoryRepository;

    public TrashCategory createTrashCategory(TrashCategory trashCategory) {
        return trashCategoryRepository.save(trashCategory);
    }

    public List<TrashCategory> getAllTrashCategories() {
        return trashCategoryRepository.findAll();
    }

    public TrashCategory getTrashCategoryById(Long id) {
        var  gameOptional = trashCategoryRepository.findById(id);
        if (gameOptional.isPresent()) {
            return gameOptional.get();
        } else {
            throw new RuntimeException("trashCategory not found");
        }
    }
}
