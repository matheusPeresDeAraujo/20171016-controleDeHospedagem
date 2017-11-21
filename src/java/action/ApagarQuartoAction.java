/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author matheus
 */
public class ApagarQuartoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("textCodigo"));
        
        if(codigo == 0){
            response.sendRedirect("index.html");
        } else{
            try{
                try{
                    //Apago na sessão
                    HttpSession session = request.getSession(true);
                    List<Quarto> quartos = (List<Quarto>) session.getAttribute("quartos");
                    for(Quarto quarto : quartos){
                        if(quarto.getCodigo() == codigo){
                            quartos.remove(quarto);
                            //Apago no banco
                            QuartoDao.getInstance().drop(codigo);
                            break;
                        }
                    }
                    
                    session.setAttribute("quartos", quartos);
                    RequestDispatcher view = request.getRequestDispatcher("CRUDquarto/Quarto.jsp");
                    view.forward(request, response);
                }catch(ClassNotFoundException ex){
                    Logger.getLogger(ApagarQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ServletException ex) {
                    Logger.getLogger(ApagarQuartoAction.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    
}
