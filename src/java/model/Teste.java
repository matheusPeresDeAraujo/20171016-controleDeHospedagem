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
public class Teste {

    public static void main(String[] args) {
        Sala sala = (Sala) SalaFactory.create("Auditorio");
        System.out.println(sala.getNome());
    }
}
