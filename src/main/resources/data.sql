insert into customers
values  ('1'), ('2'), ('3'), ('4'), ('5'),
('6'), ('7'), ('8'), ('9'), ('10'), ('11');

insert into partners
values  ('tiki'), ('lazada'), ('shopee');

insert into staffs
values  ('staff_1'), ('staff_2'), ('staff_3'), ('staff_4');

insert into transaction_policies(policy_id, policy_name, n_arguments, arguments)
values  ('policy#1', 'fraud', 1, '[
                                      {
                                          "conditionName": "user_condition",
                                          "argc": 1,
                                          "args": [["1", "2", "3", "5", "8"]]
                                      }
                                  ]'),
        ('policy#2', 'freq_cap', 2, '[5, 2]'),
        ('policy#3', 'chain', 2, '[
                                      {
                                          "policyName": "fraud",
                                          "argc": 2,
                                          "args": [
                                              {
                                                  "conditionName": "user_condition",
                                                  "argc": 1,
                                                  "args": [["1", "2", "3"]]
                                              },
                                              {
                                                  "conditionName": "event_condition",
                                                  "argc": 1,
                                                  "args": [["e0", "e1"]]
                                              }
                                          ]
                                      }, {
                                          "policyName": "fraud",
                                          "argc": 2,
                                          "args": [
                                              {
                                                  "conditionName": "user_condition",
                                                  "argc": 1,
                                                  "args": [["5", "8"]]
                                              },
                                              {
                                                  "conditionName": "event_condition",
                                                  "argc": 1,
                                                  "args": [["e99"]]
                                              }
                                          ]
                                      }
                                  ]'),