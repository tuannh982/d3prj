package com.tuannh.offer.management.infrastructure.database.repository;

import com.tuannh.offer.management.infrastructure.database.entity.RewardDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardDbEntityRepository extends JpaRepository<RewardDbEntity, String> {
    List<RewardDbEntity> getAllByRewardCodeIn(List<String> codes);
    Integer countByRewardCodeIn(List<String> codes);
}
