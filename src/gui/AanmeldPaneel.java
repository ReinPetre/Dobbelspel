package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class AanmeldPaneel extends GridPane
{
    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public AanmeldPaneel(DomeinController controller, HoofdPaneel hoofdPaneel)
    {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;
        
        configureerGrid();
        voegComponentenToe();
    }

    private void configureerGrid()
    {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        
        getColumnConstraints().addAll(col1, col2);
    }

    private final TextField email = new TextField();
    private final PasswordField wachtwoord = new PasswordField();
    private final Label foutbericht = new Label();
    
    private void voegComponentenToe()
    {
        Text header = new Text("Aanmelden");
        header.getStyleClass().add("hoofding");
        GridPane.setHalignment(header, HPos.LEFT);
        add(header, 0, 0, 2, 1);
        
        add(new Label("Email:"), 0, 1, 1, 1);
        add(email, 1, 1, 1, 1);
        
        add(new Label("Wachtwoord:"), 0, 2, 1, 1);
        add(wachtwoord, 1, 2, 1, 1);
        
        Button aanmelden = new Button("Aanmelden");
        aanmelden.setOnAction(this::aanmelden);
        aanmelden.setDefaultButton(true);
        foutbericht.getStyleClass().add("foutbericht");
        HBox controls = new HBox(aanmelden, foutbericht);
        controls.setSpacing(10);
        add(controls, 0, 3, 2, 1);
        
        Hyperlink registratie = new Hyperlink("Nog geen account?");
        registratie.setOnAction(this::registreren);
        GridPane.setHalignment(registratie, HPos.LEFT);
        add(registratie, 0, 4, 2, 1);
    }
    
    private void aanmelden(ActionEvent event)
    {
        if (email.getText().trim().isEmpty()) {
            foutbericht.setText("Gelieve uw emailadres op te geven");
            return;
        }
        if (wachtwoord.getText().trim().isEmpty()) {
            foutbericht.setText("Gelieve uw wachtwoord op te geven");
            return;
        }
        
        controller.meldAan(email.getText().trim(), wachtwoord.getText().trim());
        
        if (controller.geefSpeler() == null) {
            foutbericht.setText("Deze speler bestaat niet of het wachtwoord is verkeerd");
            return;
        }
        
        foutbericht.setText(null);
        hoofdPaneel.spelerIsAangemeld();
    }
    
    private void registreren(ActionEvent event)
    {
        hoofdPaneel.toonRegistratie();
    }
}
