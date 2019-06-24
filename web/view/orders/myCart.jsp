<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<%@ include file="/common/header.htmlf" %>

<!-- ++++++++++++++++++++++ Begin Page Content ++++++++++++++++++++++++ -->

        <div class="div_header">
            <h1>Shopping Cart</h1>
        </div>
        <%@ include file="cartmenu.htmlf" %>
        <div class="res_div_header">
            <c:out value="${param.pageTitle}" />
        </div>
        <div class="res_div_main">            
            <div>
                <table class="fileSearch" cellpadding="0" cellspacing="0">
                    <tbody>
                        <tr>
                            <td colspan="5" class="results">
                                <h2><c:out value="${param.pageTitle}" /></h2>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="Search" cellpadding="0" cellspacing="0">
                    <tbody>
                        <tr class="heading">
                            <td></td>
                            <td>
                                <h3>Part Number</h3>
                            </td>
                            <td>
                                <h3>Part Name</h3>
                            </td>
                            <td>
                                <h3>Description</h3>
                            </td>
                            <td>
                                <h3>Quantity</h3>
                            </td>
                            <td>
                                <h3>Price</h3>
                            </td>
                        </tr>

                        <c:forEach items="${cartList}" var="itemSet">
                        <tr class="data1">
                            <c:choose>
                                <c:when test="${not empty itemSet.imagesCollection}">
                            <td>
                                <div id="links" align="center">
                                    <div class="thumbnail"
                                         style="background-image: url('..<c:out value="${itemSet.defaultImage}" />');
                                                background-repeat:no-repeat;
                                                background-size: 100px 100px;
                                                heigth: 100px;
                                                width: 100px;"
                                    >
                                    </div>
                                </div>
                            </td>
                                </c:when>
                                <c:otherwise>
                            <td>
                                <img src="..<c:out value="${itemSet.defaultImage}" />" height="100" width=100" />                                           
                            </td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <c:out value="${itemSet.partNo}" />
                            </td>
                            <td>
                                <c:out value="${itemSet.partName}" />
                            </td>
                            <td>
                                <c:out value="${itemSet.description}" />
                            </td>
                            <td>
                                <c:out value="${itemSet.qty}" />
                            </td>
                            <td>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td>
                                                $<c:out value="${itemSet.retail}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="../orders/cartRemove.jsp?itemId=<c:out value="${itemSet.idItems}" />" >Remove</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <p>
            <small>&nbsp;</small>
        </p>
<!-- ++++++++++++++++++++++ Footer Begins Here ++++++++++++++++++++++++ -->
        <%@ include file="/common/footer.htmlf" %>
<!-- +++++++++++++++++++++++ Footer Ends Here +++++++++++++++++++++++++ -->
</html>
