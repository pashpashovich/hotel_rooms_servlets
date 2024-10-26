<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Бронирование номера</title>
</head>
<body>
<h2>Забронируйте номер</h2>
<form action="booking" method="post">
    <label>Имя пользователя: <input type="text" name="userName" required></label><br>
    <label>Тип номера:
        <select name="roomType">
            <option value="Single">Одноместный</option>
            <option value="Double">Двухместный</option>
            <option value="Suite">Люкс</option>
        </select>
    </label><br>
    <button type="submit">Забронировать</button>
</form>
</body>
</html>
