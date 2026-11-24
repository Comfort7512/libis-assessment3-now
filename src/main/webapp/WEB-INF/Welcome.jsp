<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>Book Catalogue</title>

    <!-- Local Bootstrap CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .navbar-brand {
            letter-spacing: 0.3px;
        }

        .page-header {
            background: linear-gradient(135deg, #2563eb, #1d4ed8);
            color: white;
            border-radius: 16px;
        }

        .catalogue-card {
            border: none;
            border-radius: 16px;
            overflow: hidden;
        }

        .table thead th {
            background-color: #f1f5f9;
            color: #374151;
            font-size: 0.85rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            border-bottom: none;
        }

        .table tbody tr {
            vertical-align: middle;
        }

        .table tbody tr:hover {
            background-color: #f8fafc;
        }

        .book-number {
            width: 60px;
            color: #6b7280;
            font-weight: 600;
        }

        .book-title {
            font-weight: 600;
            color: #111827;
        }

        .action-placeholder {
            pointer-events: none;
            opacity: 0.55;
        }

        .welcome-text {
            color: #6b7280;
        }
    </style>


</head>

<body>

<!-- Navigation -->
<%@ include file="/WEB-INF/fragments/header.jspf" %>

<!-- Main content -->
<c:if test="${not empty sessionScope.error}">

    <div class="alert alert-danger">

        <c:out value="${sessionScope.error}"/>

    </div>

    <c:remove
            var="error"
            scope="session"/>

</c:if>

<div class="container py-5">


    <!-- Page header -->
    <div class="page-header p-4 p-md-5 mb-4 shadow-sm">

        <div class="row align-items-center">

            <div class="col-md-8">
                <p class="mb-2 text-white-50 fw-semibold">
                    LIBRARY MANAGEMENT SYSTEM
                </p>

                <h1 class="display-6 fw-bold mb-2">
                    Book Catalogue
                </h1>

                <p class="mb-0 text-white-50">
                    Browse the books currently available in our library.
                </p>
            </div>

            <div class="col-md-4 text-md-end mt-4 mt-md-0">
                <div class="bg-white text-primary d-inline-block
                        rounded-3 px-4 py-3 shadow-sm">

                    <div class="small text-muted">
                        Books in catalogue
                    </div>

                    <div class="fs-3 fw-bold">
                        ${books.size()}
                    </div>

                </div>
            </div>

        </div>

    </div>
    <form action="${pageContext.request.contextPath}/catalogue-controller" method="get">
        <input type="text" name="search" placeholder="search book by title">
        <button type="submit">Search</button>

    </form>





    <!-- Catalogue table -->
    <div class="card catalogue-card shadow-sm">

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-hover mb-0">

                    <thead>
                    <tr>
                        <th class="book-number">#</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Genre</th>
                        <th class="text-center">Actions</th>
                    </tr>
                    </thead>

                    <tbody>
            <c:choose>


                <c:when test="${requestScope.foundBook}">


                        <tr>

                            <!-- Number -->
                            <td class="book-number">
                                    1
                            </td>

                            <!-- Title -->
                            <td class="book-title">
                                <c:out value="${requestScope.book.title}"/>
                            </td>

                            <!-- Author -->
                            <td>
                                <c:out value="${requestScope.book.author}"/>
                            </td>

                            <!-- Genre -->
                            <td>
                            <span class="badge bg-light text-dark border">
                                <c:out value="${requestScope.book.genre}"/>
                            </span>
                            </td>

                            <!-- Future actions -->
                            <td class="text-center">

                            <span class="badge bg-light text-secondary border  me-1">
                                <a href="${pageContext.request.contextPath}/book-controller?bookId=${requestScope.book.bookId}">View</a>
                            </span>

                                <c:if test="${pageContext.request.isUserInRole('MEMBER')}">

                                    <form
                                            action="${pageContext.request.contextPath}/borrow-controller"
                                            method="post"
                                            style="display:inline;"
                                            onsubmit="return confirm('Are you sure you want to borrow this book?');">

                                        <input type="hidden"
                                               name="bookId"
                                               value="${book.bookId}">

                                        <button type="submit" class="btn btn-success btn-sm">
                                            Borrow
                                        </button>

                                    </form>

                                </c:if>

                            <span class="badge bg-light text-secondary border ">
                            Reserve
                            </span>

                            </td>

                        </tr>


                </c:when>

                <c:when test="${requestScope.searchPerformed}">
                    <tr>
                        <td colspan="4">No books Found</td>
                    </tr>
                </c:when>

                    <c:otherwise>
                        <c:forEach var="book" items="${requestScope.books}" varStatus="bs">

                        <tr>
                                <td>${bs.count}</td>
                                <td><c:out value="${book.title}"/></td>
                                <td><c:out value="${book.author}"/></td>
                                <td><c:out value="${book.genre}"/></td>

                            <td class="text-center">

                            <span class="badge bg-light text-secondary border  me-1">
                                <a href="${pageContext.request.contextPath}/book-controller?bookId=${book.bookId}">View</a>
                            </span>


                                <c:if test="${pageContext.request.isUserInRole('MEMBER')}">

                                    <form
                                            action="${pageContext.request.contextPath}/borrow-controller"
                                            method="post"
                                            style="display:inline;"
                                            onsubmit="return confirm('Are you sure you want to borrow this book?');">

                                        <input type="hidden"
                                               name="bookId"
                                               value="${book.bookId}">

                                        <button type="submit" class="btn btn-success btn-sm">
                                            Borrow
                                        </button>

                                    </form>

                                </c:if>


                                <span class="badge bg-light text-secondary border ">
                            Reserve
                            </span>

                            </td>
                        </tr>

                        </c:forEach>
                    </c:otherwise>
            </c:choose>

                    </tbody>

                </table>

            </div>

        </div>

    </div>

    <!-- Footer note -->
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>



</div>

<!-- Local Bootstrap JS -->

<script src="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
