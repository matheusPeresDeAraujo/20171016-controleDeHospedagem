package model;

public class QuartoEstadoOcupado implements QuartoEstado{

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
        quarto.setQuartoEstado(new QuartoEstadoManutencao());
        return "Alteração aceita";
    }

    @Override
    public String getEstado(Quarto quarto) {
        quarto.setEstado("ocupado");
        return "ocupado";
    }

    @Override
    public String toString() {
        return "QuartoOcupado";
    }
    
}
