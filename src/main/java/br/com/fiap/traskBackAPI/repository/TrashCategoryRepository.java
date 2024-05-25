package br.com.fiap.traskBackAPI.repository;

import br.com.fiap.traskBackAPI.model.TrashCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashCategoryRepository extends JpaRepository<TrashCategory, Long> {

}
