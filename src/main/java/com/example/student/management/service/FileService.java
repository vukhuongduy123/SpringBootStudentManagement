package com.example.student.management.service;

import com.example.student.management.models.Student;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileService {
    final String INPUT_PATH = "E:\\Self Study Course\\java\\management\\src\\main\\resources\\templates\\excel-report-template\\student_template.xls";
    final String OUTPUT_PATH = "E:\\Self Study Course\\java\\management\\src\\main\\resources\\templates\\excel-report-template\\target\\output_student_template.xls";
    final String UPLOAD_PATH = "E:\\Self Study Course\\java\\management\\src\\main\\resources\\templates\\upload";
    public boolean exportStudents(List<Student> students) {
        try {
            InputStream is = new FileInputStream(INPUT_PATH);
            OutputStream os = new FileOutputStream(OUTPUT_PATH);

            Context context = new Context();
            context.putVar("students", students);
            JxlsHelper.getInstance().processTemplate(is, os, context);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Autowired
    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public boolean saveFile(String fileName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get(UPLOAD_PATH);
        if (!Files.exists(uploadPath))
            return false;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return true;

    }
}
