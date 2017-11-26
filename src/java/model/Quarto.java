package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.servlet.http.HttpServletRequest;
import persistence.QuartoDao;

public abstract class Quarto extends Observable{
    
    //Atributo temporário para controlar posição do memento
    protected int posicao = -2;
    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    
    protected int codigo = 0;
    protected QuartoEstado quartoEstado;
    protected int numero;
    protected String vista;


    public Quarto(){
        
    }    
    
    public Quarto(int codigo, int numero, String vista) {
        this.codigo = codigo;
        this.numero = numero;
        this.vista = vista;
    }
    
    
    private List<Observer> observers = new ArrayList();
    protected List<Quarto> estadosSalvos = new ArrayList();
    
    public abstract String getTipo();
    public abstract double getPreco();
    public abstract double getTamanho();
    public abstract int getCama();
    public abstract int getBanheiro();
    public abstract boolean getFrigobar();
    public abstract boolean getTv();
    public abstract boolean getComputador();
    
    public String getDadosQuarto(){
        return "Tipo: " + getTipo() + " - Preco: " + getPreco() + 
                " - Tamanho: " + getTamanho() + " - Cama: " + getCama() + 
                " - Banheiro: " + getBanheiro() + " - Frigobar: " + getFrigobar() + 
                " - Tv: " + getTv() + " - Computador: " + getComputador() ;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    } 

    public String getQuartoEstado() {
        return quartoEstado.getEstado(this);
    }

    public void setQuartoEstado(QuartoEstado quartoEstado) {
        this.quartoEstado = quartoEstado;
        
        if(quartoEstado.getEstado(this).equals("disponivel")){
            setChanged();
            notifyObservers();
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }
    
    public String disponivel(){
        return this.quartoEstado.Disponivel(this);
    }
    
    public String ocupado(){
        return this.quartoEstado.Ocupado(this);
    }
    
    public String manutencao(){
        return this.quartoEstado.Manutencao(this);
    }

    public List<Quarto> getEstadosSalvos() {
        return estadosSalvos;
    }

    public void setEstadosSalvos(Quarto estadosSalvos) {
        this.estadosSalvos.add(estadosSalvos);
    }
    
    public static List<Quarto> obterQuartos() throws SQLException, ClassNotFoundException{
        return QuartoDao.obterQuartos();
    }
    
    public static Quarto obterQuarto(int codigo) throws SQLException, ClassNotFoundException{
        return QuartoDao.obterQuarto(codigo);
    }
    
    public void saveQuarto(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        
        setParameter(request);
        QuartoDao.getInstance().save(this);
                
    }
    
    public void updateQuarto(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        
        setParameter(request);
        QuartoDao.getInstance().update(this);
        
    }
    
    public void setParameter(HttpServletRequest request){
        
        if(Integer.parseInt(request.getParameter("textCodigo")) != 0){
            this.codigo = Integer.parseInt(                request.getParameter("textCodigo"));
        }
            this.numero = Integer.parseInt(                request.getParameter("textNumero"));
            this.vista =                                   request.getParameter("textVista");
            this.quartoEstado = QuartoEstadoFactory.create(request.getParameter("textEstado"));
    }
    
    public static void dropQuarto(Integer codigo) throws SQLException, ClassNotFoundException{
        
        QuartoDao.getInstance().drop(codigo);
        
    }
    
    public void registerObserver(Observer observer){
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    
    public QuartoMemento saveToMemento(){
        return new QuartoMemento(quartoEstado);
    }
    
    @Override
    public void notifyObservers(){
        for(Observer ob : observers){
            System.out.println("Notificando observers");
            ob.update(this, ob);
        }
    }
    
    public void resteoreFromMemento(QuartoMemento memento){
        quartoEstado = memento.getEstadoSalvo();
    }
}
