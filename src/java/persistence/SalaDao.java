package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Sala;
import model.SalaFactory;

public class SalaDao {
    
    
    private static SalaDao instance = new SalaDao();
    private SalaDao(){}
    public static SalaDao getInstance(){return instance;}
    
    
    public void save(Sala sala) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("INSERT INTO SALA (NUMERO, PRECO, NOME) VALUES(?, ?, ?)");
            parseAtributos(stmt, sala);
        
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, stmt);
        }
    }
    
    
    public void drop(int codigo) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("DELETE FROM SALA WHERE CODIGO = ?");
            stmt.setInt     (1, codigo);
            stmt.execute();
        
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, stmt);
        }
    }
    
    
    public static List<Sala> obterSalas() throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        Statement st = null;
        List<Sala> salas = new ArrayList<>();
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM SALA");
            
            while (rs.next()){
                
                salas.add(instanciaSala(rs));
                
            }
            return salas;
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
        }
    }
    
    
    public static Sala obterSala(int codigo) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        Sala sala = null;
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("SELECT * FROM SALA WHERE CODIGO = ?");
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                
                sala = instanciaSala(rs);
                
            }
            return sala;
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, stmt);
        }
    }
    
    
    public void update(Sala sala) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("UPDATE SALA SET NUMERO = ?, PRECO = ?, NOME = ? WHERE CODIGO = ?");
            parseAtributos(stmt, sala);
                    
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, stmt);
        }
    }
    
    
    public static void closeResources (Connection conn, Statement st) throws SQLException{
        try{
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        }catch(SQLException e){
            throw e;
        }
    }
    
    
    private static void parseAtributos(PreparedStatement stmt, Sala sala) throws SQLException{
            stmt.setInt(1, sala.getNumero());
            stmt.setDouble(2, sala.getPreco());
            stmt.setString(3, sala.getNome());
        if(sala.getCodigo() != 0){
            stmt.setInt(4, sala.getCodigo());
        }
            stmt.execute();
    }
    
    
    private static Sala instanciaSala(ResultSet rs) throws SQLException{
        Sala sala = SalaFactory.create(rs.getString("nome"));
        sala.setCodigo(Integer.parseInt(rs.getString("codigo")));
        sala.setNumero(Integer.parseInt(rs.getString("numero")));
        sala.setPreco(Double.parseDouble(rs.getString("preco")));
        
        return sala;
    }
}
