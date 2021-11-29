package com.example.radiosun.modelos;

public class Sitio {
    private int id;
    private String nombre;
    private double radiacion;
    private boolean consumo;
    private double mes_1;
    private double mes_2;
    private double mes_3;
    private double mes_4;
    private double mes_5;
    private double mes_6;
    private double p_panel;
    private double n_panel;

    public Sitio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getRadiacion() {
        return radiacion;
    }

    public void setRadiacion(double radiacion) {
        this.radiacion = radiacion;
    }



    public void setConsumo(boolean consumo) {
        this.consumo = consumo;
    }

    public double getMes_1() {
        return mes_1;
    }

    public void setMes_1(double mes_1) {
        this.mes_1 = mes_1;
    }

    public double getMes_2() {
        return mes_2;
    }

    public void setMes_2(double mes_2) {
        this.mes_2 = mes_2;
    }

    public double getMes_3() {
        return mes_3;
    }

    public void setMes_3(double mes_3) {
        this.mes_3 = mes_3;
    }

    public double getMes_4() {
        return mes_4;
    }

    public void setMes_4(double mes_4) {
        this.mes_4 = mes_4;
    }

    public double getMes_5() {
        return mes_5;
    }

    public void setMes_5(double mes_5) {
        this.mes_5 = mes_5;
    }

    public double getMes_6() {
        return mes_6;
    }

    public void setMes_6(double mes_6) {
        this.mes_6 = mes_6;
    }

    public double getP_panel() {
        return p_panel;
    }

    public void setP_panel(double p_panel) {
        this.p_panel = p_panel;
    }

    public double getN_panel() {
        return n_panel;
    }

    public void setN_panel(double n_panel) {
        this.n_panel = n_panel;
    }

    //Metodo del objeto Sitio

    public boolean isConsumo() {
        return consumo;
    }

}
