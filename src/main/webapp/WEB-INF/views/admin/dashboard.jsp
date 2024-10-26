<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.clevertec.hotelbooking.entity.User" %>
<%@ page session="true" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null || user.getRole() != ru.clevertec.hotelbooking.util.Role.ADMIN) {
    response.sendRedirect("login");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Администраторская</title>
</head>
<body>
<h2>Добро пожаловать, <%= user.getUsername() %>!</h2>
<h3>Ваша роль: <%= user.getRole() %></h3>

<a href="${pageContext.request.contextPath}/admin/rooms">Управлять комнатами</a>
<a href="${pageContext.request.contextPath}/admin/users">Управлять пользователями</a>

</body>
</html>

