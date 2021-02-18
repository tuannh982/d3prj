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
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class CustomerDbEntity {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "demographic_data")
    @Type(type = "json")
    private Map<String, Object> demographicData;
}
