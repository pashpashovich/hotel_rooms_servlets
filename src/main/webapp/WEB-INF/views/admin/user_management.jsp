<%@ page import="java.util.List" %>
<%@ page import="ru.clevertec.hotelbooking.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Управление пользователями</title>
</head>
<body>
<h2>Управление пользователями</h2>
<table>
    <tr>
        <th>Логин</th>
        <th>Роль</th>
        <th>Действие</th>
    </tr>
    <%
        List<UserDTO> users = (List<UserDTO>) request.getAttribute("users");
        if (users != null) {
            for (UserDTO user : users) {
    %>
    <tr>
        <td><%= user.getUsername() %></td>
        <td><%= user.getRole() %></td>
        <td>
            <form action="<%= request.getContextPath() %>/admin/users" method="post" style="display:inline;">
                <input type="hidden" name="username" value="<%= user.getUsername() %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit">Удалить</button>
            </form>
            <form action="<%= request.getContextPath() %>/admin/users" method="post" style="display:inline;">
                <input type="hidden" name="username" value="<%= user.getUsername() %>">
                <input type="hidden" name="action" value="makeAdmin">
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
<a href="${pageContext.request.contextPath}/logout">Выйти из аккаунта</a>
</body>
</html>
