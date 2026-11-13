<%--
  Created by IntelliJ IDEA.
  User: henry
  Date: 10/11/2026
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${requestScope.success}">
    <div class="alert alert-success alert-dismissible fade show">
            Successfully recorded user

        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert">
        </button>
    </div> <br>
    <a href="${pageContext.request.contextPath}/book-controller"
       class="btn btn-outline-secondary mb-4">
        &larr; Back to Catalogue
    </a>
</c:if>
