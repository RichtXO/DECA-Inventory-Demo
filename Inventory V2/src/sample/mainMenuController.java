package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for the mainMenu.fxml
 *
 * Created by RichtXO on 7/2/16.
 */
public class mainMenuController
{
    @FXML
    private Button add;
    @FXML
    private Button view;
    @FXML
    private Button logOut;


    /**
     * Creating the add scene when the user clicked the add button
     * @param event
     * @throws IOException
     */
    @FXML
    private void addButtonAction(ActionEvent event) throws IOException
    {

        Parent add_Parent = FXMLLoader.load(getClass().getResource("add.fxml"));
        Scene add_Scene = new Scene(add_Parent);

        Stage mainScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();             //Gets info about the Stage variable window
                                                                                                  //From the Main.java class

        mainScreen.hide();
        mainScreen.setScene(add_Scene);
        mainScreen.show();

    }


    /**
     * Creating the remove scene when the user clicked the remove button
     * @param event
     * @throws IOException
     */
    @FXML
    private void viewButtonAction(ActionEvent event) throws IOException
    {
        Parent view_Parent = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene view_Scene = new Scene(view_Parent);


        Stage mainScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();             //Gets info about the Stage variable window
                                                                                                  //From the Main.java class

        mainScreen.hide();
        mainScreen.setScene(view_Scene);
        mainScreen.show();


    }


    /**
     * Bringing back to the login scene and closing all connections to the MySQL Database
     * @param event
     * @throws IOException
     */
    @FXML
    private void logOutAction(ActionEvent event) throws IOException
    {
        Parent login_Parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene login_Scene = new Scene(login_Parent);

        Stage mainScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();             //Gets info about the Stage variable window
                                                                                                  //From the Main.java class

        Main.database.closeDBAccess();       //to close all access to the database


        mainScreen.hide();
        mainScreen.setScene(login_Scene);
        mainScreen.show();
    }
}
