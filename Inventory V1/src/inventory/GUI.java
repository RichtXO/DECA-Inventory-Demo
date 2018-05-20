package inventory;

/**
 * Brings the window for user's inputted values into the inventory system
 *
 * Change this GUI USING THE SCENE BUILDER
 *
 * Created by RichtXO on 5/31/16.
 */

import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.util.*;
//import java.io.*;


//Add button about see profit per month


public class GUI extends Application
{
    //TAKE OUT IF YOU WANT TO SAVE DATA EVEN WHEN THE APP CLOSES
    list_Of_Boxes inventory = new list_Of_Boxes();

    Stage window;                   //To change name from primaryStage to window for convenience

    TableView<Box> table;           //A table to modify box's info in the inventory

    ListView<String> deleteList;    //A list to display in the menu of delete

    ListView<String> modifyList;    //A list to display in the menu of modify


    public final double TAX = 1.0875;   //Percent of Tax


    Label editInstruct;         //Instructions of what to edit...

    //***********************************************************************************

    /*
        Setting up 4 buttons to be used for the GUI
     */
    Button add;         //To add boxes into the arrayList
    Button modify;      //To change some fields in a box object.............might not need it
    Button delete;      //To remove a box from the arrayList
    Button view;        //To see a selected box OR the entire arrayList

    //Need 4 different Home buttons for each button on the Main Menu
    Button home1;        //To return to the Main Menu
    Button home2;
    Button home3;
    Button home4;


    //Buttons in different scenes
    Button save;        //To save changes if necessary
    Button remove;      //To delete an object in the inventory
    Button select;      //To select an object to modify
    Button update;      //To update new info
    Button back;        //To go back to the list of the modify


    //***********************************************************************************

    /*
        Setting  scenes
     */
    Scene adding;       //To see to add box
    Scene modifing;     //To see to change box's info
    Scene deleting;     //To see to delete box
    Scene viewing;      //To see to view box's info
    Scene mainPage;     //To see the main page
    Scene editing;      //To edit box's info

    //***********************************************************************************

    /*
        Textfields for user to enter in values
     */
    TextField name;         //Name of product
    TextField price;        //Price of box
    TextField priceT;       //Price of box with tax
    TextField amount;       //Amount of units per box
    TextField amountBox;    //Amount of boxes
    TextField sellPrice;       //How much seller is charging for an unit

    //***********************************************************************************
    public static void main(String[] args)
    {
        /*      Wont have enough time

        //  Creating the file and checking it if it exists

        final Formatter format;

        File start = new File("C:\\inventory_system\\inventory.txt");                   //Change the directory if the computer isn't running Windows OS
        if (start.exists())
        {
            launch(args);
        }
        //If the file is not created
        else
        {
            format = new Formatter("inventory.txt");
        }

        */



        launch(args);                                                                   //To launch the GUI
    }
    //***********************************************************************************

