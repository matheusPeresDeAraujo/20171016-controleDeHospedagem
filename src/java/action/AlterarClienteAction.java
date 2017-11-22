/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import controller.ActionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import persistence.ClienteDao;

/**
 *
 * @author matheus
 */
public class AlterarClienteAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        if(     request.getParameter("textCodigo").equals("")           || 
                request.getParameter("textIdade").equals("")            || 
                request.getParameter("textNome").equals("")             || 
                request.getParameter("textIdentificacao").equals("")    || 
                request.getParameter("textTelefone").equals("null")     || 
                request.getParameter("textCelular").equals("null")      || 
                request.getParameter("textEmail").equals("null")){
            
            String resposta = "Alteração recusada";
            request.setAttribute("resposta", resposta);
            
        } else{
            try{
                Cliente cliente = new Cliente(
                        request.getParameter("textCodigo"),
                        request.getParameter("textIdade"), 
                        request.getParameter("textNome"), 
                        request.getParameter("textIdentificacao"), 
                        request.getParameter("textTelefone"), 
                        request.getParameter("textCelular"), 
                        request.getParameter("textEmail"));
                
                ClienteDao.getInstance().update(cliente);   
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AlterarClienteAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Action actionObject = ActionFactory.create("BuscarCliente");
        actionObject.execute(request, response);
        
    }  
}
