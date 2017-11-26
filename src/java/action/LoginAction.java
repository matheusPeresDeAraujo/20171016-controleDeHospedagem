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
import model.Quarto;
import persistence.QuartoDao;

public class LoginAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //Capturando credenciais de acesso do usuário
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        /*
            Testo as credênciais com as possibilidades atuais:
            Usuário Administrador:
                username == admin
                password == admin
        
            Usuário Comum 01:
                username == user01
                password == user01
        
            Usuário Comum 02:
                username == user02
                password == user02
        
            Usuário Comum 03:
                username == user03
                password == user03
        */
        
        if(username.equals("admin") && password.equals("admin")){ 
            
            //Para usuário admnistrador carrego o painel pricipal.
            
            try {
                
                HttpSession session = request.getSession(true);
                List<Quarto> quartos = null;
                quartos = new ArrayList<>();
                String cont = "true";
                for(Quarto quarto : QuartoDao.getInstance().obterQuartos()){
                    quartos.add(quarto);
                    //Verifico se todos os quartos estão ocupados. Caso um estiver disponivel muda para false.
                    if(quarto.getEstado().equals("disponivel")){
                        cont = "false";
                    }
                }
                session.setAttribute("quartos", quartos);
                request.setAttribute("todosOcupados", cont);
                RequestDispatcher view = request.getRequestDispatcher("/painel.jsp");
                view.forward(request, response);
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServletException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(   (username.equals("user01") && password.equals("user01")) || 
                    (username.equals("user02") && password.equals("user02")) ||
                    (username.equals("user03") && password.equals("user03")) ){
            //Para outros usuários registrados inicio a tela de busca
            
            //++++PENDENTE++++
            
        }else{
            try {
                //Não é um usuário logado. Retorno erro
                String resposta = "Informe credenciais validas para o acesso!!!";
                request.setAttribute("resposta", resposta);
                RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
                view.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
