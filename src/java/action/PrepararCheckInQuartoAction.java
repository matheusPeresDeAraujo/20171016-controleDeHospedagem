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
import model.Cliente;
import model.Quarto;

public class PrepararCheckInQuartoAction implements Action{
            
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        try {
            
            Quarto quarto = Quarto.obterQuarto(Integer.parseInt(request.getParameter("codigo")));
            String resposta = quarto.ocupado();
            
            if(resposta.equals("Alteração recusada")){
                
                request.setAttribute("resposta", resposta);
                PainelAction painel = new PainelAction();
                painel.execute(request, response);
                
            }else{
           
                List<Cliente> clientes = Cliente.obterClientes();
                request.setAttribute("quarto", quarto);
                request.setAttribute("clientes", clientes);

                RequestDispatcher view = request.getRequestDispatcher("/QuartoCheckIn.jsp");
                view.forward(request, response);
                
            }
        } catch (SQLException | ClassNotFoundException | ServletException ex) {
            Logger.getLogger(PrepararCheckInQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
