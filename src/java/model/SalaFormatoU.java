package model;

public class SalaFormatoU extends Sala{
    
    public SalaFormatoU(){
        this.promocao = new PromocaoNv1();
    }
    
    public SalaFormatoU(int codigo, int numero, double preco){
        super();
        this.codigo = codigo;
        this.numero = numero;
        this.promocao = new PromocaoNv1();
        this.preco = preco;
        this.precoPromocao = calculaPreco();
    }

    @Override
    public String getNome() {
        return "FormatoU";
    }
}
