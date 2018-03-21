<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ПРОФИЛ</title>

        <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link href="https://cdn.rawgit.com/mdehoog/Semantic-UI/6e6d051d47b598ebab05857545f242caf2b4b48c/dist/semantic.min.css" rel="stylesheet" type="text/css" /> 

        <script src="https://cdnjs.cloudflare.com/ajax/libs/cleave.js/0.7.17/cleave.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-2.1.4.js"></script>
        <script src="https://cdn.rawgit.com/mdehoog/Semantic-UI/6e6d051d47b598ebab05857545f242caf2b4b48c/dist/semantic.min.js"></script>
    </head>

    <body>
        <div id="wrapper">
            <!-- Sidebar Holder -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#"><img src="slike/logo3.png" height="30" alt="КПУ"/></a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li><a href="spi">ШПИ</a></li>
                        <li><a href="skat">СКАТ</a></li>
                        <li><a href="prakticna">ПРАКТИЧНА</a></li>
                        <li><a href="obavestenje">ОБАВЕШТЕЊА</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active">
                            <a href="#" id="profil">
                                <span class="glyphicon glyphicon-user"></span>
                                <c:out value="${administrator.ime}" />
                                &nbsp;
                                <c:out value="${administrator.prezime}" />
                            </a>
                        </li>
                        <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Одјави се</a></li>
                    </ul>
                </div>
            </nav>
            <span class="counter pull-right"></span>
            <div class="container">
                <h3>ЛИЧНИ ПРОФИЛ</h3>
                <hr/>
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
                <div class="container">
                    <section style="padding-bottom: 50px; padding-top: 50px;">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="slike/250x250.png" class="img-rounded img-responsive" />
                                <br />
                                <label>Име: </label>
                                <input type="text" class="form-control" required autofocus placeholder="<c:out value='${administrator.ime}'/>" />
                                <label>Презиме: </label>
                                <input type="text" class="form-control" required autofocus placeholder="<c:out value='${administrator.prezime}'/>" />
                                <br>
                                <a href="#" class="btn btn-success">Ажурирај</a>
                                <br /><br/>
                            </div>
                            <div class="col-md-8">
                                <div class="alert alert-info">
                                    <h2>Лични профил : </h2>
                                    <p>
                                        Промените ваше личне податке или лозинку.
                                    </p>
                                </div>
                                <div class="form-group col-md-8">
                                    <h3>Промена лозинке</h3>
                                    <br />
                                    <label>Стара лозинка</label>
                                    <input type="password" class="form-control" required autofocus />
                                    <label>Нова лозинка</label>
                                    <input type="password" class="form-control" required autofocus />
                                    <label>Потврдите нову лозинку</label>
                                    <input type="password" class="form-control" required autofocus />
                                    <br>
                                    <a href="#" class="btn btn-warning">Промените лозинку</a>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>