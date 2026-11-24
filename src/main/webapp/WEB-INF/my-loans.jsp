<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <title>My Loans | LIBIS</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<%@ include file="/WEB-INF/fragments/header.jspf" %>

<div class="container py-5">

  <!-- Page Heading -->
  <div class="d-flex justify-content-between align-items-center flex-wrap gap-3 mb-4">

    <div>
            <span class="text-uppercase fw-semibold small"
                  style="color: #36777D; letter-spacing: 1.5px;">
                Member Dashboard
            </span>

      <h2 class="fw-bold mt-2 mb-1 text-dark">
        My Loans
      </h2>

      <p class="text-muted mb-0">
        Manage your borrowed books and keep track of your borrowing history.
      </p>
    </div>

    <div class="rounded-3 px-3 py-2 bg-white border">
      <i class="bi bi-calendar3 me-2" style="color: #36777D;"></i>
      <span class="text-muted small">Library Account</span>
    </div>

  </div>


  <!-- Loan Summary -->
  <div class="card border-0 shadow-sm rounded-4 mb-4">

    <div class="card-body p-4">

      <div class="row align-items-center">

        <div class="col-md-8">

          <div class="d-flex align-items-center gap-3">

            <div class="rounded-3 d-flex align-items-center justify-content-center"
                 style="width: 52px; height: 52px; background: #E8F2F2; color: #36777D;">

              <i class="bi bi-book-half fs-4"></i>

            </div>

            <div>
              <h5 class="fw-bold mb-1">Currently Borrowed</h5>
              <p class="text-muted mb-0 small">
                You can borrow up to 3 books at a time.
              </p>
            </div>

          </div>

        </div>

        <div class="col-md-4 mt-3 mt-md-0 text-md-end">

          <h2 class="fw-bold mb-0" style="color: #36777D;">
            <c:out value="${loanCount}"/>
            <span class="text-muted fs-5 fw-normal">/ 3</span>
          </h2>

          <small class="text-muted">Books in use</small>

        </div>

      </div>

      <div class="progress mt-4" style="height: 8px; background: #E8EEEE;">

        <div class="progress-bar"
             role="progressbar"
             style="width: ${loanCount * 33.33}%; background: #36777D;"
             aria-valuenow="${loanCount}"
             aria-valuemin="0"
             aria-valuemax="3">
        </div>

      </div>

    </div>

  </div>


  <!-- Current Loans -->
  <div class="card border-0 shadow-sm rounded-4 mb-5">

    <div class="card-header bg-white border-0 px-4 pt-4">

      <div class="d-flex justify-content-between align-items-center flex-wrap gap-2">

        <div>
          <h5 class="fw-bold mb-1">Active Loans</h5>
          <p class="text-muted small mb-0">
            Books you currently have borrowed.
          </p>
        </div>

        <span class="badge rounded-pill px-3 py-2"
              style="background: #E8F2F2; color: #36777D;">
                    <c:out value="${loanCount}"/> Active
                </span>

      </div>

    </div>

    <div class="card-body p-0 mt-3">

      <div class="table-responsive">

        <table class="table align-middle mb-0">

          <thead style="background: #F8FAFA;">

          <tr>
            <th class="px-4 py-3 text-muted small text-uppercase">Book ID</th>
            <th class="py-3 text-muted small text-uppercase">Borrowed On</th>
            <th class="py-3 text-muted small text-uppercase">Status</th>
            <th class="py-3 text-muted small text-uppercase text-end px-4">Action</th>
          </tr>

          </thead>

          <tbody>

          <c:forEach var="loan" items="${openLoans}">

            <tr>

              <td class="px-4 fw-semibold">
                #<c:out value="${loan.bookId}"/>
              </td>

              <td>
                <i class="bi bi-calendar3 me-2 text-muted"></i>
                <c:out value="${loan.borrowedOn}"/>
              </td>

              <td>
                                <span class="badge rounded-pill px-3 py-2"
                                      style="background: #FFF4DD; color: #A66A00;">
                                    <i class="bi bi-clock me-1"></i>
                                    Borrowed
                                </span>
              </td>

              <td class="text-end px-4">

                <form action="${pageContext.request.contextPath}/return-controller"
                      method="post">

                  <input type="hidden"
                         name="bookId"
                         value="${loan.bookId}">

                  <button type="submit"
                          class="btn btn-sm btn-outline-danger px-3">

                    <i class="bi bi-arrow-return-left me-1"></i>
                    Return Book

                  </button>

                </form>

              </td>

            </tr>

          </c:forEach>

          </tbody>

        </table>

      </div>

    </div>

  </div>


  <!-- Borrowing History -->
  <div class="card border-0 shadow-sm rounded-4">

    <div class="card-header bg-white border-0 px-4 pt-4">

      <h5 class="fw-bold mb-1">
        <i class="bi bi-clock-history me-2" style="color: #36777D;"></i>
        Borrowing History
      </h5>

      <p class="text-muted small mb-0">
        A record of your previously borrowed and returned books.
      </p>

    </div>

    <div class="card-body p-0 mt-3">

      <div class="table-responsive">

        <table class="table table-hover align-middle mb-0">

          <thead style="background: #F8FAFA;">

          <tr>
            <th class="px-4 py-3 text-muted small text-uppercase">Book ID</th>
            <th class="py-3 text-muted small text-uppercase">Borrowed On</th>
            <th class="py-3 text-muted small text-uppercase">Returned On</th>
            <th class="py-3 text-muted small text-uppercase">Status</th>
          </tr>

          </thead>

          <tbody>

          <c:forEach var="loan" items="${history}">

            <tr>

              <td class="px-4 fw-semibold">
                #<c:out value="${loan.bookId}"/>
              </td>

              <td>
                <c:out value="${loan.borrowedOn}"/>
              </td>

              <td>
                <c:out value="${loan.returnedOn}"/>
              </td>

              <td>
                                <span class="badge rounded-pill px-3 py-2"
                                      style="background: #E5F5ED; color: #218653;">

                                    <i class="bi bi-check-circle me-1"></i>
                                    Returned

                                </span>
              </td>

            </tr>

          </c:forEach>

          </tbody>

        </table>

      </div>

    </div>

  </div>

</div>

<%@ include file="/WEB-INF/fragments/footer.jspf" %>
<script src="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>