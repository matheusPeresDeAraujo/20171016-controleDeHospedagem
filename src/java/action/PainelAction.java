package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Quarto;

public class PainelAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            
            HttpSession session = request.getSession(true);
            
            String todosQuartosOcupados = "true";
            for(Quarto quarto : Quarto.obterQuartos()){
                if(quarto.getQuartoEstado().equals("Disponivel")){
                    todosQuartosOcupados = "false";
                }
            }
            request.setAttribute("todosQuartosOcupados", todosQuartosOcupados);
            
            List<Quarto> sessao = (List<Quarto>) session.getAttribute("sessao");
            session.setAttribute("session", sessao);
            request.setAttribute("quartos", Quarto.obterQuartos());
            RequestDispatcher view = request.getRequestDispatcher("/painel.jsp");
            view.forward(request, response);
            
        } catch (ServletException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PainelAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
