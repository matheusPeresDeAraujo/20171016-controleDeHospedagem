package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Sala;

public class PrepararEditarSalaAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            
            request.setAttribute("sala", Sala.obterSala(Integer.parseInt(request.getParameter("codigo"))));
            RequestDispatcher view = request.getRequestDispatcher("CRUDsala/SalaUpdate.jsp");
            view.forward(request, response);
            
        } catch (ServletException | IOException | ClassNotFoundException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(PrepararEditarSalaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
