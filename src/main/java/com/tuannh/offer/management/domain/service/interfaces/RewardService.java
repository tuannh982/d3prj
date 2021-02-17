package com.tuannh.offer.management.domain.service.interfaces;

import com.tuannh.offer.management.domain.entity.reward.Reward;

import java.util.Date;
import java.util.List;

public interface RewardService {
    String createReward(String rewardCode, Date from, Date to);
    List<Reward> getActiveRewards(String... rewardCodes);
}
