insert into customers
values
       ('1', '{"age" : 20,"gender": "male"}'),
       ('2', '{"age" : 22,"gender": "female"}'),
       ('3', '{"age" : 24,"gender": "male"}'),
       ('4', '{"age" : 25,"gender": "female"}'),
       ('5', '{"age" : 26,"gender": "male"}'),
       ('6', '{"age" : 27,"gender": "female"}'),
       ('7', '{"age" : 28,"gender": "male"}'),
       ('8', '{"age" : 29,"gender": "female"}'),
       ('9', '{"age" : 30,"gender": "male"}'),
       ('10', '{"age" : 31,"gender": "female"}'),
       ('11', '{"age" : 32,"gender": "male"}');

insert into partners
values  ('tiki'), ('lazada'), ('shopee');

insert into staffs
values  ('staff_1'), ('staff_2'), ('staff_3'), ('staff_4');

insert into transaction_policies(policy_id, spec)
values
(
    'policy#1',
    '{
        "cls": "com.tuannh.offer.management.domain.policy.event.transaction.fraud.FraudPolicy",
        "argc": 1,
        "classes": ["com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition"],
        "args": [{
            "cls": "com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition.UserBlackListCondition",
            "argc": 1,
            "classes": ["java.util.List"],
            "args": [["1","2","3","5","8"]]
        }]
    }'
),
(
    'policy#2',
    '{
        "cls": "com.tuannh.offer.management.domain.policy.event.transaction.freqcap.FreqCapPolicy",
        "argc": 2,
        "classes": ["int", "int"],
        "args": [5, 2]
    }'
),
(
    'policy#3',
    '{
        "cls": "com.tuannh.offer.management.domain.policy.event.transaction.chain.ChainPolicy",
        "argc": 2,
        "classes": [
            "com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy",
            "com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy"
        ],
        "args": [{
            "cls": "com.tuannh.offer.management.domain.policy.event.transaction.fraud.FraudPolicy",
            "argc": 2,
            "classes": [
                "com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition",
                "com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition"
            ],
            "args": [{
                "cls": "com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition.UserBlackListCondition",
                "argc": 1,
                "classes": ["java.util.List"],
                "args": [["1","2","3"]]
            },{
                "cls": "com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition.EventBlackListCondition",
                "argc": 1,
                "classes": ["java.util.List"],
                "args": [["e0", "e1"]]
            }]
        }, {
            "cls": "com.tuannh.offer.management.domain.policy.event.transaction.fraud.FraudPolicy",
            "argc": 2,
            "classes": [
                "com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition",
                "com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition"
            ],
            "args": [{
                "cls": "com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition.UserBlackListCondition",
                "argc": 1,
                "classes": ["java.util.List"],
                "args": [["5", "8"]]
            },{
                "cls": "com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition.EventBlackListCondition",
                "argc": 1,
                "classes": ["java.util.List"],
                "args": [["e99"]]
            }]
        }]
    }'
),
(
    'policy#4',
    '{
        "cls": "com.tuannh.offer.management.domain.policy.event.transaction.demographic.DemographicPolicy",
        "argc": 1,
        "classes": ["com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition"],
        "args": [{
            "cls": "com.tuannh.offer.management.domain.policy.event.transaction.demographic.condition.CustomerPropertiesCondition",
            "argc": 1,
            "classes": ["java.util.Map"],
            "args": [{
                "gender": {"marker": "condition", "operator": "=", "expectedValue": "male"},
                "age": {"marker": "condition", "operator": ">=", "expectedValue": 25}
            }]
        }]
    }'
);
