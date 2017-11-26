package model;

public class PromocaoNv1 implements Promocao{

    @Override
    public int obterDesconto() {
        return -10;
    }

    @Override
    public String obterPromocao() {
        return "NV1";
    }
    
}
