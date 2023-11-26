package com.tiktak.backend.repository;

import com.tiktak.backend.model.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    @Query("SELECT i FROM Inspection i WHERE i.carId = :carId ORDER BY i.createdAt DESC limit 1")
    Optional<Inspection> findLatestByCarId(String carId);
}
