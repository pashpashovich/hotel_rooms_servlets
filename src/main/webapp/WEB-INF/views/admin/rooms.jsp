<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.clevertec.hotelbooking.entity.Room" %>
<html>
<head>
    <title>Админ - Управление комнатами</title>
</head>
<body>

<h1>Управление комнатами</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Тип комнаты</th>
        <th>Цена</th>
        <th>Действие</th>
    </tr>
    <%
        List<Room> rooms = (List<Room>) request.getAttribute("rooms");
        if (rooms != null) {
            for (Room room : rooms) {
    %>
    <tr>
        <td><%= room.getId() %></td>
        <td><%= room.getRoomType() %></td>
        <td><%= room.getPrice() %></td>
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
    <input type="text" name="roomType" required>
    <label for="price">Цена:</label>
    <input type="number" step="0.01" name="price" required>
    <button type="submit">Добавить комнату</button>
</form>
</body>
</html>
