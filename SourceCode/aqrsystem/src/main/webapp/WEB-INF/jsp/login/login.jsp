<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout.jsp">
    <s:layout-component name="content">
        <s:messages/>
        <s:errors/>

        <p>${res['loginpage.explanation']}</p>
        <ol>
            <li>${res['loginpage.explanation.option1']}</li>
            <li>${res['loginpage.explanation.option2']}</li>
            <li>${res['loginpage.explanation.option3']}</li>
            <li>${res['loginpage.explanation.option4']} <a target="_blank" href="https://webgate.ec.europa.eu/cas/help.html?loginRequestId=ECAS_LR-13321911-BEWVudKCYJ9gazd91ChSy9MeBXLf8ni77BuzlIdXGzeZ446DOplEkiCYaxzpus1RZRCNUwLAtZ7Oy1NuEqa4bfi-PHslUMVSXYCDzpzzWg1zyPO-MHACNrIyndzwzQaiyvJCo6xG3fuDUas2MJdGE0VFzm0O">${res['loginpage.explanation.option4.helpdesk']}</a></li>
        </ol>
        <p>${res['loginpage.explanation.option5']} <a href="http://www.eionet.europa.eu/aqportal/plansandprogrammes" target="_blank">${res['loginpage.explanation.option5.helpdesk']}</a></p>
        <br/><br/>
        <s:link class="ftm insert btn login" beanclass="eu.europa.ec.aqrsystem.action.HomeActionBean">${res['loginpage.button']}</s:link>

    </s:layout-component>
</s:layout-render>