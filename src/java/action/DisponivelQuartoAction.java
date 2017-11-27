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
import model.Aluga;
import model.Cliente;
import model.Quarto;
import model.QuartoEstadoFactory;
import persistence.AlugaDao;
import persistence.QuartoDao;

public class DisponivelQuartoAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int codCliente = Integer.parseInt(request.getParameter("textCliente"));
        int codQuarto = Integer.parseInt(request.getParameter("textCodigo"));
        try {
            Cliente cliente = Cliente.obterCliente(codCliente);
            Quarto quarto = Quarto.obterQuarto(codQuarto);
            
            Aluga aluga = new Aluga(quarto, cliente);
            
            AlugaDao.getInstance().save(aluga);
            quarto.disponivel();
            quarto.setQuartoEstado(QuartoEstadoFactory.create(quarto.getQuartoEstado()));
            QuartoDao.getInstance().update(quarto);
            
            List<Quarto> quartos = Quarto.obterQuartos();
            int cont = 1;
//            for(int i = 0; i < quartos.size(); i++){
//                if(quartos.get(i).getEstado().equals("disponivel")){
//                    cont = 0;
//                }
//            }
            request.setAttribute("todosOcupados", cont);
            request.setAttribute("quartos", Quarto.obterQuartos());
            RequestDispatcher view = 
                    request.getRequestDispatcher("/painel.jsp");
            view.forward(request, response);
            
        } catch (SQLException | ClassNotFoundException | ServletException ex) {
            Logger.getLogger(DisponivelQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
