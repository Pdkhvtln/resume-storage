<%@ page import="com.urise.webapp.model.*" %>
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

        <h3>Секции</h3>
        <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
                <dl>
                <dt>${sectionEntry.key.title}</dt>

                <dd>
                    <% if (sectionEntry.getKey()==SectionType.PERSONAL || sectionEntry.getKey()==SectionType.OBJECTIVE){%>
                        <input type="text" name="<%=sectionEntry.getKey().name()%>" size=100 value="<%=((TextSection) sectionEntry.getValue()).getContent()%>">
                        <%}%>

                    <% if ((sectionEntry.getKey()==SectionType.ACHIEVEMENT) || (sectionEntry.getKey()==SectionType.QUALIFICATIONS)){%>
                    <ul>    <% for (String item : ((ListSection) sectionEntry.getValue()).getItems()) {%>
                        <li><input type="text" name="<%=sectionEntry.getKey().name()%>" size=100 value="<%=item%>"></li>
                        <%}%>
                    </ul>
                    <%}%>

                    <% if ((sectionEntry.getKey()==SectionType.EDUCATION) || (sectionEntry.getKey()==SectionType.EXPERIENCE)){%>
                    <ul>
                        <% for (Organization organization : ((OrganizationSection) sectionEntry.getValue()).getOrganizations()) {%>

                        <li>
                            Название организации:<input type="text" name="home_page" size=100 value="<%=organization.getHomePage().getName()%>"><br>
                            Страница в интернете:<input type="text" name="url" size=100 value="<%=organization.getHomePage().getUrl()%>"><br>
                            <% for (Organization.Position position : organization.getPositions()) {%>
                                <br>Начало: <input type="date" name="start_date" size=100 value="<%=position.getStartDate()%>">
                                <br>Конец: <input type="date" name="end_date" size=100 value="<%=position.getEndDate()%>">
                                <br>Должность: <input type="text" name="title" size=100 value="<%=position.getTitle()%>">
                                <br>Описание: <input type="text" name="description" size=100 value="<%=position.getDescription()%>">
                                <br><br>
                            <%}%>
                        </li>
                        <%}%>
                    </ul>

                    <%}%>

                </dd>
                </dl>
        </c:forEach>

        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
        </p>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>