package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
    
    
    protected int codigo;
    protected QuartoEstado quartoEstado;
    protected String estado;
    protected int numero;
    protected String vista;
    
    protected String tipo;
    protected double preco;
    protected double tamanho;
    protected int cama;
    protected int banheiro;
    protected boolean frigobar;
    protected boolean tv;
    protected boolean computador;

    public Quarto(int codigo, String estado, int numero, String vista, String tipo, double preco, double tamanho, int cama, int banheiro, boolean frigobar, boolean tv, boolean computador) {
        this.codigo = codigo;
        this.estado = estado;
        this.numero = numero;
        this.vista = vista;
        this.tipo = tipo;
        this.preco = preco;
        this.tamanho = tamanho;
        this.cama = cama;
        this.banheiro = banheiro;
        this.frigobar = frigobar;
        this.tv = tv;
        this.computador = computador;
    }
    
    public Quarto(){
        
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    
    public void registerObserver(Observer observer){
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    
    public QuartoMemento saveToMemento(){
        return new QuartoMemento(quartoEstado);
    }
    
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
