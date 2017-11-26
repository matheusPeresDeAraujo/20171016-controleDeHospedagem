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
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Reservar Quarto</h3>
                            </div>
                            <div class="panel-body">
                                <form accept-charset="UTF-8" role="form" action="FrontController?action=ReservarQuarto" method="post">
                                    <fieldset>
                                        <div class="form-group">
                                            <th><span>Codigo </span></th>
                                            <input name="textCodigo" type="text" value="${quarto.codigo}" hidden/>
                                            <input class="form-control" placeholder="Numero" name="textCodigo" type="text" value="${quarto.codigo}" readonly="readonly"/>
                                        </div>
                                        <div class="form-group">
                                            <th><span>Numero </span></th>
                                            <input name="textNumero" type="text" value="${quarto.numero}" hidden/>
                                            <input class="form-control" placeholder="Numero" name="textNumero" type="text" value="${quarto.numero}" readonly="readonly"/>
                                        </div>
                                        <div class="form-group">
                                            <th><span>Tipo </span></th>
                                            <input name="textTipo" type="text" value="${quarto.tipo}" hidden/>
                                            <input class="form-control" placeholder="Tipo" name="textTipo" type="text" value="${quarto.tipo}" readonly="readonly"/>
                                        </div>
                                        <div class="form-group">
                                            <th><span>Vista </span></th>
                                            <input name="textVista" type="text" value="${quarto.vista}" hidden/>
                                            <input class="form-control" placeholder="Vista" name="textVista" type="text" value="${quarto.vista}" readonly="readonly"/>
                                        </div>
                                        <div class="form-group">
                                            <th><span>Estado </span></th>
                                            <input name="textEstado" type="text" value="reservado" hidden/>
                                            <input class="form-control" placeholder="Estado" name="textEstado" type="text" value="reservado" readonly="readonly"/>
                                        </div>
                                        <div class="form-group">
                                            <th><span>Cliente </span></th>
                                            <select name="textCliente" class="form-control">
                                                <c:forEach items="${clientes}" var="cliente">
                                                    <option name="textCliente" value="${cliente.codigo}">${cliente.nome}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <input class="btn btn-lg btn-warning btn-block" type="submit" value="Reservar">
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="footer">
                <p>&copy; 2017 Padrões de Projetos - GRANBERY JF.</p>
            </footer>
        </div>
        <script src="https://use.fontawesome.com/45e03a14ce.js"></script>
        <script src="js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
