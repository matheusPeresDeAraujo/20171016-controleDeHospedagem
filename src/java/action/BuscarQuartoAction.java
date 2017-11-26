package action;

import controller.Action;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Quarto;

public class BuscarQuartoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Replico a sessão de quartos.
            HttpSession session = request.getSession(true);
            List<Quarto> quartos = (List<Quarto>) session.getAttribute("quartos");
            session.setAttribute("quartos", quartos);
            RequestDispatcher view = request.getRequestDispatcher("CRUDquarto/Quarto.jsp");
            view.forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(BuscarQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
