package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.commons.condition.Condition;
import com.tuannh.offer.management.commons.condition.Operator;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.chain.ChainPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.demographic.DemographicPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.demographic.condition.CustomerPropertiesCondition;
import com.tuannh.offer.management.domain.policy.event.transaction.fraud.FraudPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition.EventBlackListCondition;
import com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition.UserBlackListCondition;
import com.tuannh.offer.management.domain.policy.event.transaction.freqcap.FreqCapPolicy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("java:S2925")
class TransactionPolicyFactoryTest {
    @Test
    void createFraudPolicyTest() {
        // mock policy
        List<String> bannedList = Arrays.asList(
                "1", "2", "3", "5", "8"
        );
        TransactionEventPolicy policy = new FraudPolicy(new TransactionEventPolicyCondition[] {
                new UserBlackListCondition(bannedList)
        });
        // mock data
        TransactionEvent[] events = new TransactionEvent[] {
                new TransactionEvent("e1", "1", "TEST0", new Date()),
                new TransactionEvent("e1", "2", "TEST1", new Date()),
                new TransactionEvent("e1", "3", "TEST2", new Date()),
                new TransactionEvent("e1", "4", "TEST3", new Date()),
                new TransactionEvent("e1", "5", "TEST4", new Date()),
                new TransactionEvent("e1", "6", "TEST4", new Date()),
                new TransactionEvent("e1", "7", "TEST4", new Date()),
                new TransactionEvent("e1", "8", "TEST4", new Date()),
                new TransactionEvent("e1", "9", "TEST4", new Date()),
                new TransactionEvent("e1", "10", "TEST4", new Date())
        };
        for (TransactionEvent event : events) {
            final boolean b = policy.handle(event);
            final boolean bx = !bannedList.contains(event.getUser().getUserId());
            assertEquals(b, bx, String.format("wrong evaluation on user %s%n", event.getUser().getUserId()));
        }
    }

