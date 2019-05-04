<%--
  Created by IntelliJ IDEA.
  User: balfish
  Date: 2018/1/20
  Time: 下午2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% int x = 5; %>

<%!
    int x = 7; %>

<%!

    int getX() {

        return x;

    }

%>

<% out.print("X1 =" + x); %>

<% out.print("X2 =" + getX()); %>


<script type="text/javascript">
    function checkForm(form) {

        var reg = /^[a-zA-Z]{6,12}$/

        if (form.userId.value.match(reg)) {
            var ele = window.document.getElementById("s1");
            ele.innerHTML = "用户名不符合规则";
            return false;
        }
        if (form.password.value.match(reg)) {
            var ele = window.document.getElementById("s2");
            ele.innerHTML = "密码不符合规则";
            return false;
        }

        document.myform.submit();
    }
</script>
<form action="login.do?act=login" name="myform" method="post">
    用户帐号
    <input type=text name="userId" size="18" value="">
    <br>
    登录密码
    <input type="password" name="password" size="19" value=""/>
    <input type=button name="submit1" value="登陆" onclick="checkForm(this.form)">

</form>
</body>
</html>
