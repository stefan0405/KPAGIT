<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Step</title>
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>

        <link rel="stylesheet" href="css/student.css">


    </head>

    <body>
        <a href="logout" class="made-with-mk">
            <div class="brand"><i class="fa fa-sign-out"></i></div>
            <div class="made-with" href=""> Одјави се</div>
        </a>
        <!--   Big container   -->
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2">
                    <!-- Wizard container -->
                    <div class="wizard-container">
                        <div class="card wizard-card" data-color="red" id="wizard">
                            <form action="" method="">
                                <!--        You can switch " data-color="blue" "  with one of the next bright colors: "green", "orange", "red", "purple"             -->

                                <div class="wizard-header">
                                    <h3 class="wizard-title">
                                        Добродошао
                                    </h3>
                                    <h5><c:out value="${korisnik.student.ime}"/>&nbsp;<c:out value="${korisnik.student.prezime}"/>&nbsp;<c:out value="${korisnik.student.brindeksa}"/></h5>
                                </div>
                                <div class="wizard-navigation">
                                    <ul>
                                        <li><a href="#details" data-toggle="tab">ШПИ</a></li>
                                        <li><a href="#captain" data-toggle="tab">СКАТ</a></li>
                                        <li><a href="#description" data-toggle="tab">ПРАКТИЧНА НАСТАВА</a></li>
                                        <!--<li><a href="#" data-toggle="modal" data-target="#myModal">MOLBA</a></li>-->
                                    </ul>
                                </div>

                                <div class="tab-content">
                                    <div class="tab-pane" id="details">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <h4 class="info-text"> Дежурства</h4>
                                            </div>
                                            <div class="col-sm-12">
                                                <table class="table">
                                                    <thead>
                                                    <th>Дужност</th>
                                                    <th>Датум</th>
                                                    <th>Време</th>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${spi}" var="spi">
                                                            <tr>
                                                                <td>${spi.datum}</td>
                                                                <td>${spi.duznost}</td>
                                                                <td>${spi.radnoVreme}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="captain">
                                        <h4 class="info-text">СКАТ</h4>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <table class="table">
                                                    <thead>
                                                    <th>Датум</th>
                                                    <th>Чишћење оружја</th>
                                                    <th>Стројева обука</th>
                                                    <th>Рукованје службеним оружјем</th>
                                                    <th>Гоч</th>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${skat}" var="skat">
                                                            <tr>
                                                                <td>${skat.datum}</td>
                                                                <td>${skat.ciscenjeOruzja == false ? "НЕ":"ДА"}</td>
                                                                <td>${skat.strojevaObuka == false ? "НЕ":"ДА"}</td>
                                                                <td>${skat.rukovanjeSluzbenimOruzjem == false ? "НЕ":"ДА"}</td>
                                                                <td>${skat.terenskaObukaGoc}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="description">
                                        <div class="row">
                                            <h4 class="info-text"> Практична настава</h4>
                                            <div class="col-sm-12">
                                                <table class="table">
                                                    <thead>
                                                    <th>Датум</th>
                                                    <th>Полицијска управа</th>
                                                    <th>Полицијска станица</th>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${prakticna}" var="prakticna">
                                                            <tr>
                                                                <td>${prakticna.datum}</td>
                                                                <td>${prakticna.FKLokacija.policijskaUprava}</td>
                                                                <td>${prakticna.FKLokacija.policijskaStanica}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div> <!-- wizard container -->
                </div>
            </div> <!-- row -->
        </div> <!--  big container -->
        <!-- Modal 
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
-->
                <!-- Modal content
                <div class="modal-content" style="background-color:rgba(72,72,72,0.4)">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" align="center">ПОШАЉИ МОЛБУ</h4>
                    </div>
                    <div class="modal-body" style="background-color:rgba(72,72,72,0.4)">
                        <form class="form" id="form1" action="addMolbe" method="POST">
                            <input type="hidden" name="action" value="add"/>
                            <p class="name">
                                <select name="uprava" class="feedback-input" id="uprava">
                                    <c:forEach items="${lokacija}" var="lokacija">
                                        <option value="">${lokacija.IDLokacije}.${lokacija.policijskaUprava}-${lokacija.policijskaStanica}</option>
                                    </c:forEach>
                                </select>
                                <br>
                            </p>

                            <p class="text">
                                <textarea name="text" class="feedback-input" id="duznost" placeholder="Молба"></textarea>
                            </p>
                    </div>

                    <div class="modal-footer" >
                        <input type="submit" class="btn btn-info" value="Пошаљи"/>
                        <button type="button" class="btn btn-info" data-dismiss="modal">Затвори</button>

                    </div>
                    </form>
                </div>

            </div>
        </div>-->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>



        <script  src="js/student.js"></script>




    </body>

</html>
