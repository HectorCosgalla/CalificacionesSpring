package com.valcos98.schoolproject.courseComponents;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<CourseModel,Long>{
    @Query(value = "SELECT * FROM materias WHERE materias.semestre_id = :semestre_id", nativeQuery = true)
    List<CourseModel> findBySemester(Long semestre_id);
}
