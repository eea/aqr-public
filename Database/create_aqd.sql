/*drop-tables*/
DROP TABLE IF EXISTS measures_scenario CASCADE;
DROP TABLE IF EXISTS attainment_plan CASCADE;
DROP TABLE IF EXISTS attainment_plan CASCADE;
DROP TABLE IF EXISTS areaclassification CASCADE;
DROP TABLE IF EXISTS exceedencearea_areaclassification CASCADE;
DROP TABLE IF EXISTS reasonvalue CASCADE;
DROP TABLE IF EXISTS exceedancedescription_reasonvalue CASCADE;
DROP TABLE IF EXISTS deductionAssessmentMethod_assesmentmethods CASCADE;
DROP TABLE IF EXISTS deductionAssessmentMethod_adjustmentSource CASCADE;
DROP TABLE IF EXISTS sourceapportionment_measures CASCADE;
DROP TABLE IF EXISTS measures_expectedimpact CASCADE;
DROP TABLE IF EXISTS measures_evaluationscenario CASCADE;
DROP TABLE IF EXISTS measures_spatialscale CASCADE;
DROP TABLE IF EXISTS measures_sourcesector CASCADE;
DROP TABLE IF EXISTS measures_administrationlevel CASCADE;
DROP TABLE IF EXISTS measures_classification CASCADE;
DROP TABLE IF EXISTS evaluationscenario_publication CASCADE;
DROP TABLE IF EXISTS eveluationscenario_publication CASCADE;
DROP TABLE IF EXISTS measures_costs CASCADE;
DROP TABLE IF EXISTS plan_publication CASCADE;
DROP TABLE IF EXISTS plan_pollutant_protectiontarget CASCADE;
DROP TABLE IF EXISTS pollutant_protectiontarget CASCADE;
DROP TABLE IF EXISTS plan_statusplan CASCADE;
DROP TABLE IF EXISTS classification CASCADE;
DROP TABLE IF EXISTS exceedancearea CASCADE;
DROP TABLE IF EXISTS adjustmentType CASCADE;
DROP TABLE IF EXISTS assesmenttype CASCADE;
DROP TABLE IF EXISTS assesmentmethods CASCADE;
DROP TABLE IF EXISTS adjustmentSource CASCADE;
DROP TABLE IF EXISTS deductionAssessmentMethod CASCADE;
DROP TABLE IF EXISTS exceedanceexposure CASCADE;
DROP TABLE IF EXISTS environmentalobjective CASCADE;
DROP TABLE IF EXISTS attainment CASCADE;
DROP TABLE IF EXISTS exceedancedescription CASCADE;
DROP TABLE IF EXISTS protectiontarget CASCADE;
DROP TABLE IF EXISTS spatialscale CASCADE;
DROP TABLE IF EXISTS sourcesector CASCADE;
DROP TABLE IF EXISTS administrationlevel CASCADE;
DROP TABLE IF EXISTS expectedimpact CASCADE;
DROP TABLE IF EXISTS plannedimplementation CASCADE;
DROP TABLE IF EXISTS costs CASCADE;
DROP TABLE IF EXISTS measures CASCADE;
DROP TABLE IF EXISTS localincrement CASCADE;
DROP TABLE IF EXISTS urbanbackground CASCADE;
DROP TABLE IF EXISTS regionalbackground CASCADE;
DROP TABLE IF EXISTS sourceapportionment CASCADE;
DROP TABLE IF EXISTS scenario CASCADE;
DROP TABLE IF EXISTS evaluationscenario CASCADE;
DROP TABLE IF EXISTS publication CASCADE;
DROP TABLE IF EXISTS pollutant CASCADE;
DROP TABLE IF EXISTS relatedparty CASCADE;
DROP TABLE IF EXISTS plan CASCADE;
DROP TABLE IF EXISTS usergroup CASCADE;
DROP TABLE IF EXISTS country CASCADE;
DROP TABLE IF EXISTS userrole CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS statusplan CASCADE;
DROP TABLE IF EXISTS specificationOfHours CASCADE;
DROP TABLE IF EXISTS statusplannedimplementation CASCADE;
DROP TABLE IF EXISTS currency CASCADE;
DROP TABLE IF EXISTS measureType CASCADE;
DROP TABLE IF EXISTS timeScale CASCADE;
DROP TABLE IF EXISTS specificationOfHours CASCADE;
DROP TABLE IF EXISTS quantificationNumerical CASCADE;
DROP TABLE IF EXISTS systemconfiguration CASCADE;


/*create-codelist-tables*/
--Table: areaclassification
CREATE TABLE areaclassification
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(600),
	notation character varying(50),
	CONSTRAINT pk_areaclassification  PRIMARY KEY (uuid)
);


--Table: reasonvalue
CREATE TABLE reasonvalue
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_reasonvalue  PRIMARY KEY (uuid)
);


--Table: spatialscale
CREATE TABLE spatialscale
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_spatialscale  PRIMARY KEY (uuid)
);


--Table: sourcesector
CREATE TABLE sourcesector
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_sourcesector PRIMARY KEY (uuid)
);


