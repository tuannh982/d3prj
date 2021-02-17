package com.tuannh.offer.management.domain.repository.user;

import com.tuannh.offer.management.domain.entity.user.Partner;

public interface PartnerRepository {
    Partner get(String partnerCode);
}
