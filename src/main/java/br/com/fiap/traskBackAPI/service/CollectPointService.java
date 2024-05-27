package br.com.fiap.traskBackAPI.service;

import br.com.fiap.traskBackAPI.model.CollectPoint;
import br.com.fiap.traskBackAPI.repository.CollectPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectPointService {

    @Autowired
    private CollectPointRepository collectPointRepository;

    public CollectPoint createCollectPoint(CollectPoint collectPoint) {
        return collectPointRepository.save(collectPoint);
    }

    public List<CollectPoint> createCollectPoints(List<CollectPoint> collectPoints) {
        return collectPointRepository.saveAll(collectPoints);
    }

    public List<CollectPoint> getAllCollectPoints() {
        return collectPointRepository.findAll();
    }

    public CollectPoint getCollectionPointById(Long id) {
        return collectPointRepository.findById(id).orElse(null);
    }

    public List<CollectPoint> getCollectionPointsByTrashType(String trashType) {
        return collectPointRepository.findByTrashType(trashType);
    }

    public List<CollectPoint> getCollectionPointsByName(String name) {
        return collectPointRepository.findByName(name);
    }

    public CollectPoint updateCollectPoint(Long id, CollectPoint collectPointDetails) {
        CollectPoint collectPoint = collectPointRepository.findById(id).orElse(null);

        if (collectPoint != null) {
            if (collectPointDetails.getName() != null) {
                collectPoint.setName(collectPointDetails.getName());
            }
            if (collectPointDetails.getAddress() != null) {
                collectPoint.setAddress(collectPointDetails.getAddress());
            }
            if (collectPointDetails.getTrashType() != null) {
                collectPoint.setTrashType(collectPointDetails.getTrashType());
            }
            if (collectPointDetails.getLatitude() != null) {
                collectPoint.setLatitude(collectPointDetails.getLatitude());
            }
            if (collectPointDetails.getLongitude() != null) {
                collectPoint.setLongitude(collectPointDetails.getLongitude());
            }

            return collectPointRepository.save(collectPoint);
        }

        return null;
    }

    public void deleteCollectionPoint(Long id) {
        collectPointRepository.deleteById(id);
    }
}
