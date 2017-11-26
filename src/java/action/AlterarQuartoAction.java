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
import model.QuartoEstadoFactory;
import persistence.QuartoDao;

public class AlterarQuartoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        if(request.getParameter("textCodigo").equals("") || request.getParameter("textNumero").equals("") || request.getParameter("textTipo").equals("") || request.getParameter("textVista").equals("") || request.getParameter("textEstado").equals("")){
            try {
                String resposta = "Alteração recusada";
                HttpSession session = request.getSession(true);
                List<Quarto> quartos = (List<Quarto>) session.getAttribute("quartos");
                session.setAttribute("quartos", quartos);
                request.setAttribute("resposta", resposta);
                RequestDispatcher view = request.getRequestDispatcher("CRUDquarto/Quarto.jsp");
                view.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(AlterarQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else{
            int codigo = Integer.parseInt(request.getParameter("textCodigo"));
            int numero = Integer.parseInt(request.getParameter("textNumero"));
            //String tipo = request.getParameter("textTipo"); Bloqueio de mudança
            String vista = request.getParameter("textVista");
            String estado = request.getParameter("textEstado");
            
            try{
            
                Quarto quarto = QuartoDao.obterQuarto(codigo);
                String resposta = "";
                if(estado.equals("disponivel")){
                    resposta = quarto.disponivel();
                }else if(estado.equals("ocupado")){
                    resposta = quarto.ocupado();
                }else{
                    resposta = quarto.manutencao();
                }
                
                HttpSession session = request.getSession(true);
                List<Quarto> quartos = (List<Quarto>) session.getAttribute("quartos");
                //Se não posso fazer a alteração retorno
                if(resposta.equals("Alteração recusada")){
                    request.setAttribute("resposta", resposta);
                }else{
                
                    //Neste momento adiciono memento a lista do quarto alterado

                    //Adicionando alteração | Momnto add disponivel a lista
                    for(Quarto q : quartos){
                        if(q.getCodigo() == codigo){

                              //Se posição atual difetente do tamanho da lista removo as sobras.
                              if(q.getPosicao() != -2 && q.getPosicao() != q.getEstadosSalvos().size()-1){
                                    for(int i = q.getEstadosSalvos().size()-1; i > q.getPosicao() ; i--){
                                        q.getEstadosSalvos().remove(i);
                                    }
                                    q.setPosicao(q.getPosicao()+1);
                              }

                              q.setEstadosSalvos(Quarto.obterQuarto(codigo));
                              //Alteração em banco
                              quarto.setQuartoEstado(QuartoEstadoFactory.create(quarto.getQuartoEstado()));
                              QuartoDao.getInstance().update(quarto);
                              //Aplico modificaçao na sessão.  
                              q.setQuartoEstado(QuartoEstadoFactory.create(quarto.getQuartoEstado()));
                              break;
                        }
                    }
                }
                
                session.setAttribute("quartos", quartos);
                RequestDispatcher view = request.getRequestDispatcher("CRUDquarto/Quarto.jsp");
                    view.forward(request, response);
                    
            } catch (SQLException | ClassNotFoundException | ServletException ex) {
                Logger.getLogger(AlterarQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