    /**
     * Sets the "Main Menu" window
     * @param primaryStage main stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;


        //************* Add button ***************

        add = new Button();                         //Button to add boxes into the arrayList
        add.setText("Add Box To Current Inventory");
        add.setOnAction(e ->                        //Brings up another window to add
        {
            adding = new Scene(getAddLayout(), 900, 750);
            window.setScene(adding);
        });

        ////************* Modify button ***************

        // ADD CHART THAT IS FLEXIBLE FOR CURRENT MONTH AND ONLY SHOWS CURRENT MONTH, BUT WHEN CLICKED SHOWS THE ENTIRE
        // GRAPH FOR ALL OF THE MONTHS, ARCHIVES IT YEARLY AND STORES IN AN ACCESSIBLE FILE (DIFFERENT TAB)
        //   /\
        //  /  \
        //  |  |
        //  |__|

        //  For future changes...when we have enough time

        modify = new Button("Change fields of a box in Inventory");
        modify.setOnAction(e ->
        {
            modifing = new Scene(getModifyLayout(), 900, 750);
            window.setScene(modifing);
        });

        ////************* Delete button ***************
        delete = new Button("Remove a selected Box from Inventory");
        delete.setOnAction(e ->
        {
            deleting = new Scene(getDeleteLayout(), 900, 750);
            window.setScene(deleting);
        });

        //************* View button ***************
        view = new Button("See Inventory Storage");
        view.setOnAction(e ->
        {
            viewing = new Scene(getViewLayout(), 900, 750);
            window.setScene(viewing);
        });

        //************* Home buttons ***************
        home1 = new Button("Return the Main Menu");
        home1.setOnAction(e ->
        {
            window.setScene(mainPage);
        });

        home2 = new Button("Return the Main Menu");
        home2.setOnAction(e ->
        {
            window.setScene(mainPage);
        });

        home3 = new Button("Return the Main Menu");
        home3.setOnAction(e ->
        {
            window.setScene(mainPage);
        });

        home4 = new Button("Return the Main Menu");
        home4.setOnAction(e ->
        {
            window.setScene(mainPage);
        });

        //************* Save button ***************
        save = new Button("Save");
        save.setOnAction(e ->
        {
            try
            {
                String nameText = name.getText();
                double priceText = Double.parseDouble(price.getText());
                int amountText = Integer.parseInt(amount.getText());
                int amountBoxText = Integer.parseInt(amountBox.getText());
                double sellPriceText = Double.parseDouble(sellPrice.getText());

                save(nameText, priceText, amountText, amountBoxText, sellPriceText);        //Calling the method to save it into the array
                alertBox.display("Saved into Inventory", "You have successfully added the box into the inventory.");
                GridPane adding = getAddLayout();
                Scene x = new Scene(adding, 900, 750);
                window.setScene(x);

            }catch(NumberFormatException e1)
            {
                ErrorBox.display("Error Code 313", "Check your inputs to see that you didn't make any mistakes.");
            }
        });

        //************* Select button ***************
        select = new Button("Select");
        select.setOnAction(e ->
        {
            ObservableList<String> boxes = modifyList.getSelectionModel().getSelectedItems();           //assuming user changes one box at a time

            int pos = 0;
            for (int i = 0; i < inventory.size(); i++)
            {
                if (inventory.getBox(i).getName().equals(boxes.get(0)))
                {
                    pos = i;
                    break;                                                                              //To speed up computing time
                }
            }

            editing = new Scene(getEditLayout(pos), 900, 750);
            window.setScene(editing);

        });

        //************* Back button ***************
        back = new Button("Back");
        back.setOnAction(e ->
        {
            editing = new Scene(getModifyLayout(), 900, 750);
            window.setScene(editing);
        });

        //************* Update button ***************
        update = new Button("Update");
        update.setOnAction(e ->
        {
            try
            {
                String nameText = name.getText();
                double priceText = Double.parseDouble(price.getText());
                double priceTText = Double.parseDouble(priceT.getText());
                int amountText = Integer.parseInt(amount.getText());
                int amountBoxText = Integer.parseInt(amountBox.getText());
                double sellPriceText = Double.parseDouble(sellPrice.getText());

                boolean found = change(nameText, priceText, priceTText, amountText, amountBoxText, sellPriceText);

                if (found)
                {
                    alertBox.display("Saved into Inventory", "You have successfully change the box's info into the inventory.");
                    Scene x = new Scene(getModifyLayout(), 900, 750);
                    window.setScene(x);
                }
                else
                {
                    alertBox.display("Error 404", "This system can't find the original copy of this item. Make sure the name is same as the old one.");
                }

            }catch(NumberFormatException e1)
            {
                ErrorBox.display("Error Code 313", "Check your inputs to see that you didn't make any mistakes.");
            }
        });


        //************* Remove button ***************
        remove = new Button("Remove");
        remove.setOnAction(e ->
        {
            ObservableList<String> products = deleteList.getSelectionModel().getSelectedItems();

                for (String name : products)
                {
                    for (int i = 0; i < inventory.size(); i++)
                    {
                        if (inventory.getBox(i).getName().equals(name))
                        {
                            inventory.removeBox(i);
                        }
                    }
                }
            alertBox.display("Removed from Inventory", "You have successfully removed the chosen boxes from the inventory.");
            deleting = new Scene(getDeleteLayout(), 900, 750);
            window.setScene(deleting);
        });


        //***********************************************************************************

        //********************************************    MAIN  LAYOUT    *****************************************************

        //Layout of the MAIN page window
        Label mainTitle = new Label("Welcome to the Herricks DECA Inventory System!");
        VBox mainLayout = new VBox(20);                                                     //Vertically aligns buttons and texts
        mainLayout.getChildren().addAll(mainTitle, add, modify, delete, view);
        mainLayout.setAlignment(Pos.CENTER);                                                //Sets the layout into position (center in this case)


        //***********************************************************************************************


        // ************************    SCENES    ****************************

        mainPage = new Scene(mainLayout, 900, 750);  //Display the window with the settled layout

        adding = new Scene(getAddLayout(), 900, 750);

        modifing = new Scene(getModifyLayout(), 900, 750);

        deleting = new Scene(getDeleteLayout(), 900, 750);

        viewing = new Scene(getViewLayout(), 900, 750);


        // ***********************************************************************************

        //*******************   WINDOW WORKSHOP   **********************
        window.setScene(mainPage);

        window.setTitle("H E R R I C K S   D E C A   I N V E N T O R Y   S Y S T E M");

        //responsible of closing the app SAFELY
        window.setOnCloseRequest(e ->
        {
            e.consume();
            closeProgram();
        });

        window.show();
    }


    // ****************************************************************************************************************

    /**
     * Closes the window of the app and saves data in the inventory with the "X" button on the corner
     */
    private void closeProgram()
    {

        //Code to save the program before closing the program

        Boolean confirm = ConfirmBox.display("Exit Confirmation", "Do you really want to exit?");
        if (confirm)
            window.close();
    }


