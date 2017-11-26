package model;

public class QuartoMemento {
    
    private QuartoEstado estadoSalvo;
    
    public QuartoMemento(QuartoEstado estadoSalvar){
        estadoSalvo = estadoSalvar;
    }
    
    public QuartoEstado getEstadoSalvo(){
        return estadoSalvo;
    }
}
