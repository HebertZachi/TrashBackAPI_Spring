package br.com.fiap.traskBackAPI.service;

import br.com.fiap.traskBackAPI.dto.CollectPointDTO;
import br.com.fiap.traskBackAPI.model.CollectPoint;
import br.com.fiap.traskBackAPI.repository.CollectPointRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectPointService {

    @Autowired
    private CollectPointRepository collectPointRepository;

    public List<CollectPoint> getAllCollectPoints() {
        return collectPointRepository.findAll();
    }

    public CollectPointDTO getCollectionPointById(Long id) {
        CollectPoint collectPoint = collectPointRepository.findById(id).orElse(null);
        if (collectPoint != null) {
            collectPointRepository.deleteById(id);
            return new CollectPointDTO(
                    collectPoint.getId(),
                    collectPoint.getName(),
                    collectPoint.getAddress(),
                    collectPoint.getTrashType(),
                    collectPoint.getLatitude(),
                    collectPoint.getLongitude()
            );
        } else {
            throw new EntityNotFoundException("CollectPoint not found for the provided ID: " + id);
        }
    }

    public List<CollectPointDTO> getCollectionPointsByName(String name) {
        List<CollectPoint> collectPoints = collectPointRepository.findByName(name);
        if (!collectPoints.isEmpty()) {
            List<CollectPointDTO> collectPointDTOs = new ArrayList<>();
            for (CollectPoint collectPoint : collectPoints) {
                collectPointDTOs.add(new CollectPointDTO(
                        collectPoint.getId(),
                        collectPoint.getName(),
                        collectPoint.getAddress(),
                        collectPoint.getTrashType(),
                        collectPoint.getLatitude(),
                        collectPoint.getLongitude()
                ));
            }
            return collectPointDTOs;
        } else {
            throw new EntityNotFoundException("CollectPoints not found for the provided name: " + name);
        }
    }

    public List<CollectPointDTO> getCollectionPointsByTrashType(String trashType) {
        List<CollectPoint> collectPoints = collectPointRepository.findByTrashType(trashType);
        if (!collectPoints.isEmpty()) {
            List<CollectPointDTO> collectPointDTOs = new ArrayList<>();
            for (CollectPoint collectPoint : collectPoints) {
                collectPointDTOs.add(new CollectPointDTO(
                        collectPoint.getId(),
                        collectPoint.getName(),
                        collectPoint.getAddress(),
                        collectPoint.getTrashType(),
                        collectPoint.getLatitude(),
                        collectPoint.getLongitude()
                ));
            }
            return collectPointDTOs;
        } else {
            throw new EntityNotFoundException("CollectPoints not found for the provided trash type: " + trashType);
        }
    }

    public CollectPointDTO createCollectPoint(CollectPointDTO collectPointDTO) {
        CollectPoint collectPoint = new CollectPoint();
        BeanUtils.copyProperties(collectPointDTO, collectPoint);
        CollectPoint savedCollectPoint = collectPointRepository.save(collectPoint);
        return new CollectPointDTO(
                savedCollectPoint.getId(),
                savedCollectPoint.getName(),
                savedCollectPoint.getAddress(),
                savedCollectPoint.getTrashType(),
                savedCollectPoint.getLatitude(),
                savedCollectPoint.getLongitude()
        );
    }

    public List<CollectPointDTO> createCollectPoints(List<CollectPointDTO> collectPointDTOs) {
        List<CollectPoint> collectPoints = collectPointDTOs.stream().map(dto -> {
            CollectPoint collectPoint = new CollectPoint();
            BeanUtils.copyProperties(dto, collectPoint);
            return collectPoint;
        }).collect(Collectors.toList());

        List<CollectPoint> savedCollectPoints = collectPointRepository.saveAll(collectPoints);

        return savedCollectPoints.stream().map(savedCollectPoint -> new CollectPointDTO(
                savedCollectPoint.getId(),
                savedCollectPoint.getName(),
                savedCollectPoint.getAddress(),
                savedCollectPoint.getTrashType(),
                savedCollectPoint.getLatitude(),
                savedCollectPoint.getLongitude()
        )).collect(Collectors.toList());
    }

    public CollectPointDTO  updateCollectPoint(Long id, CollectPointDTO collectPointDTO) {
        CollectPoint collectPoint = collectPointRepository.findById(id).orElse(null);

        if (collectPoint != null) {
            if (collectPointDTO.name() != null) {
                collectPoint.setName(collectPointDTO.name());
            }
            if (collectPointDTO.address() != null) {
                collectPoint.setAddress(collectPointDTO.address());
            }
            if (collectPointDTO.trashType() != null) {
                collectPoint.setTrashType(collectPointDTO.trashType());
            }
            if (collectPointDTO.latitude() != null) {
                collectPoint.setLatitude(collectPointDTO.latitude());
            }
            if (collectPointDTO.longitude() != null) {
                collectPoint.setLongitude(collectPointDTO.longitude());
            }

            CollectPoint savedCollectPoint = collectPointRepository.save(collectPoint);
            return new CollectPointDTO(
                    savedCollectPoint.getId(),
                    savedCollectPoint.getName(),
                    savedCollectPoint.getAddress(),
                    savedCollectPoint.getTrashType(),
                    savedCollectPoint.getLatitude(),
                    savedCollectPoint.getLongitude()
            );
        }
        return null;
    }

    public void deleteCollectPoint(Long id) {
        CollectPoint collectPoint = collectPointRepository.findById(id).orElse(null);
        if (collectPoint != null) {
            collectPointRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("CollectPoint not found for the provided ID: " + id);
        }
    }

}
