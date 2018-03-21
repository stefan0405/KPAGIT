<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Логовање</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <!-- Icon -->
        <div>
            <c:if test = "${not empty errors}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-danger alert-dismissable" role="alert">
                            <c:forEach items = "${errors}" var = "error">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">x</a>
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                <span class="sr-only">Error:</span>
                                ${error} <br />
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="wrapper fadeInDown">
                <div id="formContent">
                    <!-- Tabs Titles -->
                    <h2 class="active">Улогуј се</h2>
                    <!-- Icon -->
                    <div class="fadeIn first">
                        <img src="https://www.shareicon.net/download/2015/10/19/658409_man_512x512.png" id="icon" alt="User Icon" />
                    </div>
                    <!-- Login Form -->
                    <form action="login" method="POST">
                        <input type="text" id="login" class="fadeIn second" name="korIme" placeholder="Корисничко име" required autofocus>
                        <input type="password" id="password" class="fadeIn third" name="loz" placeholder="Лозинка" required autofocus>
                        <input type="submit" class="fadeIn fourth" value="Улогуј се" id="dgme">
                    </form>
                    <!-- Remind Passowrd -->
                    <div id="formFooter">
                        <a class="underlineHover" href="rese.jsp">Заборављена лозинка?</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
