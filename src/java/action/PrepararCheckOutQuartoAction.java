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
import model.Cliente;
import model.Quarto;
import persistence.AlugaDao;

public class PrepararCheckOutQuartoAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        try {
            
            Quarto quarto = Quarto.obterQuarto(Integer.parseInt(request.getParameter("codigo")));
            String resposta = quarto.disponivel();
            
            if(resposta.equals("Alteração recusada")){ 
                
                request.setAttribute("resposta", resposta);
                PainelAction painel = new PainelAction();
                painel.execute(request, response);
                
            }else{
                
                Cliente cliente = Cliente.obterCliente(AlugaDao.getInstance().cliente(Integer.parseInt(request.getParameter("codigo"))));
                request.setAttribute("quarto", quarto);
                request.setAttribute("cliente", cliente);
                RequestDispatcher view = request.getRequestDispatcher("/QuartoCheckOut.jsp");
                view.forward(request, response);
            }
            
        } catch (SQLException | ClassNotFoundException | ServletException ex) {
            Logger.getLogger(PrepararCheckOutQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
