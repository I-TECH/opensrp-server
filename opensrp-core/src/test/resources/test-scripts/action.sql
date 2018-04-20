--clear data
TRUNCATE TABLE core.action CASCADE;

ALTER SEQUENCE core.action_id_seq RESTART WITH 35;

ALTER SEQUENCE core.action_metadata_id_seq RESTART WITH 35;

INSERT INTO core.action (id, json) VALUES 
(1, '{"_id": "05934ae338431f28bf6793b241642462", "data": {"visitCode": "OPV 0", "completionDate": "2017-02-13"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-2879a3a80caa0ab8c599b766496b8234", "timeStamp": 1520932697689, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(2, '{"_id": "05934ae338431f28bf6793b2416433c9", "data": {"visitCode": "BCG", "completionDate": "2017-02-13"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-6136b587025c608f3d4f41731eefb157", "timeStamp": 1520932698005, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(3, '{"_id": "05934ae338431f28bf6793b241643a7e", "data": {"visitCode": "PENTA 1", "completionDate": "2017-03-27"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-2f64b198e90652c816a01b59b15d10c9", "timeStamp": 1520932698821, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(4, '{"_id": "05934ae338431f28bf6793b241644dd7", "data": {"visitCode": "OPV 1", "completionDate": "2017-03-27"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-6fda14ca820ed569269b81cc28832619", "timeStamp": 1520932699621, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(5, '{"_id": "05934ae338431f28bf6793b241645535", "data": {"visitCode": "ROTA 1", "completionDate": "2017-03-27"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-f00737fd2a6354d243c161c161a886fd", "timeStamp": 1520932700149, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(6, '{"_id": "05934ae338431f28bf6793b241645f1f", "data": {"visitCode": "PCV 1", "completionDate": "2017-03-27"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-c66dfedbaf6582dc04c209c467f8a057", "timeStamp": 1520932700973, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(7, '{"_id": "05934ae338431f28bf6793b2416473e0", "data": {"visitCode": "PENTA 2", "completionDate": "2017-04-24"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-02c3a00f308e4ce385ce558cf0d944d8", "timeStamp": 1520932701845, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(8, '{"_id": "05934ae338431f28bf6793b2416481aa", "data": {"visitCode": "OPV 2", "completionDate": "2017-04-24"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-8c95d366cfe85f1b2dbfb3ca8c80fa60", "timeStamp": 1520932702653, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(9, '{"_id": "05934ae338431f28bf6793b241648e9d", "data": {"visitCode": "ROTA 2", "completionDate": "2017-04-24"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-9ad54ee7aea405a883c41203f42b7102", "timeStamp": 1520932703185, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(10, '{"_id": "05934ae338431f28bf6793b241649473", "data": {"visitCode": "PCV 2", "completionDate": "2017-04-24"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-660bbaae08f13bd1901bf0916c85d7f1", "timeStamp": 1520932703825, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(11, '{"_id": "05934ae338431f28bf6793b24164a5d7", "data": {"visitCode": "PENTA 3", "completionDate": "2017-05-22"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-6a87e5caa57732fd5c3fef5dc5a4ba71", "timeStamp": 1520932704673, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(12, '{"_id": "05934ae338431f28bf6793b24164b4ce", "data": {"visitCode": "OPV 3", "completionDate": "2017-05-22"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-9a13ae05df3839ea97d54d42150cf294", "timeStamp": 1520932705277, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(13, '{"_id": "05934ae338431f28bf6793b24164c069", "data": {"visitCode": "PCV 3", "completionDate": "2017-05-22"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-2cab2feb95c70dcaabecb6f9eed6ddf2", "timeStamp": 1520932705889, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(14, '{"_id": "05934ae338431f28bf6793b241651160", "data": {"visitCode": "MEASLES 1", "completionDate": "2017-11-14"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-8a96e017d3f3772d1fceeda32360af03", "timeStamp": 1520933247737, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "67007c17-97bb-4732-a1b8-3a0c292b5432", "isActionActive": true}'),
(15, '{"_id": "05934ae338431f28bf6793b241663fcd", "data": {"visitCode": "BCG", "completionDate": "2016-04-13"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-14792d0f46a0883ad712b1579229e327", "timeStamp": 1520933431893, "actionType": "closeAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "86c039a2-0b68-4166-849e-f49897e3a510", "isActionActive": true}'),
(16, '{"_id": "05934ae338431f28bf6793b2417bfe8f", "data": {"startDate": "2018-02-22", "visitCode": "penta1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "PENTA 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-6356c0c4202703c768764a3475d7ce3b", "timeStamp": 1520978413525, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "469597f0-eefe-4171-afef-f7234cbb2859", "isActionActive": true}'),
(17, '{"_id": "05934ae338431f28bf6793b2417bf883", "data": {"startDate": "2018-02-22", "visitCode": "pcv1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "PCV 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-076a5315cb585348af4335f3392f3f3d", "timeStamp": 1520978413537, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "469597f0-eefe-4171-afef-f7234cbb2859", "isActionActive": true}'),
(18, '{"_id": "05934ae338431f28bf6793b2417bf3c3", "data": {"startDate": "2018-02-22", "visitCode": "rota1", "expiryDate": "2018-08-01", "alertStatus": "urgent", "scheduleName": "ROTA 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-5d69b3061e54074f4e280ed3b0b06181", "timeStamp": 1520978413541, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "469597f0-eefe-4171-afef-f7234cbb2859", "isActionActive": true}'),
(19, '{"_id": "05934ae338431f28bf6793b2417c671b", "data": {"startDate": "2018-02-22", "visitCode": "rota1", "expiryDate": "2018-08-01", "alertStatus": "urgent", "scheduleName": "ROTA 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-35d72bbd1769bd66675997ac9e79164e", "timeStamp": 1520978413737, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "aabcd2cc-c111-41c6-85e6-cb5d9e350d08", "isActionActive": true}'),
(20, '{"_id": "05934ae338431f28bf6793b2417c4bae", "data": {"startDate": "2018-01-11", "visitCode": "bcg", "expiryDate": "2019-01-01", "alertStatus": "urgent", "scheduleName": "BCG", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-232d50fcd2a7a7801633b5a0546cb753", "timeStamp": 1520978413764, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "469597f0-eefe-4171-afef-f7234cbb2859", "isActionActive": true}'),
(21, '{"_id": "05934ae338431f28bf6793b2417c4d6c", "data": {"startDate": "2018-02-22", "visitCode": "pcv1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "PCV 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-2871da12dbce3cb16e0746f30cbc26fa", "timeStamp": 1520978413764, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "aabcd2cc-c111-41c6-85e6-cb5d9e350d08", "isActionActive": true}'),
(22, '{"_id": "05934ae338431f28bf6793b2417c5aae", "data": {"startDate": "2018-02-22", "visitCode": "opv1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "OPV 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-9c72f8f01fd21ae36e84a2def4db8732", "timeStamp": 1520978413765, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "469597f0-eefe-4171-afef-f7234cbb2859", "isActionActive": true}'),
(23, '{"_id": "05934ae338431f28bf6793b2417c705e", "data": {"startDate": "2018-02-22", "visitCode": "opv1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "OPV 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-f72851fdbaac0f4f091054bbf60b8ed3", "timeStamp": 1520978413880, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "aabcd2cc-c111-41c6-85e6-cb5d9e350d08", "isActionActive": true}'),
(24, '{"_id": "05934ae338431f28bf6793b2417c98f9", "data": {"startDate": "2018-02-22", "visitCode": "penta1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "PENTA 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-fc9c73741eee67f6b71c84333ad6eb25", "timeStamp": 1520978413884, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "aabcd2cc-c111-41c6-85e6-cb5d9e350d08", "isActionActive": true}'),
(25, '{"_id": "05934ae338431f28bf6793b2417c7d94", "data": {"startDate": "2018-01-11", "visitCode": "bcg", "expiryDate": "2019-01-01", "alertStatus": "urgent", "scheduleName": "BCG", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-ea88434f62397611c8e794eedbca74e4", "timeStamp": 1520978413894, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "aabcd2cc-c111-41c6-85e6-cb5d9e350d08", "isActionActive": true}'),
(26, '{"_id": "05934ae338431f28bf6793b2417ca6cd", "data": {"startDate": "2018-02-22", "visitCode": "pcv1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "PCV 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-6db5f0e05e5e69aa5f33429f6cdc75a7", "timeStamp": 1520978413945, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "4a2a4ad9-cd29-47cb-bdb9-5b617a73b898", "isActionActive": true}'),
(27, '{"_id": "05934ae338431f28bf6793b2417d0103", "data": {"startDate": "2018-02-22", "visitCode": "rota1", "expiryDate": "2018-08-01", "alertStatus": "urgent", "scheduleName": "ROTA 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-a28be37e4572a5e77a92461c76d0885e", "timeStamp": 1520978414164, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "4a2a4ad9-cd29-47cb-bdb9-5b617a73b898", "isActionActive": true}'),
(28, '{"_id": "05934ae338431f28bf6793b2417d1390", "data": {"startDate": "2018-02-22", "visitCode": "opv1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "OPV 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-8da27b44f49e08574f5b8a7ad746a88c", "timeStamp": 1520978414184, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "4a2a4ad9-cd29-47cb-bdb9-5b617a73b898", "isActionActive": true}'),
(29, '{"_id": "05934ae338431f28bf6793b2417d0b1c", "data": {"startDate": "2018-02-22", "visitCode": "penta1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "PENTA 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-87165e743ef46e7bfbdfea5d9e81d049", "timeStamp": 1520978414186, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "4a2a4ad9-cd29-47cb-bdb9-5b617a73b898", "isActionActive": true}'),
(30, '{"_id": "05934ae338431f28bf6793b2417d2ec2", "data": {"startDate": "2018-02-22", "visitCode": "penta1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "PENTA 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-8506e63790993901b69708e013df5a8e", "timeStamp": 1520978414292, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "b06e0847-0d68-4f5c-9288-58bc394fe052", "isActionActive": true}'),
(31, '{"_id": "05934ae338431f28bf6793b2417d3aa0", "data": {"startDate": "2018-02-22", "visitCode": "rota1", "expiryDate": "2018-08-01", "alertStatus": "urgent", "scheduleName": "ROTA 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-636eec945c3c237fd8e90fe854dce081", "timeStamp": 1520978414313, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "b06e0847-0d68-4f5c-9288-58bc394fe052", "isActionActive": true}'),
(32, '{"_id": "05934ae338431f28bf6793b2417db6ca", "data": {"startDate": "2018-01-11", "visitCode": "bcg", "expiryDate": "2019-01-01", "alertStatus": "urgent", "scheduleName": "BCG", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-a8177920ff1fbef63a63f03851a343ee", "timeStamp": 1520978414508, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "b06e0847-0d68-4f5c-9288-58bc394fe052", "isActionActive": true}'),
(33, '{"_id": "05934ae338431f28bf6793b2417dba2d", "data": {"startDate": "2018-02-22", "visitCode": "opv1", "expiryDate": "2022-11-01", "alertStatus": "normal", "scheduleName": "OPV 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-51466c72f83907a175263b5750fe9b6c", "timeStamp": 1520978414513, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "b06e0847-0d68-4f5c-9288-58bc394fe052", "isActionActive": true}'),
(34, '{"_id": "05934ae338431f28bf6793b2417da475", "data": {"startDate": "2018-02-22", "visitCode": "pcv1", "expiryDate": "2022-11-01", "alertStatus": "urgent", "scheduleName": "PCV 1", "beneficiaryType": "child"}, "type": "Action", "details": {}, "version": 0, "_rev": "1-f18f7d27909a2cc3e3e27c5b4c116075", "timeStamp": 1520978414514, "actionType": "createAlert", "providerId": "biddemo", "actionTarget": "alert", "baseEntityId": "b06e0847-0d68-4f5c-9288-58bc394fe052", "isActionActive": true}');

INSERT INTO core.action_metadata (id, action_id, document_id, base_entity_id, server_version, provider_id, location_id, team, team_id) VALUES 
(1, 1, '05934ae338431f28bf6793b241642462', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932697689, 'biddemo', null, null, null),
(2, 2, '05934ae338431f28bf6793b2416433c9', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932698005, 'biddemo', null, null, null),
(3, 3, '05934ae338431f28bf6793b241643a7e', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932698821, 'biddemo', null, null, null),
(4, 4, '05934ae338431f28bf6793b241644dd7', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932699621, 'biddemo', null, null, null),
(5, 5, '05934ae338431f28bf6793b241645535', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932700149, 'biddemo', null, null, null),
(6, 6, '05934ae338431f28bf6793b241645f1f', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932700973, 'biddemo', null, null, null),
(7, 7, '05934ae338431f28bf6793b2416473e0', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932701845, 'biddemo', null, null, null),
(8, 8, '05934ae338431f28bf6793b2416481aa', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932702653, 'biddemo', null, null, null),
(9, 9, '05934ae338431f28bf6793b241648e9d', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932703185, 'biddemo', null, null, null),
(10, 10, '05934ae338431f28bf6793b241649473', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932703825, 'biddemo', null, null, null),
(11, 11, '05934ae338431f28bf6793b24164a5d7', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932704673, 'biddemo', null, null, null),
(12, 12, '05934ae338431f28bf6793b24164b4ce', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932705277, 'biddemo', null, null, null),
(13, 13, '05934ae338431f28bf6793b24164c069', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520932705889, 'biddemo', null, null, null),
(14, 14, '05934ae338431f28bf6793b241651160', '67007c17-97bb-4732-a1b8-3a0c292b5432', 1520933247737, 'biddemo', null, null, null),
(15, 15, '05934ae338431f28bf6793b241663fcd', '86c039a2-0b68-4166-849e-f49897e3a510', 1520933431893, 'biddemo', null, null, null),
(16, 16, '05934ae338431f28bf6793b2417bfe8f', '469597f0-eefe-4171-afef-f7234cbb2859', 1520978413525, 'biddemo', null, null, null),
(17, 17, '05934ae338431f28bf6793b2417bf883', '469597f0-eefe-4171-afef-f7234cbb2859', 1520978413537, 'biddemo', null, null, null),
(18, 18, '05934ae338431f28bf6793b2417bf3c3', '469597f0-eefe-4171-afef-f7234cbb2859', 1520978413541, 'biddemo', null, null, null),
(19, 19, '05934ae338431f28bf6793b2417c671b', 'aabcd2cc-c111-41c6-85e6-cb5d9e350d08', 1520978413737, 'biddemo', null, null, null),
(20, 20, '05934ae338431f28bf6793b2417c4bae', '469597f0-eefe-4171-afef-f7234cbb2859', 1520978413764, 'biddemo', null, null, null),
(21, 21, '05934ae338431f28bf6793b2417c4d6c', 'aabcd2cc-c111-41c6-85e6-cb5d9e350d08', 1520978413764, 'biddemo', null, null, null),
(22, 22, '05934ae338431f28bf6793b2417c5aae', '469597f0-eefe-4171-afef-f7234cbb2859', 1520978413765, 'biddemo', null, null, null),
(23, 23, '05934ae338431f28bf6793b2417c705e', 'aabcd2cc-c111-41c6-85e6-cb5d9e350d08', 1520978413880, 'biddemo', null, null, null),
(24, 24, '05934ae338431f28bf6793b2417c98f9', 'aabcd2cc-c111-41c6-85e6-cb5d9e350d08', 1520978413884, 'biddemo', null, null, null),
(25, 25, '05934ae338431f28bf6793b2417c7d94', 'aabcd2cc-c111-41c6-85e6-cb5d9e350d08', 1520978413894, 'biddemo', null, null, null),
(26, 26, '05934ae338431f28bf6793b2417ca6cd', '4a2a4ad9-cd29-47cb-bdb9-5b617a73b898', 1520978413945, 'biddemo', null, null, null),
(27, 27, '05934ae338431f28bf6793b2417d0103', '4a2a4ad9-cd29-47cb-bdb9-5b617a73b898', 1520978414164, 'biddemo', null, null, null),
(28, 28, '05934ae338431f28bf6793b2417d1390', '4a2a4ad9-cd29-47cb-bdb9-5b617a73b898', 1520978414184, 'biddemo', null, null, null),
(29, 29, '05934ae338431f28bf6793b2417d0b1c', '4a2a4ad9-cd29-47cb-bdb9-5b617a73b898', 1520978414186, 'biddemo', null, null, null),
(30, 30, '05934ae338431f28bf6793b2417d2ec2', 'b06e0847-0d68-4f5c-9288-58bc394fe052', 1520978414292, 'biddemo', null, null, null),
(31, 31, '05934ae338431f28bf6793b2417d3aa0', 'b06e0847-0d68-4f5c-9288-58bc394fe052', 1520978414313, 'biddemo', null, null, null),
(32, 32, '05934ae338431f28bf6793b2417db6ca', 'b06e0847-0d68-4f5c-9288-58bc394fe052', 1520978414508, 'biddemo', null, null, null),
(33, 33, '05934ae338431f28bf6793b2417dba2d', 'b06e0847-0d68-4f5c-9288-58bc394fe052', 1520978414513, 'biddemo', null, null, null),
(34, 34, '05934ae338431f28bf6793b2417da475', 'b06e0847-0d68-4f5c-9288-58bc394fe052', 1520978414514, 'biddemo', null, null, null);