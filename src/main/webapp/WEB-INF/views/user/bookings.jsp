<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.clevertec.hotelbooking.entity.Booking" %>
<html>
<head>
    <title>Мои бронирования</title>
</head>
<body>

<h1>Ваши бронирования</h1>

<table border="1">
    <tr>
        <th>ID бронирования</th>
        <th>ID комнаты</th>
        <th>Дата бронирования</th>
    </tr>
    <%
        List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
        if (bookings != null) {
            for (Booking booking : bookings) {
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
        <td colspan="3">No bookings found.</td>
    </tr>
    <%
        }
    %>
</table>

<h2>Забронировать комнату</h2>
<form method="post" action="<%= request.getContextPath() + "/user/bookings" %>">
    <label for="roomId">ID комнаты:</label>
    <input type="number" name="roomId" required>
    <button type="submit">Забронировать комнату</button>
</form>

</body>
</html>
