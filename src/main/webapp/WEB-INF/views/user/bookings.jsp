<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="ru.clevertec.hotelbooking.dto.BookingDTO" %>
<%@ page import="ru.clevertec.hotelbooking.dto.RoomDTO" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Мои бронирования</title>
</head>
<body>

<h1>Ваши бронирования</h1>

<table>
    <tr>
        <th>ID бронирования</th>
        <th>ID комнаты</th>
        <th>Дата бронирования</th>
    </tr>
    <%
        List<BookingDTO> bookings = (List<BookingDTO>) request.getAttribute("bookings");
        if (bookings != null) {
            for (BookingDTO booking : bookings) {
    %>
    <tr>
        <td><%= booking.getId() %></td>
        <td><%= booking.getRoomId() %></td>
        <td><%= booking.getBookingDate() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3">Бронирования отсутствуют.</td>
    </tr>
    <%
        }
    %>
</table>

<h2>Забронировать комнату</h2>
<form method="post" action="<%= request.getContextPath() + "/user/bookings" %>">
    <label for="roomId">Выберите комнату:</label>
    <select id="roomId" name="roomId" required>
        <%
            List<RoomDTO> availableRooms = (List<RoomDTO>) request.getAttribute("availableRooms");
            if (availableRooms != null && !availableRooms.isEmpty()) {
                for (RoomDTO room : availableRooms) {
        %>
        <option value="<%= room.getId() %>">Комната ID: <%= room.getId() %> (Цена: <%= room.getPrice() %> р.)</option>
        <%
            }
        } else {
        %>
        <option value="">Нет доступных комнат</option>
        <%
            }
        %>
    </select>
    <button type="submit">Забронировать комнату</button>
</form>
<a href="${pageContext.request.contextPath}/user">Вернуться на главную</a>
<a href="${pageContext.request.contextPath}/logout">Выйти из аккаунта</a>
</body>
</html>
