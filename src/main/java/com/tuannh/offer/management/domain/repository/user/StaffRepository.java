package com.tuannh.offer.management.domain.repository.user;

import com.tuannh.offer.management.domain.entity.user.Staff;

public interface StaffRepository {
    Staff get(String staffId);
}
