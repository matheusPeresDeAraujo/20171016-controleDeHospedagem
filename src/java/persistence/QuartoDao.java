/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.QuartoCasal;
import model.QuartoDuploSolteiro;
import model.QuartoSolteiro;

/**
 *
 * @author matheus
 */
public class QuartoDao {
    
    
    private static QuartoDao instance = new QuartoDao();
    private QuartoDao(){}
    public static QuartoDao getInstance(){return instance;}
    
    
    private static String nameTable()   {return "QUARTO";       }
    private static String codigo()      {return "CODIGO";       }
    private static String numero()      {return "NUMERO";       }
    private static String tipo()        {return "TIPO";         }
    private static String preco()       {return "PRECO";        }
    private static String tamanho()     {return "TAMANHO";      }
    private static String vista()       {return "VISTA";        }
    private static String cama()        {return "CAMA";         }
    private static String banheiro()    {return "BANHEIRO";     }
    private static String frigobar()    {return "FRIGOBAR";     }
    private static String tv()          {return "TV";           }
    private static String computador()  {return "COMPUTADOR";   }
    private static String estado()      {return "ESTADO";       }
    
    
    public void save(Quarto quarto) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("INSERT INTO " + nameTable() + " (" +
                                numero()        + ", " +
                                tipo()          + ", " +
                                preco()         + ", " +
                                tamanho()       + ", " +
                                vista()         + ", " +
                                cama()          + ", " +
                                banheiro()      + ", " +
                                frigobar()      + ", " +
                                tv()            + ", " +
                                computador()    + ", " +
                                estado()        + ") " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            stmt.execute();
        
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
            stmt = conn.prepareStatement("DELETE FROM " + nameTable() + " WHERE " + 
                                codigo() + " = ?");
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
        List<Quarto> busca = null;
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM QUARTO");
            busca = new ArrayList<Quarto>();
            while (rs.next()){
                int codigo = Integer.parseInt(rs.getString("CODIGO"));
                int numero = Integer.parseInt(rs.getString("numero"));
                String tipo = rs.getString("tipo");
                String vista = rs.getString("vista");
                String estado = rs.getString("estado");
                if(tipo.equals("single room")){
                    busca.add(new QuartoSolteiro(codigo, numero, vista, estado, estado));
                }else if(tipo.equals("twin room")){
                    busca.add(new QuartoDuploSolteiro(codigo, numero, vista, estado, estado));
                }else if(tipo.equals("double room")){
                    busca.add(new QuartoCasal(codigo, numero, vista, estado, estado));
                }
            }
            return busca;
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
            ResultSet rs = st.executeQuery("select * from QUARTO where codigo = " + cod);
            while (rs.next()){
                int codigo = Integer.parseInt(rs.getString("codigo"));
                int numero = Integer.parseInt(rs.getString("numero"));
                String tipo = rs.getString("tipo");
                String vista = rs.getString("vista");
                String estado = rs.getString("estado");
                if(tipo.equals("single room")){
                    quarto = new QuartoSolteiro(codigo, numero, vista, estado, estado);
                }else if(tipo.equals("twin room")){
                    quarto = new QuartoDuploSolteiro(codigo, numero, vista, estado, estado);
                }else if(tipo.equals("double room")){
                    quarto = new QuartoCasal(codigo, numero, vista, estado, estado);
                }
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
        Statement st = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("UPDATE QUARTO SET numero = " + quarto.getNumero() + 
                    ", tipo = '" + quarto.getTipo() +
                    "', preco = " + quarto.getPreco() +
                    ", tamanho = " + quarto.getTamanho() +
                    ", vista = '" + quarto.getVista() +
                    "', cama = " + quarto.getCama() +
                    ", banheiro = " + quarto.getBanheiro() +
                    ", frigobar = " + quarto.getFrigobar() +
                    ", tv = " + quarto.getTv() +
                    ", computador = " + quarto.getComputador()+
                    ", estado = '" + quarto.getEstado()
                            +"' where codigo = " + quarto.getCodigo());
                    
        
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
        }
    }
    
    public void interesse(Cliente cliente, Quarto quarto) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        Statement st = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("insert into INTERESSE (cod_cliente, cod_quarto)" +
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
            if(conn!=null) st.close();
        }catch(SQLException e){
            throw e;
        }
    }
    
    public static void closeResources (Connection conn, PreparedStatement stmt) throws SQLException{
        try{
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        }catch(SQLException e){
            throw e;
        }
    }
}
