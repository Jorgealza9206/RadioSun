package com.example.radiosun.clases;

public class PruebaUnitaria {

    public double paneles(double consumo_1,double consumo_2,double consumo_3,double consumo_4,double consumo_5,double consumo_6){

        double consumo[] = {consumo_1,consumo_2,consumo_3,consumo_4,consumo_5,consumo_6};
        double maximo = consumo[0];

        for(int i=0;i<6;i++){
            if(maximo<consumo[i]){
                maximo = consumo[i];
            }
        }
        return maximo;
    }
}
