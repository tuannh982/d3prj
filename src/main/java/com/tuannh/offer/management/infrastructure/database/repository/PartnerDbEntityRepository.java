package com.tuannh.offer.management.infrastructure.database.repository;

import com.tuannh.offer.management.infrastructure.database.entity.PartnerDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerDbEntityRepository extends JpaRepository<PartnerDbEntity, String> {
}
