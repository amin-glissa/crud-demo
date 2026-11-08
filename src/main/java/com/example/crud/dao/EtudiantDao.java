package com.example.crud.dao;

import com.example.crud.db.Database;
import com.example.crud.model.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EtudiantDao {

    // CREATE
    public void create(Etudiant e) throws SQLException {
        String sql = "INSERT INTO etudiant (nom, email) VALUES (?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getNom());
            ps.setString(2, e.getEmail());
            ps.executeUpdate();
        }
    }

    // READ (tous)
    public List<Etudiant> findAll() throws SQLException {
        List<Etudiant> list = new ArrayList<>();
        try (Connection c = Database.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM etudiant ORDER BY id")) {
            while (rs.next()) {
                list.add(new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("email")));
            }
        }
        return list;
    }

    // READ (un seul)
    public Optional<Etudiant> findById(int id) throws SQLException {
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM etudiant WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("email")));
                }
            }
        }
        return Optional.empty();
    }

    // UPDATE
    public boolean update(Etudiant e) throws SQLException {
        String sql = "UPDATE etudiant SET nom = ?, email = ? WHERE id = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getNom());
            ps.setString(2, e.getEmail());
            ps.setInt(3, e.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(int id) throws SQLException {
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM etudiant WHERE id = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
