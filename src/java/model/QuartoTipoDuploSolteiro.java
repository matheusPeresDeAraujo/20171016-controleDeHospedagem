package model;

public class QuartoTipoDuploSolteiro extends Quarto{
    
    public QuartoTipoDuploSolteiro() {
        
    }
    
    @Override
    public String getTipo() {
        return "DuploSolteiro";
    }

    @Override
    public double getPreco() {
        return 200;
    }

    @Override
    public double getTamanho() {
        return 200;
    }

    @Override
    public int getCama() {
        return 2;
    }

    @Override
    public int getBanheiro() {
        return 1;
    }

    @Override
    public boolean getFrigobar() {
        return true;
    }

    @Override
    public boolean getTv() {
        return true;
    }

    @Override
    public boolean getComputador() {
        return false;
    }
}
