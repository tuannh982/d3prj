package com.tuannh.offer.management.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction_policies")
public class TransactionPolicyDbEntity {
    @Id
    @Column(name = "policy_id")
    private String policyId;
    @Column(name = "spec", columnDefinition = "text")
    private String spec;
}
