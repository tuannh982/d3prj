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

insert into transaction_policies(policy_id, policy_name, n_arguments, arguments)
values  ('policy#1', 'fraud', 1, '[{"conditionName": "user_condition","argc": 1,"args": [["1", "2", "3", "5", "8"]]}]'),
        ('policy#2', 'freq_cap', 2, '[5, 2]'),
        ('policy#3', 'chain', 2, '[
                                      {
                                          "policyName": "fraud","argc": 2,
                                          "args": [
                                              {"conditionName": "user_condition","argc": 1,"args": [["1", "2", "3"]]},
                                              {"conditionName": "event_condition","argc": 1,"args": [["e0", "e1"]]}
                                          ]
                                      }, {
                                          "policyName": "fraud","argc": 2,
                                          "args": [
                                              {"conditionName": "user_condition","argc": 1,"args": [["5", "8"]]},
                                              {"conditionName": "event_condition","argc": 1,"args": [["e99"]]}
                                          ]
                                      }
                                  ]'),
       ('policy#4', 'demographic', 1, '[{
                                            "conditionName": "customer_props", "argc": 1,
                                            "args": [{
                                                "gender": {"operator": "eq", "expectedValue": "male"},
                                                "age": {"operator": "ge", "expectedValue": 25}
                                            }]
                                        }]');

