
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Member Registration</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container min-vh-100 d-flex align-items-center justify-content-center py-5">

    <div class="card shadow border-0 rounded-4" style="max-width: 430px; width: 100%;">

        <div class="card-body p-4 p-md-5">

            <div class="text-center mb-4">
                <h2 class="fw-bold">Create Account</h2>
                <p class="text-muted">Register as a member</p>
            </div>

            <form method="post" action="${pageContext.request.contextPath}/login">

                <div class="mb-3">
                    <label for="firstName" class="form-label fw-semibold">
                        First Name
                    </label>
                    <input type="text"
                           name="firstName"
                           id="firstName"
                           class="form-control form-control-lg"
                           placeholder="Enter your first name"
                           required>
                </div>

                <div class="mb-3">
                    <label for="lastName" class="form-label fw-semibold">
                        Last Name
                    </label>
                    <input type="text"
                           name="lastName"
                           id="lastName"
                           class="form-control form-control-lg"
                           placeholder="Enter your last name"
                           required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label fw-semibold">
                        Email Address
                    </label>
                    <input type="email"
                           name="email"
                           id="email"
                           class="form-control form-control-lg"
                           placeholder="Enter your email"
                           required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label fw-semibold">
                        Username
                    </label>
                    <input type="text"
                           name="username"
                           id="username"
                           class="form-control form-control-lg"
                           placeholder="Enter username"
                           required>
                </div>

                <div class="mb-4">
                    <label for="password" class="form-label fw-semibold">
                        Password
                    </label>
                    <input type="password"
                           name="password"
                           id="password"
                           class="form-control form-control-lg"
                           placeholder="Create a password"
                           required>
                </div>


                <div class="d-grid">
                    <button type="submit" class="btn btn-primary btn-lg">
                        Register
                    </button>
                </div>

<%--                <div class="text-center mt-4">--%>
<%--                    <span class="text-muted">Already have an account?</span>--%>
<%--                    <a href="${pageContext.request.contextPath}/login"--%>
<%--                       class="text-decoration-none fw-semibold">--%>
<%--                        Log In--%>
<%--                    </a>--%>
<%--                </div>--%>

                <input
                        type="hidden"
                        name="role"
                        id="role"
                        value="member"
                        required>

                <input
                        type="hidden"
                        name="action"
                        id="password"
                        value="register"
                        required>


            </form>

        </div>
    </div>

</div>

</body>
</html>

