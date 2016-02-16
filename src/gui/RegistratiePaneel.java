package gui;

import domein.DomeinController;
import exceptions.EmailInGebruikException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class RegistratiePaneel extends GridPane
{
    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public RegistratiePaneel(DomeinController controller, HoofdPaneel hoofdPaneel)
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
        
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHalignment(HPos.RIGHT);
        
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHgrow(Priority.ALWAYS);
        
        getColumnConstraints().addAll(col1, col2, col3, col4);
    }
    
    private final TextField voornaam = new TextField();
    private final TextField naam = new TextField();
    private final TextField email = new TextField();
    private final DatePicker geboortedatum = new DatePicker(LocalDate.now());
    private final PasswordField wachtwoord = new PasswordField();
    private final PasswordField bevestiging = new PasswordField();
    private final Label foutbericht = new Label();
    
    private void voegComponentenToe()
    {
        Text header = new Text("Registratie");
        header.getStyleClass().add("hoofding");
        GridPane.setHalignment(header, HPos.LEFT);
        add(header, 0, 0, 4, 1);
        
        add(new Label("Voornaam:"), 0, 1, 1, 1);
        add(voornaam, 1, 1, 1, 1);
        add(new Label("Naam:"), 2, 1, 1, 1);
        add(naam, 3, 1, 1, 1);
        
        add(new Label("Email:"), 0, 2, 1, 1);
        add(email, 1, 2, 3, 1);
        
        add(new Label("Geboortedatum:"), 0, 3, 1, 1);
        add(geboortedatum, 1, 3, 3, 1);
        
        add(new Label("Wachtwoord:"), 0, 4, 1, 1);
        add(wachtwoord, 1, 4, 3, 1);
        
        add(new Label("Bevestiging:"), 0, 5, 1, 1);
        add(bevestiging, 1, 5, 3, 1);
        
        Button registreer = new Button("Registreer");
        registreer.setOnAction(this::registreer);
        registreer.setDefaultButton(true);
        foutbericht.getStyleClass().add("foutbericht");
        HBox controls = new HBox(registreer, foutbericht);
        controls.setSpacing(10);
        add(controls, 0, 6, 4, 1);
    }
    
    private void registreer(ActionEvent event)
    {
        if (voornaam.getText().trim().isEmpty() || naam.getText().trim().isEmpty()) {
            foutbericht.setText("Gelieve uw naam op te geven");
            return;
        }
        if (email.getText().trim().isEmpty()) {
            foutbericht.setText("Gelieve uw emailadres op te geven");
            return;
        }
        if (geboortedatum.getValue() == null) {
            foutbericht.setText("Gelieve uw geboortedatum op te geven");
            return;
        }
        if (wachtwoord.getText().trim().isEmpty() || bevestiging.getText().trim().isEmpty()) {
            foutbericht.setText("Gelieve uw wachtwoord op te geven");
            return;
        }
        if (!wachtwoord.getText().equals(bevestiging.getText())) {
            foutbericht.setText("Wachtwoord en bevestiging komen niet overeen");
            return;
        }
        if (geboortedatum.getValue().isAfter(LocalDate.now().minusYears(18))) {
            foutbericht.setText("Dit spel is enkel toegankelijk voor meerderjarigen");
            return;
        }
        try
        {
        controller.registreer(
                naam.getText().trim(),
                voornaam.getText().trim(),
                email.getText().trim(),
                geboortedatum.getValue(),
                wachtwoord.getText().trim(),
                bevestiging.getText().trim());
         foutbericht.setText(null);
         hoofdPaneel.spelerIsAangemeld();
        }
        catch(EmailInGebruikException e)
        {
            foutbericht.setText("e-mail moet uniek zijn!");
        }
       
    }
}
