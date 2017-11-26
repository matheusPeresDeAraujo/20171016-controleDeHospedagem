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

public class ApagarSalaAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        if (Integer.parseInt(request.getParameter("textCodigo")) != 0) {
            try {
                
                Sala.dropSala(Integer.parseInt(request.getParameter("textCodigo")));
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ApagarSalaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Action actionObject = ActionFactory.create("BuscarSala");
        actionObject.execute(request, response);

    }
}
