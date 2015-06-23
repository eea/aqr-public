<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <h2 class="sourceapp">${res['source.label.edit']}</h2>

        <fieldset>
            <legend class="sourceapp">${res['description.label.edit']}</legend>
            <p>${res['source.description.model']}</p>
            <p align="right">${res['fields.marked']}</p>
            <p align="right">${res['fields.conditional']}</p>
        </fieldset>  

        <s:form class="${actionBean.source.editable ? 'protected' : ''}" beanclass="eu.europa.ec.aqrsystem.action.EditApportionmentActionBean">
            <div><s:hidden name="source.uuid"/></div>
            <div><s:hidden name="source.editable"/></div>
            <div><s:hidden name="sourceId"/></div>

            <fieldset>
                <legend class="sourceapp">${res['source.legend.inspireid']}</legend>
                <cust:field key="source.inspireidLocalid"/>
                <cust:readOnlyField key="source.inspireidNamespace" value="${actionBean.source.inspireidNamespace}" hiddenField="true"/>
                <cust:readOnlyField key="source.inspireidVersionid" value="${actionBean.source.inspireidVersionid}" hiddenField="true"/>
            </fieldset>

            <fieldset>
                <legend class="sourceapp">${res['source.legend.providerBean']}</legend>
                <cust:field key="source.providerBean.organisationname"/>
                <cust:field key="source.providerBean.website"/>
                <cust:field key="source.providerBean.individualname"/>
                <cust:textArea key="source.providerBean.address"/>
                <cust:field key="source.providerBean.telephonevoice"/>
                <cust:field key="source.providerBean.electronicmailaddress"/>
            </fieldset>

            <fieldset>
                <legend class="sourceapp">${res['source.legend.change']}</legend>
                <s:hidden name="source.changes"/>
                <%--<cust:label key="source.changes" optional="true"/><s:checkbox name="source.changes"/><br/>--%>
                <cust:textArea key="source.descriptionofchanges"/>
            </fieldset>

            <fieldset>
                <legend class="sourceapp">${res['source.legend.reportingperiod']}</legend>
                <cust:field key="source.reportingstartdate" cssclass="datepicker"/>
                <cust:field key="source.reportingenddate" cssclass="datepicker"/>
            </fieldset>

            <fieldset>
                <legend class="sourceapp">${res['source.legend.referenceyearTimeperiod']}</legend>
                <cust:field key="source.referenceyearTimeperiod" optional="true"/>
            </fieldset>

            <fieldset class="tab">
                <legend class="sourceapp">${res['source.legend.regionalbackgroundBean']}</legend>		
                <div class="tabs">
                    <ul>
                        <li><a href="#tab-rb-1">${res['source.regionalbackgroundBean.label.total']}</a></li>
                        <li><a href="#tab-rb-2">${res['source.regionalbackgroundBean.label.fromwithinms']}</a></li>
                        <li><a href="#tab-rb-3">${res['source.regionalbackgroundBean.label.transboundary']}</a></li>
                        <li><a href="#tab-rb-4">${res['source.regionalbackgroundBean.label.naturalregionalbackground']}</a></li>
                        <li><a href="#tab-rb-5">${res['source.regionalbackgroundBean.label.other']}</a></li>							
                    </ul>
                    <div id="tab-rb-1" class="one-tab">
                        <cust:nilField key="source.regionalbackgroundBean.total"/>
                        <cust:textArea key="source.regionalbackgroundBean.totalcomment" optional="true"/>
                    </div>
                    <div id="tab-rb-2" class="one-tab">
                        <cust:nilField key="source.regionalbackgroundBean.fromwithinms"/>
                        <cust:textArea key="source.regionalbackgroundBean.fromwithinmscomment" optional="true"/>
                    </div>
                    <div id="tab-rb-3" class="one-tab">
                        <cust:nilField key="source.regionalbackgroundBean.transboundary"/>
                        <cust:textArea key="source.regionalbackgroundBean.transboundarycomment" optional="true"/>
                    </div>
                    <div id="tab-rb-4" class="one-tab">
                        <cust:nilField key="source.regionalbackgroundBean.naturalregionalbackground"/>
                        <cust:textArea key="source.regionalbackgroundBean.naturalregionalbackgroundcomment" optional="true"/>
                    </div>
                    <div id="tab-rb-5" class="one-tab">
                        <cust:nilField key="source.regionalbackgroundBean.other" optional="true"/>
                        <cust:textArea key="source.regionalbackgroundBean.othercomment" optional="true"/>
                    </div>
                </div>	
            </fieldset>

            <fieldset class="tab">
                <legend class="sourceapp">${res['source.legend.urbanbackgroundBean']}</legend>		
                <div class="tabs">
                    <ul>
                        <li><a href="#tab-ub-1">${res['source.urbanbackgroundBean.label.total']}</a></li>
                        <li><a href="#tab-ub-2">${res['source.urbanbackgroundBean.label.traffic']}</a></li>
                        <li><a href="#tab-ub-3">${res['source.urbanbackgroundBean.label.heatandpowerproduction']}</a></li>
                        <li><a href="#tab-ub-4">${res['source.urbanbackgroundBean.label.agriculture']}</a></li>
                        <li><a href="#tab-ub-5">${res['source.urbanbackgroundBean.label.commercialandresidential']}</a></li>
                        <li><a href="#tab-ub-6">${res['source.urbanbackgroundBean.label.shipping']}</a></li>
                        <li><a href="#tab-ub-7">${res['source.urbanbackgroundBean.label.offroadmobilemachinery']}</a></li>
                        <li><a href="#tab-ub-8">${res['source.urbanbackgroundBean.label.naturalurbanbackground']}</a></li>
                        <li><a href="#tab-ub-9">${res['source.urbanbackgroundBean.label.transboundary']}</a></li>
                        <li><a href="#tab-ub-10">${res['source.urbanbackgroundBean.label.other']}</a></li>							
                    </ul>
                    <div id="tab-ub-1">
                        <cust:nilField key="source.urbanbackgroundBean.total"/>
                        <cust:textArea key="source.urbanbackgroundBean.totalcomment" optional="true"/>
                    </div>
                    <div id="tab-ub-2">
                        <cust:nilField key="source.urbanbackgroundBean.traffic"/>
                        <cust:textArea key="source.urbanbackgroundBean.trafficcomment" optional="true"/>
                    </div>
                    <div id="tab-ub-3">
                        <cust:nilField key="source.urbanbackgroundBean.heatandpowerproduction"/>
                        <cust:textArea key="source.urbanbackgroundBean.heatandpowerproductioncomment" optional="true"/>
                    </div>
                    <div id="tab-ub-4">
                        <cust:nilField key="source.urbanbackgroundBean.agriculture"/>
                        <cust:textArea key="source.urbanbackgroundBean.agriculturecomment" optional="true"/>
                    </div>
                    <div id="tab-ub-5">
                        <cust:nilField key="source.urbanbackgroundBean.commercialandresidential"/>
                        <cust:textArea key="source.urbanbackgroundBean.commercialandresidentialcomment" optional="true"/>
                    </div>
                    <div id="tab-ub-6">
                        <cust:nilField key="source.urbanbackgroundBean.shipping"/>
                        <cust:textArea key="source.urbanbackgroundBean.shippingcomment" optional="true"/>
                    </div>
                    <div id="tab-ub-7">
                        <cust:nilField key="source.urbanbackgroundBean.offroadmobilemachinery"/>
                        <cust:textArea key="source.urbanbackgroundBean.offroadmobilemachinerycomment" optional="true"/>
                    </div>
                    <div id="tab-ub-8">
                        <cust:nilField key="source.urbanbackgroundBean.naturalurbanbackground"/>
                        <cust:textArea key="source.urbanbackgroundBean.naturalurbanbackgroundcomment" optional="true"/>
                    </div>
                    <div id="tab-ub-9">
                        <cust:nilField key="source.urbanbackgroundBean.transboundary"/>
                        <cust:textArea key="source.urbanbackgroundBean.transboundarycomment" optional="true"/>
                    </div>
                    <div id="tab-ub-10">
                        <cust:nilField key="source.urbanbackgroundBean.other" optional="true"/>
                        <cust:textArea key="source.urbanbackgroundBean.othercomment" optional="true"/>
                    </div>
                </div>
            </fieldset>	

            <fieldset class="tab">
                <legend class="sourceapp">${res['source.legend.localincrementBean']}</legend>		
                <div class="tabs">
                    <ul>
                        <li><a href="#tab-li-1">${res['source.localincrementBean.label.total']}</a></li>
                        <li><a href="#tab-li-2">${res['source.localincrementBean.label.traffic']}</a></li>
                        <li><a href="#tab-li-3">${res['source.localincrementBean.label.heatandpowerproduction']}</a></li>
                        <li><a href="#tab-li-4">${res['source.localincrementBean.label.agriculture']}</a></li>
                        <li><a href="#tab-li-5">${res['source.localincrementBean.label.commercialandresidential']}</a></li>
                        <li><a href="#tab-li-6">${res['source.localincrementBean.label.shipping']}</a></li>
                        <li><a href="#tab-li-7">${res['source.localincrementBean.label.offroadmobilemachinery']}</a></li>
                        <li><a href="#tab-li-8">${res['source.localincrementBean.label.naturallocalincrement']}</a></li>
                        <li><a href="#tab-li-9">${res['source.localincrementBean.label.transboundary']}</a></li>
                        <li><a href="#tab-li-10">${res['source.localincrementBean.label.other']}</a></li>							
                    </ul>
                    <div id="tab-li-1">
                        <cust:nilField key="source.localincrementBean.total"/>
                        <cust:textArea key="source.localincrementBean.totalcomment" optional="true"/>
                    </div>
                    <div id="tab-li-2">
                        <cust:nilField key="source.localincrementBean.traffic"/>
                        <cust:textArea key="source.localincrementBean.trafficcomment" optional="true"/>
                    </div>
                    <div id="tab-li-3">
                        <cust:nilField key="source.localincrementBean.heatandpowerproduction"/>
                        <cust:textArea key="source.localincrementBean.heatandpowerproductioncomment" optional="true"/>
                    </div>
                    <div id="tab-li-4">
                        <cust:nilField key="source.localincrementBean.agriculture"/>
                        <cust:textArea key="source.localincrementBean.agriculturecomment" optional="true"/>
                    </div>
                    <div id="tab-li-5">
                        <cust:nilField key="source.localincrementBean.commercialandresidential"/>
                        <cust:textArea key="source.localincrementBean.commercialandresidentialcomment" optional="true"/>
                    </div>
                    <div id="tab-li-6">
                        <cust:nilField key="source.localincrementBean.shipping"/>
                        <cust:textArea key="source.localincrementBean.shippingcomment" optional="true"/>
                    </div>
                    <div id="tab-li-7">
                        <cust:nilField key="source.localincrementBean.offroadmobilemachinery"/>
                        <cust:textArea key="source.localincrementBean.offroadmobilemachinerycomment" optional="true"/>
                    </div>
                    <div id="tab-li-8">
                        <cust:nilField key="source.localincrementBean.naturallocalincrement"/>
                        <cust:textArea key="source.localincrementBean.naturallocalincrementcomment" optional="true"/>
                    </div>
                    <div id="tab-li-9">
                        <cust:nilField key="source.localincrementBean.transboundary"/>
                        <cust:textArea key="source.localincrementBean.transboundarycomment" optional="true"/>
                    </div>
                    <div id="tab-li-10">
                        <cust:nilField key="source.localincrementBean.other" optional="true"/>
                        <cust:textArea key="source.localincrementBean.othercomment" optional="true"/>
                    </div>
                </div>
            </fieldset>	

            <fieldset>
                <legend class="sourceapp">${res['source.legend.exceedancedescription']}</legend>
                <cust:label key="source.exceedancedescriptionBean.exceedance"/><s:checkbox name="source.exceedancedescriptionBean.exceedance"/><br/>
                <cust:field key="source.exceedancedescriptionBean.numericalexceedance" optional="true"/>
                <cust:field key="source.exceedancedescriptionBean.numberexceedances" optional="true"/>

                <br/>

                <fieldset>
                    <legend class="sourceapp">${res['source.exceedancedescriptionBean.legend.deductionassessmentmethodBean']}</legend>
                    <security:allowed event="save">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.AssessmentActionBean" event="form" id="new-ass-btn" class="ftm insert btn">
                            <s:param name="sourceId" value="${actionBean.sourceId}"/>
                            ${res['source.button.addnewassessment']}
                        </s:link>
                        <br/><br/>
                    </security:allowed>
                    <div>${res['source.exceedancedescriptionBean.deductionassessmentmethodBean.legend.assessmentmethod']}</div>
                    <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.AssessmentActionBean" event="table">
                        <s:param name="sourceId" value="${actionBean.source.uuid}"/>
                    </s:url>
                    <cust:ajaxtable>
                        <jsp:attribute name="tableId">assessmentGUI</jsp:attribute>
                        <jsp:attribute name="loadTableURL">${loadTableURL}</jsp:attribute>
                        <jsp:attribute name="sorting_column">0</jsp:attribute>
                        <jsp:attribute name="sorting_order">asc</jsp:attribute>
                        <jsp:attribute name="newItemDialogHeight">380</jsp:attribute>
                        <jsp:attribute name="newItemDialogWidth">650</jsp:attribute>
                        <jsp:attribute name="newItemDialogTitle">${res['assessment.dialog.title']}</jsp:attribute>
                        <jsp:attribute name="addNewItemButtonId">new-ass-btn</jsp:attribute>
                    </cust:ajaxtable>
                    <br/><br/>
                    <cust:label key="source.exceedancedescriptionBean.deductionassessmentmethodBean.adjustmenttype_uri" optional="true"/>
                    <s:select name="source.exceedancedescriptionBean.deductionassessmentmethodBean.adjustmenttype_uri">
                        <s:option value="">None</s:option>
                        <s:options-collection collection="${actionBean.possibleAdjustmentTypes}" value="uri" label="label"/>
                    </s:select>
                    <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/adjustmenttype"/>
                    <cust:label key="source.exceedancedescriptionBean.deductionassessmentmethodBean.adjustmentsourceList_uri" optional="true"/>
                    <s:select name="source.exceedancedescriptionBean.deductionassessmentmethodBean.adjustmentsourceList_uri" multiple="true" class="multi">
                        <s:options-collection collection="${actionBean.possibleAdjustmentSources}" value="uri" label="label"/>
                    </s:select>
                    <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/adjustmentsourcetype"/>
                </fieldset>

                <fieldset>
                    <legend class="sourceapp">${res['source.exceedancedescriptionBean.legend.exceedanceareaBean']}</legend>

                    <cust:label key="source.exceedancedescriptionBean.exceedanceareaBean.areaclassificationList_uri"/>
                    <s:select name="source.exceedancedescriptionBean.exceedanceareaBean.areaclassificationList_uri" multiple="true" class="multi">
                        <s:options-collection collection="${actionBean.possibleClassifications}" value="uri" label="label"/>
                    </s:select>
                    <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/areaclassification"/>
                    <!--cust:field key="source.exceedancedescriptionBean.exceedanceareaBean.area" optional="true"/-->
                    <cust:field key="source.exceedancedescriptionBean.exceedanceareaBean.areaestimate" optional="true"/>
                    <cust:field key="source.exceedancedescriptionBean.exceedanceareaBean.roadlenghtestimate" optional="true"/>
                    <cust:tagItField key="source.exceedancedescriptionBean.exceedanceareaBean.stationused" optional="true"/>
                    <cust:tagItField key="source.exceedancedescriptionBean.exceedanceareaBean.modelused" optional="true"/>
                </fieldset>

                <fieldset>
                    <legend class="sourceapp">${res['source.exceedancedescriptionBean.legend.exceedenceexposureBean']}</legend>
                    <cust:field key="source.exceedancedescriptionBean.exceedenceexposureBean.exposedpopulation" optional="true"/>
                    <cust:field key="source.exceedancedescriptionBean.exceedenceexposureBean.exposedarea" optional="true"/>
                    <cust:field key="source.exceedancedescriptionBean.exceedenceexposureBean.sensitiveresidentpopulation" optional="true"/>
                    <cust:field key="source.exceedancedescriptionBean.exceedenceexposureBean.relevantinfrastructure" optional="true"/>
                    <cust:field key="source.exceedancedescriptionBean.exceedenceexposureBean.referenceyear" optional="true"/>
                </fieldset>

                <!--<fieldset>-->
                    <!--<legend class="sourceapp">${res['source.exceedancedescriptionBean.legend.exceedancedescription.continuation']}</legend> --!>

                <cust:label key="source.exceedancedescriptionBean.reasonvalueList_uri" optional="true"/>
                <s:select name="source.exceedancedescriptionBean.reasonvalueList_uri" multiple="true" class="multi">
                    <s:options-collection collection="${actionBean.possibleReasons}" value="uri" label="label"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/exceedancereason"/>
                <cust:field key="source.exceedancedescriptionBean.otherreason" optional="true"/>
                <cust:field key="source.exceedancedescriptionBean.comment" optional="true"/>
                <!--</fieldset>-->

            </fieldset>

            <fieldset>
                <legend class="sourceapp">${res['source.legend.comment']}</legend>
                <cust:textArea key="source.comment" optional="true"/>
            </fieldset>

            <fieldset>
                <legend class="sourceapp">${res['common.linkedresource.plural']}</legend>
                <cust:label key="source.planBean.uuid"/>
                <s:select name="source.planBean.uuid">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.existingPlans}" value="uuid" label="inspireidLocalid" sort="inspireidLocalid"/>
                </s:select>
                <cust:label key="source.attainmentBean.uuid"/>
                <s:select name="source.attainmentBean.uuid">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.existingAttainments}" value="uuid" label="inspireidLocalid" sort="inspireidLocalid"/>
                </s:select>
            </fieldset>

            <br/>
            <security:allowed event="save">
                <s:submit class="ftm save sourceapp btn" name="save" value="${res['common.save']}"/>
            </security:allowed>
        </s:form>
    </s:layout-component>
</s:layout-render>