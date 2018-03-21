<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ресетовање лозинке</title>
        <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link rel="stylesheet" href="css/rese.css">
    </head>

    <body>
        <c:if test = "${not empty errors}">
            <div class="row">
                <div class="col-md-12">
                    <div class="alert alert-danger" role="alert">
                        <c:forEach items = "${errors}" var = "error">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>
                            ${error} <br />
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test = "${not empty message}">
            <div class="row">
                <div class="col-md-12">
                    <div class="alert alert-success" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Message:</span>
                        ${message}
                    </div>
                </div>
            </div>
        </c:if>
        <div class="title-bar">
            Ресетовање лозинке
        </div>

        <div class="wrap">
            <form action="reset" method="POST">
                <div class="password__container">
                    <input class="form-control" type="text" id="indeks" name="brIndeksa" placeholder="Број индекса" required autofocus/>
                    <div ng-app="passwordToggle">
                        <div class="input-group" id="pa">
                            <input toggle-password type="password" name="lozinka" placeholder="Стара лозинка" required autofocus/>
                        </div>
                        <div class="input-group" id="pa">
                            <input toggle-password type="password" name="novaLozinka" placeholder="Нова лозинка" required autofocus/>
                        </div>

                    </div>
                </div>
                <input type="submit" class="submit" id="login" value="Ресетуј">
            </form>
        </div>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.5/angular.min.js'></script>
        <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>


        <script  src="js/rese.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/cleave.js/0.7.17/cleave.min.js"></script>
        <script>
            var cleave = new Cleave('#indeks', {
                delimiter: '/',
                blocks: [3, 4, 2],
                uppercase: true
            });
        </script>
    </body>

</html>
