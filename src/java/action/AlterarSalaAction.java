package action;

import controller.Action;
import controller.ActionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Sala;
import model.SalaFactory;
import persistence.SalaDao;

public class AlterarSalaAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        if(request.getParameter("textCodigo").equals("") || request.getParameter("textNumero").equals("") || request.getParameter("textNome").equals("") || request.getParameter("textPreco").equals("")){
           
            String resposta = "Alteração recusada";
            request.setAttribute("resposta", resposta);
                
        } else{
            int codigo = Integer.parseInt(request.getParameter("textCodigo"));
            int numero = Integer.parseInt(request.getParameter("textNumero"));
            String nome = request.getParameter("textNome");
            Double preco = Double.parseDouble(request.getParameter("textPreco"));
            try{
                
                Sala sala = (Sala) SalaFactory.create(nome);
                sala.setCodigo(codigo);
                sala.setNumero(numero);
                sala.setPreco(preco);
                
                SalaDao.getInstance().update(sala);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AlterarSalaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Action actionObject = ActionFactory.create("BuscarSala");
        actionObject.execute(request, response);
        
    }
}
