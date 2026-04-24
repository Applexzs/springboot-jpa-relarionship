package com.applexzs.springboot.jpa.repositories;

import com.applexzs.springboot.jpa.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<Course, Long> {
}
