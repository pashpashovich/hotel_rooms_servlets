<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
</head>
<body>
<h1>Регистрация пользователя</h1>
<form action="reg" method="post">
    <div>
        <label for="username">Логин:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <button type="submit">Зарегистрироваться</button>
</form>
<%
    String error = (String) request.getAttribute("error");
    if (error != null && "username_taken".equals(error)) {
%>
<p style="color:red;">Логин уже занят. Пожалуйста, выберите другой.</p>
<%
    }
%>
<p>Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Входите тут</a>.</p>
</body>
</html>
