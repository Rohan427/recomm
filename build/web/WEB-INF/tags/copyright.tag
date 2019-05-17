<%--
    Document   : copyright
    Created on : Apr 5, 2016, 11:27:46 AM
    Author     : pgallen
--%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@ tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" scope="application" class="java.util.Date" />
            <td id="copyright" colspan="3">
                &copy; ${now.year + 1900} Random Logic Consulting
            </td>