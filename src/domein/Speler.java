package domein;

import exceptions.VerplichtVeldException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Speler {

    private String naam;
    private String voornaam;
    private String email;
    private String wachtwoord;
    private boolean adminrechten;
    private LocalDate geboorteDatum;
    private BigDecimal krediet;

    public Speler(String naam, String voornaam, String email, LocalDate geboorteDatum, String wachtwoord, boolean beheerder, BigDecimal krediet) {
        setNaam(naam);
        setVoornaam(voornaam);
        setEmail(email);
        setGeboorteDatum(geboorteDatum);
        setWachtwoord(wachtwoord);
        setAdminrechten(beheerder);
        setKrediet(krediet);
    }

    public Speler(String naam, String voornaam, String emailadres, LocalDate geboorteDatum, String wachtwoord) {
        this(naam, voornaam, emailadres, geboorteDatum, wachtwoord, false, new BigDecimal(5.0));
    }

    public void pasKredietAan(BigDecimal bedrag) {
        this.krediet = this.krediet.add(bedrag);
    }

    public boolean heeftVoldoendeKrediet(BigDecimal bedrag) {
        return krediet.compareTo(bedrag) >= 0;
    }

    public String getNaam() {
        return this.naam;
    }

    private void setNaam(String naam) {
        if (naam == null || naam.length() == 0) {
            throw new VerplichtVeldException("Elke speler heeft verplicht een naam.");
        }
        this.naam = naam;
    }

    public String getVoornaam() {
        return this.voornaam;
    }

    private void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.length() == 0) {
            throw new VerplichtVeldException("Elke speler heeft verplicht een voornaam.");
        }
        this.voornaam = voornaam;
    }

    public String getEmailadres() {
        return this.email;
    }

    private void setEmail(String email) {
        if (email == null || email.length() == 0) {
            throw new VerplichtVeldException("Elke speler heeft verplicht een e-mailadres.");
        }        
        this.email = email;
    }

    public String getWachtwoord() {
        return this.wachtwoord;
    }

    private void setWachtwoord(String wachtwoord) {
        if (wachtwoord == null || wachtwoord.length() == 0) {
            throw new VerplichtVeldException("Elke speler heeft verplicht een wachtwoord.");
        }           
        this.wachtwoord = wachtwoord;
    }

    public LocalDate getGeboorteDatum() {
        return this.geboorteDatum;
    }

    private void setGeboorteDatum(LocalDate geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public BigDecimal getKrediet() {
        return this.krediet;
    }

    private void setKrediet(BigDecimal krediet) {
        this.krediet = krediet;
    }

    public boolean isAdminrechten() {
        return adminrechten;
    }

    private void setAdminrechten(boolean adminrechten) {
        this.adminrechten = adminrechten;
    }

}
