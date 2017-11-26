package action;

import controller.Action;
import controller.ActionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Sala;
import model.SalaFactory;

public class AlterarSalaAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        if(     request.getParameter("textCodigo").equals("") || 
                request.getParameter("textNumero").equals("") || 
                request.getParameter("textNome").equals("") || 
                request.getParameter("textPreco").equals("")){
           
            request.setAttribute("resposta", "Alteração recusada");
                
        } else{
            try{
                
                Sala sala = SalaFactory.create(request.getParameter("textNome"));
                sala.updateSala(request);
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AlterarSalaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Action actionObject = ActionFactory.create("BuscarSala");
        actionObject.execute(request, response);
        
    }
}
