package model;

public interface QuartoEstado {
    public String Disponivel(Quarto quarto);
    public String Ocupado(Quarto quarto);
    public String Manutencao(Quarto quarto);
    public String getEstado(Quarto quarto);
    
}
