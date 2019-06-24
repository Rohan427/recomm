<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<%@ include file="/common/header.htmlf" %>

<!-- ++++++++++++++++++++++ Begin Page Content ++++++++++++++++++++++++ -->

        <div class="div_header">
            <h1>Wish List</h1>
        </div>
        <%@ include file="listmenu.htmlf" %>
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
                                <h3>Price</h3>
                            </td>
                        </tr>

                        <c:forEach items="${wishlist}" var="itemSet">
                        <tr class="data1">
                            <c:choose>
                                <c:when test="${not empty itemSet.image}">
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
                                <table>
                                    <tbody>
                                        <tr>
                                            <td>
                                                $<c:out value="${itemSet.retail}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div id="links" align="center">
                                                    <a href="../orders/moveToCart.jsp?pageTitle=Cart&itemId=<c:out value="${itemSet.idItems}" />">
                                                        <div class="thumbnail"
                                                             style="background-image:url(../images/green-cart-button-hi.png);
                                                                    background-repeat: no-repeat;
                                                                    background-size: 60px 60px;
                                                                    width: 60px;
                                                                    height: 60px;"
                                                        >
                                                        </div>
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="../orders/wishlistRemove.jsp?itemId=<c:out value="${itemSet.idItems}" />" >Remove</a>
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
