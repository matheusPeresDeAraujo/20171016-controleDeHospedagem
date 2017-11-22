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

/**
 *
 * @author matheus
 */
public class ClienteDao {
    
    
    private static ClienteDao instance = new ClienteDao();
    private ClienteDao(){}
    public static ClienteDao getInstance(){return instance;}
    
    
    private static String nameTable()       {return "CLIENTE";      }
    private static String codigo()          {return "CODIGO";       }
    private static String nome()            {return "NOME";         }
    private static String idade()           {return "IDADE";        }
    private static String identificacao()   {return "IDENTIFICACAO";}
    private static String telefone()        {return "TELEFONE";     }
    private static String celular()         {return "CELULAR";      }
    private static String email()           {return "EMAIL";        }
    
    
    public void save(Cliente cliente) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("insert into " + nameTable() + " (" + 
                            nome()          + "," + 
                            idade()         + "," +
                            identificacao() + "," +
                            telefone()      + "," +
                            celular()       + "," +
                            email()         + ")" +
                            "values (?,?,?,?,?,?)");
            
            parseAtributos(stmt, cliente);
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
            stmt = conn.prepareStatement("delete from " + nameTable() + " where " + codigo() + " = ?");
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
        List<Cliente> busca = null;
        try{
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from " + nameTable());
            busca = new ArrayList<>();
            while (rs.next()){
               
                busca.add(instanciaCliente(
                                            rs.getString(codigo()       ),
                                            rs.getString(nome()         ),
                                            rs.getString(idade()        ),
                                            rs.getString(identificacao()),
                                            rs.getString(telefone()     ),
                                            rs.getString(celular()      ),
                                            rs.getString(email()        )
                                            ));
                
            }
            return busca;
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
            stmt = conn.prepareStatement("select * from " + nameTable() + " where " + codigo() + " = ?");
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               
                cliente = instanciaCliente(
                                            rs.getString(codigo()       ),
                                            rs.getString(nome()         ),
                                            rs.getString(idade()        ),
                                            rs.getString(identificacao()),
                                            rs.getString(telefone()     ),
                                            rs.getString(celular()      ),
                                            rs.getString(email()        )
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
            stmt = conn.prepareStatement("UPDATE " + nameTable() + " SET " + 
                    nome()          + " = ?, " + 
                    idade()         + " = ?, " +
                    identificacao() + " = ?, " +
                    telefone()      + " = ?, " +
                    celular()       + " = ?, " +
                    email()         + " = ? where " +
                    codigo()        + " = ?");
            parseAtributos(stmt, cliente);
            stmt.setInt     (7, cliente.getCodigo()         );
            stmt.execute();
        
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
    
    public static void closeResources (Connection conn, PreparedStatement stmt) throws SQLException{
        try{
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        }catch(SQLException e){
            throw e;
        }
    }
    
    private static Cliente instanciaCliente(String codigo, String nome, String idade, String identificacao, String telefone, String celular, String email) throws SQLException{
        
        int codigoCliente = Integer.parseInt(codigo);
        String nomeCliente = nome;
        int idadeCliente = Integer.parseInt(idade);
        String identificacaoCliente = identificacao;
        String telefoneCliente = telefone;
        String celularCliente = celular;
        String emailCliente = email;
        
        return new Cliente(codigoCliente, idadeCliente, nomeCliente, identificacaoCliente, telefoneCliente, celularCliente, emailCliente);
    }
    
    private static void parseAtributos(PreparedStatement stmt, Cliente cliente) throws SQLException{
            stmt.setString  (1, cliente.getNome()           );
            stmt.setInt     (2, cliente.getIdade()          );
            stmt.setString  (3, cliente.getIdentificacao()  );
            stmt.setString  (4, cliente.getTelefone()       );
            stmt.setString  (5, cliente.getCelular()        );
            stmt.setString  (6, cliente.getEmail()          );
    }
}
