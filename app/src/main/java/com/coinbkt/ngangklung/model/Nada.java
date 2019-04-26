package com.coinbkt.ngangklung.model;

public class Nada {
    private int nadaImg;
    private boolean isSelected = false;

    public Nada(int nadaImg){
        this.nadaImg = nadaImg;
    }

    public void setNadaImg(int nadaImg) {
        this.nadaImg = nadaImg;
    }

    public int getNadaImg() {
        return nadaImg;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean selected) {
        isSelected = selected;
    }

    public void toggleSelected() {
        isSelected = !isSelected;
    }
}
