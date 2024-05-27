package br.com.fiap.traskBackAPI.repository;

import br.com.fiap.traskBackAPI.model.CollectPoint;
import br.com.fiap.traskBackAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CollectPointRepository extends JpaRepository<CollectPoint, Long> {

    List<CollectPoint> findByTrashType(String trashType);
    List<CollectPoint> findByName(String name);
}
