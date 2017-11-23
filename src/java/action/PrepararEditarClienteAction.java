/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.Quarto;
import model.QuartoEstadoDisponivel;
import model.QuartoMemento;
import persistence.QuartoDao;

/**
 *
 * @author matheus
 */
public class PrepararEditarClienteAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
           int codigo = Integer.parseInt(request.getParameter("codigo"));
            Cliente cliente;
            cliente = Cliente.obterCliente(codigo);
            
            request.setAttribute("cliente", cliente);
            RequestDispatcher view = request.getRequestDispatcher("CRUDcliente/ClienteUpdate.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(PrepararEditarClienteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
