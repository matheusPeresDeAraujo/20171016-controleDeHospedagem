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
import model.Aluga;
import model.Cliente;
import model.Quarto;
import model.QuartoEstadoFactory;
import persistence.AlugaDao;
import persistence.QuartoDao;

public class OcuparQuartoAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int codCliente = Integer.parseInt(request.getParameter("textCliente"));
        int codQuarto = Integer.parseInt(request.getParameter("textCodigo"));
        try {
            Cliente cliente = Cliente.obterCliente(codCliente);
            Quarto quarto = Quarto.obterQuarto(codQuarto);
            Aluga aluga = new Aluga(quarto, cliente);
            AlugaDao.getInstance().save(aluga);
            
            //Defino o quarto antes da mudança em memento
            HttpSession session = request.getSession(true);
            List<Quarto> quartos = (List<Quarto>) session.getAttribute("quartos");
            for(Quarto q : quartos){
                if(q.getCodigo() == codQuarto){
                    
                    //Se posição atual diferente do tamanho da lista removo as sobras.
                    if(q.getPosicao() != -2 && q.getPosicao() != q.getEstadosSalvos().size()-1){
                        for(int i = q.getEstadosSalvos().size()-1; i > q.getPosicao() ; i--){
                            q.getEstadosSalvos().remove(i);
                        }
                        q.setPosicao(q.getPosicao()+1);
                    }
                    
                    q.setEstadosSalvos(Quarto.obterQuarto(codQuarto));
                    //Realizo a mudança
                    quarto.ocupado();
                    quarto.setQuartoEstado(QuartoEstadoFactory.create(quarto.getQuartoEstado()));
                    QuartoDao.getInstance().update(quarto);
                    //Aplico modificaçao na sessão.
                    q.setQuartoEstado(QuartoEstadoFactory.create(quarto.getQuartoEstado()));
                    
                    break;
                }
            }
            session.setAttribute("quartos", quartos);
            
            String cont = "true";
            for(Quarto qp : quartos){
                    if(qp.getQuartoEstado().equals("disponivel")){
                    cont = "false";
                }
            }
            
            request.setAttribute("todosOcupados", cont);
            RequestDispatcher view = 
                    request.getRequestDispatcher("/painel.jsp");
            view.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(OcuparQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OcuparQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(OcuparQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
