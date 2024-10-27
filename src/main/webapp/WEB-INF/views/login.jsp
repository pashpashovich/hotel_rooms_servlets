<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Вход</h2>
<form action="login" method="post">
    <label for="username">Логин:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">Войти</button>
</form>
<%
    String error = (String) request.getAttribute("error");
    if ("password_not_correct".equals(error)) {
%>
<p style="color:red;">Пароль не верен... Повторите попытку.</p>
<%
    }
%>
<%
    String error2 = (String) request.getAttribute("error");
    if ("username_not_found".equals(error2)) {
%>
<p style="color:red;">Пользователь с таким логином не зарегистрирован... Проверьте правильность логина.</p>
<%
    }
%>
<p>Нет аккаунта? <a href="${pageContext.request.contextPath}/reg">Регистрируйтесь тут</a>.</p>
</body>
</html>
