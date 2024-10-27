<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="ru.clevertec.hotelbooking.entity.User" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null || user.getRole() != ru.clevertec.hotelbooking.util.Role.ADMIN) {
    response.sendRedirect("login");
    return;
  }
%>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Администраторская</title>
</head>
<body>
<h2>Добро пожаловать, <%= user.getUsername() %>!</h2>
<h3>Ваша роль: <%= user.getRole() %></h3>

<a href="${pageContext.request.contextPath}/admin/rooms">Управлять комнатами</a>
<a href="${pageContext.request.contextPath}/admin/users">Управлять пользователями</a>
<a href="${pageContext.request.contextPath}/logout">Выйти из аккаунта</a>
</body>
</html>

