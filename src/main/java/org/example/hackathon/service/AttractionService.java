package org.example.hackathon.service;

import org.example.hackathon.model.Attraction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttractionService {
    private List<Attraction> list = new ArrayList<>();

    public List<Attraction> search(String keyword) {
        if (keyword == null || keyword.isBlank()) return list;
        String k = keyword.toLowerCase();
        return list.stream()
                .filter(a -> a.getName().toLowerCase().contains(k) ||
                        a.getLocation().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }

    public void save(Attraction attraction) {
        if (attraction.getId() == null) {
            attraction.setId(System.currentTimeMillis());
            list.add(attraction);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(attraction.getId())) {
                    list.set(i, attraction);
                    return;
                }
            }
        }
    }

    public Attraction getById(Long id) {
        return list.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    public void delete(Long id) {
        list.removeIf(a -> a.getId().equals(id));
    }
}