--Table: administrationlevel
CREATE TABLE administrationlevel
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_administrationlevel PRIMARY KEY (uuid)
);


--Table: classification
CREATE TABLE classification
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_classification PRIMARY KEY (uuid)
);


--Table: specificationOfHours
CREATE TABLE specificationOfHours
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_specificationOfHours PRIMARY KEY (uuid)
);


--Table: statusplannedimplementation
CREATE TABLE statusplannedimplementation
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_statusplannedimplementation PRIMARY KEY (uuid)
);


--Table: currency
CREATE TABLE currency
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_currency PRIMARY KEY (uuid)
);


--Table: measureType
CREATE TABLE measureType
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_measureType PRIMARY KEY (uuid)
);


--Table: timeScale
CREATE TABLE timeScale
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_timeScale PRIMARY KEY (uuid)
);


--Table: quantificationNumerical
CREATE TABLE quantificationNumerical
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_quantificationNumerical PRIMARY KEY (uuid)
);


--Table: adjustmentType
CREATE TABLE adjustmentType
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_adjustmentType  PRIMARY KEY (uuid)
);

--Table: assesmenttype
CREATE TABLE assesmenttype
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(650),
	notation character varying(50),
	CONSTRAINT pk_assesmenttype  PRIMARY KEY (uuid)
);


