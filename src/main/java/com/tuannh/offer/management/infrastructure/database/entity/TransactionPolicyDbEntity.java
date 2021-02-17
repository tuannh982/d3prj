package com.tuannh.offer.management.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

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
    @Column(name = "policy_class")
    private String policyClass;
    @Column(name = "n_arguments")
    private Integer numberOfArguments;
    @Column(name = "arguments")
    @Type(type = "json")
    private List<String> arguments;
}
