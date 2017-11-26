/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author matheus
 */
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
