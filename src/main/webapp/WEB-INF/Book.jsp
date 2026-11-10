<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Full Book Details</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/css/bootstrap.min.css">

</head>

<body class="bg-light">

<div class="container py-5">


    <a href="${pageContext.request.contextPath}/book-controller"
       class="btn btn-outline-secondary mb-4">
        &larr; Back to Catalogue
    </a>

    <c:set var="found" value="${false}"/>

<%--    <c:forEach items="${requestScope.book}" var="book">--%>
<%--        <c:if test="${book.bookId == param.id}">--%>

<%--            <c:set var="found" value="${true}"/>--%>

            <div class="card shadow-sm border-0">
                <div class="card-body p-4">

                    <h1 class="h2 mb-3">
                        <c:out value="${requestScope.book.title}"/>
                    </h1>

                    <p class="text-muted mb-4">
                        By <c:out value="${requestScope.book.author}"/>
                    </p>

                    <hr>

                    <div class="mb-3">
                        <h2 class="h6 text-secondary">Genre</h2>
                        <p>
                            <c:out value="${requestScope.book.genre}"/>
                        </p>
                    </div>

                    <div class="mb-4">
                        <h2 class="h6 text-secondary">Synopsis</h2>
                        <p>
                            <c:out value="${requestScope.book.synopsis}"/>
                        </p>
                    </div>

<%--                    <div>--%>
<%--                        <h2 class="h6 text-secondary">Availability</h2>--%>
<%--                        <span class="badge bg-success">--%>
<%--                        <c:out value="${requestScope.book.available}"/>--%>
<%--                    </span>--%>
<%--                    </div>--%>

                </div>
            </div>

<%--        </c:if>--%>
<%--    </c:forEach>--%>


</div>

<script src="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
