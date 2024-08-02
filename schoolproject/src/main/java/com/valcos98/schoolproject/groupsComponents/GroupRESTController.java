package com.valcos98.schoolproject.groupsComponents;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.opencsv.exceptions.CsvValidationException;
import com.valcos98.schoolproject.courseComponents.CourseModel;
import com.valcos98.schoolproject.courseComponents.CourseRepository;
import com.valcos98.schoolproject.generalComponents.CsvUtilities;
import com.valcos98.schoolproject.generalComponents.PublicUtilities;
import com.valcos98.schoolproject.semesterComponents.SemesterModel;
import com.valcos98.schoolproject.semesterComponents.SemesterRepository;
import com.valcos98.schoolproject.studentsComponents.StudentModel;
import com.valcos98.schoolproject.studentsComponents.StudentsRepository;

@RestController
@RequestMapping("/grupos")
public class GroupRESTController {
    SemesterRepository semesterRepository;
    CourseRepository courseRepository;
    GroupRepository groupRepository;
    StudentsRepository studentsRepository;

    public GroupRESTController(
            SemesterRepository semesterRepository,
            CourseRepository courseRepository,
            GroupRepository groupRepository,
            StudentsRepository studentsRepository
        ){
        this.semesterRepository = semesterRepository;
        this.courseRepository = courseRepository;
        this.groupRepository = groupRepository;
        this.studentsRepository = studentsRepository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<GroupModel> findById(@PathVariable Long id){
        GroupModel group = PublicUtilities.getModelObjectById(id, groupRepository);
        if (!group.equals(null)) {
            return ResponseEntity.ok(group);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "",consumes = {"multipart/form-data"})
    private ResponseEntity<Void> createANewGroup(
        @RequestPart("file") MultipartFile csvStudents,
        @RequestParam(value = "semester", required = true) String semester, 
        @RequestPart("letter") String group,
        UriComponentsBuilder ucb
    ) throws CsvValidationException, IOException{

        SemesterModel semesterModel = semesterRepository.findByName(semester);
        List<CourseModel> listOfCourses = courseRepository.findBySemester(semesterModel.getId());
        List<StudentModel> listOfStudents = studentsRepository.saveAll(CsvUtilities.csvToStudentsList(csvStudents));
        GroupModel newGroup = new GroupModel(group);

        newGroup.setCourses(listOfCourses);
        newGroup.setStudents(listOfStudents);
        newGroup.setSemester(semesterModel);
        GroupModel savedGroup = groupRepository.save(newGroup);

        URI locationOfNewGroup = ucb
            .path("/grupos/{id}")
            .buildAndExpand(savedGroup.getId())
            .toUri();
            return ResponseEntity.created(locationOfNewGroup).build();
    }

    @GetMapping
    private ResponseEntity<List<GroupModel>> findAll(Pageable pageable){
        Page<GroupModel> page = groupRepository.findAll(
            PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC,"letter"))
            )
        );

        return ResponseEntity.ok(page.getContent());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteAGroup(@PathVariable Long id){
        if (id != null) {
            GroupModel group = PublicUtilities.getModelObjectById(id, groupRepository);
            group.setCourses(null);
            groupRepository.save(group);
            groupRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
