package domein;

import exceptions.EmailInGebruikException;
import persistentie.SpelerMapper;

public class SpelerRepository {

    private final SpelerMapper mapper;
    //private List<Speler> spelers; //de lijst wordt eigenlijk niet gebruikt 

    public SpelerRepository() {
        mapper = new SpelerMapper();
        //spelers = new ArrayList<>();
    }
    
    public void voegToe(Speler speler) {
       if (bestaatSpeler(speler.getEmailadres()))
            throw new EmailInGebruikException();
       
       mapper.voegToe(speler);
    }

    private boolean bestaatSpeler(String emailadres){
        return mapper.geefSpeler(emailadres)!=null;
    }
    
    public Speler geefSpeler(String emailadres, String wachtwoord){
        Speler s = mapper.geefSpeler(emailadres);
        if (s.getWachtwoord().equals(wachtwoord))
            return s;
        
        return null;
    }
    
    public void slaKredietOp(Speler speler) {
        mapper.slaKredietOp(speler);
    }
    
}
