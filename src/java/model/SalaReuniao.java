package model;

public class SalaReuniao extends Sala{
    public SalaReuniao(){
        this.promocao = new PromocaoNv2();
    }
    
    public SalaReuniao(int codigo, int numero, double preco){
        super();
        this.codigo = codigo;
        this.numero = numero;
        this.promocao = new PromocaoNv2();
        this.preco = preco;
        this.precoPromocao = calculaPreco();
    }

    @Override
    public String getNome() {
        return "Reuniao";
    }
}
