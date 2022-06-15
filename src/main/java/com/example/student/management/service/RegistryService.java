package com.example.student.management.service;

import com.example.student.management.models.Registry;
import com.example.student.management.repositories.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistryService {
    @Autowired
    RegistryRepository registryRepository;

    public List<Registry> getAllRegistries() {
        return registryRepository.findAll();
    }

    public int insert(Registry registry) {
        return registryRepository.RegisterCourse(registry.getStudentId(),registry.getCourseId());
    }
}
