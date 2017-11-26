package model;

public class SalaBanquete extends Sala{
    
    public SalaBanquete(){
        this.promocao = new PromocaoNv1();
    }
    
    public SalaBanquete(int codigo, int numero, double preco){
        super();
        this.codigo = codigo;
        this.numero = numero;
        this.promocao = new PromocaoNv1();
        this.preco = preco;
        this.precoPromocao = calculaPreco();
    }
    
    @Override
    public String getNome() {
        return "Banquete";
    }
}