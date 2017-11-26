package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

public class ApagarClienteAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        if(Integer.parseInt(request.getParameter("textCodigo")) != 0){
            try {
                
                Cliente.dropCliente(Integer.parseInt(request.getParameter("textCodigo")));
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ApagarClienteAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        BuscarClienteAction buscarCliente = new BuscarClienteAction();
        buscarCliente.execute(request, response);
        
    }
    
}
