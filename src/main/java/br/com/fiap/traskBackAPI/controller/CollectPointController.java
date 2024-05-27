package br.com.fiap.traskBackAPI.controller;

import br.com.fiap.traskBackAPI.model.CollectPoint;
import br.com.fiap.traskBackAPI.service.CollectPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collect-points")
public class CollectPointController {

    @Autowired
    private CollectPointService collectPointService;

    @GetMapping
    public ResponseEntity<List<CollectPoint>> getAllCollectPoints() {
        List<CollectPoint> collectPoints = collectPointService.getAllCollectPoints();
        return new ResponseEntity<>(collectPoints, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectPoint> getCollectPointById(@PathVariable Long id) {
        CollectPoint collectPoint = collectPointService.getCollectionPointById(id);
        if (collectPoint != null) {
            return new ResponseEntity<>(collectPoint, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-trash-type")
    public ResponseEntity<List<CollectPoint>> getCollectPointsByTrashType(@RequestParam String trashType) {
        List<CollectPoint> collectPoints = collectPointService.getCollectionPointsByTrashType(trashType);
        return new ResponseEntity<>(collectPoints, HttpStatus.OK);
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<CollectPoint>> getCollectPointsByName(@RequestParam String name) {
        List<CollectPoint> collectPoints = collectPointService.getCollectionPointsByName(name);
        return new ResponseEntity<>(collectPoints, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CollectPoint> createCollectPoint(@RequestBody CollectPoint collectPoint) {
        CollectPoint createdCollectPoint = collectPointService.createCollectPoint(collectPoint);
        return new ResponseEntity<>(createdCollectPoint, HttpStatus.CREATED);
    }

    @PostMapping("/create-list")
    public ResponseEntity<List<CollectPoint>> createCollectPoints(@RequestBody List<CollectPoint> collectPoints) {
        List<CollectPoint> createdCollectPoints = collectPointService.createCollectPoints(collectPoints);
        return new ResponseEntity<>(createdCollectPoints, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectPoint> updateCollectPoint(@PathVariable Long id, @RequestBody CollectPoint collectPointDetails) {
        CollectPoint updatedCollectPoint = collectPointService.updateCollectPoint(id, collectPointDetails);
        if (updatedCollectPoint != null) {
            return new ResponseEntity<>(updatedCollectPoint, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectPoint(@PathVariable Long id) {
        collectPointService.deleteCollectionPoint(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
