<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <h2>${res['plan.label.edit']}</h2>

        <fieldset>
            <legend>${res['description.label.edit']}</legend>
            <p>${res['plan.description.model']}</p>
            <p align="right">${res['fields.marked']}</p>
        </fieldset>  

        <s:form class="${actionBean.plan.editable ? 'protected' : ''}" beanclass="eu.europa.ec.aqrsystem.action.EditPlanActionBean">
            <div><s:hidden name="plan.uuid"/></div>
            <div><s:hidden name="plan.editable"/></div>
            <div><s:hidden name="planId"/></div>
            <fieldset>
                <legend>${res['plan.legend.inspireid']}</legend>
                <cust:field key="plan.inspireidLocalid"/>
                <cust:readOnlyField key="plan.inspireidNamespace" value="${actionBean.plan.inspireidNamespace}" hiddenField="true"/>
                <cust:readOnlyField key="plan.inspireidVersionid" value="${actionBean.plan.inspireidVersionid}" hiddenField="true"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.provider']}</legend>
                <cust:field key="plan.providerBean.organisationname"/>
                <cust:field key="plan.providerBean.website"/>
                <cust:field key="plan.providerBean.individualname"/>
                <cust:textArea key="plan.providerBean.address"/>
                <cust:field key="plan.providerBean.telephonevoice"/>
                <cust:field key="plan.providerBean.electronicmailaddress"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.change']}</legend>
                <cust:label key="plan.changes" optional="true"/><s:checkbox name="plan.changes"/><br/>
                <cust:textArea key="plan.descriptionofchanges"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.reportingperiod']}</legend>
                <cust:field key="plan.reportingstartdate" cssclass="datepicker"/>
                <cust:field key="plan.reportingenddate" cssclass="datepicker"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.code']}</legend>					
                <cust:field key="plan.code"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.name']}</legend>	
                <cust:field key="plan.name"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.competentauthority']}</legend>
                <cust:field key="plan.relatedpartyBean.organisationname"/>
                <cust:field key="plan.relatedpartyBean.website"/>
                <cust:field key="plan.relatedpartyBean.individualname"/>
                <cust:textArea key="plan.relatedpartyBean.address"/>
                <cust:field key="plan.relatedpartyBean.telephonevoice"/>
                <cust:field key="plan.relatedpartyBean.electronicmailaddress"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.firstexceedanceyearTimeposition']}</legend>					
                <cust:field key="plan.firstexceedanceyearTimeposition"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.status']}</legend>					
                <cust:label key="plan.link"/>
                <s:select name="plan.link">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.possibleStatus}" value="link" label="preferredLabel"/>
                </s:select>
                <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/statusaqplan"/>
            </fieldset>

            <fieldset>
                <legend class="required">${res['plan.legend.pollutantcovered']}</legend>	
                <security:allowed event="save">
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.PollutantActionBean" event="form" id="new-pol-btn" class="ftm insert btn">
                        <s:param name="planId" value="${actionBean.planId}"/>
                        ${res['plan.button.addnewpollutant']}
                    </s:link>
                </security:allowed>

                <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.PollutantActionBean" event="table">
                    <s:param name="planId" value="${actionBean.plan.uuid}"/>
                </s:url>
                <cust:ajaxtable>
                    <jsp:attribute name="tableId">pollutantGUI</jsp:attribute>
                    <jsp:attribute name="loadTableURL">${loadTableURL}</jsp:attribute>
                    <jsp:attribute name="sorting_column">0</jsp:attribute>
                    <jsp:attribute name="sorting_order">asc</jsp:attribute>
                    <jsp:attribute name="newItemDialogHeight">380</jsp:attribute>
                    <jsp:attribute name="newItemDialogWidth">650</jsp:attribute>
                    <jsp:attribute name="newItemDialogTitle">${res['pollutant.dialog.title']}</jsp:attribute>
                    <jsp:attribute name="addNewItemButtonId">new-pol-btn</jsp:attribute>
                </cust:ajaxtable>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.dateofofficialadoption']}</legend>					
                <cust:field cssclass="datepicker" key="plan.adoptiondateTimeposition" optional="true"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.timetableofimplementation']}</legend>					
                <cust:field key="plan.timetable"/>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.referencetoairqualityplan']}</legend>					
                <cust:field key="plan.referenceaqplan"/>
            </fieldset>
            <fieldset>
                <legend>${res['plan.legend.referencetoimplementation']}</legend>					
                <cust:field key="plan.referenceimplementation"/>
            </fieldset>

            <fieldset>
                <legend class="required">${res['plan.legend.relevantpublication']}</legend>

                <security:allowed event="save">
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.PublicationActionBean" event="form" id="new-pub-btn" class="ftm insert btn">
                        <s:param name="planId" value="${actionBean.planId}"/>
                        ${res['plan.button.addnewpublication']}
                    </s:link>
                </security:allowed>

                <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.PublicationActionBean" event="table">
                    <s:param name="planId" value="${actionBean.plan.uuid}"/>
                </s:url>
                <cust:ajaxtable>
                    <jsp:attribute name="tableId">publicationGUI</jsp:attribute>
                    <jsp:attribute name="loadTableURL">${loadTableURL}</jsp:attribute>
                    <jsp:attribute name="sorting_column">0</jsp:attribute>
                    <jsp:attribute name="sorting_order">asc</jsp:attribute>
                    <jsp:attribute name="newItemDialogHeight">395</jsp:attribute>
                    <jsp:attribute name="newItemDialogWidth">600</jsp:attribute>
                    <jsp:attribute name="newItemDialogTitle">${res['publication.dialog.title']}</jsp:attribute>
                    <jsp:attribute name="addNewItemButtonId">new-pub-btn</jsp:attribute>
                </cust:ajaxtable>
            </fieldset>

            <fieldset>
                <legend>${res['plan.legend.comment']}</legend>
                <cust:textArea key="plan.comment" optional="true"/>
            </fieldset>	

            <fieldset>
                <legend>${res['common.linkedresource.plural']}</legend>
                <cust:label key="plan.attainmentBeanList"/>
                <s:select class="multi" multiple="true" name="plan.attainmentBeanList" size="12">
                    <s:options-collection collection="${actionBean.existingAttainments}" value="uuid" label="inspireidLocalid" sort="inspireidLocalid"/>
                </s:select>
            </fieldset>

            <br/>

            <security:allowed event="save">
                <s:submit class="ftm save btn" name="save" value="${res['common.save']}"/>
            </security:allowed>
        </s:form>
            
    </s:layout-component>
</s:layout-render>