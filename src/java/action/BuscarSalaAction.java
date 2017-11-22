/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.SalaDao;

/**
 *
 * @author matheus
 */
public class BuscarSalaAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
         try {
            request.setAttribute("salas", SalaDao.obterSalas());
            RequestDispatcher view = 
                    request.getRequestDispatcher("CRUDsala/Sala.jsp");
            view.forward(request, response);
        } catch (ServletException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BuscarSalaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
