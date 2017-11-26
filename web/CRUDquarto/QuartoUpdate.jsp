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
                                <h3 class="panel-title">Alterar Quarto</h3>
                            </div>
                            <div class="panel-body">
                                <form accept-charset="UTF-8" role="form" action="FrontController?action=AlterarQuarto" method="post">
                                    <fieldset>
                                        <div class="form-group">
                                            <th><span>Codigo </span></th>
                                            <input name="textCodigo" type="text" value="${quarto.codigo}" hidden/>
                                            <input class="form-control" placeholder="Numero" name="textCodigo" type="text" value="${quarto.codigo}" readonly="readonly"/>
                                        </div>
                                        <div class="form-group">
                                            <th><span>Numero </span></th>
                                            <input class="form-control" placeholder="Numero" name="textNumero" type="text" value="${quarto.numero}" />
                                        </div>
                                        <div class="form-group">
                                            <th><span>Tipo </span></th>
                                            <select name="textTipo" class="form-control">
                                                <option name="textTipo" value="Solteiro" <c:if test="${quarto.tipo.equals('Solteiro')}"> selected</c:if>>Solteiro</option>
                                                <option name="textTipo" value="DuploSolteiro" <c:if test="${quarto.tipo.equals('DuploSolteiro')}"> selected</c:if>>Duplo Solteiro</option>
                                                <option name="textTipo" value="Casal" <c:if test="${quarto.tipo.equals('Casal')}"> selected</c:if>>Casal</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <th><span>Vista </span></th>
                                            <input class="form-control" placeholder="Vista" name="textVista" type="text" value="${quarto.vista}" />
                                        </div>
                                        <div class="form-group">
                                            <th><span>Estado </span></th>
                                            <select name="textEstado" class="form-control">
                                                <option name="textEstado" value="Disponivel" <c:if test="${quarto.quartoEstado.equals('Disponivel')}"> selected</c:if>>Disponivel</option>
                                                <option name="textEstado" value="Ocupado" <c:if test="${quarto.quartoEstado.equals('Ocupado')}"> selected</c:if>>Ocupado</option>
                                                <option name="textEstado" value="Manutencao" <c:if test="${quarto.quartoEstado.equals('Manutencao')}"> selected</c:if>>Manutencao</option>
                                            </select>
                                        </div>
                                        <div class="table-responsive">
                                            <th><span>Informações Adicionais </span></th>
                                            <table class="table">
                                                <tr>
                                                    <td>TIPO</td>
                                                    <td>${quarto.tipo}</td>
                                                </tr>
                                                <tr>
                                                    <td>PRECO</td>
                                                    <td>${quarto.preco}</td>
                                                </tr>
                                                <tr>
                                                    <td>TAMANHO</td>
                                                    <td>${quarto.tamanho}</td>
                                                </tr>
                                                <tr>
                                                    <td>CAMA</td>
                                                    <td>${quarto.cama}</td>
                                                </tr>
                                                <tr>
                                                    <td>BANHEIRO</td>
                                                    <td>${quarto.banheiro}</td>
                                                </tr>
                                                <tr>
                                                    <td>FRIGOBAR</td>
                                                    <td>${quarto.frigobar}</td>
                                                </tr>
                                                <tr>
                                                    <td>TV</td>
                                                    <td>${quarto.tv}</td>
                                                </tr>
                                                <tr>
                                                    <td>COMPUTADOR</td>
                                                    <td>${quarto.computador}</td>
                                                </tr>
                                            </table>
                                        </div>
                                        <input class="btn btn-lg btn-warning btn-block" type="submit" value="Alterar">
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
