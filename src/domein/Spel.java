package domein;

import exceptions.OnvoldoendeKredietException;
import java.math.BigDecimal;
import java.util.*;

public class Spel {

    public static final BigDecimal VEREIST_KREDIET = BigDecimal.ONE;

    private final List<Dobbelsteen> dobbelstenen;
    private final Speler speler;
    private int score;
    private int eersteWorp;

    public Spel(Speler speler) {

        //initialisatie
        score = -1;
        eersteWorp = 0;
        dobbelstenen = new ArrayList<>();

        if (!speler.heeftVoldoendeKrediet(VEREIST_KREDIET)) {
            throw new OnvoldoendeKredietException();
        }

        this.speler = speler;
        speler.pasKredietAan(BigDecimal.ONE.negate());

        dobbelstenen.add(new Dobbelsteen());
        dobbelstenen.add(new Dobbelsteen());
    }

    public void rolDobbelstenen() {
        if (isAfgelopen()) {
            throw new IllegalStateException("Het spel is reeds afgelopen");
        }

        for (Dobbelsteen d : dobbelstenen) {
            d.rol();
        }

        int aantalOgenWorp = getAantalOgenWorp();

        if (eersteWorp == 0) {
            setEersteWorp(aantalOgenWorp);
            if (aantalOgenWorp == 7 || aantalOgenWorp == 11) {
                setScore(2);
            }
        } else if (aantalOgenWorp == eersteWorp) {
            setScore(1);
        } else if (aantalOgenWorp == 7 || aantalOgenWorp == 11) {
            setScore(0);
        }

        if (score != -1) {
            speler.pasKredietAan(new BigDecimal(score));
        }
    }

    public int getAantalOgenWorp() {
        int aantalOgenWorp = 0;
        for (Dobbelsteen d : dobbelstenen) {
            aantalOgenWorp += d.getAantalOgen();
        }
        return aantalOgenWorp;
    }

    public boolean isAfgelopen() {
        return score != -1;
    }

    public int getScore() {
        return this.score;
    }

    public int getEersteWorp() {
        return this.eersteWorp;
    }

    private void setScore(int score) {
        this.score = score;
    }

    private void setEersteWorp(int eersteWorp) {
        this.eersteWorp = eersteWorp;
    }
    
    
}
