package model;

public class SalaEscolar extends Sala{
    public SalaEscolar(){
        this.promocao = new PromocaoNv1();
    }
    
    public SalaEscolar(int codigo, int numero, double preco){
        super();
        this.codigo = codigo;
        this.numero = numero;
        this.promocao = new PromocaoNv1();
        this.preco = preco;
        this.precoPromocao = calculaPreco();
    }

    @Override
    public String getNome() {
        return "Escolar";
    }
}