    //********************************************       LAYOUTS METHODS      *****************************************************

    /**
     * The adding layout to add the box's info into the inventory
     * @return Gridpane of adding Layout
     */
    private GridPane getAddLayout()
    {
        //**********    Adding Layout   *******************

        Label addInstruct = new Label("Enter the following fields of the item you want to put into the inventory:");
        GridPane addLayout = new GridPane();
        addLayout.setPadding(new Insets(10, 10, 10, 10));
        addLayout.setVgap(8);
        addLayout.setHgap(10);
        GridPane.setConstraints(addInstruct, 0, 0);

        //Labels
        Label nameLabel = new Label("Name of Product:");
        GridPane.setConstraints(nameLabel, 0, 1);

        Label priceLabel = new Label("Price of box:");
        GridPane.setConstraints(priceLabel, 0, 2);


        Label amountLabel = new Label("How many in a box:");
        GridPane.setConstraints(amountLabel, 0, 3);

        Label amountBoxLabel = new Label("How many boxes of product:");
        GridPane.setConstraints(amountBoxLabel, 0, 4);

        Label sellPriceLabel = new Label("How much you charging per unit (not boxes):");
        GridPane.setConstraints(sellPriceLabel, 0, 5);


        //Labels User Input
        name = new TextField();
        name.setPromptText("Name");
        GridPane.setConstraints(name, 1, 1);

        price = new TextField();
        price.setPromptText("Price");
        GridPane.setConstraints(price, 1, 2);

        amount = new TextField();
        amount.setPromptText("Amount of unit per box");
        GridPane.setConstraints(amount, 1, 3);

        amountBox = new TextField();
        amountBox.setPromptText("Amount of boxes");
        GridPane.setConstraints(amountBox, 1, 4);

        sellPrice = new TextField();
        sellPrice.setPromptText("Charge per unit");
        GridPane.setConstraints(sellPrice, 1, 5);

        //Save Button
        GridPane.setConstraints(save, 4, 4);
        //Home button
        GridPane.setConstraints(home1, 5, 4);

        addLayout.getChildren().addAll(nameLabel, name, priceLabel, price, amountLabel, amount, amountBoxLabel, amountBox, sellPriceLabel, sellPrice, save, home1);

        return addLayout;
    }


    /**
     * To get the modify Layout of the BUI
     * @return a VBox of modifyLayout
     */
    private VBox getModifyLayout()
    {
        ////********************   Layout Modifying page   **************************
        Label modifyInstruct = new Label("What do you want to change?");

        modifyList = getListOfInventory();

        VBox modifyLayout = new VBox(20);
        modifyLayout.setPadding(new Insets(20, 20, 20, 20));
        modifyLayout.getChildren().addAll(modifyInstruct, modifyList, select, home3);

        return modifyLayout;
    }


