/*insert-draft-plan*/
INSERT INTO relatedparty (uuid, individualname, organisationname, website, address, telephonevoice, electronicmailaddress) VALUES ('ff230598e05b748954ebbb3286dbc170', 'Emily Connolly', 'Atmosphere and Local Environment Programme (ALE) Department for Environment, Food and Rural Affairs Area 5E Ergon House', 'www.defra.gov.uk', 'London, 17 Smith Square, SW1P3JR', '+44 (0) 207238 6476', 'emily.connolly@defra.qsi.gov.uk');
INSERT INTO relatedparty (uuid, individualname, organisationname, website, address, telephonevoice, electronicmailaddress) VALUES ('ff230598e05b748954ebbb3286dbc171', 'Emily Connolly', 'Atmosphere and Local Environment Programme (ALE) Department for Environment, Food and Rural Affairs Area 5E Ergon House', 'www.defra.gov.uk', 'London, 17 Smith Square, SW1P3JR', '+44 (0) 207238 6476', 'emily.connolly@defra.qsi.gov.uk');
INSERT INTO plan (uuid, inspireid_localid, inspireid_namespace, inspireid_versionid, code, name, provider, competentauthority, users, firstexceedanceyear_id, firstexceedanceyear_timeposition, adoptiondate_id, adoptiondate_timeposition, timetable, referenceaqplan, referenceimplementation, comment, statusplan, reportingstartdate, reportingenddate, datecreation, datelastupdate, changes, descriptionOfChanges, completed) VALUES ('fe9f504ee877f2eacf9c1157a616c58a', 'UK0001_H_NO2_2011', 'http://uk-air.defra.gov.uk/e-reporting/aq/', '2014-06-04.10.55.803', 'UK0001_H_NO2_2011', 'Air Quality Plan for the achievement of EU air quality limit values for nitrogen dioxide (NO2) in greater London Urban Area (UK0001)', 'ff230598e05b748954ebbb3286dbc171', 'ff230598e05b748954ebbb3286dbc170', '5', '', '2001', '', '2011-09-01', 'not defined', 'www.someurl.co.uk', 'www.someurl.co.uk', 'This is NOx source apportionment given in Âµgm-3 NOx as NO2. This is the source apportionment for the location with the highest modelled NO2 concentration.', '1', '2013-01-01', '2014-01-01', '2014-03-21 14:17:54.669', '2014-03-21 14:20:02.154', true, 'The changes include ...', true);
INSERT INTO plan_pollutant_protectiontarget (uuid, plan, pollutant, protectiontarget) VALUES ('a9da1c41a2f89dba4667d533edb79368', 'fe9f504ee877f2eacf9c1157a616c58a', '422', '0');
INSERT INTO publication (uuid, title, description, author, publicationdate_id, publicationdate_timeposition, publisher, weblink) VALUES ('70c5bba6496e59201c6ec3c92bf83193', 'Air Quality Plan for the achievement of EU air quality limit values for nitrogen dioxide (NO2) in Greater London Urban Area (UK0001)', 'Air quality plan for the Zone', 'Defra (Department for Environment, Food and Rural Affairs)', 'publicationDate_1', '2011', 'Published by Defra', 'http://uk-air.defra.gov.uk/library/no2ten/');
INSERT INTO plan_publication (uuid, plan, publication) VALUES ('ea74c390cfcf529d7d9c9ef8844ffa5b', 'fe9f504ee877f2eacf9c1157a616c58a', '70c5bba6496e59201c6ec3c92bf83193');


