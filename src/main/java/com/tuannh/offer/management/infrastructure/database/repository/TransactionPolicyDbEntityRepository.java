package com.tuannh.offer.management.infrastructure.database.repository;

import com.tuannh.offer.management.infrastructure.database.entity.TransactionPolicyDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionPolicyDbEntityRepository extends JpaRepository<TransactionPolicyDbEntity, String> {
}
