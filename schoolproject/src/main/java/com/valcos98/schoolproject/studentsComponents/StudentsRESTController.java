package com.valcos98.schoolproject.studentsComponents;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.valcos98.schoolproject.groupsComponents.GroupModel;
import com.valcos98.schoolproject.groupsComponents.GroupRepository;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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
        StudentModel student = getStudentbyId(id);
        if (!student.equals(null)) {
            return ResponseEntity.ok(student);
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
        GroupModel group = getGroupById(groupId);
        if (!group.equals(null)) {
            student.setGroup(group);
            StudentModel savedStudent = studentsRepository.save(student);
            group.getStudents().add(savedStudent);
            groupRepository.save(group);
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
    
    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putStudent(
        @PathVariable Long requestedId, 
        @RequestBody StudentModel studentUpdate) {
        StudentModel student = getStudentbyId(requestedId);
        if (!student.equals(null)) {
            student.setNames(studentUpdate.getNames());
            student.setMiddleName(studentUpdate.getMiddleName());
            student.setLastName(studentUpdate.getLastName());
            studentsRepository.save(student);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{requestedId}")
    private ResponseEntity<Void> deleteStudent(@PathVariable Long requestedId){
        if (requestedId != null && studentsRepository.existsById(requestedId)) {
            studentsRepository.deleteById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private StudentModel getStudentbyId(Long id){
        if (id != null) {
            return studentsRepository.findById(id).get();
        }
        return null;
    }

    private GroupModel getGroupById(Long id){
        if(id != null)
            return groupRepository.findById(id).get();
        return null;
    }
}