--Table: assesmentmethods
CREATE TABLE assesmentmethods
(
	uuid character varying(250) NOT NULL,
	assesmenttype character varying(50),
	assesmentTypeDescription character varying(300),
	metadata TEXT,
	CONSTRAINT pk_assesmentmethods  PRIMARY KEY (uuid),
	CONSTRAINT fk_assesmentmethods_assesmenttype FOREIGN KEY (assesmenttype) 
		REFERENCES assesmenttype (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


--Table: adjustmentSource
CREATE TABLE adjustmentSource
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_adjustmentSource PRIMARY KEY (uuid)
);


-- Table: statusplan
CREATE TABLE statusplan
(
  uuid character varying(250) NOT NULL,
  preferred_label character varying(250) NOT NULL,
  link character varying(250) NOT NULL,
  CONSTRAINT statusplan_pkey PRIMARY KEY (uuid)
)
WITH (
  OIDS=FALSE
);

--Table: protectiontarget
CREATE TABLE protectiontarget
(
	uuid character varying(250) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_protectiontarget PRIMARY KEY (uuid)
);


--Table: pollutant
CREATE TABLE pollutant
(
	uuid character varying(50) NOT NULL,
	uri character varying(250) NOT NULL,
	label character varying(250) NOT NULL,
	definition character varying(250),
	notation character varying(50),
	CONSTRAINT pk_pollutant  PRIMARY KEY (uuid)
);




/*create-annex-tables*/
--Table: relatedparty
CREATE TABLE relatedparty
(
	uuid character varying(250) NOT NULL,
	
	individualName character varying(250) NOT NULL,
	organisationName character varying(200) NOT NULL,
	
	website character varying(250) NOT NULL,
	address character varying(200) NOT NULL,
	
	telephoneVoice character varying(50) NOT NULL,
	electronicMailAddress character varying(250) NOT NULL,
	CONSTRAINT pk_relatedparty  PRIMARY KEY (uuid)
);


-- Table: country
CREATE TABLE country
(
  uuid character varying(250) NOT NULL,
  countryName character varying(250) NOT NULL,
  CONSTRAINT usergroup_pkey PRIMARY KEY (uuid)
)
WITH (
  OIDS=FALSE
);
  

-- Table: userrole
CREATE TABLE userrole
(
  uuid character varying(250) NOT NULL,
  rolename character varying(250),
  label character varying (50),
  CONSTRAINT userrole_pkey PRIMARY KEY (uuid)
)
WITH (
  OIDS=FALSE
);


--Table: publication
CREATE TABLE publication
(
	uuid character varying(250) NOT NULL,
	title character varying(200) NOT NULL,
	description character varying(300) NOT NULL,
	author character varying(100),
	publicationDate_id character varying(50) NOT NULL,
	publicationDate_timePosition character varying(50) NOT NULL,
	publisher character varying(50) NOT NULL,
	webLink character varying(250),
	CONSTRAINT pk_publication  PRIMARY KEY (uuid)
);


--Table: exceedancearea
CREATE TABLE exceedancearea
(
	uuid character varying(250) NOT NULL,
	area character varying(50),
	areaEstimate character varying(50),
	roadLenghtEstimate character varying(20),
	administrativeUnits character varying(100),
	modelUsed TEXT,
	stationUsed TEXT,
	CONSTRAINT pk_exceedancearea  PRIMARY KEY (uuid)
);


--Table: exceedanceexposure
CREATE TABLE exceedanceexposure
(
	uuid character varying(250) NOT NULL,
	exposedPopulation character varying(50),
	exposedArea character varying(20),
	sensitiveResidentPopulation character varying(50),
	relevantInfrastructure character varying(50),
	referenceYear character varying(50),
	CONSTRAINT pk_exceedanceexposure  PRIMARY KEY (uuid)
);


--Table: scenario
CREATE TABLE scenario
(
	uuid character varying(250) NOT NULL,
	
	inspireId_localId character varying(45) NOT NULL,
	inspireId_namespace character varying(45) NOT NULL,
	inspireId_versionId character varying(50) NOT NULL,
	
	description character varying(1000) NOT NULL,
	totalEmissions character varying(50) NOT NULL,
	
	expectedConcentration character varying(50),
	expectedExceedence character varying(50),
	comment TEXT NOT NULL,
	CONSTRAINT pk_scenario  PRIMARY KEY (uuid)
);


-- Table: systemconfiguration
CREATE TABLE systemconfiguration
(
  uuid character varying(250) NOT NULL,
  namespace character varying(250) NOT NULL,
  country character varying(250) NOT NULL,
  CONSTRAINT systemconfiguration_pkey PRIMARY KEY (uuid),
  CONSTRAINT fk_systemconfiguration_country FOREIGN KEY (country) 
		REFERENCES country (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION	
)
WITH (
  OIDS=FALSE
);

--Table: expectedimpact
CREATE TABLE expectedimpact
(
	uuid character varying(250) NOT NULL,
	levelOfConcentration character varying(20),
	numberOfExceedence character varying(250),
	specificationOfHours character varying(250),
	comment TEXT,
	CONSTRAINT pk_expectedimpact  PRIMARY KEY (uuid),
	CONSTRAINT fk_expectedimpact_specificationOfHours FOREIGN KEY (specificationOfHours) 
		REFERENCES specificationOfHours (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


--Table:  plannedimplementation
CREATE TABLE plannedimplementation
(
	uuid character varying(250) NOT NULL,
	statusplannedimplementation character varying(250),
	
	implementationPlannedTimePeriod_id character varying(50) NOT NULL,
	implementationPlannedTimePeriod_beginPosition character varying(50) NOT NULL,
	implementationPlannedTimePeriod_endPosition character varying(50) NOT NULL,
	
	implementationActualTimePeriod_id character varying(50),
	implementationActualTimePeriod_beginPosition character varying(50),
	implementationActualTimePeriod_endPosition character varying(50),
	
	plannedfulleffectDate_id character varying(50) NOT NULL,
	plannedfulleffectDate_timePosition character varying(50) NOT NULL,
	plannedfulleffectDate_timePosition_nil boolean default true,
	plannedfulleffectDate_timePosition_nilreason character varying(500),
	
	otherDate character varying(250),
	
	monitoringProgressIndicators character varying(250) NOT NULL,
	monitoringProgressIndicators_nil boolean default false,
	monitoringProgressIndicators_nilreason character varying(500),
	
	comment TEXT,
	CONSTRAINT pk_plannedimplementation  PRIMARY KEY (uuid),
	CONSTRAINT fk_plannedimplementation_statusplannedimplementation FOREIGN KEY (statusplannedimplementation) 
		REFERENCES statusplannedimplementation (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


--Table: costs
CREATE TABLE costs
(
	uuid character varying(250) NOT NULL,
	
	extimatedImplementationCosts character varying(50),
	extimatedImplementationCosts_nil boolean default false,
	extimatedImplementationCosts_nilreason character varying(500),
	
	finalImplementationCosts character varying(50),
	currency character varying(250),
	comment TEXT,
	CONSTRAINT pk_costs PRIMARY KEY (uuid),	
	CONSTRAINT fk_cost_currency FOREIGN KEY (currency) 
		REFERENCES currency (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION
);

--Table: localincrement
CREATE TABLE localincrement
(
	uuid character varying(250) NOT NULL,
	
	total character varying(250) NOT NULL,
	totalcomment character varying(500),
	total_nil boolean default false,
	total_nilreason character varying(500),
	
	traffic character varying(250) NOT NULL,
	trafficcomment character varying(500),
	traffic_nil boolean default false,
	traffic_nilreason character varying(500),
	
	heatAndPowerProduction character varying(250) NOT NULL,
	heatAndPowerProductioncomment character varying(500),
	heatAndPowerProduction_nil boolean default false,
	heatAndPowerProduction_nilreason character varying(500),
	
	agriculture character varying(250) NOT NULL,
	agriculturecomment character varying(500),
	agriculture_nil boolean default false,
	agriculture_nilreason character varying(500),
	
	commercialAndResidential character varying(250) NOT NULL,
	commercialAndResidentialcomment character varying(500),
	commercialAndResidential_nil boolean default false,
	commercialAndResidential_nilreason character varying(500),
	
	shipping character varying(250) NOT NULL,
	shippingcomment character varying(500),
	shipping_nil boolean default false,
	shipping_nilreason character varying(500),
	
	offRoadMobileMachinery character varying(250) NOT NULL,
	offRoadMobileMachinerycomment character varying(500),
	offRoadMobileMachinery_nil boolean default false,
	offRoadMobileMachinery_nilreason character varying(500),
	
	naturallocalincrement character varying(250) NOT NULL,
	naturallocalincrementcomment character varying(500),
	naturallocalincrement_nil boolean default false,
	naturallocalincrement_nilreason character varying(500),
	
	transboundary character varying(250) NOT NULL,
	transboundarycomment character varying(500),
	transboundary_nil boolean default false,
	transboundary_nilreason character varying(500),
	
	other character varying(250),
	othercomment character varying(500),
	other_nil boolean default false,
	other_nilreason character varying(500),
	
	unitMisure character varying(30),
	unitMisurecomment character varying(500),
	unitMisure_nil boolean default false,
	unitMisure_nilreason character varying(500),
	
	CONSTRAINT pk_localincrement  PRIMARY KEY (uuid)
);


--Table: urbanbackground
CREATE TABLE urbanbackground
(
	uuid character varying(250) NOT NULL,
	
	total character varying(250) NOT NULL,
	totalcomment character varying(500),
	total_nil boolean default false,
	total_nilreason character varying(500),
	
	traffic character varying(250) NOT NULL,
	trafficcomment character varying(500),
	traffic_nil boolean default false,
	traffic_nilreason character varying(500),
	
	heatAndPowerProduction character varying(250) NOT NULL,
	heatAndPowerProductioncomment character varying(500),
	heatAndPowerProduction_nil boolean default false,
	heatAndPowerProduction_nilreason character varying(500),
	
	agriculture character varying(250) NOT NULL,
	agriculturecomment character varying(500),
	agriculture_nil boolean default false,
	agriculture_nilreason character varying(500),
	
	commercialAndResidential character varying(250) NOT NULL,
	commercialAndResidentialcomment character varying(500),
	commercialAndResidential_nil boolean default false,
	commercialAndResidential_nilreason character varying(500),
	
	shipping character varying(250) NOT NULL,
	shippingcomment character varying(500),
	shipping_nil boolean default false,
	shipping_nilreason character varying(500),
	
	offRoadMobileMachinery character varying(250) NOT NULL,
	offRoadMobileMachinerycomment character varying(500),
	offRoadMobileMachinery_nil boolean default false,
	offRoadMobileMachinery_nilreason character varying(500),
	
	naturalurbanbackground character varying(250) NOT NULL,
	naturalurbanbackgroundcomment character varying(500),
	naturalurbanbackground_nil boolean default false,
	naturalurbanbackground_nilreason character varying(500),
	
	transboundary character varying(250) NOT NULL,
	transboundarycomment character varying(500),
	transboundary_nil boolean default false,
	transboundary_nilreason character varying(500),
	
	other character varying(250),
	othercomment character varying(500),
	other_nil boolean default false,
	other_nilreason character varying(500),
	
	unitMisure character varying(30),
	unitMisurecomment character varying(500),
	unitMisure_nil boolean default false,
	unitMisure_nilreason character varying(500),
	
	CONSTRAINT pk_urbanbackground PRIMARY KEY (uuid)
);


--Table: regionalbackground
CREATE TABLE regionalbackground
(
	uuid character varying(250) NOT NULL,
	
	total character varying(250) NOT NULL,
	totalcomment character varying(500),
	total_nil boolean default false,
	total_nilreason character varying(500),
	
	fromWithinMS character varying(250) NOT NULL,
	fromWithinMScomment character varying(500),
	fromWithinMS_nil boolean default false,
	fromWithinMS_nilreason character varying(500),
	
	transboundary character varying(250) NOT NULL,
	transboundarycomment character varying(500),
	transboundary_nil boolean default false,
	transboundary_nilreason character varying(500),
	
	naturalregionalbackground character varying(250) NOT NULL,
	naturalregionalbackgroundcomment character varying(500),
	naturalregionalbackground_nil boolean default false,
	naturalregionalbackground_nilreason character varying(500),
	
	other character varying(250),
	othercomment character varying(500),
	other_nil boolean default false,
	other_nilreason character varying(500),
	
	unitMisure character varying(30),
	unitMisurecomment character varying(500),
	unitMisure_nil boolean default false,
	unitMisure_nilreason character varying(500),
	
	CONSTRAINT pk_regionalbackground PRIMARY KEY (uuid)
);


--Table: environmentalobjective
CREATE TABLE environmentalobjective
(
	uuid character varying(250) NOT NULL,
	objectiveTypeValue character varying(250),
	protectionTarget character varying(50),
	reportingMetricValue character varying(250),
	CONSTRAINT pk_environmentalobjective  PRIMARY KEY (uuid),
	CONSTRAINT fk_environmentalobjective_protectiontarget FOREIGN KEY (protectionTarget) 
		REFERENCES protectiontarget (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);

--Table: deductionAssessmentMethod
CREATE TABLE deductionAssessmentMethod
(
	uuid character varying(250) NOT NULL,
	adjustmentType character varying(50),
	CONSTRAINT pk_deductionAssessmentMethod  PRIMARY KEY (uuid),
	CONSTRAINT fk_exceedancedescription_assesmentmethods_adjustmentType FOREIGN KEY (adjustmentType) 
		REFERENCES adjustmentType (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


--Table: exceedancedescription
CREATE TABLE exceedancedescription
(
	uuid character varying(250) NOT NULL,
	
	exceedance boolean NOT NULL,
	numericalExceedance character varying(50),
	numberExceedances character varying(50),
	
	deductionAssessmentMethod character varying(50),
	exceedanceArea character varying(50),
	exceedanceexposure character varying(50),
	
	otherReason character varying(250),
	
	datecreation timestamp without time zone NOT NULL DEFAULT now(),
    datelastupdate timestamp without time zone,
	comment TEXT,
	CONSTRAINT pk_exceedancedescription  PRIMARY KEY (uuid),	
	CONSTRAINT fk_exceedancedescription_exceedanceexposure FOREIGN KEY (exceedanceexposure) 
		REFERENCES exceedanceexposure (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_exceedancedescription_deductionAssessmentMethod FOREIGN KEY (deductionAssessmentMethod) 
		REFERENCES deductionAssessmentMethod (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_exceedancedescription_exceedancearea FOREIGN KEY (exceedanceArea) 
		REFERENCES exceedancearea (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


/*create-main-tables*/
-- Table: users
CREATE TABLE users
(
  uuid character varying(250) NOT NULL,
  email character varying(250) NOT NULL,
  name character varying(250) NOT NULL,
  surname character varying(250) NOT NULL,
  country character varying(50),
  userrole character varying(50) NOT NULL,
  provider character varying(250) NOT NULL,
  enable boolean default true,
  datecreation timestamp without time zone,
  lastmodified timestamp without time zone,
  CONSTRAINT user_pkey PRIMARY KEY (uuid),
  CONSTRAINT fk_users_provider FOREIGN KEY (provider) 
		REFERENCES relatedparty (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT users_usergroup_id_fkey FOREIGN KEY (country)
      REFERENCES country (uuid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT users_userrole_id_fkey FOREIGN KEY (userrole)
      REFERENCES userrole (uuid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


--Table: measures
CREATE TABLE measures
(
	uuid character varying(250) NOT NULL,
	
	inspireId_localId character varying(100) NOT NULL,
	inspireId_namespace character varying(100) NOT NULL,
	inspireId_versionId character varying(100) NOT NULL,
	
	provider character varying(250) NOT NULL,
	
	users character varying(250) NOT NULL,
	
	code character varying(200) NOT NULL,
	name character varying(200) NOT NULL,
	
	description TEXT NOT NULL,
	
	measureType character varying(250),
	timeScale character varying(250),
	
	plannedImplementation character varying(50) NOT NULL,
	
	commentForClarification TEXT,
	completed boolean default false,
	
	costs character varying(250),
	
	reductionOfEmission character varying(50),
	reductionOfEmission_nil boolean default false,
	reductionOfEmission_nilreason character varying(500),
	
	quantificationNumerical character varying(250),
	
	comment TEXT,
	
	expectedimpact character varying(250),
	
	changes boolean default false NOT NULL,
	descriptionOfChanges character varying(250),
	reportingStartDate character varying(50) NOT NULL,
	reportingEndDate character varying(50) NOT NULL,
	
	datecreation timestamp without time zone NOT NULL DEFAULT now(),
    datelastupdate timestamp without time zone,
	
	CONSTRAINT pk_measures PRIMARY KEY (uuid),
	CONSTRAINT fk_measures_users FOREIGN KEY (users) 
		REFERENCES users (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_measures_provider FOREIGN KEY (provider) 
		REFERENCES relatedparty (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_measures_measureType FOREIGN KEY (measureType) 
		REFERENCES measureType (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_measures_timeScale FOREIGN KEY (timeScale) 
		REFERENCES timeScale (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_measures_plannedImplementation FOREIGN KEY (plannedImplementation) 
		REFERENCES plannedImplementation (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_measures_costs FOREIGN KEY (costs) 
		REFERENCES costs (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_measures_quantificationNumerical FOREIGN KEY (quantificationNumerical) 
		REFERENCES quantificationNumerical (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_measures_expectedimpact FOREIGN KEY (expectedimpact) 
		REFERENCES expectedimpact (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION			
);


--Table: attainment
CREATE TABLE attainment
(
	uuid character varying(250) NOT NULL,
	
	inspireId_localId character varying(100) NOT NULL,
	inspireId_namespace character varying(100) NOT NULL,
	inspireId_versionId character varying(100) NOT NULL,
	
	comment TEXT,
	
	users character varying(250) NOT NULL,
	
	environmentalObject character varying(50),
	pollutant character varying(50),
	
	validityPeriod_id character varying(50),
	validityPeriod_beginPosition timestamp without time zone,
	validityPeriod_endPosition timestamp without time zone,
	
	datecreation timestamp without time zone NOT NULL DEFAULT now(),
    datelastupdate timestamp without time zone,
	CONSTRAINT pk_attainment  PRIMARY KEY (uuid),
	CONSTRAINT fk_attainment_users FOREIGN KEY (users) 
		REFERENCES users (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_attainment_environmentalobjective FOREIGN KEY (environmentalObject) 
		REFERENCES environmentalobjective (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
     CONSTRAINT fk_attainment_pollutant FOREIGN KEY (pollutant) 
		REFERENCES pollutant (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION        
);


--Table: plan
CREATE TABLE plan
(
	uuid character varying(250) NOT NULL,
	
	inspireid_localId character varying(100) NOT NULL,
	inspireid_namespace character varying(100) NOT NULL,
	inspireid_versionId character varying(100) NOT NULL,
	
	code character varying(200) NOT NULL,
	name character varying(200) NOT NULL,
	
	provider character varying(250) NOT NULL,
	competentauthority character varying(250) NOT NULL,
	users character varying(250) NOT NULL,
	
	firstExceedanceYear_id character varying(50) NOT NULL,
	firstExceedanceYear_timePosition character varying(4) NOT NULL,
	
	adoptionDate_id character varying(50),
	adoptionDate_timePosition character varying(50),
	
	timeTable character varying(300) NOT NULL,
	referenceAQPlan character varying(300) NOT NULL,
	referenceImplementation character varying(300) NOT NULL,
	
	comment TEXT,
	statusplan character varying(250),
	completed boolean default false,
	
	changes boolean default false NOT NULL,
	descriptionOfChanges character varying(250),
	reportingStartDate character varying(50) NOT NULL,
	reportingEndDate character varying(50) NOT NULL,
	
	datecreation timestamp without time zone NOT NULL DEFAULT now(),
    datelastupdate timestamp without time zone,
	
	CONSTRAINT pk_plan  PRIMARY KEY (uuid),
	CONSTRAINT fk_plan_users FOREIGN KEY (users) 
		REFERENCES users (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_plan_provider FOREIGN KEY (provider) 
		REFERENCES relatedparty (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_plan_relatedparty FOREIGN KEY (competentauthority) 
		REFERENCES relatedparty (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_plan_statusplan FOREIGN KEY (statusplan) 
		REFERENCES statusplan (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION		
);


--Table: sourceapportionment
CREATE TABLE sourceapportionment
(
	uuid character varying(250) NOT NULL,
	
	inspireid_localId character varying(100) NOT NULL,
	inspireid_namespace character varying(100) NOT NULL,
	inspireid_versionId character varying(100) NOT NULL,
	
	provider character varying(250) NOT NULL,
	
	users character varying(250) NOT NULL,
	
	changes boolean default false NOT NULL,
	descriptionOfChanges character varying(250),
	reportingStartDate character varying(50) NOT NULL,
	reportingEndDate character varying(50) NOT NULL,
	
	referenceYear_id character varying(50),
	referenceYear_timePeriod character varying(50),
	
	regionalBackground character varying(50) NOT NULL,
	urbanBackground character varying(50) NOT NULL,
	localIncrement character varying(50) NOT NULL,
	
	exceedancedescription character varying(50),
	
	comment TEXT,
	completed boolean default false,
	
	attainment character varying(50),
	plan character varying(50),
	
	datecreation timestamp without time zone NOT NULL DEFAULT now(),
    datelastupdate timestamp without time zone,
	CONSTRAINT pk_sourceapportionment  PRIMARY KEY (uuid),
	CONSTRAINT fk_sourceapportionment_users FOREIGN KEY (users) 
		REFERENCES users (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_sourceapportionment_provider FOREIGN KEY (provider) 
		REFERENCES relatedparty (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_sourceapportionment_attainment FOREIGN KEY (attainment) 
		REFERENCES attainment (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_sourceapportionment_exceedancedescription FOREIGN KEY (exceedancedescription) 
		REFERENCES exceedancedescription (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_sourceapportionment_plan FOREIGN KEY (plan) 
		REFERENCES plan (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_sourceapportionment_urbanbackground FOREIGN KEY (urbanBackground) 
		REFERENCES urbanbackground (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_sourceapportionment_regionalbackground FOREIGN KEY (regionalBackground) 
		REFERENCES regionalbackground (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_sourceapportionment_localincrement FOREIGN KEY (localIncrement) 
		REFERENCES localincrement (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: evaluationscenario
CREATE TABLE evaluationscenario
(
	uuid character varying(250) NOT NULL,
	
	inspireid_localId character varying(100) NOT NULL,
	inspireid_namespace character varying(100) NOT NULL,
	inspireid_versionId character varying(100) NOT NULL,
	
	provider character varying(250) NOT NULL,
	users character varying(250) NOT NULL,
	
	codeOfScenario character varying(250) NOT NULL,
	
	attainmentYear_id character varying(50) NOT NULL,
	attainmentYear_periodTime character varying(4) NOT NULL,
	
	startYear_id character varying(50) NOT NULL,
	startYear_periodTime character varying(50) NOT NULL,
	
	plan character varying(50),
	sourceapportionment character varying(50),
	
	baselinescenario character varying(50) NOT NULL,
	projectionscenario character varying(50) NOT NULL,
	
	completed boolean default false,
	
	changes boolean default false NOT NULL,
	descriptionOfChanges character varying(250),
	reportingStartDate character varying(50) NOT NULL,
	reportingEndDate character varying(50) NOT NULL,
	
	datecreation timestamp without time zone NOT NULL DEFAULT now(),
    datelastupdate timestamp without time zone,
	CONSTRAINT pk_evaluationscenario  PRIMARY KEY (uuid),
	CONSTRAINT fk_evaluationscenario_users FOREIGN KEY (users) 
		REFERENCES users (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_evaluationscenario_provider FOREIGN KEY (provider) 
		REFERENCES relatedparty (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_evaluationscenario_plan FOREIGN KEY (plan) 
		REFERENCES plan (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_evaluationscenario_sourceapportionment FOREIGN KEY (sourceapportionment) 
		REFERENCES sourceapportionment (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_evaluationscenario_baselinescenario FOREIGN KEY (baselinescenario) 
		REFERENCES scenario (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_evaluationscenario_projectionscenario FOREIGN KEY (projectionscenario) 
		REFERENCES scenario (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION		
);


/*create-crossreference-tables*/
--Table: pollutant_protectiontarget
CREATE TABLE pollutant_protectiontarget
(
	uuid character varying(50) NOT NULL,
	pollutant character varying(50) NOT NULL,
	protectiontarget character varying(50) NOT NULL,
	CONSTRAINT pk_pollutant_protectiontarget PRIMARY KEY (uuid),
	CONSTRAINT fk_pollutant_protectiontarget_pollutant FOREIGN KEY (pollutant) 
		REFERENCES pollutant (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_pollutant_protectiontarget_protectiontarget FOREIGN KEY (protectiontarget) 
		REFERENCES protectiontarget (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: exceedencearea_areaclassification
CREATE TABLE exceedencearea_areaclassification
(
	uuid character varying(250) NOT NULL,
	exceedancearea character varying(50) NOT NULL,
	areaclassification character varying(50) NOT NULL,
	CONSTRAINT pk_exceedencearea_areaclassification  PRIMARY KEY (uuid),
	CONSTRAINT fk_exceedencearea_areaclassification_areaclassification 	FOREIGN KEY (areaclassification) 
		REFERENCES areaclassification (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_exceedencearea_areaclassification_exceedancearea FOREIGN KEY (exceedancearea) 	
		REFERENCES exceedancearea (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: deductionAssessmentMethod_adjustmentSource
CREATE TABLE deductionAssessmentMethod_adjustmentSource
(
	uuid character varying(250) NOT NULL,
	deductionAssessmentMethod character varying(50) NOT NULL,
	adjustmentSource character varying(50) NOT NULL,
	CONSTRAINT pk_deductionAssessmentMethod_adjustmentSource PRIMARY KEY (uuid),
	CONSTRAINT fk_deductionAssessmentMethod_adjustmentSource_deductionAssessmentMethod FOREIGN KEY (deductionAssessmentMethod) 
		REFERENCES deductionAssessmentMethod (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_deductionAssessmentMethod_adjustmentSource_adjustmentSource FOREIGN KEY (adjustmentSource) 
		REFERENCES adjustmentSource (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: deductionAssessmentMethod_assesmentmethods
CREATE TABLE deductionAssessmentMethod_assesmentmethods
(
	uuid character varying(250) NOT NULL,
	deductionAssessmentMethod character varying(50) NOT NULL,
	assesmentmethods character varying(50) NOT NULL,
	CONSTRAINT pk_deductionAssessmentMethod_assesmentmethods PRIMARY KEY (uuid),
	CONSTRAINT fk_deductionAssessmentMethod_assesmentmethods_deductionAssessmentMethod FOREIGN KEY (deductionAssessmentMethod) 
		REFERENCES deductionAssessmentMethod (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_deductionAssessmentMethod_assesmentmethods_assesmentmethods FOREIGN KEY (assesmentmethods) 
		REFERENCES assesmentmethods (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: exceedancedescription_reasonvalue
CREATE TABLE exceedancedescription_reasonvalue
(
	uuid character varying(250) NOT NULL,
	exceedancedescription character varying(50) NOT NULL,
	reasonvalue character varying(50) NOT NULL,
	CONSTRAINT pk_exceedancedescription_reasonvalue  PRIMARY KEY (uuid),
	CONSTRAINT fk_exceedancedescription_reasonvalue_exceedancedescription FOREIGN KEY (exceedancedescription) 
		REFERENCES exceedancedescription (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_exceedancedescription_reasonvalue_reasonvalue FOREIGN KEY (reasonvalue) 
		REFERENCES reasonvalue (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: measures_spatialscale
CREATE TABLE measures_spatialscale
(
	uuid character varying(250) NOT NULL,
	measures character varying(50) NOT NULL,
	spatialscale character varying(50) NOT NULL,
	CONSTRAINT pk_measures_spatialscale PRIMARY KEY (uuid),
	CONSTRAINT fk_measures_spatialscale_measures FOREIGN KEY (measures) 
		REFERENCES measures (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_measures_spatialscale_spatialscale FOREIGN KEY (spatialscale) 
		REFERENCES spatialscale (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: measures_sourcesector
CREATE TABLE measures_sourcesector
(
	uuid character varying(250) NOT NULL,
	measures character varying(50) NOT NULL,
	sourcesector character varying(50) NOT NULL,
	CONSTRAINT pk_measures_sourcesector PRIMARY KEY (uuid),
	CONSTRAINT fk_measures_sourcesector_measures FOREIGN KEY (measures) 
		REFERENCES measures (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_measures_sourcesector_sourcesector FOREIGN KEY (sourcesector) 
		REFERENCES sourcesector (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: measures_administrationlevel
CREATE TABLE measures_administrationlevel
(
	uuid character varying(250) NOT NULL,
	measures character varying(50) NOT NULL,
	administractionlevel character varying(50) NOT NULL,
	CONSTRAINT pk_measures_administrationlevel  PRIMARY KEY (uuid),
	CONSTRAINT fk_measures_administrationlevel_administrationlevel FOREIGN KEY (administractionlevel) 
		REFERENCES administrationlevel (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_measures_administrationlevel_measures FOREIGN KEY (measures) 
		REFERENCES measures (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


--Table: measures_classification
CREATE TABLE measures_classification
(
	uuid character varying(250) NOT NULL,
	measures character varying(50) NOT NULL,
	classification character varying(50) NOT NULL,
	CONSTRAINT pk_measures_classification  PRIMARY KEY (uuid),
	CONSTRAINT fk_measures_classification_classification FOREIGN KEY (classification) 
		REFERENCES classification (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_measures_classification_measures FOREIGN KEY (measures) 
		REFERENCES measures (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: attainment_plan
CREATE TABLE attainment_plan
(
	uuid character varying(250) NOT NULL,
	attainment character varying(50) NOT NULL,
	plan character varying(50) NOT NULL,
	CONSTRAINT pk_attainment_plan  PRIMARY KEY (uuid),
	CONSTRAINT fk_attainment_plan_plan FOREIGN KEY (plan) 
		REFERENCES plan (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_attainment_plan_attainment FOREIGN KEY (attainment) 
		REFERENCES attainment (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


--Table: measures_evaluationscenario
CREATE TABLE measures_evaluationscenario
(
	uuid character varying(250) NOT NULL,
	measures character varying(50) NOT NULL,
	evaluationscenario character varying(50) NOT NULL,
	CONSTRAINT pk_measures_evaluationscenario  PRIMARY KEY (uuid),
	CONSTRAINT fk_measures_evaluationscenario_evaluationscenario FOREIGN KEY (evaluationscenario) 
		REFERENCES evaluationscenario (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_measures_evaluationscenario_measures FOREIGN KEY (measures) 
		REFERENCES measures (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


--Table: evaluationscenario_publication
CREATE TABLE evaluationscenario_publication
(
	uuid character varying(250) NOT NULL,
	evaluationscenario character varying(50) NOT NULL,
	publication character varying(50) NOT NULL,
	CONSTRAINT pk_evaluationscenario_publication  PRIMARY KEY (uuid),
	CONSTRAINT fk_evaluationscenario_publication_evaluationscenario	FOREIGN KEY (evaluationscenario) 
		REFERENCES evaluationscenario (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_eveluationscenario_publication_publication FOREIGN KEY (publication) 
		REFERENCES publication (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION
);


--Table: plan_publication
CREATE TABLE plan_publication
(
	uuid character varying(250) NOT NULL,
	plan character varying(50) NOT NULL,
	publication character varying(50) NOT NULL,
	CONSTRAINT pk_plan_publication  PRIMARY KEY (uuid),
	CONSTRAINT fk_plan_publication_plan FOREIGN KEY (plan) 
		REFERENCES plan (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_plan_publication_publication FOREIGN KEY (publication) 
		REFERENCES publication (uuid) MATCH SIMPLE 
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: measures_scenario
CREATE TABLE measures_scenario
(
	uuid character varying(250) NOT NULL,
	measures character varying(50) NOT NULL,
	scenario character varying(50) NOT NULL,
	CONSTRAINT pk_measures_scenario  PRIMARY KEY (uuid),
	CONSTRAINT fk_measures_scenario_measures FOREIGN KEY (measures) 
		REFERENCES measures (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_measures_scenario_scenario FOREIGN KEY (scenario) 
		REFERENCES scenario (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: sourceapportionment_measures
CREATE TABLE sourceapportionment_measures
(
	uuid character varying(250) NOT NULL,
	sourceapportionment character varying(50) NOT NULL,
	measures character varying(50) NOT NULL,
	CONSTRAINT pk_sourceapportionment_measures  PRIMARY KEY (uuid),
	CONSTRAINT fk_sourceapportionment_measures_measures FOREIGN KEY (measures) 
		REFERENCES measures (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_sourceapportionment_measures_sourceapportionment FOREIGN KEY (sourceapportionment) 
		REFERENCES sourceapportionment (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);


--Table: plan_pollutant_protectiontarget
CREATE TABLE plan_pollutant_protectiontarget
(
	uuid character varying(250) NOT NULL,
	plan character varying(50) NOT NULL,
	pollutant character varying(50) NOT NULL,
	protectiontarget character varying(50) NOT NULL,
	CONSTRAINT pk_plan_pollutant_protectiontarget  PRIMARY KEY (uuid),
	CONSTRAINT fk_plan_pollutant_protectiontarget_plan FOREIGN KEY (plan) 
		REFERENCES plan (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,	
	CONSTRAINT fk_plan_pollutant_protectiontarget_pollutant FOREIGN KEY (pollutant) 
		REFERENCES pollutant (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_plan_pollutant_protectiontarget_protectiontarget FOREIGN KEY (protectiontarget) 
		REFERENCES protectiontarget (uuid) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION	
);
