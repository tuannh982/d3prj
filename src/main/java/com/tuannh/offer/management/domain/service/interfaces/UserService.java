package com.tuannh.offer.management.domain.service.interfaces;

import com.tuannh.offer.management.domain.entity.user.Customer;
import com.tuannh.offer.management.domain.entity.user.Partner;
import com.tuannh.offer.management.domain.entity.user.Staff;

public interface UserService {
    Customer getCustomer(String userId);
    Partner getPartner(String partner);
    Staff getStaff(String staffId);
}
