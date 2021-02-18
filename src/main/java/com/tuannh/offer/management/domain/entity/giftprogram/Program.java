package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.commons.fsm.FsmEntity;
import com.tuannh.offer.management.commons.fsm.FsmState;
import com.tuannh.offer.management.domain.entity.reward.Reward;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/*
Thông tin của 1 chương trình quà tặng được định nghĩa bao gồm:
    Tên chương trình (program_name)
    Mã đối tác (partner_code): Là mã của đối tác đang cung cấp chương trình quà tặng
    Thời gian bắt đầu, kết thúc của chương trình quà tặng (program_effective_from, program_effective_to)
    Điều kiện của chương trình quà tặng (Policy) có thể bao gồm các loại điều kiện sau
    Nhân khẩu học: Tuổi/Giới tính (age/gender)
    Loại giao dịch: Chọn 1 hoặc nhiều loại giao dịch.
    Số quà tặng tối đa trong chương trình cho 1 khách hàng (max_reward_per_user)
    Danh sách quà tặng(reward_list)
 */
@Getter
@AllArgsConstructor
public class Program implements FsmEntity {
    private final String programName;
    private final String partnerCode;
    private final Date programEffectiveFrom;
    private final Date programEffectiveTo;
    private final ProgramPolicy policy;
    private final Integer maxRewardPerUser;
    private final List<Reward> rewardList;
    // state
    private ProgramFsmState currentStatus;

    @Override
    public ProgramFsmState state() {
        return currentStatus;
    }

    @Override
    public void changeState(FsmState newState) {
        currentStatus = (ProgramFsmState) newState;
    }
}
