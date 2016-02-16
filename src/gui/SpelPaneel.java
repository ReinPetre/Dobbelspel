package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class SpelPaneel extends VBox
{
    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public SpelPaneel(DomeinController controller, HoofdPaneel hoofdPaneel)
    {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;
        
        voegComponentenToe();
    }
    
    private final Button start = new Button("Start");
    private final Button rol = new Button("Rol");
    private final TextArea resultaten = new TextArea();
    
    private void voegComponentenToe()
    {
        setPadding(new Insets(10));
        setSpacing(10);
        
        start.setOnAction(this::start);
        rol.setOnAction(this::rol);
        
        start.setDisable(true);
        rol.setDisable(true);
        
        getChildren().addAll(start, resultaten, rol);
    }
    
    public void controleerKrediet()
    {
        start.setDisable(!controller.isErVoldoendeKrediet());
    }
    
    private void start(ActionEvent event)
    {
        controller.startNieuwSpel();
        resultaten.appendText("Het spel is gestart.\n");
        start.setDisable(true);
        rol.setDisable(false);
        hoofdPaneel.vernieuwStatus();
    }
    
    private void rol(ActionEvent event)
    {
        controller.rolDobbelstenen();
        resultaten.appendText("U rolde een " + controller.geefAantalOgenWorp() + ".\n");
        
        if (controller.isEindeSpel()) {
            resultaten.appendText("Uw score: " + controller.geefScore() + ".\n");
            rol.setDisable(true);
            controleerKrediet();
            hoofdPaneel.vernieuwStatus();
        }
    }
}
