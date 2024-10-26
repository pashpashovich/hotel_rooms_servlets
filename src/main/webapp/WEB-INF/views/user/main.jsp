<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.clevertec.hotelbooking.entity.User" %>
<%@ page import="ru.clevertec.hotelbooking.util.Role" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getRole() != Role.USER) {
        response.sendRedirect("login");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Мой кабинет</title>
</head>
<body>
<h2>Добро пожаловать, <%= user.getUsername() %>!</h2>
<h3>Роль: <%= user.getRole() %></h3>

<a href="${pageContext.request.contextPath}/user/bookings">Перейти к бронированию</a>
</body>
</html>

