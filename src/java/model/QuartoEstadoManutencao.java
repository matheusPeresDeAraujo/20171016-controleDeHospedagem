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
public class QuartoEstadoManutencao implements QuartoEstado{

    @Override
    public String Disponivel(Quarto quarto) {
        quarto.setQuartoEstado(new QuartoEstadoDisponivel());
        return "Alteração aceita";
    }

    @Override
    public String Ocupado(Quarto quarto) {
        return "Alteração recusada";
    }

    @Override
    public String Manutencao(Quarto quarto) {
        return "Alteração recusada";
    }

    @Override
    public String getEstado(Quarto quarto) {
        quarto.setEstado("manutencao");
        return "manutencao";
    }

    @Override
    public String toString() {
        return "QuartoManutencao";
    }
    
}
