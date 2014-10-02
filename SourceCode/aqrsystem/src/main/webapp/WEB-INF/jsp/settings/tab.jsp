<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <div id="tabs" class="tabs" data-activetab="${actionBean.activeTab}">
            <s:useActionBean var="userBean" beanclass="eu.europa.ec.aqrsystem.action.UserActionBean"/>

            <ul>
                <security:allowed event="importData"><li><a href="#tab-attainments">${res['settings.attainment.label']}</a></li></security:allowed>
                <security:allowed bean="userBean"><li><a href="#tab-users">${res['settings.users.label']}</a></li></security:allowed>
                <security:allowed event="saveNamespace"><li><a href="#tab-namespace">${res['settings.namespace.label']}</a></li></security:allowed>
                <security:allowed event="saveProvider"><li><a href="#tab-provider">${res['settings.provider.label']}</a></li></security:allowed>
                </ul>


            <security:allowed event="importData">
                <div id="tab-attainments" class="one-tab">
                    <s:form beanclass="eu.europa.ec.aqrsystem.action.SettingsActionBean">
                        <input type="hidden" name="activeTab" value="tab-attainments"/>
                        <fieldset>
                            <legend>${res['attainments']}</legend>
                            <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.SettingsActionBean" event="table"/>
                            <cust:ajaxtable>
                                <jsp:attribute name="tableId">attainmentGUI</jsp:attribute>
                                <jsp:attribute name="loadTableURL">${loadTableURL}</jsp:attribute>
                                <jsp:attribute name="sorting_column">1</jsp:attribute>
                                <jsp:attribute name="sorting_order">desc</jsp:attribute>
                            </cust:ajaxtable>

                        </fieldset>
                    </s:form>

                    <cust:jqueryLink cssclass="ftm insertfromfile btn right" label="${res['attainments.import']}" title="${res['attainments.import.title']}" id="import-btn"/>

                    <cust:importDialog>
                        <jsp:attribute name="label">${res['attainments.import']}</jsp:attribute>
                        <jsp:attribute name="beanclass">eu.europa.ec.aqrsystem.action.SettingsActionBean</jsp:attribute>
                        <jsp:attribute name="file">xmlexamples/attainments.xml</jsp:attribute>
                    </cust:importDialog>

                    <br/><br/>
                </div>
            </security:allowed>
            <security:allowed bean="userBean">
                <div id="tab-users" class="one-tab">
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.UserActionBean" event="form" id="new-usr-btn" class="ftm insert btn">
                        ${res['settings.user.addnewuser']}
                    </s:link>

                    <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.UserActionBean" event="table"/>

                    <cust:ajaxtable>
                        <jsp:attribute name="tableId">usersGUI</jsp:attribute>
                        <jsp:attribute name="loadTableURL">${loadTableURL}</jsp:attribute>
                        <jsp:attribute name="sorting_column">0</jsp:attribute>
                        <jsp:attribute name="sorting_order">asc</jsp:attribute>
                        <jsp:attribute name="newItemDialogHeight">380</jsp:attribute>
                        <jsp:attribute name="newItemDialogWidth">600</jsp:attribute>
                        <jsp:attribute name="newItemDialogTitle">${res['settings.user.dialog.title']}</jsp:attribute>
                        <jsp:attribute name="addNewItemButtonId">new-usr-btn</jsp:attribute>
                    </cust:ajaxtable>
                </div>
            </security:allowed>
            <security:allowed event="saveNamespace">
                <div id="tab-namespace" class="one-tab">
                    <s:form beanclass="eu.europa.ec.aqrsystem.action.SettingsActionBean">
                        <input type="hidden" name="activeTab" value="tab-namespace"/>
                        <fieldset>
                            <legend>${res['namespace']}</legend>
                            <cust:field key="namespace"/>
                        </fieldset>
                        <s:submit class="ftm save btn" name="saveNamespace" value="${res['common.save']}"/>
                    </s:form>

                    <br/><br/>
                </div>
            </security:allowed>
            <security:allowed event="saveProvider">
                <div id="tab-provider" class="one-tab">
                    <s:form beanclass="eu.europa.ec.aqrsystem.action.SettingsActionBean">
                        <input type="hidden" name="activeTab" value="tab-provider"/>
                        <fieldset>
                            <legend>${res['currentUser.providerBean.legend']}</legend>
                            <cust:field key="currentUser.providerBean.organisationname"/>
                            <cust:field key="currentUser.providerBean.website"/>
                            <cust:field key="currentUser.providerBean.individualname"/>
                            <cust:textArea key="currentUser.providerBean.address"/>
                            <cust:field key="currentUser.providerBean.telephonevoice"/>
                            <cust:field key="currentUser.providerBean.electronicmailaddress"/>
                        </fieldset>
                        <s:submit class="ftm save btn" name="saveProvider" value="${res['common.save']}"/>
                    </s:form>

                    <br/><br/>
                </div>
            </security:allowed>
        </div>
    </s:layout-component>
</s:layout-render>