    private GridPane getEditLayout(int pos)
    {
        editInstruct = new Label("What do you need to change about this box?");

        Box x = inventory.getBox(pos);

        GridPane editLayout = new GridPane();
        editLayout.setPadding(new Insets(20, 20, 20, 20));
        editLayout.setVgap(8);
        editLayout.setHgap(10);
        GridPane.setConstraints(editInstruct, 0, 0);

        //Labels
        Label nameLabel = new Label("Name of Product:");
        GridPane.setConstraints(nameLabel, 0, 1);

        Label priceLabel = new Label("Price of box:");
        GridPane.setConstraints(priceLabel, 0, 2);

        Label priceTLabel = new Label("Price with tax:");
        GridPane.setConstraints(priceTLabel, 0, 3);

        Label amountLabel = new Label("How many in a box:");
        GridPane.setConstraints(amountLabel, 0, 4);

        Label amountBoxLabel = new Label("How many boxes of product:");
        GridPane.setConstraints(amountBoxLabel, 0, 5);

        Label sellPriceLabel = new Label("How much you charging per unit (not boxes):");
        GridPane.setConstraints(sellPriceLabel, 0, 6);


        //Labels User Input
        name = new TextField(x.getName());
        GridPane.setConstraints(name, 1, 1);

        price = new TextField("" + x.getPriceBox());
        GridPane.setConstraints(price, 1, 2);

        priceT = new TextField("" + x.getPriceBoxWTax());
        GridPane.setConstraints(priceT, 1, 3);

        amount = new TextField("" + x.getAmtInBox());
        GridPane.setConstraints(amount, 1, 4);

        amountBox = new TextField("" + x.getAmount());
        GridPane.setConstraints(amountBox, 1, 5);

        sellPrice = new TextField("" + x.getCharge());
        GridPane.setConstraints(sellPrice, 1, 6);

        //Save Button
        GridPane.setConstraints(update, 4, 5);
        //Home button
        GridPane.setConstraints(home1, 5, 5);

        editLayout.getChildren().addAll(nameLabel, name, priceLabel, price, priceTLabel, priceT, amountLabel, amount, amountBoxLabel, amountBox, sellPriceLabel, sellPrice, update, home1);

        return editLayout;


    }


    /**
     * To get the layout of the delete section of the GUI
     * @return a gridpane of DeleteLayout
     */
    private GridPane getDeleteLayout()
    {
        //********************   Layout deleting page   **************************
        Label deleteInstruct = new Label("What do you wish to remove from the inventory?");

        //ListView for user to check what boxes to delete off the inventory
        deleteList = getListOfInventory();

        //GridPane
        GridPane deleteLayout = new GridPane();
        deleteLayout.setPadding(new Insets(10, 10, 10, 10));
        deleteLayout.setVgap(8);
        deleteLayout.setHgap(10);
        GridPane.setConstraints(deleteInstruct, 0, 0);
        GridPane.setConstraints(deleteList, 0, 1);
        GridPane.setConstraints(home2, 2, 1);
        GridPane.setConstraints(remove, 0, 2);



        deleteLayout.getChildren().addAll(deleteInstruct, deleteList, remove, home2);
        return deleteLayout;
    }


    private VBox getViewLayout()
    {
        ////********************   Layout Viewing page   **************************
        Label viewingInstruct = new Label("Here's the entire list of items in the inventory.");

        //Creating the table columns
        //Name column
        TableColumn<Box, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Box, String>("name"));

        //Price of Box column
        TableColumn<Box, Double> priceBoxColumn = new TableColumn<>("Price of Box");
        priceBoxColumn.setMinWidth(150);
        priceBoxColumn.setCellValueFactory(new PropertyValueFactory<Box, Double>("priceBox"));

        //Price of Box with Tax column
        TableColumn<Box, Double> priceBoxTaxColumn = new TableColumn<>("Price of Box with Tax");
        priceBoxTaxColumn.setMinWidth(150);
        priceBoxTaxColumn.setCellValueFactory(new PropertyValueFactory<Box, Double>("priceBoxWTax"));

        //Amount in Box column
        TableColumn<Box, Integer> amtInBoxColumn = new TableColumn<>("Amount per Box");
        amtInBoxColumn.setMinWidth(150);
        amtInBoxColumn.setCellValueFactory(new PropertyValueFactory<Box, Integer>("amtInBox"));

        //Amount of Boxes column
        TableColumn<Box, Integer> amountColumn = new TableColumn<>("Amount of Boxes");
        amountColumn.setMinWidth(150);
        amountColumn.setCellValueFactory(new PropertyValueFactory<Box, Integer>("amount"));

