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
            <link href="css/eionet-style.css?${res['application.version']}" media="screen" rel="stylesheet" type="text/css">
            <link rel="stylesheet" type="text/css" href="http://www.eionet.europa.eu/styles/eionet2007/print.css" media="print" />
            <link rel="stylesheet" type="text/css" href="http://www.eionet.europa.eu/styles/eionet2007/handheld.css" media="handheld" />
            <link rel="stylesheet" type="text/css" href="http://www.eionet.europa.eu/styles/eionet2007/screen.css" media="screen" title="Eionet 2007 style" />
            <link rel="stylesheet" type="text/css" href="css/eionet2007.css" media="screen" title="Eionet 2007 style"/>
            <link rel="stylesheet" type="text/css" href="css/application.css" media="screen"/>

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
        <body ${bodyAttribute}>
            <div id="container">
                <div id="toolribbon">
                    <div id="lefttools">
                        <a id="eealink" href="http://www.eea.europa.eu/">EEA</a>
                        <a id="ewlink" href="http://www.ewindows.eu.org/">EnviroWindows</a>
                    </div>
                    <div id="righttools">
                        <!-- Temporarily removed language selection -->
                        <div style="display:none;">
                          <cust:include
                            beanclass="eu.europa.ec.aqrsystem.action.LocalisationActionBean"
                            event="showSelect"/>
                         </div>
                        <div>
                          <c:choose>
                              <c:when test="${empty actionBean.userEmail}">
                                  <!--<s:link class="ftm insert btn login" beanclass="eu.europa.ec.aqrsystem.action.HomeActionBean">${res['loginpage.button']}</s:link>-->
                              </c:when>
                              <c:otherwise>
                                  <s:link beanclass="eu.europa.ec.aqrsystem.action.LoginActionBean" event="logout">${res['header.logout']}</s:link>
                              </c:otherwise>
                          </c:choose>
                          <a id="printlink" title="Print this page" href="javascript:this.print();"><span>Print</span></a>
                          <a id="fullscreenlink" href="javascript:toggleFullScreenMode()" title="Switch to/from full screen mode"><span>Switch to/from full screen mode</span></a>
                          <a id="acronymlink" href="http://www.eionet.europa.eu/acronyms" title="About ${res['common.title']}"><span>About</span></a>
                        </div>
                    </div>
                </div><!-- toolribbon -->
              <div id="pagehead">
                  <a href="/"><img src="images/eea-print-logo.gif" alt="Logo" id="logo" /></a>
                  <div id="networktitle">Eionet</div>
                  <div id="sitetitle">${res['common.title']}</div>
                  <div id="sitetagline">${res['common.subtitle']}</div>
              </div> <!-- pagehead -->
              <div id="menuribbon">
              <!-- Last updated: 2014-10-22T07:11:32Z
                   To update download http://www.eionet.europa.eu/dropdownmenus.txt -->
                  <ul id="dropdowns">
                   <li><a href="http://www.eionet.europa.eu/menuservices">Services</a>
                    <ul>
                     <li><a href="http://www.eionet.europa.eu/helpdesk">Helpdesk</a></li>
                     <li><a href="http://forum.eionet.europa.eu/">Forum</a></li>
                     <li><a href="http://projects.eionet.europa.eu/">Projects</a></li>
                     <li><a href="http://www.eionet.europa.eu/ldap-roles">Roles</a></li>
                     <li><a href="http://planner.eionet.europa.eu/">Workplan</a></li>
                     <li><a href="http://uns.eionet.europa.eu/">Subscriptions</a></li>
                     <li><a href="http://www.eionet.europa.eu/networknews">News</a></li>
                     <li><a href="http://www.eionet.europa.eu/calendar">Calendar</a></li>
                     <li><a href="http://www.eionet.europa.eu/rolemails">NFP mails</a></li>
                     <li><a href="http://archives.eionet.europa.eu/">Archived IGs</a></li>
                    </ul>
                   </li>
                   <li><a href="http://www.eionet.europa.eu/menureportnet">Reportnet</a>
                    <ul>
                     <li><a href="http://cdr.eionet.europa.eu/">CDR Repository</a></li>
                     <li><a href="http://rod.eionet.europa.eu/">ROD Obligations</a></li>
                     <li><a href="http://dd.eionet.europa.eu/">Data Dictionary</a></li>
                     <li><a href="http://etcdd.eionet.europa.eu/">ETC Data Dictionary</a></li>
                     <li><a href="http://cr.eionet.europa.eu/">Content Registry</a></li>
                     <li><a href="http://converters.eionet.europa.eu/">Conversion service</a></li>
                     <li><a href="http://mdr.eionet.europa.eu/">Mediterranean Data Repository</a></li>
                     <li><a href="https://bdr.eionet.europa.eu/">Business Data Repository</a></li>
                     <li><a href="http://dampos.eionet.europa.eu/">Dam Positioning</a></li>
                     <li><a href="http://papers.eionet.europa.eu/">AQ Plans &amp; Programmes reporting</a></li>
                     <li><a href="http://uwwtd.eionet.europa.eu/">UWWTD reporting</a></li>
                     <li><a href="http://www.eionet.europa.eu/reportnet">Reportnet Documents</a></li>
                    </ul>
                   </li>
                   <li><a href="http://www.eionet.europa.eu/menutools">Tools</a>
                    <ul>
                     <li><a href="http://www.eionet.europa.eu/gis/">GIS</a></li>
                     <li><a href="http://www.eionet.europa.eu/seis/">SEIS</a></li>
                     <li><a href="http://www.eionet.europa.eu/software/">Software</a></li>
                     <li><a href="https://svn.eionet.europa.eu/">SVN Repository</a></li>
                     <li><a href="https://taskman.eionet.europa.eu/">Task Management</a></li>
                     <li><a href="http://www.eionet.europa.eu/software/design">Design Guidelines</a></li>
                     <li><a href="http://www.eionet.europa.eu/software/swstandards">Standards</a></li>
                     <li><a href="http://ci.eionet.europa.eu/">Continuous Integration</a></li>
                     <li><a href="http://archiva.eionet.europa.eu/">Maven repository</a></li>
                     <li><a href="http://www.eionet.europa.eu/gemet">GEMET Thesaurus</a></li>
                     <li><a href="http://sparqlclient.eionet.europa.eu/">SPARQL client</a></li>
                    </ul>
                   </li>
                   <li><a href="http://www.eionet.europa.eu/menutopics">Topics (ETCs)</a>
                    <ul>
                     <li><a href="http://acm.eionet.europa.eu/">Air pollution and Climate Change mitigation</a></li>
                     <li><a href="http://bd.eionet.europa.eu/">Biological Diversity</a></li>
                     <li><a href="http://cca.eionet.europa.eu/">Climate Change impacts, vulnerability and Adaptation</a></li>
                     <li><a href="http://etc-wmge.vito.be/">ETC WMGE Waste and Material in Green Economy</a></li>
                     <li><a href="http://icm.eionet.europa.eu/">Inland, Coastal and Marine waters</a></li>
                     <li><a href="http://uls.eionet.europa.eu/">Urban, Land and Soil systems</a></li>
                    </ul>
                   </li>
                  </ul>
              </div>
              <div class="breadcrumbtrail">
                <div class="breadcrumbhead">You are here:</div>
                <div class="breadcrumbitem eionetaccronym">
                  <a href="http://www.eionet.europa.eu">Eionet</a>
                </div>
                <div class="breadcrumbitem"><a href="http://ec.europa.eu/index_en.htm">${res['common.eucom']}</a></div>
                <div class="breadcrumbitemlast"><s:link href="/">${res['common.title']}</s:link></div>
                <div class="breadcrumbtail"></div>
              </div>
              <!--<div id="leftcolumn" class="localnav">
                <ul>
                  <li><a href=""></a></li>
                </ul>
              </div>-->
              <div id="workarea">
                <div class="nocontainer">
                  <s:layout-component name="content"/>
                </div>
              </div>

              <div id="logo-wrapper">
                <div id="pagefoot" style="float:left; max-width: none;">
                  <p><a href="mailto:cr@eionet.europa.eu">E-mail</a> | <a href="mailto:helpdesk@eionet.europa.eu?subject=Feedback from the ${initParam.appDispName} website">Feedback</a></p>
                  <p><a href="http://www.eea.europa.eu/"><b>European Environment Agency</b></a>
                  <br/>Kgs. Nytorv 6, DK-1050 Copenhagen K, Denmark - Phone: +45 3336 7100</p>
                </div>
                <div id="commission-logo"></div>
              </div>

              <div class="dbc"><!-- the menu on the right hand side -->
                  <div class="dxbox help">
                      <span><a target="_blank" href="UserManual.pdf">${res['common.usermanual']}</a></span>
                  </div>
              </div>
            </div>
        </body>
    </html>
</s:layout-definition>