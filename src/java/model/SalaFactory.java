package model;

public class SalaFactory {
    public static Sala create(String nome){
        Sala salaObject =  null;
        String nomeClasse = "model.Sala"+nome;
        Class classe = null;
        Object objeto = null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            return null;
        }
        if(!(objeto instanceof Sala)) return null;
        salaObject = (Sala) objeto;
        return salaObject;
    }
}
