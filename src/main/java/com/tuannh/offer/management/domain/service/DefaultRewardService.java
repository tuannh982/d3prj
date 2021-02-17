package com.tuannh.offer.management.domain.service;

import com.tuannh.offer.management.domain.entity.reward.Reward;
import com.tuannh.offer.management.domain.repository.reward.RewardRepository;
import com.tuannh.offer.management.domain.service.interfaces.RewardService;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public class DefaultRewardService implements RewardService {
    private final RewardRepository rewardRepository;

    public DefaultRewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    @Override
    public String createReward(String rewardCode, @NonNull Date from, @NonNull Date to) { // reward must be time-bounded
        rewardRepository.save(
                new Reward(rewardCode, from, to, false)
        );
        return rewardCode;
    }

    @Override
    public List<Reward> getActiveRewards(String... rewardCodes) {
        return rewardRepository.getActiveRewardsFromList(rewardCodes);
    }
}
