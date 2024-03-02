package com.ecole221;

import com.ecole221.service.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.random;

public class Main2 {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Saisir le libelle de la classe");
            String libelle = sc.nextLine();
            System.out.println("Saisir le code de la classe");
            String code = sc.nextLine();

            int frais_inscription = 0;
            do {
                System.out.println("Saisir les frais d'inscription de la classe");
                frais_inscription = sc.nextInt();
             } while (frais_inscription < 1000);

            int mensualite = 0;
            do {
                System.out.println("Saisir la mensualite");
                mensualite = sc.nextInt();
            } while (mensualite  < 1000);

            int autres_frais = 0;
            do {
                System.out.println("Saisir les autres frais");
                autres_frais = sc.nextInt();
             } while (autres_frais < 1000);


            Random random = new Random();
            DBHelper db =  DBHelper.getInstance();
            String sql = "SELECT id FROM filiere";
            ResultSet rs = db.excuteSelect(sql, null);
            int filiere_id = 0;
            List<Integer> filiereIds = new ArrayList<>();
            while (rs.next()) {
                filiereIds.add(rs.getInt("id"));
            }
            rs.close();
            if (!filiereIds.isEmpty()) {
                filiere_id = filiereIds.get(random.nextInt(filiereIds.size()));
            }

            ClasseService classeService = new ClasseService();
            if(!classeService.findClasseByLibelle(libelle)){
                // insertion de la filiere
                classeService.addClasse(libelle,code,frais_inscription,mensualite,autres_frais,filiere_id);
                System.out.println("Cette classe a ete ajoute avec succes");
            }
            else{
                System.out.println("Cette classe existe déjà");
                System.out.println("Cette classe existe déjà zefsdgjhklj");
              }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
