package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HoofdPaneel extends BorderPane
{
    private final DomeinController controller;
    private final RegistratiePaneel registratie;
    private final AanmeldPaneel aanmelden;
    private final SpelPaneel spel;

    public HoofdPaneel(DomeinController controller)
    {
        this.controller = controller;
        this.registratie = new RegistratiePaneel(controller, this);
        this.aanmelden = new AanmeldPaneel(controller, this);
        this.spel = new SpelPaneel(controller, this);
        
        voegComponentenToe();
    }
    
    private final Label status = new Label();
    
    private void voegComponentenToe()
    {
        ImageView dobbelsteen = new ImageView(getClass().getResource("/gui/images/dobbelsteen.png").toExternalForm());
        Text titel = new Text("Dobbelsteen");
        titel.setId("titel");
        
        HBox titelBox = new HBox(dobbelsteen, titel);
        titelBox.setSpacing(10);
        titelBox.setPadding(new Insets(10));
        
        status.setText("Welkom speler. Gelieve u hieronder aan te melden om het spel te spelen.");
        status.setId("status");
        status.setMaxWidth(Double.MAX_VALUE);
        
        VBox hoofding = new VBox(titelBox, status);
        setTop(hoofding);
        
        setCenter(aanmelden);
    }
    
    public void spelerIsAangemeld()
    {
        setCenter(spel);
        spel.controleerKrediet();
        vernieuwStatus();
    }
    
    public void toonRegistratie()
    {
        setCenter(registratie);
    }
    
    public void vernieuwStatus()
    {
        String voornaam = controller.geefSpeler()[0];
        String krediet = controller.geefSpeler()[2];
        status.setText(String.format("Welkom %s. Uw krediet bedraagt €%s.", voornaam, krediet));
    }
}
