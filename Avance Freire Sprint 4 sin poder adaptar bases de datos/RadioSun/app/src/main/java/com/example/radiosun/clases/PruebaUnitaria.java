package com.example.radiosun.clases;

public class PruebaUnitaria {

    public double paneles(double radiacion, double consumo_1,double consumo_2,double consumo_3,double consumo_4,double consumo_5,double consumo_6){

        double consumo[] = {consumo_1,consumo_2,consumo_3,consumo_4,consumo_5,consumo_6};
        double maximo = consumo[0];
        double p = 0.0;

        for(int i=0;i<6;i++){
            if(maximo<consumo[i]){
                maximo = consumo[i];
            }
        }

        p = (((maximo/30.0)/radiacion)*1000.0)/445.0;
        p = Math.ceil(p);

        return p;
    }
}
