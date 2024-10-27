<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.clevertec.hotelbooking.dto.RoomDTO" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Админ - Управление комнатами</title>
</head>
<body>

<h1>Управление комнатами</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Тип комнаты</th>
        <th>Цена</th>
        <th>Действие</th>
    </tr>
    <%
        List<RoomDTO> rooms = (List<RoomDTO>) request.getAttribute("rooms");
        if (rooms != null) {
            for (RoomDTO room : rooms) {
    %>
    <tr>
        <td><%= room.getId() %>
        </td>
        <td><%= room.getRoomType() %>
        </td>
        <td><%= room.getPrice() %>
        </td>
        <td>
            <form method="post" action="<%= request.getContextPath() + "/admin/rooms/delete/" + room.getId() %>">
                <button type="submit">Удалить</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<h2>Добавить комнату</h2>
<form method="post" action="<%= request.getContextPath() + "/admin/rooms" %>">
    <label for="roomType">Тип комнаты:</label>
    <input type="text" name="roomType" id="roomType" required>
    <label for="price">Цена:</label>
    <input type="number" step="0.01" id="price" name="price" required>
    <button type="submit">Добавить комнату</button>
</form>
<a href="<%= request.getContextPath() %>/profile">Вернуться на главную</a>
<a href="${pageContext.request.contextPath}/logout">Выйти из аккаунта</a>
</body>
</html>
