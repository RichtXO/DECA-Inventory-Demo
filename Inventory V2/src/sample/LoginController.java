package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * Display the Login Screen
 *
 * Created by RichtXO on 7/2/16.
 */
public class LoginController {

    //*****************************************************************************************************************


    @FXML
    private Button loginButton;                                  //variables used for the login.fxml
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label error;


    //*****************************************************************************************************************

    @FXML
    private void loginButtonCLicked(ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;

        try
        {
            Main.database = new DBAccess(username.getText(), password.getText());

            boolean login = Main.database.ifConnection();

            /*
            Checks if the username and password are correct
            If true, it will bring the main Menu
            If not, it will bring an error screen
            */
            if (!login)
            {

                System.out.print("In login");

                username.clear();
                password.clear();
                error.setText("LOGIN FAILURE");

            }
            else
            {
                //Creating the main Menu scene if the user login in correctly

                /* DELETE WHEN FOUND A BETTER SOLUTION!!!!!!!!
                Parent mainMenu_Parent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                Scene mainMenu_Scene = new Scene(mainMenu_Parent);

                Stage mainMenu = (Stage) ((Node) event.getSource()).getScene().getWindow();             //Gets info about the Stage variable window
                                                                                                        //From the Main.java class

                mainMenu.hide();
                mainMenu.setScene(mainMenu_Scene);
                mainMenu.show();
                */





                //get reference to the button's stage
                stage=(Stage) loginButton.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

            }

        }catch (Exception e)
        {
            /*Testing purposes
            System.out.println("Error in the login GUI:");
            e.printStackTrace();
            */
        }
    }


    //*****************************************************************************************************************

}
