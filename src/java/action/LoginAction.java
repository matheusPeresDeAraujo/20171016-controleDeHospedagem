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
            
            PainelAction painel = new PainelAction();
            painel.execute(request, response);
             
        }else{
            try {
                
                request.setAttribute("error", "true");
                request.setAttribute("resposta", " Credênciais Incorretas!");
                RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
                view.forward(request, response);
                
            } catch (ServletException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
