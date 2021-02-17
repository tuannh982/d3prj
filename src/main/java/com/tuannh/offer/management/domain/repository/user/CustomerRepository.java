package com.tuannh.offer.management.domain.repository.user;

import com.tuannh.offer.management.domain.entity.user.Customer;

public interface CustomerRepository {
    Customer get(String userId);
}
