<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <h2 class="evsc">${res['evaluation.label.edit']}</h2>

        <fieldset>
            <legend class="evsc">${res['description.label.edit']}</legend>
            <p>${res['evaluation.description.model']}</p>
            <p align="right">${res['fields.marked']}</p>
            <p align="right">${res['fields.conditional']}</p>
        </fieldset>  

        <s:form class="${actionBean.evaluation.editable ? 'protected' : ''}" beanclass="eu.europa.ec.aqrsystem.action.EditEvaluationActionBean">
            <div><s:hidden name="evaluation.uuid"/></div>
            <div><s:hidden name="evaluation.editable"/></div>
            <div><s:hidden name="evaluationId"/></div>

            <fieldset>
                <legend class="evsc">${res['evaluation.legend.inspireid']}</legend>
                <cust:field key="evaluation.inspireidLocalid"/>
                <cust:readOnlyField key="evaluation.inspireidNamespace" value="${actionBean.evaluation.inspireidNamespace}" hiddenField="true"/>
                <cust:readOnlyField key="evaluation.inspireidVersionid" value="${actionBean.evaluation.inspireidVersionid}" hiddenField="true"/>
            </fieldset>

            <fieldset>
                <legend class="evsc">${res['evaluation.legend.providerBean']}</legend>
                <cust:field key="evaluation.providerBean.organisationname"/>
                <cust:field key="evaluation.providerBean.website"/>
                <cust:field key="evaluation.providerBean.individualname"/>
                <cust:textArea key="evaluation.providerBean.address"/>
                <cust:field key="evaluation.providerBean.telephonevoice"/>
                <cust:field key="evaluation.providerBean.electronicmailaddress"/>
            </fieldset>

            <fieldset>
                <legend class="evsc">${res['evaluation.legend.change']}</legend>
                <s:hidden name="evaluation.changes"/>
                <%--<cust:label key="evaluation.changes" optional="true"/><s:checkbox name="evaluation.changes"/><br/>--%>
                <cust:textArea key="evaluation.descriptionofchanges"/>
            </fieldset>

            <fieldset>
                <legend class="evsc">${res['evaluation.legend.reportingperiod']}</legend>
                <cust:field key="evaluation.reportingstartdate" cssclass="datepicker"/>
                <cust:field key="evaluation.reportingenddate" cssclass="datepicker"/>
            </fieldset>

            <fieldset>
                <legend class="evsc">${res['evaluation.legend.codeofscenario']}</legend>					
                <cust:field key="evaluation.codeofscenario"/>
            </fieldset>

            <fieldset>
                <legend class="evsc" class="required">${res['evaluation.legend.relevantpublication']} <font color="red">*</font></legend>
                    <security:allowed event="save">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.PublicationForEvaluationActionBean" event="form" id="new-pub-btn" class="ftm insert btn evsc">
                            <s:param name="evaluationId" value="${actionBean.evaluationId}"/>
                            ${res['evaluation.button.addnewpublication']}
                        </s:link>
                    </security:allowed>

                <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.PublicationForEvaluationActionBean" event="table">
                    <s:param name="evaluationId" value="${actionBean.evaluation.uuid}"/>
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
                <legend class="evsc">${res['evaluation.legend.attainmentyearPeriodtime']}</legend>					
                <cust:field key="evaluation.attainmentyearPeriodtime"/>
            </fieldset>

            <fieldset>
                <legend class="evsc">${res['evaluation.legend.startyearPeriodtime']}</legend>					
                <cust:field key="evaluation.startyearPeriodtime"/>
            </fieldset>

            <fieldset>
                <legend class="evsc">${res['evaluation.legend.baselinescenario']}</legend>
                <div><s:hidden name="evaluation.baselinescenario.uuid"/></div>

                <cust:textArea key="evaluation.baselinescenario.description"/>
                <cust:field key="evaluation.baselinescenario.totalemissions"/>
                <cust:field key="evaluation.baselinescenario.expectedconcentration" optional="true"/>
                <cust:field key="evaluation.baselinescenario.expectedexceedence" optional="true"/>
                <cust:textArea key="evaluation.baselinescenario.comment" optional="true"/>
                <cust:label key="evaluation.baselinescenario.measuresUuid"/>
                <s:select class="multi" multiple="true" name="evaluation.baselinescenario.measuresUuid">
                    <s:options-collection collection="${actionBean.existingMeasures}" value="uuid" label="inspireidLocalid" sort="inspireidLocalid"/>
                </s:select>
            </fieldset>

            <fieldset>
                <legend class="evsc">${res['evaluation.legend.projectionscenario']}</legend>
                <div><s:hidden name="evaluation.projectionscenario.uuid"/></div>

                <cust:textArea key="evaluation.projectionscenario.description"/>
                <cust:field key="evaluation.projectionscenario.totalemissions"/>
                <cust:field key="evaluation.projectionscenario.expectedconcentration" optional="true"/>
                <cust:field key="evaluation.projectionscenario.expectedexceedence" optional="true"/>
                <cust:textArea key="evaluation.projectionscenario.comment" optional="true"/>
                <cust:label key="evaluation.projectionscenario.measuresUuid"/>
                <s:select class="multi" multiple="true" name="evaluation.projectionscenario.measuresUuid">
                    <s:options-collection collection="${actionBean.existingMeasures}" value="uuid" label="inspireidLocalid" sort="inspireidLocalid"/>
                </s:select>
            </fieldset>

            <fieldset>
                <legend class="evsc">${res['common.linkedresource.plural']}</legend>
                <cust:label key="evaluation.plan.uuid" optional="true"/>
                <s:select name="evaluation.plan.uuid">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.existingPlans}" value="uuid" label="inspireidLocalid" sort="inspireidLocalid"/>
                </s:select>
                <cust:label key="evaluation.sourceapportionment.uuid" optional="true"/>
                <s:select name="evaluation.sourceapportionment.uuid">
                    <s:option value="">None</s:option>
                    <s:options-collection collection="${actionBean.existingSourceApportionments}" value="uuid" label="inspireidLocalid" sort="label"/>
                </s:select>
            </fieldset>

            <br/>
            <security:allowed event="save">
                <s:submit class="ftm save evsc btn" name="save" value="${res['common.save']}"/>
            </security:allowed>

        </s:form>
    </s:layout-component>
</s:layout-render>