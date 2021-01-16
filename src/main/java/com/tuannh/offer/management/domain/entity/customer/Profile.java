package com.tuannh.offer.management.domain.entity.customer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Profile {
    private String id;
    private String name;
    private Integer age;
}
