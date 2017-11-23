package action;

import controller.Action;
import controller.ActionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.ClienteDao;

public class ApagarClienteAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int codigo = Integer.parseInt(request.getParameter("textCodigo"));
        
        if(codigo != 0){
            try{
                ClienteDao.getInstance().drop(codigo);
            }catch(ClassNotFoundException | SQLException ex){
                Logger.getLogger(ApagarClienteAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        BuscarClienteAction buscarCliente = new BuscarClienteAction();
        buscarCliente.execute(request, response);
        
    }
    
}