/*insert-draft-measures*/
INSERT INTO costs VALUES ('f1925756d97e34ab1fc5975c23ab2e82', 704000000, FALSE, '', 1000000000, '5', 'Some comment ...');
INSERT INTO expectedimpact VALUES ('1d04ea68c3b9c09715c738b5e9fea82c', 0, 0, '1', 'Some comment ...');
INSERT INTO relatedparty VALUES ('ff4e993ffbfbe39ec96c3c0929aa1c71', 'Emily Connolly', 'Atmosphere and Local Environment Programme (ALE) Department for Environment, Food and Rural Affairs Area 5E Ergon House', 'www.defra.gov.uk', 'London, 17 Smith Square, SW1P3JR', '+44 (0) 207238 6476', 'emily.connolly@defra.qsi.gov.uk');
INSERT INTO plannedimplementation VALUES ('5ceaef53a524a73660847e5bd0b233b0', '4', '', '2011-01-01', '2018-01-01', NULL, '2011-01-01', '2018-01-01', '', '2018-01-01', FALSE, '', 'No further information available', 'No idicators', FALSE, '', 'Some comment ...');
INSERT INTO measures VALUES ('5800bef4578d5e075b32e9c00b06eba3', 'UK0001_K_NO2_annual_1_2011_UK_G3', 'http://uk-air.defra.gov.uk/e-reporting/aq/', '2014-06-04.10.55.803', 'ff4e993ffbfbe39ec96c3c0929aa1c71', '5', 'UKNotNI_A17', 'Further electrification of the railway network', 'Electrification of lines in England, Wales and Scotland including the Great Western main line from London to Cardiff. Enables replacement of diesel trains.', '1', '1', '5ceaef53a524a73660847e5bd0b233b0', 'The impact of this measure on NO2 concentrations has not been quantified.', true, 'f1925756d97e34ab1fc5975c23ab2e82', 0, FALSE,'', '0', 'Some comment ...', '1d04ea68c3b9c09715c738b5e9fea82c', true, 'The changes include ...', '2013-01-01', '2014-01-01', '2014-05-22 09:46:13.765', '2014-05-22 09:56:14.082');
INSERT INTO measures_administrationlevel VALUES ('239b9a8c644c67f12d16b32af6db32a2', '5800bef4578d5e075b32e9c00b06eba3', '1');
INSERT INTO measures_classification VALUES ('7fde4ac5fdfc170510bd8b1f3c61b8b5', '5800bef4578d5e075b32e9c00b06eba3', '16');
INSERT INTO measures_sourcesector VALUES ('c88dd77d57b85ef5bc95ac66c385b208', '5800bef4578d5e075b32e9c00b06eba3', '6');
INSERT INTO measures_spatialscale VALUES ('e8d52f613a0169312ce3287e078cdaaa', '5800bef4578d5e075b32e9c00b06eba3', '1');


/*insert-draft-attainments*/
INSERT INTO attainment VALUES ('3df64d695df15eb15882eda78b08c868', 'GB_Attainment_1734', 'http://environment.data.gov.uk/air-quality/so', 'Wed Jun 04 11:28:11 CEST 2014', NULL, '5', NULL, NULL, NULL, NULL, NULL, '2014-06-04 11:28:11.284', '2014-06-04 11:28:11.284');
INSERT INTO attainment VALUES ('e5db111871fff28c18a219016ceccf06', 'GB_Attainment_1728', 'http://environment.data.gov.uk/air-quality/so', 'Wed Jun 04 11:28:11 CEST 2014', NULL, '5', NULL, NULL, NULL, NULL, NULL, '2014-06-04 11:28:11.31', '2014-06-04 11:28:11.31');
INSERT INTO attainment VALUES ('025c5cfd4f902950b4727dcbd2d7e8fb', 'GB_Attainment_1721', 'http://environment.data.gov.uk/air-quality/so', 'Wed Jun 04 11:28:11 CEST 2014', NULL, '5', NULL, NULL, NULL, NULL, NULL, '2014-06-04 11:28:11.314', '2014-06-04 11:28:11.314');
INSERT INTO attainment VALUES ('f27ce64c1bb0ee3c8a23a253cb53e6eb', 'GB_Attainment_1733', 'http://environment.data.gov.uk/air-quality/so', 'Wed Jun 04 11:28:11 CEST 2014', NULL, '5', NULL, NULL, NULL, NULL, NULL, '2014-06-04 11:28:11.319', '2014-06-04 11:28:11.319');
INSERT INTO attainment VALUES ('e7e38c7cf93c7f5986ab8280a5027107', 'GB_Attainment_1729', 'http://environment.data.gov.uk/air-quality/so', 'Wed Jun 04 11:28:11 CEST 2014', NULL, '5', NULL, NULL, NULL, NULL, NULL, '2014-06-04 11:28:11.324', '2014-06-04 11:28:11.324');
INSERT INTO attainment_plan VALUES ('e7e38c7cf93c7f5986ab8280a502aaaa', '025c5cfd4f902950b4727dcbd2d7e8fb', 'fe9f504ee877f2eacf9c1157a616c58a');


