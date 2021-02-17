package com.tuannh.offer.management.domain.event;

import lombok.Getter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
Thông tin giao dịch bao gồm:
    event_name: Loại giao dịch, gồm các loại sau:
        airtime: Nạp tiền topup di động
        loan: Thanh toán khoản vay
        transfer: Chuyển tiền theo số tài khoản
    properties: các thông tin của giao dịch, bao gồm:
    event_id: của giao dịch
    timestamp: Thời điểm giao dịch được thực hiện thành công, theo định dạng Epoch second.
    user_id: user id của End user.
 */
@Getter
public class TransactionEvent extends DomainEvent {
    private final String userId;
    private final Map<String, Object> properties;

    public TransactionEvent(String eventName, String userId, String eventId, Date timestamp) {
        super(eventName);
        properties = new HashMap<>();
        properties.put("timestamp", timestamp);
        properties.put("event_id", eventId);
        this.userId = userId;
    }
}
