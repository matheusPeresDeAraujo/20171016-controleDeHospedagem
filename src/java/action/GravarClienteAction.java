package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

public class GravarClienteAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        if(     request.getParameter("textIdade").equals("")            || 
                request.getParameter("textNome").equals("")             || 
                request.getParameter("textIdentificacao").equals("")    || 
                request.getParameter("textTelefone").equals("null")     || 
                request.getParameter("textCelular").equals("null")      || 
                request.getParameter("textEmail").equals("null")){
            
                request.setAttribute("resposta", "Alteração recusada");
                
        } else{
            try {
                
                Cliente cliente = new Cliente();
                cliente.saveCliente(request);
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(GravarClienteAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        BuscarClienteAction buscarCliente = new BuscarClienteAction();
        buscarCliente.execute(request, response);
        
    } 
}
