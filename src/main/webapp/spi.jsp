<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ШПИ</title>

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
                        <a class="navbar-brand" href="#"><img src="slike/logo3.png" height="30" alt="КПУ"/></a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="spi">ШПИ</a></li>
                        <li><a href="skat">СКАТ</a></li>
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
                <h3>ШКОЛСКА ПОЛИЦИЈСКА ИСПОСТАВА</h3>
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
                                <th>Дужност</th>
                                <th>Време</th>
                                <th>Датум</th>
                                <th class="hide" id="skri">Јавио се</th>
                                <th class="hide" id="skri">Закаснио</th>
                                <th class="hide" id="skri">Изостанак</th>
                                <th class="hide" id="skri">Оправдање</th>
                                <th class="hide" id="skri">Напомена</th>
                                <th class="dgm">Измени</th>
                                <th class="dgm">Обриши</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${spi}" var="spi">
                                <tr>
                                    <td class="dgm" id="td_${spi.IDSpi}">${spi.IDSpi}</td>
                                    <td id="td_ime_${spi.IDSpi}">${spi.FKBrIndeks.ime}&nbsp;${spi.FKBrIndeks.prezime}</td>
                                    <td id="td_brindeksa_${spi.IDSpi}">${spi.FKBrIndeks.brindeksa}</td>
                                    <td id="td_duznost_${spi.IDSpi}">${spi.duznost}</td>
                                    <td id="td_radnoVreme_${spi.IDSpi}">${spi.radnoVreme}</td>
                                    <td id="td_datum_${spi.IDSpi}">${spi.datum}</td>

                                    <td id="td_IDOstalo_${spi.IDSpi}" hidden>${spi.FKOStalo.IDOstalo}</td>
                                    <td id="td_javiose_${spi.IDSpi}" hidden>${spi.FKOStalo.javiose}</td>
                                    <td id="td_zakasnjenje_${spi.IDSpi}" hidden>${spi.FKOStalo.zakasnjenje}</td>
                                    <td id="td_izostanak_${spi.IDSpi}" hidden>${spi.FKOStalo.izostanak}</td>
                                    <td id="td_opravdanje_${spi.IDSpi}" hidden>${spi.FKOStalo.opravdanje}</td>


                                    <td class="kolSkri hide">${spi.FKOStalo.javiose==true ? '<span class="glyphicon glyphicon-ok"/>' : '<span class="glyphicon glyphicon-remove"/>'}</td>
                                    <td class="kolSkri hide">${spi.FKOStalo.zakasnjenje==true ? '<span class="glyphicon glyphicon-ok"/>' : '<span class="glyphicon glyphicon-remove"/>'}</td>
                                    <td class="kolSkri hide">${spi.FKOStalo.izostanak==true ? '<span class="glyphicon glyphicon-ok"/>' : '<span class="glyphicon glyphicon-remove"/>'}</td>
                                    <td class="kolSkri hide">${spi.FKOStalo.opravdanje==true ? '<span class="glyphicon glyphicon-ok"/>' : '<span class="glyphicon glyphicon-remove"/>'}</td>
                                    <td id="td_napomena_${spi.IDSpi}" class="kolSkri hide">${spi.FKOStalo.napomena}</td>
                                    <td class="dgm"><a class="btn btn-block btn-info" data-toggle="modal" data-target="#myModal-edit"
                                                       onclick="popuniPolja('${spi.IDSpi}', 'edit')" >Izmeni</a></td>
                                    <td class="dgm"><a class="btn btn-block btn-info" data-toggle="modal" data-target="#myModal-delete"
                                                       onclick="popuniPolja('${spi.IDSpi}', 'delete')" >Obrisi</a></td>
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
                                Додај дежурство
                            </header>
                            <form action="spi" method="POST">
                                <section>
                                    <input id="input" type="hidden" name="action" value="add"/>
                                    <input id="input" type="text" name="brIndeksa" placeholder="2A1/0000/00" class="indeks" required autofocus>
                                    <select class="selectpicker" id="duznost" name="duznost">
                                        <option value="Командир">Командир</option>
                                        <option value="Заменик командира">Заменик командира</option>
                                        <option value="Вођа смене">Вођа смене</option>
                                        <option value="Дежурни">Дежурни</option>
                                    </select>
                                    <select class="selectpicker" name="radnoVreme">
                                        <option id="duz1">06:30-18:30</option>
                                        <option id="duz2">18:30-06:30</option>
                                    </select>
                                    <div class="ui calendar" id="datePicker">
                                        <div class="ui input left icon">
                                            <i class="calendar icon"></i>
                                            <input type="text" placeholder="Datum" name="Datum" required autofocus />
                                        </div>
                                    </div>
                                </section>
                                <footer id="footer">
                                    <input type="submit" class="dodaj" value="Додај" onclick="stampaj()"/>
                                    <button type="button" class="izadji" data-dismiss="modal">Изађи</button>
                                </footer>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Modal izmeni -->
                <div class="modal fade" id="myModal-edit" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <header id="header">
                                Измени
                            </header>
                            <form action="spi" method="POST">
                                <section>
                                    <input type="hidden" name="action" value="edit"/>
                                    <input type="hidden" id="IDSpi-edit" name="IDSpi" />
                                    <input type="hidden" id="brindeksa-edit" name="brIndeksa"/>
                                    <div class="form-group">
                                        <label for="duznost" >Дужност</label>
                                        <select class="selectpicker" id="duznost-edit" name="duznost">
                                            <option>Командир</option>
                                            <option>Заменик командира</option>
                                            <option>Вођа смене</option>
                                            <option>Дежурни</option>
                                        </select>
                                        <!--<input id="input"type="text" id="duznost-edit" name="duznost"/>
                                        <input id="input" type="text" id="radnoVreme-edit" name="radnoVreme"/>
                                        
                                        -->
                                    </div>
                                    <div class="form-group">
                                        <label for="radnoVreme">Радно време</label>
                                        <select class="selectpicker" id="radnoVreme-edit" name="radnoVreme">
                                            <option>06:30-18:30</option>
                                            <option>18:30-06:30</option>
                                            <option>07:00-19:00</option>
                                            <option>19:00-07:00</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="Datum">Датум</label>
                                        <input id="input" type="text" class="datum-edit" name="Datum" required autofocus/>
                                    </div>
                                    <input id="input" type="hidden" class="IDOstalo-edit" name="IDOstalo"/>
                                    <div class="form-group">
                                        <label for="javiose">Јавио се</label>
                                        <select class="selectpicker" id="javiose-edit" name="javiose">
                                            <option value="ДА">ДА</option>
                                            <option value="НЕ">НЕ</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="zakasnjenje">Закаснио</label>
                                        <select class="selectpicker" id="zakasnjenje-edit" name="zakasnjenje">
                                            <option id="duz1">ДА</option>
                                            <option id="duz2">НЕ</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="izostanak">Изостанак</label>
                                        <select class="selectpicker" id="izostanak-edit" name="izostanak">
                                            <option id="duz1">ДА</option>
                                            <option id="duz2">НЕ</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="opravdanje">Оправдање</label>
                                        <select class="selectpicker" id="opravdanje-edit" name="opravdanje">
                                            <option id="duz1">ДА</option>
                                            <option id="duz2">НЕ</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="napomena">Напомена</label>
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
                            <form action="spi" method="POST">
                                <input type="hidden" name="action" value="delete" />
                                <input type="hidden" id="IDSpi-delete" name="IDSpi" value="" />
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
            function popuniPolja(IDSpi, action) {
                $("#IDSpi-" + action).val(IDSpi);
                $(".ime-" + action).val($("#td_ime_" + IDSpi).html());
                $("#brindeksa-" + action).val($("#td_brindeksa_" + IDSpi).html());
                $("#duznost-" + action).val($("#td_duznost_" + IDSpi).html());
                $("#radnoVreme-" + action).val($("#td_radnoVreme_" + IDSpi).html());
                $(".datum-" + action).val($("#td_datum_" + IDSpi).html());
                $(".IDOstalo-" + action).val($("#td_IDOstalo_" + IDSpi).html());
                var jav = $("#td_javiose_" + IDSpi).html();
                if (jav === "true") {
                    $("#javiose-" + action).val("ДА");
                } else {
                    $("#javiose-" + action).val("НЕ");
                }
                //$("#javiose-" + action).val($("#td_javiose_" + IDSpi).html());
                var zak = $("#td_zakasnjenje_" + IDSpi).html();
                if (zak === "true") {
                    $("#zakasnjenje-" + action).val("ДА");
                } else {
                    $("#zakasnjenje-" + action).val("НЕ");
                }
                //$("#zakasnjenje-" + action).val($("#td_zakasnjenje_" + IDSpi).html());
                var izo = $("#td_izostanak_" + IDSpi).html();
                if (zak === "true") {
                    $("#izostanak-" + action).val("ДА");
                } else {
                    $("#izostanak-" + action).val("НЕ");
                }
                //$("#izostanak-" + action).val($("#td_izostanak_" + IDSpi).html());
                //$("#opravdanje-" + action).val($("#td_opravdanje_" + IDSpi).html());
                var opr = $("#td_opravdanje_" + IDSpi).html();
                if (opr === "true") {
                    $("#opravdanje-" + action).val("ДА");
                } else {
                    $("#opravdanje-" + action).val("НЕ");
                }
                $("#napomena-" + action).val($("#td_napomena_" + IDSpi).html());
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