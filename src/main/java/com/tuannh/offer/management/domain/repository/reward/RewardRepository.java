package com.tuannh.offer.management.domain.repository.reward;

import com.tuannh.offer.management.domain.entity.reward.Reward;

import java.util.Date;
import java.util.List;

public interface RewardRepository {
    void save(Reward reward);
    Reward get(String rewardCode);
    List<Reward> getRewardsWithTimeRange(Date from, Date to);
    List<Reward> getRewardsWithTimeRange(Date from, Date to, boolean used);
    List<Reward> getActiveRewardsFromList(String... rewardCodes);

    default List<Reward> getUnusedRewardsWithTimeRange(Date from, Date to) {
        return getRewardsWithTimeRange(from, to, false);
    }

    default List<Reward> getUsedRewardsWithTimeRange(Date from, Date to) {
        return getRewardsWithTimeRange(from, to, true);
    }
}
