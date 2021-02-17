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
@Table(name = "programs")
public class ProgramDbEntity {
    @Id
    @Column(name = "program_name")
    private String programName;
    @Column(name = "partner_code")
    private String partnerCode;
    @Column(name = "program_effective_from")
    private Date programEffectiveFrom;
    @Column(name = "program_effective_to")
    private Date programEffectiveTo;
    @Column(name = "max_reward_per_user")
    private Integer maxRewardPerUser;
    @Column(name = "status")
    private String status;
}
