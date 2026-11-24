<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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

<%@ include file="/WEB-INF/fragments/header.jspf" %>

<div class="container py-5">


    <a href="${pageContext.request.contextPath}/book-controller"
       class="btn btn-outline-secondary mb-4">
        &larr; Back to Catalogue
    </a>

    <c:set var="found" value="${false}"/>


    <div class="card border-0 shadow-lg rounded-4 overflow-hidden">

        <div class="bg-primary text-white p-4">
            <h1 class="mb-1 fw-bold">
                <c:out value="${requestScope.book.title}"/>
            </h1>

            <p class="mb-0 opacity-75">
                By <c:out value="${requestScope.book.author}"/>
            </p>
        </div>

        <div class="card-body p-4">

            <div class="row g-4">

                <div class="col-md-4">
                    <div class="border rounded-3 p-3 bg-light h-100">
                        <small class="text-muted text-uppercase fw-bold">
                            Genre
                        </small>

                        <h5 class="mt-2">
                            <c:out value="${requestScope.book.genre}"/>
                        </h5>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="border rounded-3 p-3 h-100">
                        <small class="text-muted text-uppercase fw-bold">
                            Synopsis
                        </small>

                        <p class="mt-2 mb-0">
                            <c:out value="${requestScope.book.synopsis}"/>
                        </p>
                    </div>
                </div>

            </div>

            <div class="mt-4">
                <button
                        class="btn btn-primary px-4"
                        onclick="toggleEditForm()">

                    Edit Book

                </button>
            </div>

        </div>

    </div>

    <div id="editFormContainer" class="card shadow-sm border-0 mt-4" style="display:none;">

        <div class="card-body">

            <h3>Edit Book</h3>

            <form method="post"
                  action="${pageContext.request.contextPath}/book-controller">

                <input type="hidden"
                       name="action"
                       value="update">

                <input type="hidden"
                       name="bookId"
                       value="${requestScope.book.bookId}">

                <div class="mb-3">
                    <label class="form-label">ISBN</label>
                    <input type="text"
                           class="form-control"
                           name="isbn"
                           value="${requestScope.book.isbn}"
                           required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Title</label>
                    <input type="text"
                           class="form-control"
                           name="title"
                           value="${requestScope.book.title}"
                           required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Author</label>
                    <input type="text"
                           class="form-control"
                           name="author"
                           value="${requestScope.book.author}"
                           required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Published Year</label>
                    <input type="date"
                           class="form-control"
                           name="publishedYear"
                           value="${requestScope.book.publishedYear}"
                           required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Genre</label>
                    <input type="text"
                           class="form-control"
                           name="genre"
                           value="${requestScope.book.genre}">
                </div>

                <div class="mb-3">
                    <label class="form-label">Synopsis</label>
                    <textarea class="form-control"
                              rows="5"
                              name="synopsis">${requestScope.book.synopsis}</textarea>
                </div>

                <button type="submit"
                        class="btn btn-success">
                    Save Changes
                </button>

                <button type="button"
                        class="btn btn-secondary"
                        onclick="toggleEditForm()">
                    Cancel
                </button>

            </form>

        </div>

    </div>




</div>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>
<script>

    function toggleEditForm() {

        const formContainer =
            document.getElementById("editFormContainer");

        if (formContainer.style.display === "none") {
            formContainer.style.display = "block";
        } else {
            formContainer.style.display = "none";
        }
    }

</script>
<script src="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
