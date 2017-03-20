<%@ page import="com.urise.webapp.model.*" %>
<%@ page import="com.urise.webapp.utils.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" valu="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты</h3>
        <p>
            <c:forEach var="type" items="<%=ContactType.values()%>">
        <dl>
            <dt>${type.title}</dt>
            <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
        </dl>
        </c:forEach>
        <hr>


        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
            <h3><a>${type.title}</a></h3>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <input type="text" name="${type}" size="75" value="<%=((TextSection)section).getContent()%>">
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <textarea name="${type}" cols="75" rows=5><%=((TextSection)section).getContent()%></textarea>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <textarea name="${type}" cols="75"
                              rows=5><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>"
                               varStatus="counter">
                        <dl>
                            <dt>Название учреждения:</dt>
                            <dd><input type="text" name="${type}" size="100" value="${org.homePage.name}"></dd>
                        </dl>
                        <dl>
                            <dt>Сайт учреждения:</dt>
                            <dd><input type="text" name="${type}url" size="100" value="${org.homePage.url}"></dd>
                        </dl>
                        <br>
                        <div style="margin-left: 30px;">
                            <c:forEach var="pos" items="${org.positions}">
                                <jsp:useBean id="pos" type="com.urise.webapp.model.Organization.Position"/>
                                <dl>
                                    <dt>Начальная дата:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}startDate" size="10"
                                               value="<%=DateUtil.format(pos.getStartDate())%>" placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Конечная дата:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}endDate" size="10"
                                               value="<%=DateUtil.format(pos.getEndDate())%>" placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Должность:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}title" size="75"
                                               value="${pos.title}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Описание:</dt>
                                    <dd>
                                        <textarea name="${type}${counter.index}description" rows="2"
                                                  cols="75">${pos.description}</textarea>
                                    </dd>
                                </dl>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>

            <%--<dl>--%>
            <%--<dt>${sectionEntry.key.title}</dt>--%>

            <%--<dd>--%>
            <%--<% if (sectionEntry.getKey()==SectionType.PERSONAL || sectionEntry.getKey()==SectionType.OBJECTIVE){%>--%>
            <%--<input type="text" name="<%=sectionEntry.getKey().name()%>" size=100 value="<%=((TextSection) sectionEntry.getValue()).getContent()%>">--%>
            <%--<%}%>--%>

            <%--<% if ((sectionEntry.getKey()==SectionType.ACHIEVEMENT) || (sectionEntry.getKey()==SectionType.QUALIFICATIONS)){%>--%>
            <%--<ul>    <% for (String item : ((ListSection) sectionEntry.getValue()).getItems()) {%>--%>
            <%--<li><input type="text" name="<%=sectionEntry.getKey().name()%>" size=100 value="<%=item%>"></li>--%>
            <%--<%}%>--%>
            <%--</ul>--%>
            <%--<%}%>--%>

            <%--<% if ((sectionEntry.getKey()==SectionType.EDUCATION) || (sectionEntry.getKey()==SectionType.EXPERIENCE)){%>--%>
            <%--<ul>--%>
            <%--<% for (Organization organization : ((OrganizationSection) sectionEntry.getValue()).getOrganizations()) {%>--%>

            <%--<li>--%>
            <%--Название организации:<input type="text" name="home_page" size=100 value="<%=organization.getHomePage().getName()%>"><br>--%>
            <%--Страница в интернете:<input type="text" name="url" size=100 value="<%=organization.getHomePage().getUrl()%>"><br>--%>
            <%--<% for (Organization.Position position : organization.getPositions()) {%>--%>
            <%--<br>Начало: <input type="date" name="start_date" size=100 value="<%=position.getStartDate()%>">--%>
            <%--<br>Конец: <input type="date" name="end_date" size=100 value="<%=position.getEndDate()%>">--%>
            <%--<br>Должность: <input type="text" name="title" size=100 value="<%=position.getTitle()%>">--%>
            <%--<br>Описание: <input type="text" name="description" size=100 value="<%=position.getDescription()%>">--%>
            <%--<br><br>--%>
            <%--<%}%>--%>
            <%--</li>--%>
            <%--<%}%>--%>
            <%--</ul>--%>

            <%--<%}%>--%>

            <%--</dd>--%>
            <%--</dl>--%>

        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
        </p>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>