package com.example.crud;

import com.example.crud.dao.EtudiantDao;
import com.example.crud.db.Database;
import com.example.crud.model.Etudiant;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Database.init();
        EtudiantDao dao = new EtudiantDao();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== GESTION DES ETUDIANTS ===");
            System.out.println("1. Ajouter");
            System.out.println("2. Lister");
            System.out.println("3. Modifier");
            System.out.println("4. Supprimer");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            String choix = sc.nextLine();

            switch (choix) {
                case "1" -> {
                    System.out.print("Nom : ");
                    String nom = sc.nextLine();
                    System.out.print("Email : ");
                    String email = sc.nextLine();
                    dao.create(new Etudiant(nom, email));
                    System.out.println("Etudiant ajouté.");
                }
                case "2" -> dao.findAll().forEach(System.out::println);
                case "3" -> {
                    System.out.print("ID à modifier : ");
                    int id = Integer.parseInt(sc.nextLine());
                    var opt = dao.findById(id);
                    if (opt.isEmpty()) {
                        System.out.println("Introuvable.");
                        break;
                    }
                    Etudiant e = opt.get();
                    System.out.print("Nouveau nom (" + e.getNom() + ") : ");
                    e.setNom(sc.nextLine());
                    System.out.print("Nouvel email (" + e.getEmail() + ") : ");
                    e.setEmail(sc.nextLine());
                    dao.update(e);
                    System.out.println("Modifié.");
                }
                case "4" -> {
                    System.out.print("ID à supprimer : ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println(dao.delete(id) ? "Supprimé." : "Introuvable.");
                }
                case "0" -> {
                    System.out.println("Au revoir !");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }
}
