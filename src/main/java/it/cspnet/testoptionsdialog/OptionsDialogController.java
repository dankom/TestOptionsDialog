/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cspnet.testoptionsdialog;

import it.cspnet.testoptionsdialog.controls.Messenger;
import it.cspnet.numberspinner.NumberSpinner;
//import it.cspnet.parolierefx.interfaces.IMediator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Danko
 */
public class OptionsDialogController extends Stage implements Initializable, Runnable{
    private ResourceBundle paroliereStrings;
    private Parent parent;
//    private IMediator mediator;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ComboBox<String> cmbUser;
    @FXML private ComboBox<String> cmbLanguage;
    @FXML private ComboBox<String> cmbOpponent;
    @FXML private Button btnOptionsOK;
    @FXML private NumberSpinner spinnerGridDimension;
    @FXML private NumberSpinner spinnerGameDuration;
    @FXML private NumberSpinner spinnerMinWordLength;
    @FXML private NumberSpinner spinnerMinLenScore;
    @FXML private NumberSpinner spinnerAddScore;
    @FXML private NumberSpinner spinnerDoubleScoreLength;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinnerGridDimension.setMinValue(3);
        spinnerGridDimension.setMaxValue(8);
        spinnerGameDuration.setMinValue(1);
        spinnerGameDuration.setMaxValue(10);
        spinnerMinWordLength.setMinValue(4);
        spinnerMinWordLength.setMaxValue(10);
        spinnerMinLenScore.setMinValue(1);
        spinnerMinLenScore.setMaxValue(5);
        spinnerAddScore.setMinValue(1);
        spinnerAddScore.setMaxValue(20);
        spinnerDoubleScoreLength.setMinValue(1);
        spinnerDoubleScoreLength.setMaxValue(20);
    }
    public void setResources(ResourceBundle paroliereStrings) {
        this.paroliereStrings = paroliereStrings;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

//    public void setMediator(IMediator mediator) {
//        this.mediator = mediator;
//    }

    public void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/cspnet/parolierefx/viewcontroller/optionsDialog.fxml"));
        fxmlLoader.setResources(paroliereStrings);
        fxmlLoader.setController(this);
        try {
            Scene scene = new Scene((Parent) fxmlLoader.load());
//  2014/11/09          
//            scene.getStylesheets().add("/styles/numberskinner.css");
//  2014/11/09             
            setScene(scene);
        } catch (IOException e) {
            Messenger.showException(e, "Errore nel caricamento di Options Dialog.");
        }
    }

    private void spinnerMinWordLengthStateChanged() {
        /* Resetta il valore minimo di  spinnerDoubleScore
         * Se il valore di spinnerDoubleScore è incongruente con il nuovo minimo
         * lo aggiorna.
         */

        spinnerDoubleScoreLength.setMinValue(spinnerMinWordLength.getValue());
        int otherValue = (int) spinnerDoubleScoreLength.getValue();
        int newValue = (int) spinnerMinWordLength.getValue();
        if (otherValue < newValue) {
            spinnerDoubleScoreLength.setValue(newValue);
        }

    }

    @Override
    public void run() {
        showAndWait();
    }
    
}
