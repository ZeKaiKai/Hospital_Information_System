<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/PatientInfoSystem/registerServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg" style="display: none">用户名已存在</span>
                </td>
            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" >${register_msg}</span>
                </td>
            </tr>


            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img src="/PatientInfoSystem/checkCodeServlet" id="checkCodeImg">
                    <a href="#" id="changeImg" onlick="">看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>
<script src="js/axios-0.18.0.js"></script>
<script>
    document.getElementById("changeImg").onclick = function() {
        document.getElementById("checkCodeImg").src = "/PatientInfoSystem/checkCodeServlet?" + new Date().getMilliseconds();
    }
    document.getElementById("username").onblur = function(){
        var username = this.value;
        axios.get("http://localhost:8080/PatientInfoSystem/ajaxServlet?username=zhangsan")
            .then(function (resp){
                alert(this.responseText)
                if(resp.value.toString() == "true"){
                    //用户名存在，输出提示
                    document.getElementById("username_err").style.display='';
                }else{
                    //用户名不存在，清除提示
                    document.getElementById("username_err").style.display='none';
                }
            })

        // //创建核心对象
        // var username = this.value;
        // var xhttp;
        // if (window.XMLHttpRequest) {
        //     xhttp = new XMLHttpRequest();
        // } else {
        //     // code for IE6, IE5
        //     xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        // }
        // //发送请求
        // xhttp.open("GET", "http://localhost:8080/Login/ajaxServlet?username="+username);
        // xhttp.send();
        // //获取响应
        // xhttp.onreadystatechange = function() {
        //     if (this.readyState == 4 && this.status == 200) {
        //         if(this.responseText == "true"){
        //             //用户名存在，输出提示
        //             document.getElementById("username_err").style.display='';
        //         }else{
        //             //用户名不存在，清除提示
        //             document.getElementById("username_err").style.display='none';
        //         }
        //     }
        // };
    }
</script>
</body>
</html>