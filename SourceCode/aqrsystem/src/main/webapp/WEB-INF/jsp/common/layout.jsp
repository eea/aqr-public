<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-definition>
    <!DOCTYPE html>
    <html>
        <head>        
            <title>${res['common.title']}</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="application-name" content="${res['application.name']}/>"/>
            <!-- Styles -->
            <link href="css/style.css?${res['application.version']}" media="screen" rel="stylesheet" type="text/css">
            <link rel="icon" href="img/favicon.ico" type="image/x-icon" /> 
            <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
            <!-- Scripts -->
            <script src="js/common/iefix.js?${res['application.version']}"></script>
            <!--[if lt IE 9]><script type="text/javascript" src="js/libs/modernizr.js"></script><![endif]-->
            <script src="js/libs/jquery.min.js"></script>
            <script src="js/libs/jquery.dataTables.js"></script>
            <script src="js/libs/jquery-ui.min.js"></script>
            <script src="js/libs/jquery.livequery.min.js"></script>
            <script src="js/libs/tag-it.min.js"></script>
            <script src="js/localization/customMessagesManager.js?${res['application.version']}"></script>
            <script src="js/localization/customMessagesManager_${res['common.langid']}.js?${res['application.version']}"></script>
            <script src="js/common/ErrorProofExec.js?${res['application.version']}"></script>
            <script src="js/common/Listeners.js?${res['application.version']}"></script>
            <script src="js/common/utils.js?${res['application.version']}"></script>
            <script src="js/common/toDoWarningManager.js?${res['application.version']}"></script>
            <script src="js/common/confirmationMessageManager.js?${res['application.version']}"></script>
            <script src="js/common/languageManager.js?${res['application.version']}"></script>
            <script src="js/common/datePickerManager.js?${res['application.version']}"></script>
            <script src="js/common/NonAjaxDialog.js?${res['application.version']}"></script>
            <script src="js/common/AjaxReloadableComponent.js?${res['application.version']}"></script>
            <script src="js/common/AjaxDialog.js?${res['application.version']}"></script>
            <script src="js/common/AjaxTable.js?${res['application.version']}"></script>
            <script src="js/common/formsManager.js?${res['application.version']}"></script>
            <script src="js/common/userManualManager.js?${res['application.version']}"></script>
            <!-- these files may be included based on needs, if there are more of them -->
            <script src="js/common/pollutantManager.js?${res['application.version']}"></script>
            <script src="js/common/costManager.js?${res['application.version']}"></script>
            <script src="js/common/changeCheckboxManager.js?${res['application.version']}"></script>
            <script src="js/common/importDialogManager.js?${res['application.version']}"></script>
            <script src="js/common/tabManager.js?${res['application.version']}"></script>
            <script src="js/common/userFormManager.js?${res['application.version']}"></script>
            <script src="js/common/nilReasonFieldsManager.js?${res['application.version']}"></script>
            <script src="js/common/tagItManager.js?${res['application.version']}"></script>
        </head>
        <body>
            <header>
                <div id="topright">
                    <div id="topright-links">
                        <cust:jqueryLink id="about" label="${res['header.about']}"/> | 
                        <a href="mailto:${res['application.contact']}">${res['header.contact']}</a> |         
                        <a target="_blank" href="http://ec.europa.eu/geninfo/legal_notices_en.htm">${res['header.legal-notice']}</a> |
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.LoginActionBean" event="logout">${res['header.logout']}</s:link>
                            <br/><br/>
                        <c:if test="${actionBean.userEmail != null}">
                            ${res['user.label']}: ${actionBean.userEmail}
                        </c:if>
                    </div>
                    <cust:include
                        beanclass="eu.europa.ec.aqrsystem.action.LocalisationActionBean"
                        event="showSelect"/>
                </div>
                <div id="breadctxt">
                    <a href="http://ec.europa.eu/index_en.htm">${res['common.eucom']}</a>&nbsp;&gt;
                    <s:link href="/">${res['common.title']}</s:link>
                    </div>

                    <!--About dialog-->
                <cust:dialog>
                    <jsp:attribute name="height">230</jsp:attribute>
                    <jsp:attribute name="width">400</jsp:attribute>
                    <jsp:attribute name="dialogId">aboutd</jsp:attribute>
                    <jsp:attribute name="openDialogButtonId">about</jsp:attribute>
                    <jsp:body>
                        <div class="dialog" id="aboutd" title="${res['about.label']}">
                            <p>${res['about.text']}</p>
                            <br/>
                            <cust:jqueryLink cssclass="btn ftm ok btn_dialog_cancel" label="${res['common.ok']}"/>
                        </div>
                    </jsp:body>
                </cust:dialog>
            </header>

            <div class="container">
                <s:layout-component name="content"/>
            </div>

            <footer>
                <div class="cnt">${res['common.title']} - v${res['application.version']}</div>
            </footer>

            <div class="dbc"><!-- the menu on the right hand side -->
                <div class="dxbox help">
                    <span><a target="_blank" href="UserManual.pdf">${res['common.usermanual']}</a></span>
                </div>
            </div>             
        </body>
    </html>
</s:layout-definition>