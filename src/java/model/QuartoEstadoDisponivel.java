package model;

public class QuartoEstadoDisponivel implements QuartoEstado{
    @Override
    public String Disponivel(Quarto quarto) {
        return "Alteração recusada";
    }

    @Override
    public String Ocupado(Quarto quarto) {
        quarto.setQuartoEstado(new QuartoEstadoOcupado());
        return "Alteração aceita";
    }

    @Override
    public String Manutencao(Quarto quarto) {
        quarto.setQuartoEstado(new QuartoEstadoManutencao());
        return "Alteração aceita";
    }

    @Override
    public String getEstado(Quarto quarto) {
        quarto.setEstado("disponivel");
        return "disponivel";
    }

    @Override
    public String toString() {
        return "QuartoDisponivel";
    }
    
    
}
