package domein;

import exceptions.EmailException;
import java.time.LocalDate;

public class DomeinController {

    private final SpelerRepository spelerRepository;
    private Speler speler;
    private Spel spel;

    public DomeinController() {
        spelerRepository = new SpelerRepository();
    }

    public void startNieuwSpel() {
        setSpel(new Spel(speler));
        spelerRepository.slaKredietOp(speler);
    }

    public void rolDobbelstenen() {
        spel.rolDobbelstenen();
        if (spel.isAfgelopen()) {
            spelerRepository.slaKredietOp(speler);
        }
    }

    public boolean isErVoldoendeKrediet() {
        return speler.heeftVoldoendeKrediet(Spel.VEREIST_KREDIET);
    }

    public boolean isEindeSpel() {
        return spel.isAfgelopen();
    }

    public void registreer(String naam, String voornaam, String email, LocalDate geboortedatum, String wachtwoord, String wachtwoordBevestiging) {
        if (!wachtwoord.equals(wachtwoordBevestiging)) {
            throw new EmailException();
        }

        Speler nieuweSpeler = new Speler(naam, voornaam, email, geboortedatum, wachtwoord);
        setSpeler(nieuweSpeler); // ONTBREEKT!!
        spelerRepository.voegToe(nieuweSpeler);

    }

    public void meldAan(String emailadres, String wachtwoord) {
        Speler gevondenSpeler = spelerRepository.geefSpeler(emailadres, wachtwoord);
        if (gevondenSpeler != null) {
            setSpeler(gevondenSpeler);
        }
    }

    public int geefAantalOgenWorp() {
        return spel.getAantalOgenWorp();
    }

    public int geefScore() {
        return spel.getScore();
    }

    public String[] geefSpeler() {
        if (speler == null) {
            return null;
        }

        String[] spelerS = new String[3];
        spelerS[0] = speler.getVoornaam();
        spelerS[1] = speler.getNaam();
        spelerS[2] = String.format("%.2f", speler.getKrediet().doubleValue());
        return spelerS;
    }

    private void setSpeler(Speler speler) {
        this.speler = speler;
    }

    private void setSpel(Spel spel) {
        this.spel = spel;
    }

}
