package com.tuannh.offer.management.domain.entity.reward;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

/*
reward_code: Mã voucher
reward_effective_from: Thời điểm voucher bắt đầu có hiệu lực (epoch second)
reward_effective_to: Thời điểm voucher hết hiệu lực (epoch second)
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Reward {
    private final String rewardCode;
    private final Date rewardEffectiveFrom;
    private final Date rewardEffectiveTo;
    private final boolean used;
}
