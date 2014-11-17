/* 
 * Copyright (C) 2014 Daniele Comand
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.cspnet.testoptionsdialog.controls;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
//import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe statica di utilità per generare semplicemente dialoghi di messaggio
 * swing. <BR>
 * I metodi dela classe sono statici, ma la classe ha bisogno di un parent
 * (finestra di riferimento) e di un nome per caricare il resourceBundle.
 *
 * @author Daniele Comand
 */
public class Messenger {

    static Parent parent;
    private static java.util.ResourceBundle resourceBundle;

    /**
     * Carica la ResouceBundle (stringhe internazionalizzate).
     *
     * @param bundle
     */
    public static void setResourceBundle(ResourceBundle bundle) {
        Messenger.resourceBundle = bundle;
    }

    Messenger() {
    }

    /**
     * Imposta la Scene su cui visualizzare i messaggi
     *
     * @param parent finestra sulla quale visualizzare i messaggi
     */
    public static void setParent(Parent parent) {
        Messenger.parent = parent;
    }

    /**
     * Visualizza un box con messaggi di errore dell'appplicazione
     *
     * @param msg messaggio di errore
     */
    public static void showApplicationError(String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(resourceBundle.getString("ERROR_HAPPENED_e"));
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     * Emette un messaggio di warning
     *
     * @param msg messaggio
     */
    public static void showWarning(String msg) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(resourceBundle.getString("WARNING_w"));
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     * Emette un messaggio informativo
     *
     * @param msg messaggio
     */
    public static void showMessage(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(resourceBundle.getString("INFO_msg"));
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     * Mostra una eccezione intercettata come box di errore
     *
     * @param e eccezione da mostrare
     * @param msg messaggio di errore
     */
    public static void showException(Exception e, String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(resourceBundle.getString("INTERNAL_ERROR_e"));
        alert.setHeaderText(null);
        alert.setContentText(msg);

// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label(resourceBundle.getString("EXCEPTION_STACK_msg"));

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }


    /**
     * mostra una finestra di dialogo per messaggi che richiedono la conferma
     * utente
     *
     * @param msg messaggio
     * @return scelta utente (YES=true/NO=false)
     */
    public static boolean getChoice(String msg) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString("CHOICHE_DIALOG_s"));
        alert.setHeaderText(null);
        alert.setContentText(msg);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mostra una richiesta di input all'utente e ne restitusce il valore
     *
     * @param msg messaggio per l'utente
     * @param title titolo del dialogo
     * @return immissione dell'utente
     */
    public static String getParameter(String msg, String title) {
//        return JOptionPane.showInputDialog(parent, msg, title, 1);
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("title");
        dialog.setHeaderText(null);
        dialog.setContentText(msg);

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return "";
        }
    }

    /**
     * Mostra una lista di selezione in cui l'utente deve fare una scelta
     *
     * @param msg messaggio per l'utente
     * @param title titolo del dialogo
     * @param selectionValues valori da immettere nella combo
     * @param initialSelectionValue valore iniziale da selezionare
     * @return stringa selezionata nella combo
     */
    public static String getUserSelection(String msg, String title, String[] selectionValues, String initialSelectionValue) {
        List<String> choices = Arrays.asList(selectionValues);;
        ChoiceDialog<String> dialog = new ChoiceDialog<>(initialSelectionValue, choices);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(msg);

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }
}
