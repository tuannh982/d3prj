package com.tuannh.offer.management.infrastructure.database.entity;

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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction_policies")
//@TypeDef(name = "json", typeClass = JsonStringType.class)
public class TransactionPolicyDbEntity {
    @Id
    @Column(name = "policy_id")
    private String policyId;
    @Column(name = "policy_name")
    private String policyName;
    @Column(name = "n_arguments")
    private Integer numberOfArguments;
    @Column(name = "arguments", columnDefinition = "text")
//    @Type(type = "json")
    private String arguments;
}
