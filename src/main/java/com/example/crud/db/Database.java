package com.example.crud.db;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:h2:./data/crud_db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "sa", "");
    }

    public static void init() {
        String sql = """
            CREATE TABLE IF NOT EXISTS etudiant (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nom VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL
            )""";
        try (Connection c = getConnection(); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur d'initialisation de la base", e);
        }
    }
}
