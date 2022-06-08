package com.example.student.management.controller;

import com.example.student.management.models.FileUploadInfo;
import com.example.student.management.models.ResponseObject;
import com.example.student.management.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/export")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/createFile")
    ResponseEntity<ResponseObject> createFile() {
        boolean res = fileService.exportStudents(fileService.getStudentService().getAllStudents());
        return res ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("","File created", ResponseObject.Status.STATUS_OK))
                    : ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("","File cannot created", ResponseObject.Status.STATUS_FAILED));
    }

    @PostMapping("/uploadFile")
    ResponseEntity<ResponseObject> export(@RequestParam("file") MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (!fileService.saveFile(fileName, multipartFile))
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    "", "Cant save", ResponseObject.Status.STATUS_FAILED));
        FileUploadInfo fileUploadInfo = new FileUploadInfo("upload_" + fileName, "/downloadFile/" + fileName,
                multipartFile.getSize());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                fileUploadInfo, "File saved", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/downloadStudent")
    ResponseEntity<?> downloadStudent() {
        Resource resource = fileService.downloadFile(fileService.OUTPUT_PATH);
        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
