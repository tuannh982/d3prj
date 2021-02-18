package com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.NonNull;

import java.util.List;

public class UserBlackListCondition extends TransactionEventPolicyCondition {
    private final List<String> bannedList;

    public UserBlackListCondition(List<String> bannedList) {
        super(1, new Object[] {bannedList}, new Class[] {List.class});
        this.bannedList = bannedList;
    }

    public UserBlackListCondition(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        super(argc, args, argsType);
        this.bannedList = (List<String>) args[0];
    }

    @Override
    public boolean handle(TransactionEvent data) {
        return !bannedList.contains(data.getUser().getUserId());
    }
}
