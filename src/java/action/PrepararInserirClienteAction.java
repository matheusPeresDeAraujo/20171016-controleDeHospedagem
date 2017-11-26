package action;

import controller.Action;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrepararInserirClienteAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            
            RequestDispatcher view = request.getRequestDispatcher("CRUDcliente/ClienteCreate.jsp");
            view.forward(request, response);
            
        } catch (ServletException | IOException ex) {}
    }
    
}
