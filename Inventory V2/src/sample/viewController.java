package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.*;

/**
 * Controls the view.fxml
 *
 * VARIABLES NEEDED TO BE ADDED Expected Profits, Expected Profits w/ Tax, Price of box w/ tax
 *
 * Created by RichtXO on 7/7/16.
 */
public class viewController implements Initializable
{

    @FXML
    private Button homeButton;
    @FXML
    private Button update;
    @FXML
    private Button remove;


    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Double> priceColumn;
    @FXML
    private TableColumn<Item, Integer> amountColumn;
    @FXML
    private TableColumn<Item, Integer> amountBoxColumn;
    @FXML
    private TableColumn<Item, Double> chargeColumn;
    @FXML
    private TableColumn<Item, Double> expectProfitColumn;
    @FXML
    private TableColumn<Item, Double> priceBoxTaxColumn;


    private ObservableList<Item> items;                          //to hold the list of items for the tableView to display


   @Override
   public void initialize(URL location, ResourceBundle rb)
    {

        items = FXCollections.observableArrayList();

        //System.out.println("------------");

        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'UserMaster.fxml'.";

        getData();


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        amountBoxColumn.setCellValueFactory(new PropertyValueFactory<>("amountBox"));

        chargeColumn.setCellValueFactory(new PropertyValueFactory<>("charge"));

        tableView.refresh();

        tableView.setItems(null);
        tableView.setItems(items);




    }


    /**
     * Gets data from the MySQL Database
     * If resultset returns null, nothing is displayed
     */
    private void getData()
    {


        try
        {
            ResultSet result = Main.database.getData();                           //extracting data from database

            while (result.next())
            {

                Item thing = new Item(result.getString("name"), Double.parseDouble(result.getString("price")), Integer.parseInt(result.getString("amount")),
                        Integer.parseInt(result.getString("amountBox")), Double.parseDouble(result.getString("charge")));

                items.add(thing);

            }



            // Data is in the ObservableList
            //Displaying it in the console
            System.out.println("setting data\n");

            System.out.println("-----   Checking in data ObserverableList   -----\n");
            for (Item x : items)
            {

                System.out.println("name = " + x.getName());
                System.out.println("price = " + x.getPrice());
                System.out.println("amount = " + x.getAmount());
                System.out.println("amountBox = " + x.getAmountBox());
                System.out.println("charge = " + x.getCharge());

                System.out.println("-------------------------------");


            }


            result.close();     //to close the session

        } catch (Exception e)
        {
            /*
            //Testing Purposes
            System.out.println("Error in the getItems() in viewController.java: ");
            e.printStackTrace();
            */
        }


    }


    /**
     * Opens a new window to modify the desired record's fields
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void updateButtonAction(ActionEvent event) throws IOException {
        String selectedItem = tableView.getSelectionModel().toString();                     //to extract the data the user clicked


    }


    /**
     * Deletes the record that the user desires to delete
     *
     * @throws IOException
     */
    @FXML
    private void removeButtonAction() throws IOException
    {

        String selectedItem = tableView.getSelectionModel().toString();                     //to extract the data the user clicked

        Boolean confirm = ConfirmBox.display("Delete this " + selectedItem + " ?",
                "Are you sure you want to delete this from your inventory?");

        if (confirm) {
            Main.database.removeData(selectedItem);
        }

    }


    /**
     * Brings back to the main menu scene
     *
     * @param event         to get info of current page/scene
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