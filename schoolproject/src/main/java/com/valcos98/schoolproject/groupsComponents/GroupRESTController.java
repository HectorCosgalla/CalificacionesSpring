package com.valcos98.schoolproject.groupsComponents;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.valcos98.schoolproject.courseComponents.CourseModel;
import com.valcos98.schoolproject.courseComponents.CourseRepository;
import com.valcos98.schoolproject.generalComponents.PublicUtilities;
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
        GroupModel group = PublicUtilities.getModelObjectById(id, groupRepository);
        if (!group.equals(null)) {
            return ResponseEntity.ok(group);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<Void> createANewGroup(
        @RequestParam(value = "courseId", required = true) String coursesIds, 
        @RequestBody GroupModel group,
        UriComponentsBuilder ucb
    ){
        String[] listOfCoursesIds = coursesIds.split(" ");
        List<CourseModel> listOfCourses = new ArrayList<>();
        for (String courseId : listOfCoursesIds) {
            CourseModel course = PublicUtilities.getModelObjectById(Long.parseLong(courseId), courseRepository);
            listOfCourses.add(course);
        }
        GroupModel newGroup = new GroupModel(group.getLetter());
        newGroup.setCourses(listOfCourses);
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
