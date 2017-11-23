package model;

public class SalaAuditorio extends Sala{
    public SalaAuditorio(){
        this.nome = "auditorio";
        this.promocao = new PromocaoNv1();
    }
    
    public SalaAuditorio(int numero, double preco){
        super();
        this.nome = "auditorio";
        this.numero = numero;
        this.preco = preco;
    }
    
    public SalaAuditorio(int codigo, int numero, double preco){
        super();
        this.nome = "auditorio";
        this.codigo = codigo;
        this.numero = numero;
        this.promocao = new PromocaoNv1();
        this.preco = preco;
        this.precoPromocao = calculaPreco();
    }
}
