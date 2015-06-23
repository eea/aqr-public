<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <h2 class="measures">${res['measure.label.edit']}</h2>

        <fieldset>
            <legend class="measures">${res['description.label.edit']}</legend>
            <p>${res['measure.description.model']}</p>
            <p align="right">${res['fields.marked']}</p>
            <p align="right">${res['fields.conditional']}</p>
        </fieldset>  

        <s:form class="${actionBean.measure.editable ? 'protected' : ''}" beanclass="eu.europa.ec.aqrsystem.action.EditMeasureActionBean">
            <div><s:hidden name="measure.uuid"/></div>
            <div><s:hidden name="measure.editable"/></div>
            <div><s:hidden name="measureId"/></div>

            <fieldset>
                <legend class="measures">${res['measure.legend.inspireid']}</legend>
                <cust:field key="measure.inspireidLocalid"/>
                <cust:readOnlyField key="measure.inspireidNamespace" value="${actionBean.measure.inspireidNamespace}" hiddenField="true"/>
                <cust:readOnlyField key="measure.inspireidVersionid" value="${actionBean.measure.inspireidVersionid}" hiddenField="true"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.providerBean']}</legend>
                <cust:field key="measure.providerBean.organisationname"/>
                <cust:field key="measure.providerBean.website"/>
                <cust:field key="measure.providerBean.individualname"/>
                <cust:textArea key="measure.providerBean.address"/>
                <cust:field key="measure.providerBean.telephonevoice"/>
                <cust:field key="measure.providerBean.electronicmailaddress"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.change']}</legend>
                <s:hidden name="measure.changes"/>
                <%--<cust:label key="measure.changes" optional="true"/><s:checkbox name="measure.changes"/><br/>--%>
                <cust:textArea key="measure.descriptionofchanges"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.reportingperiod']}</legend>
                <cust:field key="measure.reportingstartdate" cssclass="datepicker"/>
                <cust:field key="measure.reportingenddate" cssclass="datepicker"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.code']}</legend>					
                <cust:field key="measure.code"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.name']}</legend>	
                <cust:field key="measure.name"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.description']}</legend>	
                <cust:textArea key="measure.description"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.classification']}</legend>
                <cust:label key="measure.classification_uri"/>
                <s:select class="multi" multiple="true" name="measure.classification_uri">
                    <s:options-collection collection="${actionBean.possibleClassifications}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/measureclassification"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.type']}</legend>					
                <cust:label key="measure.measuretype_uri"/>
                <s:select name="measure.measuretype_uri">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.possibleTypes}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/measuretype"/>
            </fieldset>

            <fieldset>			
                <legend class="measures">${res['measure.legend.adminlevel']}</legend>
                <cust:label key="measure.administrationlevel_uri"/>
                <s:select class="multi" multiple="true" name="measure.administrationlevel_uri">
                    <s:options-collection collection="${actionBean.possibleAdminLevels}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/administrativelevel"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.timescale']}</legend>					
                <cust:label key="measure.timescale_uri"/>
                <s:select name="measure.timescale_uri">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.possibleTimeScales}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/timescale"/>
            </fieldset>

            <fieldset>
                <legend class="measures" title="${res['measure.legend.cost.blue.star']}">${res['measure.legend.cost']} <font color="blue">*</font></legend>
                <s:link beanclass="eu.europa.ec.aqrsystem.action.CostActionBean" event="reloadView" id="reload-view-link">
                    <s:param name="measureId" value="${actionBean.measureId}"/>
                    <div class="hidden"></div>
                </s:link>
                <security:allowed event="save">
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.CostActionBean" event="form" id="new-cost" class="ftm insert btn measures">
                        <s:param name="measureId" value="${actionBean.measureId}"/>
                        ${res['measure.costsBean.new']}
                    </s:link>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.CostActionBean" event="form" id="edit-cost" class="ftm edit btn measures">
                        <s:param name="measureId" value="${actionBean.measureId}"/>
                        ${res['measure.costsBean.edit']}
                    </s:link>            
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.CostActionBean" event="delete" id="delete-cost" class="ftm delete btn measures confirm">
                        <s:param name="measureId" value="${actionBean.measureId}"/>
                        ${res['measure.costsBean.delete']}
                    </s:link>
                </security:allowed>
                <cust:ajaxMessages prefix="cost"/>
                <cust:ajaxInProgress id="cost-view"/>
                <cust:ajaxdialog>
                    <jsp:attribute name="height">490</jsp:attribute>
                    <jsp:attribute name="width">650</jsp:attribute>
                    <jsp:attribute name="title">${res['measure.costsBean.dialog.title']}</jsp:attribute>
                    <jsp:attribute name="dialogId">costNewItem</jsp:attribute>
                    <jsp:attribute name="addNewItemButtonId">new-cost</jsp:attribute>
                </cust:ajaxdialog>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.sourcesector']}</legend>
                <cust:label key="measure.sourcesector_uri"/>
                <s:select class="multi" multiple="true" name="measure.sourcesector_uri">
                    <s:options-collection collection="${actionBean.possibleSourceSectors}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/sourcesectors"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.spatialscale']}</legend>
                <cust:label key="measure.spatialscale_uri"/>
                <s:select class="multi" multiple="true" name="measure.spatialscale_uri">
                    <s:options-collection collection="${actionBean.possibleSpatialScales}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/spatialscale"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.plannedimplementation']}</legend>
                <cust:label key="measure.plannedimplementationBean.statusplannedimplementation" optional="true"/>
                <s:select name="measure.plannedimplementationBean.statusplannedimplementation_uri">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.possibleImplementationStatuses}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/measureimplementationstatus"/>

                <div class="legend measures">${res['measure.plannedimplementationBean.implementationplannedtimeperiod']}</div><br/>
                <cust:field cssclass="datepicker" key="measure.plannedimplementationBean.implementationplannedtimeperiodBeginposition"/>
                <cust:field cssclass="datepicker" key="measure.plannedimplementationBean.implementationplannedtimeperiodEndposition"/>

                <br/><br/><div class="legend measures">${res['measure.plannedimplementationBean.implementationactualtimeperiod']}</div><br/>
                <cust:field cssclass="datepicker" key="measure.plannedimplementationBean.implementationactualtimeperiodBeginposition" optional="true"/>
                <cust:field cssclass="datepicker" key="measure.plannedimplementationBean.implementationactualtimeperiodEndposition" optional="true"/>

                <br/><br/><div class="legend measures">${res['measure.plannedimplementationBean.plannedfulleffectdate']}</div><br/>
                <cust:nilField cssclass="datepicker" key="measure.plannedimplementationBean.plannedfulleffectdateTimeposition"/>

                <br/><br/>
                <cust:field key="measure.plannedimplementationBean.otherdate" optional="true"/>
                <cust:nilField key="measure.plannedimplementationBean.monitoringprogressindicators"/>
                <cust:textArea key="measure.plannedimplementationBean.comment" optional="true"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.reductionofemission']}</legend>
                <cust:nilField key="measure.reductionofemission" linkedField="measure.quantificationnumerical_uri" linkedFieldCollection="${actionBean.possibleQuantificationNummericals}" linkedFieldCollectionURL="http://dd.eionet.europa.eu/vocabulary/uom/emission"/>
                <cust:textArea key="measure.comment" optional="true"/>
            </fieldset>

            <fieldset>
                <legend class="measures" title="${res['measure.legend.expectedimpact.title']}">${res['measure.legend.expectedimpact']} <font color="blue">*</font></legend>	

                <cust:field key="measure.expectedimpactBean.levelofconcentration" optional="true"/>
                <br/>
                <cust:field key="measure.expectedimpactBean.numberofexceedence" optional="true"/>
                <cust:label key="measure.expectedimpactBean.specificationofhours" optional="true"/>
                <s:select name="measure.expectedimpactBean.specificationofhours_uri">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.possibleSpecificationOfHours}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/uom/time"/>
                <cust:textArea key="measure.expectedimpactBean.comment" optional="true"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['measure.legend.comment']}</legend>
                <cust:textArea key="measure.commentForClarification" optional="true"/>
            </fieldset>

            <fieldset>
                <legend class="measures">${res['common.linkedresource.plural']}</legend>
                <cust:label key="measure.sourceapportionmentBeanList"/>
                <s:select class="multi" multiple="true" name="measure.sourceapportionmentBeanList">
                    <s:options-collection collection="${actionBean.existingSourceApportionments}" value="uuid" label="inspireidLocalid" sort="inspireidLocalid"/>
                </s:select>
                <cust:label key="measure.evaluationscenarioBeanList" optional="true"/>
                <s:select class="multi" multiple="true" name="measure.evaluationscenarioBeanList">
                    <s:options-collection collection="${actionBean.existingEvaluationScenarios}" value="uuid" label="inspireidLocalid" sort="inspireidLocalid"/>
                </s:select>
            </fieldset>

            <br/>
            <security:allowed event="save">
                <s:submit class="ftm save measures btn" name="save" value="${res['common.save']}"/>
            </security:allowed>
        </s:form>
    </s:layout-component>      
</s:layout-render>