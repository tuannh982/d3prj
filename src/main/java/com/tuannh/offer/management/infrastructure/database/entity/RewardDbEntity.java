package com.tuannh.offer.management.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "rewards")
public class RewardDbEntity {
    @Id
    @Column(name = "reward_code")
    private String rewardCode;
    @Column(name = "reward_effective_from")
    private Date rewardEffectiveFrom;
    @Column(name = "reward_effective_to")
    private Date rewardEffectiveTo;
    @Column(name = "used")
    private boolean used;
}
