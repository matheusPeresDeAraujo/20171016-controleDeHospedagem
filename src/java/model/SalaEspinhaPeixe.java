package model;

public class SalaEspinhaPeixe extends Sala{
    public SalaEspinhaPeixe(){
        this.promocao = new PromocaoNv1();
    }
    
    public SalaEspinhaPeixe(int codigo, int numero, double preco){
        super();
        this.codigo = codigo;
        this.numero = numero;
        this.promocao = new PromocaoNv1();
        this.preco = preco;
        this.precoPromocao = calculaPreco();
    }

    @Override
    public String getNome() {
        return "EspinhaPeixe";
    }
}
