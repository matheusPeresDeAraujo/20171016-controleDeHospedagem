/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import controller.ActionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Sala;
import persistence.SalaDao;

/**
 *
 * @author matheus
 */
public class ApagarSalaAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int codigo = Integer.parseInt(request.getParameter("textCodigo"));

        if (codigo != 0) {
            try {
                SalaDao.getInstance().drop(codigo);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ApagarSalaAction.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
        
        Action actionObject = ActionFactory.create("BuscarSala");
        actionObject.execute(request, response);

    }
}
