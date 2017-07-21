<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>cookie test</title>
</head>

<body>
${message}
<form action="/cookie/login" method="post">
    username<input type="text" name="username"/><br/>
    password<input type="password" name="password"/><br/>
    submit<input type="submit" name="submit"/><br/>
</form>

</body>
</html>