package com.example.crud.model;

public class Etudiant {
    private int id;
    private String nom;
    private String email;

    public Etudiant(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public Etudiant(String nom, String email) {
        this(0, nom, email);
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "[" + id + "] " + nom + " - " + email;
    }
}
