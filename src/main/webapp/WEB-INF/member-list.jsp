<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Manage Members</title>

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container py-5">

  <div class="d-flex justify-content-between align-items-center mb-4">

    <h2 class="fw-bold">
      Member Management
    </h2>

    <a href="${pageContext.request.contextPath}/register-member.jsp"
       class="btn btn-primary">
      Add Member
    </a>

  </div>

  <form action="${pageContext.request.contextPath}/member-controller" method="get">

    <div class="mb-3">

      <label for="searchType">Search By</label>

      <select name="searchType" id="searchType" class="form-select">

        <option value="id">Member ID</option>
        <option value="username">UserName</option>
        <option value="memberNumber">Member Number</option>
        <option value="email">Email</option>
<%--        <option value="name">Name</option>--%>

      </select>

    </div>

    <div class="mb-3">

      <label for="keyword">Search</label>

      <input type="text"
             name="keyword"
             id="keyword"
             class="form-control"
             placeholder="Enter search keyword">

    </div>

    <button type="submit" class="btn btn-primary">
      Search
    </button>

  </form>
  <div class="card shadow-sm">

    <div class="card-body">

      <table class="table table-hover">

        <thead class="table-light">

        <tr>
          <th>#</th>
          <th>Member Number</th>
          <th>Name</th>
          <th>Email</th>
          <th>Username</th>
          <th>Role</th>
          <th>Actions</th>
        </tr>

        </thead>

        <tbody>

        <c:choose>
          <c:when test="${requestScope.searchPerformed}">
            <tr>

              <td class="member-number">
                1
              </td>
              <td>
                <c:out value="${requestScope.member.member_number}"/>
              </td>

              <td>
                <c:out value="${requestScope.member.name}"/>
                <c:out value="${requestScope.member.surname}"/>
              </td>

              <td>
                <c:out value="${requestScope.member.email}"/>
              </td>

              <td>
                <c:out value="${requestScope.member.username}"/>
              </td>

              <td>
                            <span class="badge bg-info">
                                <c:out value="${requestScope.member.role}"/>
                            </span>
              </td>

              <td>

                <button class="btn btn-sm btn-warning" disabled>
                  Edit
                </button>

                <form action="${pageContext.request.contextPath}/member-controller"
                      method="post"
                      style="display:inline;">

                  <input type="hidden"
                         name="memberId"
                         value="${requestScope.member_id}">

                  <input type="hidden"
                         name="action"
                         value="delete">

                  <button type="submit"
                          class="btn btn-sm btn-danger"
                          onclick="return confirm('Delete this member?');">
                    Delete
                  </button>
                </form>

              </td>

            </tr>
          </c:when>
          <c:otherwise>

            <c:forEach var="member" items="${requestScope.members}" varStatus="st">

              <tr>

                <td>${st.count}</td>

                <td>
                  <c:out value="${member.member_number}"/>
                </td>

                <td>
                  <c:out value="${member.name}"/>
                  <c:out value="${member.surname}"/>
                </td>

                <td>
                  <c:out value="${member.email}"/>
                </td>

                <td>
                  <c:out value="${member.username}"/>
                </td>

                <td>
                            <span class="badge bg-info">
                                <c:out value="${member.role}"/>
                            </span>
                </td>

                <td>

                  <button class="btn btn-sm btn-warning" disabled>
                    Edit
                  </button>

                  <form action="${pageContext.request.contextPath}/member-controller"
                        method="post"
                        style="display:inline;">

                    <input type="hidden"
                           name="memberId"
                           value="${member.member_id}">

                    <input type="hidden"
                           name="action"
                           value="delete">

                    <button type="submit"
                            class="btn btn-sm btn-danger"
                            onclick="return confirm('Delete this member?');">
                      Delete
                    </button>
                  </form>

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

<script src="${pageContext.request.contextPath}/bootstrap-5.0.2-dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>