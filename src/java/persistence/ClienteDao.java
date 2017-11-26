package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDao {
    
    
    private static ClienteDao instance = new ClienteDao();
    private ClienteDao(){}
    public static ClienteDao getInstance(){return instance;}
    
    
    public void save(Cliente cliente) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("INSERTE INTO CLIENTE "
                    + "(NOME, IDADE, IDENTIFICACAO, TELEFONE, CELULAR, EMAIL) "
                    + "VALUES (?,?,?,?,?,?)");
            parseAtributos(stmt, cliente);
            
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
            stmt = conn.prepareStatement("DELETE FROM CLIENTE WHERE CODIGO = ?");
            stmt.setInt(1, codigo);
            stmt.execute();
        
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, stmt);
        }
    }
    
    public static List<Cliente> obterClientes() throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        Statement st = null;
        List<Cliente> clientes = new ArrayList<>();
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CLIENTE");
            while (rs.next()){
               
                clientes.add(instanciaCliente(
                                            rs.getString("CODIGO"       ),
                                            rs.getString("NOME"         ),
                                            rs.getString("IDADE"        ),
                                            rs.getString("IDENTIFICACAO"),
                                            rs.getString("TELEFONE"     ),
                                            rs.getString("CELULAR"      ),
                                            rs.getString("EMAIL"        )
                                            ));
                
            }
            return clientes;
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, st);
        }
    }
    
    public static Cliente obterCliente(int codigo) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        Cliente cliente = null;
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("SELECT * FROM CLIENTE WHERE CODIGO = ?");
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               
                cliente = instanciaCliente(
                                            rs.getString("CODIGO"       ),
                                            rs.getString("NOME"         ),
                                            rs.getString("IDADE"        ),
                                            rs.getString("IDENTIFICACAO"),
                                            rs.getString("TELEFONE"     ),
                                            rs.getString("CELULAR"      ),
                                            rs.getString("EMAIL"        )
                                            );
                
            }
            return cliente;
        }catch(SQLException e){
            throw e;
        }finally{
            closeResources(conn, stmt);
        }
    }
    
    public void update(Cliente cliente) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("UPDATE CLIENTE SET NOME = ?, IDADE = ?, IDENTIFICACAO = ?, TELEFONE = ?, CELULAR = ?, EMAIL = ? WHERE CODIGO = ?");
            parseAtributos(stmt, cliente);
        
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
    
    private static Cliente instanciaCliente(String codigo, String nome, String idade, String identificacao, String telefone, String celular, String email) throws SQLException{
        
        int codigoCliente = Integer.parseInt(codigo);
        int idadeCliente = Integer.parseInt(idade);
        
        return new Cliente(codigoCliente, idadeCliente, nome, identificacao, telefone, celular, email);
    }
    
    private static void parseAtributos(PreparedStatement stmt, Cliente cliente) throws SQLException{
            stmt.setString  (1, cliente.getNome()           );
            stmt.setInt     (2, cliente.getIdade()          );
            stmt.setString  (3, cliente.getIdentificacao()  );
            stmt.setString  (4, cliente.getTelefone()       );
            stmt.setString  (5, cliente.getCelular()        );
            stmt.setString  (6, cliente.getEmail()          );
        if(cliente.getCodigo() != 0){
            stmt.setInt     (7, cliente.getCodigo()         );
        }
            stmt.execute();
    }
}
