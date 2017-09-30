package sample;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

/**
 * Displays information onto the screen for the user
 *
 * Created by RichtXO on 6/27/16.
 */

public class Main extends Application
{
    private Stage window;           //Replaces primaryStage with window for conveniences

    static DBAccess database;

    public static void main(String[] args) {launch(args);}


    /**
     * Shows to login window of the Inventory System
     * @param primaryStage    the name of stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {

        window = primaryStage;                                                      //Easy use
        Parent loginScene = FXMLLoader.load(getClass().getResource("Login.fxml"));

        window.setTitle("H E R R I C K S   D E C A   I N V E N T O R Y   S Y S T E M");
        window.setScene(new Scene(loginScene, 700, 400));

        //responsible for closing the app SAFELY
        window.setOnCloseRequest(e ->
        {
            e.consume();
            closeProgram();
        });

        window.show();
    }
    // *****************************************************************************************************************

    /**
     * Closes the window
     */
    private void closeProgram()
    {
        Boolean confirm = ConfirmBox.display("Exit Confirmation", "Do you really want to exit?");
        if (confirm)
        {
            //To close session if opened
            if (database.ifConnection())
            {
                database.closeDBAccess();
            }

            window.close();
        }
    }

    //***************************************************************************************************************
}