        //Price of Unit column
        TableColumn<Box, Double> priceUnitColumn = new TableColumn<>("Price of Unit");
        priceUnitColumn.setMinWidth(150);
        priceUnitColumn.setCellValueFactory(new PropertyValueFactory<Box, Double>("price"));

        //Selling price per Unit column
        TableColumn<Box, Double> sellPriceColumn = new TableColumn<>(("Selling Price per Unit"));
        sellPriceColumn.setMinWidth(150);
        sellPriceColumn.setCellValueFactory(new PropertyValueFactory<Box, Double>("charge"));

        //Profit Column
        TableColumn<Box, Double> profitColumn = new TableColumn<>("Profit");
        profitColumn.setMinWidth(150);
        profitColumn.setCellValueFactory(new PropertyValueFactory<Box, Double>("profit"));

        table = new TableView<>();
        table.setItems(getBox());
        table.getColumns().addAll(nameColumn, priceBoxColumn, priceBoxTaxColumn, amountColumn, amtInBoxColumn,  priceUnitColumn, sellPriceColumn, profitColumn);

        VBox viewLayout = new VBox(20);
        viewLayout.setPadding(new Insets(10, 10, 10, 10));
        viewLayout.getChildren().addAll(viewingInstruct, table, home3);

        return viewLayout;
    }


    //***************************************************************************************************************************************************

    /**
     * To simplify the coding of making the box object
     * @param name      name of product
     * @param price     price of box
     * @param amount    amount of unit per box
     * @param amountBox amount of boxes
     * @param sellPrice    how much seller is selling for
     * @return the box object
     */
    private Box makeBox(String name, double price, int amount, int amountBox, double sellPrice)
    {
        double priceT = price * TAX;
        priceT = Math.round(priceT *100.0)/100.0;


        double priceUnit = priceT/amount;
        priceUnit = Math.round(priceUnit * 100.0)/100.0;


        double profit = sellPrice - priceUnit;
        profit = Math.round(profit * 100.0)/100.0;

        double charge =  Math.round(sellPrice * 100.0)/100.0;

        Box box = new Box(name, price, priceT, amount, amountBox, priceUnit, charge, profit);
        return box;
    }


    /**
     * Adding the box into the inventory
     * @param name      name of product
     * @param price     price of box
     * @param amount    amount of unit per box
     * @param amountBox amount of boxes
     * @param sellPrice    how much seller is selling for
     */
    private void save(String name, double price, int amount, int amountBox, double sellPrice)
    {
        Box box = makeBox(name, price, amount, amountBox, sellPrice);
        inventory.addBox(box);
        inventory.FindDuplicates();
    }


    /**
     * Modifies the box object
     * @param name          name of product
     * @param price         price of box
     * @param priceT        price of box with tax
     * @param amount        amount of unit per box
     * @param amountBox     amount of boxes
     * @param sellPrice     how much seller is selling for
     * @return true if found (Shouldn't display anything and changes the amount)
     *      false if not found (Should display "Doesn't exist in Inventory")
     */
    private boolean change(String name, double price, double priceT, int amount, int amountBox, double sellPrice)
    {
        Box box = makeBox(name, price, amount, amountBox, sellPrice);

        boolean found = false;

        //To check through the inventory is there's another object to overwrite
        ArrayList<Box> system = inventory.getAll();
        for (Box element : system) {
            if (element.getName().equals(box.getName())) {
                element.changeAmtInBox(amount);
                element.changeAmount(amountBox);
                element.changePriceBox(price);
                element.changePriceTax(priceT);
                found = true;
            }
        }

        return found;
    }


    /**
     * Get a listView of the inventory to edit/remove
     * @return a listView list
     */
    private ListView<String> getListOfInventory()
    {
        ListView<String> x = new ListView<String>();

        //to get all names of each boxes in the inventory to the list
        ArrayList<String> names = new ArrayList<String>();
        for (Box element : inventory.getAll())
        {
            names.add(element.getName());
        }

        //Put string names into the listView
        for (String element : names)
        {
            x.getItems().add(element);
        }

        x.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        return x;
    }

    /**
     * makes the list for table
     * @return the list to display at the View scene
     */
    public ObservableList<Box> getBox()
    {
        ObservableList<Box> boxes = FXCollections.observableArrayList();

        for (Box element : inventory.getAll())
        {
            boxes.add(element);
        }

        return boxes;
    }


    /**
     * To Save the inventory into a separate file
     */
    //public void saveFile(File )
    {

    }
}
