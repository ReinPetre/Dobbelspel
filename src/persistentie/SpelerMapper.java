package persistentie;

import domein.Speler;
import exceptions.EmailInGebruikException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SpelerMapper {

    public void voegToe(Speler speler) {

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO speler (naam, voornaam, emailadres, geboortedatum, wachtwoord, beheerder, krediet)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            query.setString(1, speler.getNaam());
            query.setString(2, speler.getVoornaam());
            query.setString(3, speler.getEmailadres());
            query.setLong(4, speler.getGeboorteDatum().toEpochDay());
            query.setString(5, speler.getWachtwoord());
            query.setBoolean(6, speler.isAdminrechten());
            query.setBigDecimal(7, speler.getKrediet());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Speler> geefSpelers() {
        List<Speler> spelers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM speler");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("naam");
                    String voornaam = rs.getString("voornaam");
                    String emailadres = rs.getString("emailadres");
                    LocalDate geboortedatum = LocalDate.ofEpochDay(rs.getLong("geboortedatum"));
                    String wachtwoord = rs.getString("wachtwoord");
                    boolean beheerder = rs.getBoolean("beheerder");
                    BigDecimal krediet = rs.getBigDecimal("krediet");

                    spelers.add(new Speler(naam, voornaam, emailadres, geboortedatum, wachtwoord, beheerder, krediet));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelers;
    }

    public Speler geefSpeler(String emailadres) {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM speler WHERE emailadres = ?");
            query.setString(1, emailadres);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String naam = rs.getString("naam");
                    String voornaam = rs.getString("voornaam");
                    LocalDate geboortedatum = LocalDate.ofEpochDay(rs.getLong("geboortedatum"));
                    String wachtwoord = rs.getString("wachtwoord");
                    boolean beheerder = rs.getBoolean("beheerder");
                    BigDecimal krediet = rs.getBigDecimal("krediet");

                    speler = new Speler(naam, voornaam, emailadres, geboortedatum, wachtwoord, beheerder, krediet);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }

    public void slaKredietOp(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("UPDATE speler SET krediet = ? WHERE emailadres = ?");
            query.setBigDecimal(1, speler.getKrediet());
            query.setString(2, speler.getEmailadres());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
