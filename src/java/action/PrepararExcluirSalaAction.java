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

public class PrepararExcluirSalaAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        Sala sala = null;
        try {
            sala = Sala.obterSala(codigo);
            request.setAttribute("sala", sala);
            RequestDispatcher view = request.getRequestDispatcher("CRUDsala/SalaDelete.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(PrepararExcluirSalaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
