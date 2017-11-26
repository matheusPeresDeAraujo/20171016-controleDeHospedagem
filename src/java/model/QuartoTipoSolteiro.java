package model;

public class QuartoTipoSolteiro extends Quarto{
    
    public QuartoTipoSolteiro() {
        
    }
    
    @Override
    public String getTipo() {
        return "Solteiro";
    }

    @Override
    public double getPreco() {
        return 300;
    }

    @Override
    public double getTamanho() {
        return 300;
    }

    @Override
    public int getCama() {
        return 1;
    }

    @Override
    public int getBanheiro() {
        return 1;
    }

    @Override
    public boolean getFrigobar() {
        return false;
    }

    @Override
    public boolean getTv() {
        return false;
    }

    @Override
    public boolean getComputador() {
        return false;
    }
    
}
