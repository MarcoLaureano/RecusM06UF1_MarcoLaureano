package com.balmes;


import java.io.Serializable;

public class ArticlesCompra implements Serializable {
    private String descripcio;
    private double preu;
    private double quantitat;
    private String unitat;
    private String seccio;

    public ArticlesCompra() {
    }

    public ArticlesCompra(String descripcio,double preu, double quantitat, String unitat, String seccio) {
        this.descripcio = descripcio;
        this.preu = preu;
        this.quantitat = quantitat;
        this.unitat = unitat;
        this.seccio = seccio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public double getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(double quantitat) {
        this.quantitat = quantitat;
    }

    public String getUnitat() {
        return unitat;
    }

    public void setUnitat(String unitat) {
        this.unitat = unitat;
    }

    public String getSeccio() {
        return seccio;
    }

    public void setSeccio(String seccio) {
        this.seccio = seccio;
    }
}