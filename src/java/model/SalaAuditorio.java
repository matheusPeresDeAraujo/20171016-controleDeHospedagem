package model;

public class SalaAuditorio extends Sala{
    public SalaAuditorio(){
        this.promocao = new PromocaoNv1();
    }
    
    public SalaAuditorio(int codigo, int numero, double preco){
        super();
        this.codigo = codigo;
        this.numero = numero;
        this.promocao = new PromocaoNv1();
        this.preco = preco;
        this.precoPromocao = calculaPreco();
    }
    
    @Override
    public String getNome() {
        return "Auditorio";
    }
}
