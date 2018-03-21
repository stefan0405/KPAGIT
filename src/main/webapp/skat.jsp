<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>СКАТ</title>

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
                        <li class="active"><a href="skat">СКАТ</a></li>
                        <li><a href="prakticna">ПРАКТИЧНА</a></li>
                        <li><a href="obavestenje">ОБАВЕШТЕЊА</a></li>
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

                <h3>ОБУКА У РУКОВАЊУ СЛУЖБЕНИМ ОРУЖЈЕМ</h3>
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
                    <div class="form-inline">
                        <button class="btn btn-danger" id="otkri">Прикажи све</button>
                        <button class="btn btn-danger" id="stampaj">Штампај</button>
                        <div class="form-group">
                            <label>Претрага:</label>
                            <input type="text" class="form-control" name="search" id="search" placeholder="Претрага">
                            <span id="clearsearch" class="glyphicon glyphicon-remove"></span>
                        </div>
                    </div>
                    <div class="clearfix gap"></div>
                    <table id="tablecontent" class="table table-bordered">
                        <thead>
                            <tr class="active">
                                <th class="dgm">#</th>
                                <th>Име и презиме</th>
                                <th>Број индекса</th>
                                <th>Датум</th>
                                <th>Чишћење оружја</th>
                                <th>Стројева обука</th>
                                <th data-toggle="tooltip" title="Руковање службеним оружјем">Руковање СО</th>
                                <th data-toggle="tooltip" title="Сектор за ванредне ситуације">СЗВС</th>
                                <th>Гоч</th>
                                <th class="hide" id="skri">Напомена</th>
                                <th class="dgm">Измени</th>
                                <th class="dgm">Обриши</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${skat}" var="skat">
                                <tr>
                                    <td class="dgm" id="td_${skat.IDSkat}">${skat.IDSkat}</td>
                                    <td id="td_ime_${skat.IDSkat}">${skat.FKBrindeksa.ime} ${skat.FKBrindeksa.prezime}</td>
                                    <td id="td_brindeksa_${skat.IDSkat}">${skat.FKBrindeksa.brindeksa}</td>
                                    <td id="td_datum_${skat.IDSkat}">${skat.datum}</td>

                                    <td id="td_sOruz_${skat.IDSkat}" hidden>${skat.ciscenjeOruzja}</td>
                                    <td id="td_sObuka_${skat.IDSkat}" hidden>${skat.strojevaObuka}</td>
                                    <td id="td_rso_${skat.IDSkat}" hidden>${skat.rukovanjeSluzbenimOruzjem}</td>
                                    <td id="td_szvs_${skat.IDSkat}" hidden>${skat.sektorZaVanredneSituacije}</td>

                                    <td>${skat.ciscenjeOruzja==true ? '<span class="glyphicon glyphicon-ok"/>' : '<span class="glyphicon glyphicon-remove"/>'}</td>
                                    <td>${skat.strojevaObuka==true ? '<span class="glyphicon glyphicon-ok"/>' : '<span class="glyphicon glyphicon-remove"/>'}</td>
                                    <td>${skat.rukovanjeSluzbenimOruzjem==true ? '<span class="glyphicon glyphicon-ok"/>' : '<span class="glyphicon glyphicon-remove"/>'}</td>
                                    <td>${skat.sektorZaVanredneSituacije==true ? '<span class="glyphicon glyphicon-ok"/>' : '<span class="glyphicon glyphicon-remove"/>'}</td>
                                    <td id="td_goc_${skat.IDSkat}">${skat.terenskaObukaGoc}</td>
                                    <!-- ostalo --->
                                    <td class="kolSkri hide" id="td_IDOstalo_${skat.IDSkat}">${skat.napomena}</td>
                                    <td class="dgm"><a class="btn btn-block btn-info" data-toggle="modal" data-target="#myModal-edit"
                                                       onclick="popuniPolja('${skat.IDSkat}', 'edit')" >Izmeni</a></td>
                                    <td class="dgm"><a class="btn btn-block btn-info" data-toggle="modal" data-target="#myModal-delete"
                                                       onclick="popuniPolja('${skat.IDSkat}', 'delete')" >Obrisi</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="dgm container">
                    <div class="container">
                        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal-add">Додај</button>
                    </div>
                </div>
                <!-- Modal dodaj -->
                <div class="modal fade" id="myModal-add" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <header id="header">
                                <h1 class='icon-calendar-empty'>Додај</h1>
                            </header>
                            <form action="skat" method="POST">
                                <section>
                                    <input type="hidden" name="action" value="add"/>
                                    <div class="form-group">
                                        <label for="brIndeksa">Број индекса:</label>
                                        <input id="input" type="text" name="brIndeksa" placeholder="2A1/0000/00" class="indeks" required autofocus>
                                    </div>
                                    <div class="form-group">
                                        <label for="cOruzije">Чишћење оружија:</label>
                                        <select class="selectpicker" id="cOruzije-add" name="cOruzije">
                                            <option>Да</option>
                                            <option>Не</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="sObuka">Стројева обука:</label>
                                        <select class="selectpicker" id="sObuka-add" name="sObuka">
                                            <option>Да</option>
                                            <option>Не</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="rso">Руковање службеним оружјем:</label>
                                        <select class="selectpicker" id="rso-add" name="rso">
                                            <option>Да</option>
                                            <option>Не</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="szvs">Сектор за ванредне ситуације:</label>
                                        <select class="selectpicker" id="szvs-add" name="szvs">
                                            <option>Да</option>
                                            <option>Не</option>
                                        </select>
                                    </div>
                                    <div class="ui calendar" id="datePicker">
                                        <div class="ui input left icon">
                                            <i class="calendar icon"></i>
                                            <input type="text" placeholder="Datum" name="Datum" required autofocus />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="goc">Гоч</label>
                                        <input id="input" type="text" name="goc" class="goc-add" required autofocus/>
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
                            <form action="skat" method="POST">
                                <section>
                                    <input type="hidden" name="action" value="edit"/>
                                    <input type="hidden" id="IDSkat-edit" name="IDSkat" />
                                    <input type="hidden" id="brindeksa-edit" name="brIndeksa"/>
                                    <div class="form-group">
                                        <label for="sOruzije">Чишћење оружија:</label>
                                        <select class="selectpicker" id="sOruzije-edit" name="sOruzije">
                                            <option>Да</option>
                                            <option>Не</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="sObuka">Стројева обука:</label>
                                        <select class="selectpicker" id="sObuka-edit" name="sObuka">
                                            <option>Да</option>
                                            <option>Не</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="rso">Руковање службеним оружјем:</label>
                                        <select class="selectpicker" id="rso-edit" name="rso">
                                            <option>Да</option>
                                            <option>Не</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="szvs">Сектор за ванредне ситуације:</label>
                                        <select class="selectpicker" id="szvs-edit" name="szvs">
                                            <option>Да</option>
                                            <option>Не</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="Datum">Датум:</label>
                                        <input id="input" type="text" class="datum-edit" name="Datum" required autofocus/>
                                    </div>
                                    <div class="form-group">
                                        <label for="goc">Гоч</label>
                                        <input id="input" type="text" name="goc" class="goc-edit" required autofocus/>
                                    </div>
                                    <div class="form-group">
                                        <label for="napomena">Напомена:</label>
                                        <textarea id="napomena-edit" rows="4" cols="30" name="napomena" ></textarea>
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
                            <form action="skat" method="POST">
                                <input type="hidden" name="action" value="delete" />
                                <input type="hidden" id="IDSkat-delete" name="IDSkat" value="" />
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
            function popuniPolja(IDSkat, action) {
                $("#IDSkat-" + action).val(IDSkat);
                $("#brindeksa-" + action).val($("#td_brindeksa_" + IDSkat).html());
                $(".goc-" + action).val($("#td_goc_" + IDSkat).html());
                $(".datum-" + action).val($("#td_datum_" + IDSkat).html());
                var jav = $("#td_sOruz_" + IDSkat).html();
                if (jav === "true") {
                    $("#sOruzije-" + action).val("Да");
                } else {
                    $("#sOruzije-" + action).val("Не");
                }
                var zak = $("#td_sObuka_" + IDSkat).html();
                if (zak === "true") {
                    $("#sObuka-" + action).val("Да");
                } else {
                    $("#sObuka-" + action).val("Не");
                }
                var izo = $("#td_rso_" + IDSkat).html();
                if (zak === "true") {
                    $("#rso-" + action).val("Да");
                } else {
                    $("#rso-" + action).val("Не");
                }
                var opr = $("#td_szvs_" + IDSkat).html();
                if (opr === "true") {
                    $("#szvs-" + action).val("Да");
                } else {
                    $("#szvs-" + action).val("Не");
                }
                $("#napomena-" + action).val($("#td_IDOstalo_" + IDSkat).html());
            }
        </script>
        <script>
            $(document).ready(function () {
                $("#otkri").click(function () {
                    $('th').each(function () {
                        if ($(this).attr('id') === 'skri') {
                            if ($(this).hasClass('hide')) {
                                $(this).removeClass('hide');
                                $('td').each(function () {
                                    if ($(this).hasClass('hide')) {
                                        $(this).removeClass('hide');
                                    }
                                });
                                $("#otkri").text("Сакриј");
                            } else {
                                $(this).addClass('hide');
                                $('td').each(function () {
                                    if ($(this).hasClass('kolSkri')) {
                                        $(this).addClass('hide');
                                    }
                                });
                                $("#otkri").text("Прикажи све");
                            }
                        }
                    });
                });
            });
        </script>
        <script>
            $("#stampaj").click(function () {
                print();
            });
        </script>
        <script>
            $(document).ready(function () {
                $("#duznost").click(function () {
                    var tip = $(this).val();
                    switch (tip) {
                        case "Командир":
                            $("#duz1").text("06:30-18:30");
                            $("#duz2").text("18:30-06:30");
                            break;
                        case "Заменик командира":
                            $("#duz1").text("06:30-18:30");
                            $("#duz2").text("18:30-06:30");
                            break;
                        case "Вођа смене":
                            $("#duz1").text("06:30-18:30");
                            $("#duz2").text("18:30-06:30");
                            break;
                        default:
                            $("#duz1").text("07:00-19:00");
                            $("#duz2").text("19:00-07:00");
                            break;
                    }
                });
            });
        </script>
        <script  src="js/tabela.js"></script>
        <script  src="js/picker.js"></script>
        <script>
            var cleave = new Cleave('.indeks', {
                delimiter: '/',
                blocks: [3, 4, 2],
                uppercase: true
            });
        </script>    
        <script>
            /* $(document).ready(function(){
             $("#indeks").autocomplete("autoIndeks.jsp");
             })*/
        </script>

    </body>
</html>