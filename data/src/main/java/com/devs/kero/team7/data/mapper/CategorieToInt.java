package com.devs.kero.team7.data.mapper;

import com.devs.kero.team7.domain.entities.Categorie;

import static com.devs.kero.team7.domain.entities.Categorie.Blue;
import static com.devs.kero.team7.domain.entities.Categorie.Defeult;
import static com.devs.kero.team7.domain.entities.Categorie.Green;
import static com.devs.kero.team7.domain.entities.Categorie.Red;
import static com.devs.kero.team7.domain.entities.Categorie.Yellow;

public class CategorieToInt  {
    public static   Categorie to(int integer) {
        switch (integer){
            case 0: return  Defeult ;
            case 1 : return Red  ;
            case 2 : return Blue ;
            case 3: return Green  ;
            case 4 : return Yellow ;
            default:return Defeult ;
        }


    }


    public static int from(Categorie categore) {
        int categorie ;
        switch (categore){
            case Defeult: categorie =0 ;
                break;
            case Yellow: categorie = 4 ;
                break;
            case Green: categorie =3  ;
                break;
            case Blue: categorie = 2;
                break;
            case Red:categorie = 1 ;
                break;
            default: categorie= 0 ;
        }
        return categorie;
    }
}
