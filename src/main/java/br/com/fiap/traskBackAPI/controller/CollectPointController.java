package br.com.fiap.traskBackAPI.controller;

import br.com.fiap.traskBackAPI.dto.CollectPointDTO;
import br.com.fiap.traskBackAPI.model.CollectPoint;
import br.com.fiap.traskBackAPI.service.CollectPointService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> getCollectPointById(@PathVariable Long id) {
        try {
            CollectPointDTO collectPointDTO = collectPointService.getCollectionPointById(id);
            return new ResponseEntity<>(collectPointDTO, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-name")
    public ResponseEntity<?> getCollectPointsByName(@RequestParam String name) {
        try {
            List<CollectPointDTO> collectPointDTO = collectPointService.getCollectionPointsByName(name);
            return new ResponseEntity<>(collectPointDTO, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-trash-type")
    public ResponseEntity<?> getCollectPointsByTrashType(@RequestParam String trashType) {
        try {
            List<CollectPointDTO> collectPointDTOs = collectPointService.getCollectionPointsByTrashType(trashType);
            return new ResponseEntity<>(collectPointDTOs, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CollectPointDTO> createCollectPoint(@Valid @RequestBody CollectPointDTO collectPointDTO) {
        CollectPointDTO createdCollectPoint = collectPointService.createCollectPoint(collectPointDTO);
        return new ResponseEntity<>(createdCollectPoint, HttpStatus.CREATED);
    }

    @PostMapping("/create-list")
    public ResponseEntity<List<CollectPointDTO>> createCollectPoints(@Valid @RequestBody List<CollectPointDTO> collectPointDTOs) {
        List<CollectPointDTO> createdCollectPointDTOs = collectPointService.createCollectPoints(collectPointDTOs);
        return new ResponseEntity<>(createdCollectPointDTOs, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCollectPoint(@PathVariable Long id, @RequestBody CollectPointDTO collectPointDTO) {
        CollectPointDTO updatedCollectPoint = collectPointService.updateCollectPoint(id, collectPointDTO);
        if (updatedCollectPoint != null) {
            return new ResponseEntity<>(updatedCollectPoint, HttpStatus.OK);
        } else {
            return buildErrorResponse("CollectPoint not found for the provided ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCollectPoint(@PathVariable Long id) {
        try {
            collectPointService.deleteCollectPoint(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException ex) {
            return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    private ResponseEntity<Map<String, String>> buildErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(Map.of("messageError", message), status);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        Map<String, String> response = new HashMap<>();
        response.put("messageError", errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

