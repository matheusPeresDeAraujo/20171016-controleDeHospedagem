package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Quarto;
import model.QuartoEstadoFactory;
import model.QuartoFactory;
import model.QuartoTipoCasal;
import model.QuartoTipoDuploSolteiro;
import model.QuartoTipoSolteiro;

public class QuartoDao {
    
    
    private static QuartoDao instance = new QuartoDao();
    private QuartoDao(){}
    public static QuartoDao getInstance(){return instance;}
    
    
    public void save(Quarto quarto) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("INSERT INTO QUARTO (NUMERO, TIPO, PRECO, TAMANHO, VISTA, CAMA, BANHEIRO, FRIGOBAR, TV, COMPUTADOR, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            parseAtributos(stmt, quarto);
        
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
            stmt = conn.prepareStatement("DELETE FROM QUARTO WHERE CODIGO = ?");
            stmt.setInt(1, codigo);
            stmt.execute();
        
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, stmt);
        }
    }
    
    
    public static List<Quarto> obterQuartos() throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        Statement st = null;
        List<Quarto> quartos = new ArrayList<>();
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM QUARTO");
            while (rs.next()){
                
                Quarto quarto = QuartoFactory.create(rs.getString("TIPO"));
                quarto.setCodigo(Integer.parseInt(rs.getString("CODIGO")));
                quarto.setNumero(Integer.parseInt(rs.getString("NUMERO")));
                quarto.setVista(rs.getString("VISTA"));
                quarto.setQuartoEstado(QuartoEstadoFactory.create(rs.getString("ESTADO")));
               
                quartos.add(quarto);
            }
            return quartos;
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
        }
    }
    
    public static Quarto obterQuarto(int cod) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        Statement st = null;
        Quarto quarto = null;
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM QUARTO WHERE CODIGO = " + cod);
            while (rs.next()){
                
                quarto = QuartoFactory.create(rs.getString("TIPO"));
                quarto.setCodigo(Integer.parseInt(rs.getString("CODIGO")));
                quarto.setNumero(Integer.parseInt(rs.getString("NUMERO")));
                quarto.setVista(rs.getString("VISTA"));
                quarto.setQuartoEstado(QuartoEstadoFactory.create(rs.getString("ESTADO")));
               
            }
            return quarto;
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
        }
    }
    
    public void update(Quarto quarto) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("UPDATE QUARTO SET NUMERO = ?, TIPO = ?, PRECO = ?, TAMANHO = ?, VISTA = ?, CAMA = ?, BANHEIRO = ?, FRIGOBAR = ?, TV = ?, COMPUTADOR = ?, ESTADO = ? WHERE CODIGO = ?");
            parseAtributos(stmt, quarto);           
        
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, stmt);
        }
    }
    
    public void interesse(Cliente cliente, Quarto quarto) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        Statement st = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("INSERT INTO INTERESSE (cod_cliente, cod_quarto)" +
                    "values (" +
                    cliente.getCodigo() + "," +
                    quarto.getCodigo() + ")");
        
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
        }
    }
    
    public List interessados(Quarto quarto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List busca = new ArrayList<>();
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select cod_cliente from INTERESSE where cod_quarto = " + quarto.getCodigo());
            
            while (rs.next()){
                int codigo = Integer.parseInt(rs.getString("cod_cliente"));
                busca.add(codigo);
            }
            return busca;
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
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
    
    private static void parseAtributos(PreparedStatement stmt, Quarto quarto) throws SQLException{
            stmt.setInt     (1, quarto.getNumero()       );
            stmt.setString  (2, quarto.getTipo()         );
            stmt.setDouble  (3, quarto.getPreco()        );
            stmt.setDouble  (4, quarto.getTamanho()      );
            stmt.setString  (5, quarto.getVista()        );
            stmt.setInt     (6, quarto.getCama()         );
            stmt.setInt     (7, quarto.getBanheiro()     );
            stmt.setBoolean (8, quarto.getFrigobar()     );
            stmt.setBoolean (9, quarto.getTv()           );
            stmt.setBoolean (10, quarto.getComputador()  );
            stmt.setString  (11, quarto.getQuartoEstado());
        if(quarto.getCodigo() != 0){    
            stmt.setInt     (12, quarto.getCodigo()      );
        }
            stmt.execute();
    }
}
