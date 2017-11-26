package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
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
            HttpSession session = request.getSession(true);
            List<Quarto> sessao = (List<Quarto>) session.getAttribute("sessao");
            session.setAttribute("session", sessao);
            request.setAttribute("quartos", Quarto.obterQuartos());
            RequestDispatcher view = request.getRequestDispatcher("CRUDquarto/Quarto.jsp");
            view.forward(request, response);
            
        } catch (ServletException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BuscarQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
