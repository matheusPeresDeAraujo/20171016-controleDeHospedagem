package model;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import persistence.SalaDao;

public abstract class Sala {
    
    protected int codigo;
    protected int numero;
    protected double preco;
    protected double precoPromocao;
    protected Promocao promocao;

    public abstract String getNome();

    public String getPromocao() {
        return promocao.obterPromocao();
    }
    
    public int getDesconto(){
        return promocao.obterDesconto();
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPrecoPromocao() {
        return precoPromocao;
    }

    public void setPrecoPromocao(double precoPromocao) {
        this.precoPromocao = precoPromocao;
    }
    
    protected Double calculaPreco(){
        double desconto = 100 + this.getDesconto();
        return this.preco * desconto/100;
        
    }
    
    public static List<Sala> obterSalas() throws SQLException, ClassNotFoundException{
        return SalaDao.obterSalas();
    }
    
    public static Sala obterSala(int codigo) throws SQLException, ClassNotFoundException{
        return SalaDao.obterSala(codigo);
    }
    
    public void saveSala(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        
        setParameter(request);
        SalaDao.getInstance().save(this);
        
    }
    
    public void updateSala(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        
        this.codigo = Integer.parseInt(request.getParameter("textCodigo"));
        setParameter(request);
        SalaDao.getInstance().update(this);
        
    }
    
    public void setParameter(HttpServletRequest request){
        this.numero = Integer.parseInt(request.getParameter("textNumero"));
        this.preco = Double.parseDouble(request.getParameter("textPreco"));
    }
    
    public static void dropSala(int codigo) throws SQLException, ClassNotFoundException{
        
        SalaDao.getInstance().drop(codigo);
        
    }
}
