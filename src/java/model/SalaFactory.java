/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Action;

/**
 *
 * @author matheus
 */
public class SalaFactory {
    public static Sala create(String nome){
        Sala salaObject =  null;
        String nomeClasse = "model.Sala"+nome;
        Class classe = null;
        Object objeto = null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            return null;
        }
        if(!(objeto instanceof Sala)) return null;
        salaObject = (Sala) objeto;
        return salaObject;
    }
}
