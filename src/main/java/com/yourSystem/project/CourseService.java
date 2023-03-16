package com.yourSystem.project;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDTO createCourse(CourseDTO course) {
        return courseRepository.save(course);
    }

    public CourseDTO mapToCourseDTO(CourseDTO course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setContent(course.getContent());
        courseDTO.setMaxNumberOfParticipants(course.getMaxNumberOfParticipants());
        courseDTO.setUrl(course.getUrl());
        return courseDTO;
    }

    public CourseDTO mapToCourse(CourseDTO courseDTO) {
        CourseDTO course = new CourseDTO();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setContent(courseDTO.getContent());
        course.setMaxNumberOfParticipants(courseDTO.getMaxNumberOfParticipants());
        course.setUrl(courseDTO.getUrl());
        return course;
    }

    public CourseDTO getCourseById(Long id) {
        Optional<CourseDTO> courseOptional = courseRepository.findById(id);
        CourseDTO course = courseOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        return mapToCourseDTO(course);
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courses = courseRepository.findAll();
        return courses.stream().map(this::mapToCourseDTO).collect(Collectors.toList());
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        CourseDTO existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) {
            return null;
        } else {
            CourseDTO course = mapToCourse(courseDTO);
            course.setId(id);
            course = courseRepository.save(course);
            return mapToCourseDTO(course);
        }
    }

    public void deleteCourseDTO(Long id) {
        courseRepository.deleteById(id);

    }

    public void exportToExcel(String filePath) throws IOException {
        List<CourseDTO> courses = courseRepository.findAll();
        // create a new Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Courses");
        // create a header row for the sheet
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Content");
        headerRow.createCell(3).setCellValue("Max Number of Participants");
        headerRow.createCell(4).setCellValue("URL");
        // add course data to the sheet
        int rowNum = 1;
        for (CourseDTO course : courses) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(course.getId());
            row.createCell(1).setCellValue(course.getName());
            row.createCell(2).setCellValue(course.getContent());
            row.createCell(3).setCellValue(course.getMaxNumberOfParticipants());
            row.createCell(4).setCellValue(course.getUrl());
        }
        // write the workbook to a file
        OutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        outputStream.close();
    }

}
