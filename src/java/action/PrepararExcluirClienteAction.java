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
import model.Cliente;

public class PrepararExcluirClienteAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        Cliente cliente = null;
        try {
            cliente = Cliente.obterCliente(codigo);
            request.setAttribute("cliente", cliente);
            RequestDispatcher view = request.getRequestDispatcher("CRUDcliente/ClienteDelete.jsp");
            view.forward(request, response);
        } catch (ServletException | IOException | ClassNotFoundException ex) {
        } catch (SQLException ex) {
            Logger.getLogger(PrepararExcluirClienteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
