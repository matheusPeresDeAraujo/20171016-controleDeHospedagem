package model;

public class PromocaoNv2 implements Promocao{
    
    @Override
    public int obterDesconto() {
        return -20;
    }

    @Override
    public String obterPromocao() {
        return "NV2";
    }
}
