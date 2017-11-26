package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Aluga;

public class AlugaDao {
    
    
    private static AlugaDao instance = new AlugaDao();
    private AlugaDao(){}
    public static AlugaDao getInstance(){return instance;}
    
    
    public void save(Aluga aluga) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        Statement st = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("insert into CLIENTE_ALUGA_QUARTO (cod_cliente, cod_quarto)" +
                    "values (" +
                    aluga.getCodCliente().getCodigo() + "," +
                    aluga.getCodQuarto().getCodigo() + ")");
        
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
        }
    }
    
    
    public int cliente(int codigo) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        Statement st = null;
        int cliente = 0;
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select cod_cliente from CLIENTE_ALUGA_QUARTO where cod_quarto = " + codigo);
            while (rs.next()){
                cliente = Integer.parseInt(rs.getString("cod_cliente"));
            }
            return cliente;
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
        }
    }
    
    
    public static void closeResources (Connection conn, Statement st) throws SQLException{
        try{
            if(st!=null) st.close();
            if(conn!=null) st.close();
        }catch(SQLException e){
            throw e;
        }
    }
}
