package com.valcos98.schoolproject.groupsComponents;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.valcos98.schoolproject.courseComponents.CourseModel;
import com.valcos98.schoolproject.courseComponents.CourseRepository;
import com.valcos98.schoolproject.semesterComponents.SemesterRepository;

@RestController
@RequestMapping("/grupos")
public class GroupRESTController {
    SemesterRepository semesterRepository;
    CourseRepository courseRepository;
    GroupRepository groupRepository;

    public GroupRESTController(SemesterRepository semesterRepository, CourseRepository courseRepository, GroupRepository groupRepository){
        this.semesterRepository = semesterRepository;
        this.courseRepository = courseRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<GroupModel> findById(@PathVariable Long id){
        Optional<GroupModel> group = groupRepository.findById(id);
        
        for (CourseModel course : group.get().getCourses()) {
            System.out.println(course.getCourseName());
        }
        if (group.isPresent()) {
            return ResponseEntity.ok(group.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    private ResponseEntity<Void> createANewGroup(
        @RequestParam(value = "courseId", required = true) Long courseId, 
        @RequestBody GroupModel group,
        UriComponentsBuilder ucb
    ){
        GroupModel newGroup = new GroupModel(group.getLetter());
        Optional<CourseModel> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            GroupModel savedGroup = groupRepository.save(newGroup);
            course.get().getGroups().add(savedGroup);
            courseRepository.save(course.get());
            URI locationOfNewGroup = ucb
                    .path("/grupos/{id}")
                    .buildAndExpand(savedGroup.getId())
                    .toUri();

            return ResponseEntity.created(locationOfNewGroup).build();
        } else {
            return ResponseEntity.notFound().build();
        }

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
}
