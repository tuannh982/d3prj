package com.tuannh.offer.management.infrastructure.database.entity;

import com.tuannh.offer.management.domain.entity.reward.Reward;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "programs")
@TypeDef(name = "json", typeClass = JsonStringType.class)
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
    @Column(name = "policy_id")
    private String policyId;
    @Column(name = "max_reward_per_user")
    private Integer maxRewardPerUser;
    @Column(name = "reward_list")
    @Type(type = "json")
    private List<String> rewardList;
    @Column(name = "status")
    private String status;
}
