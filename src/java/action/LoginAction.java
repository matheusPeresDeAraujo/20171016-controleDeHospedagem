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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username.equals("admin") && password.equals("admin")){ 
            
            try {
                
                HttpSession session = request.getSession(true);
                List<Quarto> quartos = null;
                quartos = new ArrayList<>();
                String cont = "true";
                for(Quarto quarto : QuartoDao.getInstance().obterQuartos()){
                    quartos.add(quarto);
                    //Verifico se todos os quartos estão ocupados. Caso um estiver disponivel muda para false.
                    if(quarto.getQuartoEstado().equals("disponivel")){
                        cont = "false";
                    }
                }
                session.setAttribute("quartos", quartos);
                request.setAttribute("todosOcupados", cont);
                RequestDispatcher view = request.getRequestDispatcher("/painel.jsp");
                view.forward(request, response);
                
            } catch (SQLException | ClassNotFoundException | ServletException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
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
