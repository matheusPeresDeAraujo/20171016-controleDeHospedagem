<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title> Controle de Hospedagem </title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/jumbotron-narrow.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <div class="header clearfix">
                <h1 class="text-muted"><img src="img/predio.png" width="48" alt="..." /> Controle de Hospedagem</h1>
            </div>
            <div class="container">
                <br />
                <div class="row vertical-offset-100">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="col-md-6">
                                    <h3 class="panel-title">Painel</h3>
                                </div>
                                <div class="btn-group">
                                    <a href="FrontController?action=Painel" class="btn btn-info" role="button">Painel</a>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Cliente
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="FrontController?action=BuscarCliente" class="" role="button">Painel</a></li>
                                        <li><a href="FrontController?action=PrepararInserirCliente" class="" role="button">Cadastro</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Salas
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="FrontController?action=BuscarSala" class="" role="button">Painel</a></li>
                                        <li><a href="FrontController?action=PrepararInserirSala" class="" role="button">Cadastro</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Quartos
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="FrontController?action=BuscarQuarto" class="" role="button">Painel</a></li>
                                        <li><a href="FrontController?action=PrepararInserirQuarto" class="" role="button">Cadastro</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <div class="alert alert-danger" role="alert"  <c:if test="${!resposta.equals('Alteração recusada')}"> hidden</c:if>>
                                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                            <span class="sr-only">Erro:</span>
                                            ${resposta}
                                        </div>
                                        <tr>
                                            <th>Numero</th>
                                            <th>Tipo</th>
                                            <th>Status</th>
                                            <th colspan=2>Ação</th>
                                            <th colspan=2>Memento</th>
                                        </tr>
                                        <c:forEach items="${quartos}" var="quarto">
                                            <tr>
                                                <td><c:out value="${quarto.numero}" /></td>
                                                <td><c:out value="${quarto.tipo}" /></td>
                                                <td><c:out value="${quarto.estado}" /></td>
                                                <!--
                                                <td><a class="btn btn-success btn-xs <c:if test="${quarto.estado.equals('ocupado') || quarto.estado.equals('manutencao')}"> disabled</c:if>" role="button" href="FrontController?action=PrepararCheckInQuarto&codigo=<c:out value="${quarto.codigo}"/>">Check-in</a></td>
                                                <td><a class="btn btn-danger btn-xs <c:if test="${!quarto.estado.equals('ocupado')}"> disabled</c:if>" role="button" href="FrontController?action=PrepararCheckOutQuarto&codigo=<c:out value="${quarto.codigo}"/>">Check-out</a></td>
                                                <td><a class="btn btn-primary btn-xs <c:if test="${!quarto.estado.equals('reservado') && !quarto.estado.equals('disponivel')}"> disabled</c:if>" role="button" href="FrontController?action=PrepararReservarQuarto&codigo=<c:out value="${quarto.codigo}"/>">Reservar</a></td>
                                                    -->
                                                <td><a class="btn btn-success btn-xs " role="button" href="FrontController?action=PrepararCheckInQuarto&codigo=<c:out value="${quarto.codigo}"/>">Check-in</a></td>
                                                <td><a class="btn btn-danger btn-xs " role="button" href="FrontController?action=PrepararCheckOutQuarto&codigo=<c:out value="${quarto.codigo}"/>">Check-out</a></td>
                                                <!--<td><a class="btn btn-primary btn-xs " role="button" href="FrontController?action=PrepararReservarQuarto&codigo=<c:out value="${quarto.codigo}"/>">Reservar</a></td>-->
                                                <td><a href="FrontController?action=MementoAnte&codigo=<c:out value="${quarto.codigo}"/>" class="" role="button"><span class="glyphicon glyphicon-backward" aria-hidden="true"></span></a></td>
                                                <td><a href="FrontController?action=MementoProx&codigo=<c:out value="${quarto.codigo}"/>" class="" role="button"><span class="glyphicon glyphicon-forward" aria-hidden="true"></span></a></td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <div class="alert" role="alert"  <c:if test="${!todosOcupados.equals('true')}"> hidden</c:if>>
                                        <a type="button" href="FrontController?action=PrepararAvise" class="btn btn-danger col-lg-6 col-lg-offset-3">Avise-me quando surgir quarto disponivel!</a>
                                    </div>
                                        <!-- Para que serve -->
                                        ${resp}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <footer class="footer">
                <p>&copy; 2017 Padrões de Projetos - GRANBERY JF.</p>
            </footer>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>