    @Test
    void createFreqCapPolicyTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        // policy
        TransactionEventPolicy policy = new FreqCapPolicy(2, new Object[]{5, 2});
        // data
        long baseTs = System.currentTimeMillis();
        long baseTsAndHalf = baseTs + 500;
        long baseTsPlusOne = baseTs + 1000;
        long baseTsPlusOneAndHalf = baseTs + 1500;
        long baseTsPlusTwoAndHalf = baseTs + 2500;
        TransactionEvent[] events = new TransactionEvent[] {
                new TransactionEvent("e1", "1", "TEST0", new Date(baseTsAndHalf)),
                new TransactionEvent("e1", "3", "TEST2", new Date(baseTsPlusOne)),
                new TransactionEvent("e1", "6", "TEST4", new Date(baseTsPlusOneAndHalf)),
                new TransactionEvent("e1", "9", "TEST4", new Date(baseTsPlusTwoAndHalf)),
        };
        // process
        Thread.sleep(500);
        boolean[] b = new boolean[] {
                policy.handle(events[0]),
                policy.handle(events[0]),
        };
        assertTrue(b[0]);
        assertTrue(b[1]);
        Thread.sleep(500);
        b = new boolean[] {
                policy.handle(events[1]),
        };
        assertTrue(b[0]);
        Thread.sleep(500);
        b = new boolean[] {
                policy.handle(events[2]),
                policy.handle(events[2]),
                policy.handle(events[2]),
        };
        assertTrue(b[0]);
        assertTrue(b[1]);
        assertFalse(b[2]);
        Thread.sleep(1000);
        b = new boolean[] {
                policy.handle(events[3]),
                policy.handle(events[3]),
        };
        assertTrue(b[0]);
        assertFalse(b[1]);
    }

    @Test
    void createChainPolicyTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // mock policy
        TransactionEventPolicy policy = new ChainPolicy(new TransactionEventPolicy[] {
                new FraudPolicy(
                        new TransactionEventPolicyCondition[] {
                                new UserBlackListCondition(Arrays.asList("1", "2", "3")),
                                new EventBlackListCondition(Arrays.asList("e0", "e1"))
                        }
                ),
                new FraudPolicy(
                        new TransactionEventPolicyCondition[] {
                                new UserBlackListCondition(Arrays.asList("5", "8")),
                                new EventBlackListCondition(Collections.singletonList("e99"))
                        }
                ),
        });
        // mock data
        TransactionEvent[] events = new TransactionEvent[] {
                new TransactionEvent("e0", "1", "TEST4", new Date()),
                new TransactionEvent("e1", "2", "TEST1", new Date()),
                new TransactionEvent("e2", "3", "TEST6", new Date()),
                new TransactionEvent("e3", "4", "TEST4", new Date()),
                new TransactionEvent("e4", "5", "TEST2", new Date()),
                new TransactionEvent("e1", "6", "TEST0", new Date()),
                new TransactionEvent("e5", "7", "TEST0", new Date()),
                new TransactionEvent("e4", "8", "TEST4", new Date()),
                new TransactionEvent("e8", "9", "TEST4", new Date()),
                new TransactionEvent("e0", "10", "TEST0", new Date()),
                new TransactionEvent("e99", "102", "TEST0", new Date())
        };
        List<String> bannedUsers = Arrays.asList("1", "2", "3", "5", "8");
        List<String> bannedEvents = Arrays.asList("e0", "e1", "e99");
        // process
        for (TransactionEvent event : events) {
            final boolean b = policy.handle(event);
            final boolean bx = !bannedUsers.contains(event.getUser().getUserId());
            final boolean by = !bannedEvents.contains(event.getEventName());
            assertEquals(b, bx && by, String.format("wrong evaluation on user %s%n", event.getUser().getUserId()));
        }
    }

    @SuppressWarnings("rawtypes")
    @Test
    void createDemographicPolicyTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<String, Condition> props = new HashMap<>();
        props.put("gender", new Condition(Operator.EQ, "male"));
        props.put("age", new Condition(Operator.GE, 25));
        // is male and age >= 25
        TransactionEventPolicy policy = new DemographicPolicy(
                new TransactionEventPolicyCondition[] {
                        new CustomerPropertiesCondition(props)
                }
        );
        // mock data
        TransactionEvent[] events = new TransactionEvent[] {
                new TransactionEvent("e0", "1", "TEST4", new Date()),
                new TransactionEvent("e1", "2", "TEST1", new Date()),
                new TransactionEvent("e2", "3", "TEST6", new Date()),
                new TransactionEvent("e3", "4", "TEST4", new Date()),
                new TransactionEvent("e3", "4", "TEST4", new Date()),
                new TransactionEvent("e3", "4", "TEST4", new Date()),
        };
        // force update data
        for (TransactionEvent event : events) {
            event.getUser().setDemographicData(new HashMap<>());
        }
        events[0].getUser().getDemographicData().put("gender", "male");
        events[1].getUser().getDemographicData().put("gender", "female");
        events[2].getUser().getDemographicData().put("gender", "male");
        events[3].getUser().getDemographicData().put("gender", "male");
        events[4].getUser().getDemographicData().put("gender", "male");
        events[5].getUser().getDemographicData().put("gender", "male");

        events[0].getUser().getDemographicData().put("age", 24);
        events[1].getUser().getDemographicData().put("age", 25);
        events[2].getUser().getDemographicData().put("age", 26);
        events[3].getUser().getDemographicData().put("age", 27);
        events[4].getUser().getDemographicData().put("age", 28);
        events[5].getUser().getDemographicData().put("age", 21);
        for (TransactionEvent event : events) {
            final boolean b = policy.handle(event);
            assertEquals(
                    b,
                    "male".equals(event.getUser().getDemographicData().get("gender")) &&
                            25 <= (Integer) event.getUser().getDemographicData().get("age"),
                    String.format("wrong evaluation on user %s%n", event.getUser().getUserId())
            );
        }
     }
}
