<%--
  Created by IntelliJ IDEA.
  User: henry
  Date: 14/11/2026
  Time: 04:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 class="mb-3">
  My Current Loans
</h2>

<p>

  <c:out value="${loanCount}"/>

  of 3 books in use

</p>

<table class="table table-bordered">

  <thead>

  <tr>

    <th>Book ID</th>
    <th>Borrowed On</th>
    <th>Action</th>

  </tr>

  </thead>

  <tbody>

  <c:forEach
          var="loan"
          items="${openLoans}">

    <tr>

      <td>

        <c:out value="${loan.bookId}"/>

      </td>

      <td>

        <c:out value="${loan.borrowedOn}"/>

      </td>

      <td>

        <form
                action="${pageContext.request.contextPath}/return-controller"
                method="post">

          <input
                  type="hidden"
                  name="bookId"
                  value="${loan.bookId}">

          <button
                  class="btn btn-danger">

            Return

          </button>

        </form>

      </td>

    </tr>

  </c:forEach>

  </tbody>

</table>

<h2 class="mt-5">

  Borrowing History

</h2>

<table class="table table-striped">

  <thead>

  <tr>

    <th>Book ID</th>
    <th>Borrowed</th>
    <th>Returned</th>

  </tr>

  </thead>

  <tbody>

  <c:forEach
          var="loan"
          items="${history}">

    <tr>

      <td>

        <c:out value="${loan.bookId}"/>

      </td>

      <td>

        <c:out value="${loan.borrowedOn}"/>

      </td>

      <td>

        <c:out value="${loan.returnedOn}"/>

      </td>

    </tr>

  </c:forEach>

  </tbody>

</table>

</body>
</html>
