<%@ page import="java.util.List" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png" alt="Edit"></a>
    </h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <c:set var="contact" value="${contactEntry.getKey().toHtml(contactEntry.getValue())}"/>
                ${contact}<br/>
                    <%--<%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>--%>
        </c:forEach>

        <c:forEach var="sectionEntry" items="${resume.sections}">
                <jsp:useBean id="sectionEntry"
                             type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
    <hr><h3><%=sectionEntry.getKey().getTitle()%></h3>
        <% if (sectionEntry.getKey()==SectionType.PERSONAL || sectionEntry.getKey()==SectionType.OBJECTIVE){%>
            <%=((TextSection) sectionEntry.getValue()).getContent()%><%}%>
        <% if ((sectionEntry.getKey()==SectionType.ACHIEVEMENT) || (sectionEntry.getKey()==SectionType.QUALIFICATIONS)){%>
        <ul>    <% for (String item : ((ListSection) sectionEntry.getValue()).getItems()) {%>
            <li><%=item%></li>
            <%}%>
        </ul>
        <%}%>
        <% if ((sectionEntry.getKey()==SectionType.EDUCATION) || (sectionEntry.getKey()==SectionType.EXPERIENCE)){%>
        <ul>
        <% for (Organization organization : ((OrganizationSection) sectionEntry.getValue()).getOrganizations()) {%>

        <li><a href=<%=organization.getHomePage().getUrl()%>><%=organization.getHomePage().getName()%></a><br/>
            <% for (Organization.Position position : organization.getPositions()) {%>
            <i><%=position.getStartDate()%>-<%=position.getEndDate()%></i>: <b><%=position.getTitle()%></b><br/>
            <%=position.getDescription()%><br/><br/>
            <%}%>
        </li>
        <%}%>
        </ul>

        <%}%>

    </c:forEach>
    </p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>