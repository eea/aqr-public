<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout.jsp">
    <s:layout-component name="content">
        <s:messages/>
        <s:errors/>

        <p>${res['loginpage.explanation.intro']}</p>
        <p>${res['loginpage.explanation']}</p>
        <ol>
            <li>${res['loginpage.explanation.option1']}</li>
            <li>${res['loginpage.explanation.option2']}</li>
            <li>${res['loginpage.explanation.option3']}</li>
            <li>${res['loginpage.explanation.option4']} <a target="_blank" href="https://webgate.ec.europa.eu/cas/help.html?loginRequestId=ECAS_LR-13321911-BEWVudKCYJ9gazd91ChSy9MeBXLf8ni77BuzlIdXGzeZ446DOplEkiCYaxzpus1RZRCNUwLAtZ7Oy1NuEqa4bfi-PHslUMVSXYCDzpzzWg1zyPO-MHACNrIyndzwzQaiyvJCo6xG3fuDUas2MJdGE0VFzm0O">${res['loginpage.explanation.option4.helpdesk']}</a></li>
            <li>${res['loginpage.explanation.option5']}</li>
            <li>
                ${res['loginpage.explanation.option6']}
                <table>
                    <tr>
                        <td> <a href="mailto:wolfgang.spangl@umweltbundesamt.at">Austria</a></td>
                        <td><a href="mailto:fierens@irceline.be;peeters@irceline.be">Belgium</a></td>
                        <td><a href="mailto:z.solarska@eea.government.bg;fonmon@eea.government.bg;gyordanova@moew.government.bg">Bulgaria</a></td>
                        <td><a href="mailto:vedran.vadic@azo.hr">Croatia</a></td>
                    </tr>
                    <tr>
                         <td><a href="mailto:skleanthous@dli.mlsi.gov.cy;cpapadopoulos@dli.mlsi.gov.cy">Cyprus</a></td>
                         <td><a href="mailto:Tarja.Lahtinen@ymparisto.fi;mika.vestenius@fmi.fi">Finland</a></td>
                         <td><a href="mailto:thomas.bouyer@developpement-durable.gouv.fr;soes-webdata-contact@developpement-durable.gouv.fr;aurelie.volokhoff@developpement-durable.gouv.fr">France</a></td>
                         <td><a href="mailto:marcel.richter@uba.de">Germany</a></td>
                    </tr>
                    <tr>
                         <td><a href="mailto:gyarmatine.e@met.hu;puskas.monika@met.hu;judit.varga@fm.gov.hu">Hungary</a></td>
                         <td><a href="mailto:kristjan.andresson@ust.is;sigurdurb@ust.is;Vanda.Hellsing@umhverfisstofnun.is;thorsteinnj@ust.is">Iceland</a></td>
                         <td><a href="mailto:m.odwyer@epa.ie">Ireland</a></td>
                         <td><a href="mailto:tamara.vasiljeva@lvgmc.lv">Latvia</a></td>
                    </tr>
                    <tr>
                          <td><a href="mailto:mindaugas.bernatonis@aaa.am.lt;vilma.bimbaite@aaa.am.lt;p.blonskis@aaa.am.lt;d.perkauskas@aaa.am.lt;z.siliene@aaa.am.lt">Lithuania</a></td>
                          <td><a href="mailto:pierre.dornseiffer@aev.etat.lu;serge.solagna@aev.etat.lu;eric.vansuypeene@aev.etat.lu">Luxembourg</a></td>
                          <td><a href="mailto:Hans.Berkhout@rivm.nl;wil.prins@minienm.nl;peter.vervoorn@rws.nl">Netherlands</a></td>
                          <td><a href="mailto:borghild.rime.bay@miljodir.no;silje.aksnes.bratland@miljodir.no;Sigmund.guttu@miljodir.no;Nina.Landvik@miljodir.no">Norway</a></td>
                    </tr>
                    <tr>
                        <td><a href="mailto:grazyna.sztandera@mos.gov.pl;marzena.zawalich@mos.gov.pl">Poland</a></td>
                        <td><a href="mailto:cristina.figueiredo@ccdr-n.pt;dilia.jardim@apambiente.pt;isabel.marques@ccdr-lvt.pt;claudia.martins@apambiente.pt;sandra.mesquita@ccdr-lvt.pt;vitor.monteiro@ccdr-n.pt;luisa.nogueira@ccdr-lvt.pt">Portugal</a></td>
                        <td><a href="mailto:alexandru.brusten@anpm.ro;patricia.lungu@anpm.ro">Romania</a></td>
                        <td><a href="mailto:jana.matejovicova@shmu.sk;radoslav.virgovic@sazp.sk">Slovakia</a></td>
                    </tr>
                    <tr>
                        <td><a href="mailto:Jana.Faganeli-Pucer@gov.si">Slovenia</a></td>
                        <td><a href="mailto:mpallares@magrama.es">Spain</a></td>
                        <td><a href="mailto:Anders.Foureaux@naturvardsverket.se;titus.kyrklund@naturvardsverket.se;Matthew.Ross-Jones@Naturvardsverket.se;helena.sabelstrom@naturvardsverket.se">Sweden</a></td>
                        <td><a href="mailto:tony.bush@ricardo-aea.com;Sally.Cooke@ricardo-aea.com;Charlotte.Jones@defra.gsi.gov.uk;John.Newington@defra.gsi.gov.uk;John.Stedman@aeat.co.uk">United Kingdom</a></td>
                    </tr>
                </table>
            </li>
            <li>${res['loginpage.explanation.option7']} <a href="mailto:aqipr.helpdesk@eionet.europa.eu">${res['loginpage.explanation.option7.helpdesk']}</a></li>
        </ol>
        <p>${res['loginpage.explanation.option8']} <a href="http://www.eionet.europa.eu/aqportal/plansandprogrammes" target="_blank">${res['loginpage.explanation.option8.helpdesk']}</a></p>


        <br/><br/>

    </s:layout-component>
</s:layout-render>