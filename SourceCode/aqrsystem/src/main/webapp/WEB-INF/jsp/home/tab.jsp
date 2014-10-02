<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <h2>${res['home.statisctics.label']}</h2>

        <div class="sbox">
            <h3><s:link beanclass="eu.europa.ec.aqrsystem.action.PlanActionBean">${res['home.statistics.plans']}</s:link></h3>
                <ul>
                    <li>${res['home.draft']}: ${actionBean.statistics.planDrafts}</li>	
                <li>${res['home.complete']}: ${actionBean.statistics.planComplete}</li>		
            </ul>
        </div>

        <div class="sbox">
            <h3><s:link beanclass="eu.europa.ec.aqrsystem.action.ApportionmentActionBean">${res['home.statistics.sources']}</s:link></h3>
                <ul>
                    <li>${res['home.draft']}: ${actionBean.statistics.sourceDrafts}</li>	
                <li>${res['home.complete']}: ${actionBean.statistics.sourceComplete}</li>		
            </ul>
        </div>

        <div class="sbox">
            <h3><s:link beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean">${res['home.statistics.scenarios']}</s:link></h3>
                <ul>
                    <li>${res['home.draft']}: ${actionBean.statistics.scenarioDrafts}</li>	
                <li>${res['home.complete']}: ${actionBean.statistics.scenarioComplete}</li>		
            </ul>
        </div>

        <div class="sbox">
            <h3><s:link beanclass="eu.europa.ec.aqrsystem.action.MeasureActionBean">${res['home.statistics.measures']}</s:link></h3>
                <ul>
                    <li>${res['home.draft']}: ${actionBean.statistics.measureDrafts}</li>	
                <li>${res['home.complete']}: ${actionBean.statistics.measureComplete}</li>	
            </ul>
        </div>
    </s:layout-component>
</s:layout-render>