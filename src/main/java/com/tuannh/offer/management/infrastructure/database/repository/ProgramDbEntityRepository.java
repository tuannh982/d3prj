package com.tuannh.offer.management.infrastructure.database.repository;

import com.tuannh.offer.management.infrastructure.database.entity.ProgramDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramDbEntityRepository extends JpaRepository<ProgramDbEntity, String> {
    @Query("update ProgramDbEntity set status = :newStatus where programName = :programName")
    void updateStatus(@Param("programName") String programName, @Param("newStatus") String newStatus);
}
