package com.tuannh.offer.management.infrastructure.database.repository;

import com.tuannh.offer.management.infrastructure.database.entity.ProgramDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDbEntityRepository extends JpaRepository<ProgramDbEntity, String> {
}
