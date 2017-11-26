package model;

public class QuartoTipoCasal extends Quarto{
    
    public QuartoTipoCasal() {
        
    }
    
    @Override
    public String getTipo() {
        return "Casal";
    }

    @Override
    public double getPreco() {
        return 100;
    }

    @Override
    public double getTamanho() {
        return 100;
    }

    @Override
    public int getCama() {
        return 1;
    }

    @Override
    public int getBanheiro() {
        return 2;
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
        return true;
    }
}
