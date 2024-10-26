<%@ page import="ru.clevertec.hotelbooking.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Управление пользователями</title>
</head>
<body>
<h2>Управление пользователями</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Логин</th>
        <th>Роль</th>
        <th>Действие</th>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            for (User user : users) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getRole() %></td>
        <td>
            <form action="<%= request.getContextPath() %>/admin/users" method="post" style="display:inline;">
                <input type="hidden" name="userId" value="<%= user.getId() %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit">Удалить</button>
            </form>
            <form action="<%= request.getContextPath() %>/admin/users" method="post" style="display:inline;">
                <input type="hidden" name="userId" value="<%= user.getId() %>">
                <input type="hidden" name="action" value="makeUser">
                <button type="submit">Сделать админом</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<a href="<%= request.getContextPath() %>/profile">Вернуться на главную</a>
</body>
</html>