/*insert-draft-sourceapp*/
INSERT INTO assesmentmethods (uuid, assesmenttype, assesmenttypedescription, metadata) VALUES ('220e9a39d22781213589061f681defc5', '0', 'Some description ...', '');
INSERT INTO deductionassessmentmethod (uuid, adjustmenttype) VALUES ('915f0b23da93c5d3797b9bbfd48a27fe', '0');
INSERT INTO deductionassessmentmethod_adjustmentsource (uuid, deductionassessmentmethod, adjustmentsource) VALUES ('04fde1cd4f0af98b21eb72ddfd3ca784', '915f0b23da93c5d3797b9bbfd48a27fe', '8');
INSERT INTO deductionassessmentmethod_adjustmentsource (uuid, deductionassessmentmethod, adjustmentsource) VALUES ('233d7342331e6022404e8a3523b8aadd', '915f0b23da93c5d3797b9bbfd48a27fe', '9');
INSERT INTO deductionassessmentmethod_assesmentmethods (uuid, deductionassessmentmethod, assesmentmethods) VALUES ('d4f1128b2b8eb327089c59bd3ab3006f', '915f0b23da93c5d3797b9bbfd48a27fe', '220e9a39d22781213589061f681defc5');
INSERT INTO exceedancearea (uuid, area, areaestimate, roadlenghtestimate, administrativeunits, modelused) VALUES ('fa49cf98898ae720f775c092a7168e9c', '', '1.1', '1.3', '', '');
INSERT INTO exceedencearea_areaclassification (uuid, exceedancearea, areaclassification) VALUES ('cf0f5e4a3f16ab1397de0cb930e3d793', 'fa49cf98898ae720f775c092a7168e9c', '0');
INSERT INTO exceedencearea_areaclassification (uuid, exceedancearea, areaclassification) VALUES ('e34b80078b18481ba631d73edd1266b6', 'fa49cf98898ae720f775c092a7168e9c', '1');
INSERT INTO exceedanceexposure (uuid, exposedpopulation, exposedarea, sensitiveresidentpopulation, relevantinfrastructure, referenceyear) VALUES ('bc3288dda0bdc4c044bd8a6fc7c1df62', '10000', '0.5', '5000', '2', '2008');
INSERT INTO exceedancedescription (uuid, exceedance, numericalexceedance, numberexceedances, deductionassessmentmethod, exceedancearea, exceedanceexposure, otherreason, datecreation, datelastupdate, comment) VALUES ('de4a72379cacfb0d0d9d15d9d5b0e95c', true, '0.0', '0', '915f0b23da93c5d3797b9bbfd48a27fe', 'fa49cf98898ae720f775c092a7168e9c', 'bc3288dda0bdc4c044bd8a6fc7c1df62', 'Some reasons ...', '2014-06-25 15:27:21.793', '2014-06-25 15:39:52.744', 'Some comments ...');
INSERT INTO exceedancedescription_reasonvalue (uuid, exceedancedescription, reasonvalue) VALUES ('a2d7d4bca1212329880eeb8e43af9829', 'de4a72379cacfb0d0d9d15d9d5b0e95c', '1');
INSERT INTO relatedparty (uuid, individualname, organisationname, website, address, telephonevoice, electronicmailaddress) VALUES ('1040b0ef83124582f8af7125680f5f0f', 'Emily Connolly', 'Atmosphere and Local Environment Programme (ALE) Department for Environment, Food and Rural Affairs Area 5E Ergon House', 'www.defra.gov.uk', 'London, 17 Smith Square, SW1P3JR', '+44 (0) 207238 6476', 'emily.connolly@defra.qsi.gov.uk');
INSERT INTO localincrement (uuid, total, totalcomment, total_nil, total_nilreason, traffic, trafficcomment, traffic_nil, traffic_nilreason, heatandpowerproduction, heatandpowerproductioncomment, heatandpowerproduction_nil, heatandpowerproduction_nilreason, agriculture, agriculturecomment, agriculture_nil, agriculture_nilreason, commercialandresidential, commercialandresidentialcomment, commercialandresidential_nil, commercialandresidential_nilreason, shipping, shippingcomment, shipping_nil, shipping_nilreason, offroadmobilemachinery, offroadmobilemachinerycomment, offroadmobilemachinery_nil, offroadmobilemachinery_nilreason, naturallocalincrement, naturallocalincrementcomment, naturallocalincrement_nil, naturallocalincrement_nilreason, transboundary, transboundarycomment, transboundary_nil, transboundary_nilreason, other, othercomment, other_nil, other_nilreason, unitmisure, unitmisurecomment, unitmisure_nil, unitmisure_nilreason) VALUES ('e2588c0411d9bbc3f6c2a73e1f3e49bc', '305.5', '', false, 'Unpopulated', '305.5', '', false, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', '', false, '');
INSERT INTO regionalbackground (uuid, total, totalcomment, total_nil, total_nilreason, fromwithinms, fromwithinmscomment, fromwithinms_nil, fromwithinms_nilreason, transboundary, transboundarycomment, transboundary_nil, transboundary_nilreason, naturalregionalbackground, naturalregionalbackgroundcomment, naturalregionalbackground_nil, naturalregionalbackground_nilreason, other, othercomment, other_nil, other_nilreason, unitmisure, unitmisurecomment, unitmisure_nil, unitmisure_nilreason) VALUES ('b85c59d21478b2e2789375151a1e36a4', '11.0', '', false, 'Unpopulated', '6.9', '', false, 'Unpopulated', '4.1', '', false, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', '', false, '');
INSERT INTO urbanbackground (uuid, total, totalcomment, total_nil, total_nilreason, traffic, trafficcomment, traffic_nil, traffic_nilreason, heatandpowerproduction, heatandpowerproductioncomment, heatandpowerproduction_nil, heatandpowerproduction_nilreason, agriculture, agriculturecomment, agriculture_nil, agriculture_nilreason, commercialandresidential, commercialandresidentialcomment, commercialandresidential_nil, commercialandresidential_nilreason, shipping, shippingcomment, shipping_nil, shipping_nilreason, offroadmobilemachinery, offroadmobilemachinerycomment, offroadmobilemachinery_nil, offroadmobilemachinery_nilreason, naturalurbanbackground, naturalurbanbackgroundcomment, naturalurbanbackground_nil, naturalurbanbackground_nilreason, transboundary, transboundarycomment, transboundary_nil, transboundary_nilreason, other, othercomment, other_nil, other_nilreason, unitmisure, unitmisurecomment, unitmisure_nil, unitmisure_nilreason) VALUES ('2f7656995d90049937dddc9c0c34dfb0', '89.9', '', false, 'Unpopulated', '43.5', '', false, 'Unpopulated', '9.8', '', false, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '28.6', '', false, 'Unpopulated', '0.0', '', false, 'Unpopulated', '6.8', '', false, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '', 'N/A', true, 'Unpopulated', '1.1', '', false, 'Unpopulated', '', '', false, '');
INSERT INTO sourceapportionment (uuid, inspireid_localid, inspireid_namespace, inspireid_versionid, provider, users, changes, descriptionofchanges, reportingstartdate, reportingenddate, referenceyear_id, referenceyear_timeperiod, regionalbackground, urbanbackground, localincrement, exceedancedescription, comment, completed, attainment, plan, datecreation, datelastupdate) VALUES ('f00d99036586e651c9cce3380da526ef', 'UK0001_I_NO2_annual_1_2011', 'http://uk-air.defra.gov.uk/e-reporting/aq/', '2014-06-25.15.39.743', '1040b0ef83124582f8af7125680f5f0f', '5', true, 'The changes include ...', '2013-01-01', '2014-01-01', '', '2008', 'b85c59d21478b2e2789375151a1e36a4', '2f7656995d90049937dddc9c0c34dfb0', 'e2588c0411d9bbc3f6c2a73e1f3e49bc', 'de4a72379cacfb0d0d9d15d9d5b0e95c', 'This is NOx source apportionment given in Âµgm-3 NOx as NO2. This is the source apportionment for the location with the highest modelled NO2 concentration.', true, '025c5cfd4f902950b4727dcbd2d7e8fb', 'fe9f504ee877f2eacf9c1157a616c58a', '2014-06-25 15:27:21.786', '2014-06-25 15:39:52.744');
INSERT INTO sourceapportionment_measures VALUES('0d9108559f81923442d8b2d4e2deaxxx', 'f00d99036586e651c9cce3380da526ef', '5800bef4578d5e075b32e9c00b06eba3');


/*insert-draft-evaluationscenario*/
INSERT INTO publication VALUES ('c9de20a141a7b2c55c51830dbde0e318', 'Air Quality Plan for the achievement of EU air quality limit values for nitrogen dioxide (NO2) in Greater London Urban Area (UK0001)', 'Air quality plan for the Zone', 'Defra (Department for Environment, Food and Rural Affairs)', '', '2011', 'Published by Defra', 'http://uk-air.defra.gov.uk/library/no2ten/');
INSERT INTO relatedparty VALUES ('80077eb0151b2f4d7bf68ab2db53b4ef', 'Emily Connolly', 'Defra (Department for Environment, Food and Rural Affairs)', 'http://www.defra.gov.uk/', 'Atmosphere and Local Environment Programme (ALE) Department for Environment, Food and Rural Affairs Area 5E Ergon House, 17 Smith Square, London, SW1P 3JR', '00 44 (0) 207 238 6476', 'emily.connolly@defra.gsi.gov.uk');
INSERT INTO scenario VALUES ('b0109f14de5120ec62ac94be9f26e69c', '', '', 'Tue Jun 03 17:24:03 CEST 2014', 'Baseline emissions projections, starting from the 2008 reference year. The road transport projections used road transport emission factors published in 2009 by the Department for Transport and road transport activity projections from Department for Transport (DfT AF (09), published in May 2010). The emission factors are based on a combination of measurements from UK test programmes combined with data from European sources including ARTEMIS. The projections for non-road traffic sources were calculated by the NAEI based on the Updated Energy Projections 37 from the Department of Energy and Climate Change.', '838', '114.8', '', 'Baseline total emissions in J.2.3 are the UK total');
INSERT INTO scenario VALUES ('6f1d4077eaafdfdd24c1237ed0042765', '', '', 'Tue Jun 03 17:24:03 CEST 2014', 'Emissions projections (based on the baseline described in J.2.2) that also include the impact of the LEZ measure.', '0', '93.6', '', 'N/A');
INSERT INTO measures_scenario VALUES ('d96c08d685fefb8b38f12fbc62927eda', '5800bef4578d5e075b32e9c00b06eba3', 'b0109f14de5120ec62ac94be9f26e69c');
INSERT INTO measures_scenario VALUES ('a85d19878c02a3106500ce3742dac678', '5800bef4578d5e075b32e9c00b06eba3', '6f1d4077eaafdfdd24c1237ed0042765');
INSERT INTO evaluationscenario VALUES ('d1ca53d7caa621fb8147b1d324b48c94', 'UK0001_J_NO2_annual_1_2011', 'http://uk-air.defra.gov.uk/e-reporting/aq/', '2014-06-04.10.55.803', '80077eb0151b2f4d7bf68ab2db53b4ef', '5', 'UK0001_NO2_annual_1_2011', '', '2008', '', '2008', 'fe9f504ee877f2eacf9c1157a616c58a', 'f00d99036586e651c9cce3380da526ef', 'b0109f14de5120ec62ac94be9f26e69c', '6f1d4077eaafdfdd24c1237ed0042765', true, true, 'Some description ...', '2008-01-01', '2015-12-31', '2014-06-03 16:28:20.119', '2014-06-03 17:24:03.652');
INSERT INTO evaluationscenario_publication VALUES ('0d9108559f81923442d8b2d4e2dea1db', 'd1ca53d7caa621fb8147b1d324b48c94', 'c9de20a141a7b2c55c51830dbde0e318');
INSERT INTO measures_evaluationscenario VALUES('0d9108559f81923442d8b2d4e2dea1ac', '5800bef4578d5e075b32e9c00b06eba3', 'd1ca53d7caa621fb8147b1d324b48c94');
