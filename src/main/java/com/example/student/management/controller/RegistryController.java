package com.example.student.management.controller;

import com.example.student.management.models.Registry;
import com.example.student.management.models.ResponseObject;
import com.example.student.management.repositories.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/registry")
public class RegistryController {
    @Autowired
    RegistryRepository registryRepository;

    @GetMapping("/getAllRegistries")
    ResponseEntity<ResponseObject> getAllRegistries() {
        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseObject(registryRepository.findAll(),
                "List of registries",ResponseObject.Status.STATUS_OK));
    }

    @PostMapping("/insertRegistry")
    ResponseEntity<ResponseObject> insert(@RequestBody Registry registry) {
        int res = registryRepository.RegisterCourse(registry.getStudentId(),registry.getCourseId());
        if (res > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(registry, "Registry success", ResponseObject.Status.STATUS_OK));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(registry, "Registry failed", ResponseObject.Status.STATUS_FAILED));

    }
}
