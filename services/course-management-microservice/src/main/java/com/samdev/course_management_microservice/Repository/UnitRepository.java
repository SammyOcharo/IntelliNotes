package com.samdev.course_management_microservice.Repository;

import com.samdev.course_management_microservice.Entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    boolean existsByUnitCode(String unitCode);
}
