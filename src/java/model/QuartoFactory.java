package model;

public class QuartoFactory {
    public static Quarto create(String tipo){
        Quarto quartoObject =  null;
        String nomeClasse = "model.QuartoTipo"+tipo;
        Class classe = null;
        Object objeto = null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            return null;
        }
        if(!(objeto instanceof Quarto)) return null;
        quartoObject = (Quarto) objeto;
        return quartoObject;
    }
}
