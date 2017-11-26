package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Quarto;
import model.QuartoFactory;

public class GravarQuartoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        if(     request.getParameter("textNumero").equals("") || 
                request.getParameter("textTipo").equals("") || 
                request.getParameter("textVista").equals("") || 
                request.getParameter("textEstado").equals("")){
            
            request.setAttribute("resposta", "Alteração recusada");
            
        } else{
            try {
                
                Quarto quarto = QuartoFactory.create(request.getParameter("textTipo"));
                quarto.saveQuarto(request);
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(GravarQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        BuscarQuartoAction buscarQuarto = new BuscarQuartoAction();
        buscarQuarto.execute(request, response);
    }
    
}
