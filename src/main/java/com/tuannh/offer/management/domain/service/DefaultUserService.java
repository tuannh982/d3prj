package com.tuannh.offer.management.domain.service;

import com.tuannh.offer.management.domain.entity.user.Customer;
import com.tuannh.offer.management.domain.entity.user.Partner;
import com.tuannh.offer.management.domain.entity.user.Staff;
import com.tuannh.offer.management.domain.repository.user.CustomerRepository;
import com.tuannh.offer.management.domain.repository.user.PartnerRepository;
import com.tuannh.offer.management.domain.repository.user.StaffRepository;
import com.tuannh.offer.management.domain.service.interfaces.UserService;

public class DefaultUserService implements UserService {
    private final CustomerRepository customerRepository;
    private final PartnerRepository partnerRepository;
    private final StaffRepository staffRepository;

    public DefaultUserService(CustomerRepository customerRepository, PartnerRepository partnerRepository, StaffRepository staffRepository) {
        this.customerRepository = customerRepository;
        this.partnerRepository = partnerRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public Customer getCustomer(String userId) {
        return customerRepository.get(userId);
    }

    @Override
    public Partner getPartner(String partnerCode) {
        return partnerRepository.get(partnerCode);
    }

    @Override
    public Staff getStaff(String staffId) {
        return staffRepository.get(staffId);
    }
}
