package com.Dudek9.kdhrt.alkomat01;

public class Alkomat {


    public double policz(double procent,double mlC,double ilosc){
        double ml=(mlC*procent*ilosc)/100;
        double g=ml*0.79;
        System.out.println("ml:"+ml+"\ng:"+g);
        return g;
    }

    public double policzP(double procent,double mlC,double ilosc,boolean plec,double waga){
        double g=policz(procent,mlC,ilosc);
        if(plec==true){
            return (g/(0.7*waga));
        }else
            return (g/(0.6*waga));
    }
}
