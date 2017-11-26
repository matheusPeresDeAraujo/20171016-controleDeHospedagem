package model;

public class Aluga {
    
    private int codigo;
    private Quarto codQuarto;
    private Cliente codCliente;

    public Aluga() {
    }

    public Aluga(int codigo, Quarto codQuarto, Cliente codCliente) {
        this.codigo = codigo;
        this.codQuarto = codQuarto;
        this.codCliente = codCliente;
    }
    
    public Aluga(Quarto codQuarto, Cliente codCliente) {
        this.codQuarto = codQuarto;
        this.codCliente = codCliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Quarto getCodQuarto() {
        return codQuarto;
    }

    public void setCodQuarto(Quarto codQuarto) {
        this.codQuarto = codQuarto;
    }

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }
    
    
}
