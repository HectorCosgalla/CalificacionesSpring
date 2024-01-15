package com.valcos98.schoolproject.studentsComponents;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.valcos98.schoolproject.groupsComponents.GroupModel;
import com.valcos98.schoolproject.groupsComponents.GroupRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/alumnos")
public class StudentsRESTController {

    private StudentsRepository studentsRepository;
    private GroupRepository groupRepository;

    public StudentsRESTController(StudentsRepository studentsRepository, GroupRepository groupRepository){
        this.studentsRepository = studentsRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<StudentModel> findById(@PathVariable Long id) {
        Optional<StudentModel> student = studentsRepository.findById(id);

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createAStudent(
        @RequestParam(value = "groupId",required = true) Long groupId, 
        @RequestBody StudentModel student, 
        UriComponentsBuilder ucb
    ) {
        StudentModel newStudent = new StudentModel(student.getNames(), student.getMiddleName(), student.getLastName());
        Optional<GroupModel> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            newStudent.setGroup(group.get());
            StudentModel savedStudent = studentsRepository.save(newStudent);
            group.get().getStudents().add(savedStudent);
            groupRepository.save(group.get());
            URI locationOfNewStudent = ucb
                    .path("/alumnos/{id}")
                    .buildAndExpand(savedStudent.getId())
                    .toUri();
            return ResponseEntity.created(locationOfNewStudent).build();
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }

    @GetMapping
    private ResponseEntity<List<StudentModel>> findAll(Pageable pageable){
        Page<StudentModel> page = studentsRepository.findAll(
            PageRequest.of(
                pageable.getPageNumber(), 
                pageable.getPageSize(), 
                pageable.getSortOr(Sort.by(Sort.Direction.ASC,"middleName"))
                )
            );

        return ResponseEntity.ok(page.getContent());
    }
    
    
}
