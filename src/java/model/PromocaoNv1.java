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
public class PromocaoNv1 implements Promocao{

    @Override
    public int obterDesconto() {
        return -10;
    }

    @Override
    public String obterPromocao() {
        return "NV1";
    }
    
}
