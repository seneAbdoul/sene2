package com.ecole221;

import com.ecole221.service.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Saisir le nouvelle libelle de la filiere");
            String libelle = sc.nextLine();

            int id = 0;
            do {
                System.out.println("Saisir l'id de la filiere à modifier :");
                id = sc.nextInt();
              } while (id < 0);

            DBHelper db = DBHelper.getInstance();
            String sql = "SELECT id FROM filiere";
            ResultSet rs = db.excuteSelect(sql, null);

            List<Integer> filiereIds = new ArrayList<>();
            while (rs.next()) {
                filiereIds.add(rs.getInt("id"));
            }
            rs.close();

            boolean idFound = false;
            for (Integer filiereId : filiereIds) {
                if (filiereId == id) {
                    idFound = true;
                    break;
                }
            }

            if (idFound) {
                FiliereService filiereService = new FiliereService();
                if(!filiereService.findFiliereByLibelle(libelle)){
                    // insertion de la filiere
                    filiereService.updateFiliere(id,libelle);
                    System.out.println("Cette filiere a ete modifie avec success");
                }
                else{
                    System.out.println("Cette filiere existe déjà");
                }
            } else {
                System.out.println("cette id n'existe pas dans la base de donnee");
                System.out.println("impossible d'effectuer une modification");
            }

          } catch (Exception e) {
            System.out.println(e);
           }
    }
}
