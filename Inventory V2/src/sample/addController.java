package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controls the add.fxml
 *
 * Created by RichtXO on 7/4/16.
 */
public class addController {

    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField amt_Item;
    @FXML
    private TextField amt_Box;
    @FXML
    private TextField charge;
    @FXML
    private Button save;
    @FXML
    private Button homeButton;


    /**
     * Saves all the info from the user inputted and inserts it as a new record into the database
     * When the save button is clicked...
     * @param event
     * @throws IOException
     */
    @FXML
    private void saveButtonAction(ActionEvent event) throws IOException
    {
        //catches mis-inputs from user
        try {
            String NameOfProduct = name.getText();
            Double PriceOfProduct = Double.parseDouble(price.getText());
            int amount = Integer.parseInt(amt_Item.getText());
            int amountBox = Integer.parseInt(amt_Box.getText());
            double chargeOfProduct = Double.parseDouble(charge.getText());


            Main.database.insertData(NameOfProduct, PriceOfProduct, amount, amountBox, chargeOfProduct);         //passing them into the database


            ErrorBox.display("Successful", name.getText() + " was added into the inventory system.");      //opens a different window telling the user the insertion was successful


            //refreshing the add page
            name.clear();
            price.clear();
            amt_Item.clear();
            amt_Box.clear();
            charge.clear();

        }catch (NumberFormatException e)
        {
            ErrorBox.display("Error Code 313", "Check your inputs to see that you didn't make any mistakes.");
        }
    }


    /**
     * Brings back to the main menu scene
     * When the home button is clicked...
     * @param event
     * @throws IOException
     */
    @FXML
    private void homeButtonAction(ActionEvent event) throws IOException
    {
        Parent mainMenu_Parent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenu_Scene = new Scene(mainMenu_Parent);

        Stage mainMenu = (Stage) ((Node) event.getSource()).getScene().getWindow();             //Gets info about the Stage variable window
                                                                                                //From the Main.java class

        // add Animations



        mainMenu.hide();
        mainMenu.setScene(mainMenu_Scene);
        mainMenu.show();
    }

}
