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
import model.Quarto;

public class PrepararExcluirQuartoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        Quarto quarto = null;
        try {
            quarto = Quarto.obterQuarto(codigo);
            request.setAttribute("quarto", quarto);
            RequestDispatcher view = request.getRequestDispatcher("CRUDquarto/QuartoDelete.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(PrepararExcluirQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
