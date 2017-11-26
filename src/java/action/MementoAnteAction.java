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
import persistence.QuartoDao;

public class MementoAnteAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            
            int codigo = Integer.parseInt(request.getParameter("codigo")); // Quarto que estou procurando anterar
            
            HttpSession session = request.getSession(true);
            List<Quarto> quartos = (List<Quarto>) session.getAttribute("quartos");
            
            for(Quarto q : quartos){
                if(q.getCodigo() == codigo){
                    // Verifico se tenho possibilidade de ir para um estado anterior
                    if(q.getEstadosSalvos().size() > 0 && q.getPosicao() != -1){ // Se tiver uma lista não nula,consigo voltar em algum estado
                        if(q.getPosicao() == -2){
                            q.setPosicao(q.getEstadosSalvos().size()-1);//Posição na lista
                        }
                        //Se é o primeiro ant preciso guardar estado atual na lista
                        if(q.getPosicao() == q.getEstadosSalvos().size()-1){
                            q.setEstadosSalvos(Quarto.obterQuarto(codigo));
                        }
                        //Mudo o estado atual para o que esta nesta posição.
//                        q.setEstado(q.getEstadosSalvos().get(q.getPosicao()).getEstado());//Verificar instrução."""
//                        QuartoDao.getInstance().update(q);
//                        //Realizo a movimentação no estado.
                        q.setPosicao(q.getPosicao()-1);

                        //Verifico na lista a posição atual de estado -1 e retorno
                        //Persisto na sessao e no banco a alteração
                        
                        // OK entrando aqui quando existe algo em lista
                        
                    }else{
                        String resposta = "Alteração recusada";
                        request.setAttribute("resposta", resposta);
                    }
                    
                    break;
                }
            }
            
                        
            String cont = "true";
            for(Quarto quarto : quartos){
                //Verifico se todos os quartos estão ocupados. Caso um estiver disponivel muda para false.
                if(quarto.getQuartoEstado().equals("disponivel")){
                    cont = "false";
                }
            }
            session.setAttribute("quartos", quartos);
            request.setAttribute("todosOcupados", cont);
            RequestDispatcher view = request.getRequestDispatcher("/painel.jsp");
            view.forward(request, response);
            
            
        } catch (ServletException ex) {
            Logger.getLogger(MementoAnteAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MementoAnteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
