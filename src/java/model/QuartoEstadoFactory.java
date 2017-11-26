package model;

public class QuartoEstadoFactory {
    public static QuartoEstado create(String estado){
        QuartoEstado quartoEstadoObject =  null;
        String nomeClasse = "model.QuartoEstado"+estado;
        Class classe = null;
        Object objeto = null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            return null;
        }
        if(!(objeto instanceof QuartoEstado)) return null;
        quartoEstadoObject = (QuartoEstado) objeto;
        return quartoEstadoObject;
    }
}
