<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ОБАВЕШТЕЊА</title>

        <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link href="https://cdn.rawgit.com/mdehoog/Semantic-UI/6e6d051d47b598ebab05857545f242caf2b4b48c/dist/semantic.min.css" rel="stylesheet" type="text/css" /> 

        <script src="https://cdnjs.cloudflare.com/ajax/libs/cleave.js/0.7.17/cleave.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-2.1.4.js"></script>
        <script src="https://cdn.rawgit.com/mdehoog/Semantic-UI/6e6d051d47b598ebab05857545f242caf2b4b48c/dist/semantic.min.js"></script>

        <link rel="stylesheet" href="css/tabela.css">
        <link rel="stylesheet" href="css/modal.css"/>
    </head>

    <body>

        <div id="wrapper">
            <!-- Sidebar Holder -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#"><img src="slike/logo3.png" height="30" alt="СКАТ"/></a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li><a href="spi">ШПИ</a></li>
                        <li><a href="skat">СКАТ</a></li>
                        <li><a href="prakticna">ПРАКТИЧНА</a></li>
                        <li class="active"><a href="obavestenje">ОБАВЕШТЕЊА</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="admP.jsp" id="profil">
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

                <h3>ОБАВЕШТЕЊА</h3>
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
                <div class="row">
                    <div class="clearfix gap"></div>
                    <div class="clearfix gap"></div>
                    <table id="tablecontent" class="table table-bordered">
                        <thead>
                            <tr class="active">
                                <th class="dgm">#</th>
                                <th>Датум</th>
                                <th>Наслов</th>
                                <th>Текст</th>
                                <th class="dgm">Измени</th>
                                <th class="dgm">Обриши</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${obavestenje}" var="o">
                                <tr>
                                    <td class="dgm" id="td_${o.IDObavestenje}">${o.IDObavestenje}</td>
                                    <td id="td_datum_${o.IDObavestenje}">${o.datum}</td>
                                    <td id="td_naslov_${o.IDObavestenje}">${o.naziv}</td>
                                    <td id="td_tekst_${o.IDObavestenje}">${o.komentar}</td>
                                    <td class="dgm"><a class="btn btn-block btn-info" data-toggle="modal" data-target="#myModal-edit"
                                                       onclick="popuniPolja('${o.IDObavestenje}', 'edit')" >Izmeni</a></td>
                                    <td class="dgm"><a class="btn btn-block btn-danger" data-toggle="modal" data-target="#myModal-delete"
                                                       onclick="popuniPolja('${o.IDObavestenje}', 'delete')" >Obrisi</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="dgm container">
                    <div class="container">
                        <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModal-add">Додај</button>
                    </div>
                </div>
                <!-- Modal dodaj -->
                <div class="modal fade" id="myModal-add" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <header id="header">
                                <h1 class='icon-calendar-empty'>Додај</h1>
                            </header>
                            <form action="obavestenje" method="POST">
                                <section>
                                    <input type="hidden" name="action" value="add"/>
                                    <div class="form-group">
                                        <label for="naslov">Наслов: </label>
                                        <input id="input" type="text" name="naslov" class="indeks" required autofocus>
                                    </div>
                                    <div class="form-group">
                                        <label for="tekst">Текст</label>
                                        <textarea rows="10" cols="30" name="tekst" required autofocus></textarea>
                                    </div>
                                </section>
                                <footer id="footer">
                                    <input type="submit" class="dodaj" value="Додај"/>
                                    <button type="button" class="izadji" data-dismiss="modal">Изађи</button>
                                </footer>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Modal edit -->
                <div class="modal fade" id="myModal-edit" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <header id="header">
                                <h1 class='icon-calendar-empty'>Измени</h1>
                            </header>
                            <form action="obavestenje" method="POST">
                                <section>
                                    <input type="hidden" name="action" value="edit"/>
                                    <input type="hidden" id="IDObavestenje-edit" name="IDObavestenje" />
                                    <input type="hidden" id="datum-edit" name="datum"/>
                                    <div class="form-group">
                                        <label for="naslov">Наслов: </label>
                                        <input id="input" type="text" name="naslov" class="naslov-edit" required autofocus>
                                    </div>
                                    <div class="form-group">
                                        <label for="tekst">Текст</label>
                                        <textarea rows="10" cols="30" name="tekst" id="tekst-edit" required autofocus></textarea>
                                    </div>
                                </section>
                                <footer id="footer">
                                    <input type="submit" class="dodaj" value="Додај"/>
                                    <button type="button" class="izadji" data-dismiss="modal">Изађи</button>
                                </footer>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Modal delete -->
                <div class="modal fade" id="myModal-delete" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <header id="header1">
                                Да ли сте сигурни да желите да обришете?
                            </header>
                            <form action="obavestenje" method="POST">
                                <input type="hidden" name="action" value="delete" />
                                <input type="hidden" id="IDObavestenje-delete" name="IDObavestenje" value="" />
                                <input type="hidden" id="datum-delete" name="datum"/>
                                <input type="hidden" id="tekst-delete" name="tekst"/>
                                <input type="hidden" class="naslov-delete" name="naslov"/>
                                <footer id="footer">
                                    <input type="submit" class="dodaj" value="Обриши"/>
                                    <button type="button" class="izadji" data-dismiss="modal">Изађи</button>
                                </footer>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function popuniPolja(IDObavestenje, action) {
                $("#IDObavestenje-" + action).val(IDObavestenje);
                $(".naslov-" + action).val($("#td_naslov_" + IDObavestenje).html());
                $("#tekst-" + action).val($("#td_tekst_" + IDObavestenje).html());
                $("#datum-" + action).val($("#td_datum_" + IDObavestenje).html());
            }
        </script>
        <script  src="js/tabela.js"></script>
        <script  src="js/picker.js"></script>   
    </body>
</html>