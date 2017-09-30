package sample;

/**
 * Connects to the desired database in MySQL
 *
 * DATABASE THAT'S CURRENTLY CODED IS FOR TESTING PURPOSES ONLY
 * CHANGE ONCE IT'S FINALIZED AND OTHER COMMANDS IN ALL METHODS
 *
 * Created by RichtXO on 6/27/16.
 */

import java.sql.*;
import java.util.ArrayList;

public class DBAccess
{
    private Connection conn;                //connects to MySQL Database

    private boolean connSuccessful;         //to see if connection to the Database is successful



    private ResultSet resultInfo;           //a table in the MySQL Database
    private String username;                //username to access database
    private String password;                //password with username to access database


    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";                    //URL to load the driver to access MySQL Database
    private static final String PATH_WAY = "jdbc:mysql://localhost:3306/Inventory";        //Path way to database...


    private String query;
    private Statement stmt;


    //******************************************************************************************************************

    /**
     * Constructor establish connection to the MySQL database
     * @param user  user inputted the username
     * @param pass  user inputted the password for the corresponding username
     *
     * @return true if username and password are correct
     *          false if username and password aren't correct
     */
    public DBAccess(String user, String pass)
    {

        try{
            connSuccessful = false;

            Class.forName(JDBC_DRIVER);


            username = user;
            password = pass;


            conn = DriverManager.getConnection(PATH_WAY, username, password);       //change the username and password that the user would like to input instead
                                                                                    //instead of -u root -p root


            connSuccessful = true;

            stmt = conn.createStatement();

        }catch(Exception e)
        {
            closeDBAccess();

            //Testing purposes
            System.out.println("Error from DBAccess(): ");
            e.printStackTrace();

        }
    }


    //******************************            CONNECTION SUCCESSFUL             **************************************

    /**
     * Checks if connection was successful for the login page
     * @return  true if there is no error
     *          false if there is an/more error(s)
     */
    public boolean ifConnection()
    {
        return connSuccessful;
    }

    //*********************************            RETURN RECORDS               ****************************************

    /**
     * Returns resultset of all the data in the Database
     *      if there's an error of retrieving the data, null will be returned.
     */
    public ResultSet getData()
    {
        try
        {
            query = "SELECT * FROM inventory;";

            resultInfo = stmt.executeQuery(query);

            return resultInfo;

            /*/Testing purposes
            System.out.println("Data from MySQL DB:");

            while(resultInfo.next())
            {
                int id = resultInfo.getInt("id");
                String first = resultInfo.getString("first");
                String last = resultInfo.getString("last");
                String dob = resultInfo.getString("DOB");

                System.out.println("id : " + id);
                System.out.println("first : " + first);
                System.out.println("last : " + last);
                System.out.println("DOB : " + dob);
                System.out.println();
            }*/


        }catch (Exception e)
        {
            /*
            System.out.println("Error from getData(): ");           //Testing purpose
            e.printStackTrace();
            */
            return null;
        }
    }

    //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

    /**
     *
     * NOT NEEDED
     * NOT NEEDED
     * NOT NEEDED
     * NOT NEEDED
     *
     * To get data with desired conditions
     *
     * ALOT OF WORK TO DO TO GET DESIRED CONDITIONS
     *
    public ResultSet getDesiredData(String field, String conditions)
    {
        try {

            query = "SELECT ";          //fix this

            resultInfo = stmt.executeQuery(query);

            return resultInfo;


        } catch(Exception e)
        {
            /*
            Testing purposes
            System.out.println("Error from getting desired data:");
            e.printStackTrace();
            *
            return null;
        }
    }
    */

    //*********************************                 ADD RECORDS             ****************************************

    /**
     * To add objects into the tables of the database with available fields
     *
     * @param name          name of the product
     * @param price         how much the user pay for the product box
     * @param amount        amount of items of the product
     * @param amountBox     how many boxes of the product
     * @param charge        the selling price that user inputted
     */
    public void insertData(String name, double price, int amount, int amountBox, double charge)
    {
        try
        {

            query = "Insert into inventory " +
                    "VALUES ('" + name + "', " + price + ", " + amount + ", " + amountBox + ", " + charge + ");";

            stmt.executeUpdate(query);      //executes the query statement


        } catch (SQLException e)
        {
            /*
            //Testing purposes
            System.out.println("Insertion failed in Table1.");
            e.printStackTrace();
            */


        }
    }

    //***************************************       REMOVE RECORDS          ********************************************

    /**
     * To remove desired record from the database
     *
     * @param name    the name of the product
     */
    public void removeData(String name)
    {
        //name.toLowerCase();                                     //Lowercase the name for syntax

        try
        {
            query = "DELETE FROM inventory WHERE name=" + name + ";";

            stmt.executeUpdate(query);

        } catch (Exception e)
        {
            /*
            Testing purposes
            System.out.println("Insertion failed in Table2.");
            e.printStackTrace();
             */
        }
    }

    //*********************************         CLOSE DATABASE CONNECTIONS          ************************************

    /**
     * Closes all connection to the database
     */
    public void closeDBAccess()
    {
        try{

            conn.close();
            resultInfo.close();
            stmt.close();
            connSuccessful = false;

        }catch (Exception e)
        {
            /*
            Testing purposes
            System.out.println("closeDBAccess failed");
            e.printStackTrace();
            */
        }
    }

    //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
    /**
     * To edit desired object's info in the database
     *
     * @param field   the desired field the user wants to edit
     * @param info    the new updated info to replace the old into in the desired field
     * @param name    the name of the item
     */
    public void modifyData(String field, String info, String name)
    {
        try
        {
            if (field.toLowerCase().equals("first") || field.toLowerCase().equals("last"))
            {
                query = "UPDATE inventory SET " + field + "=" + info + "WHERE name=" + name + ";";
            }

            stmt.executeUpdate(query);

            //System.out.println("Update completed");             //Testing purposes

        }catch (Exception e)
        {
            /*
            Testing purposes
            System.out.println("Updated failed: ");
            e.printStackTrace();
            */
        }
    }


    //******************************************************************************************************************


    /**
     * Would alert the seller which products are running out
     *
     * not sure if it would work...
     */
    public ArrayList<String> runningOut()
    {
        ArrayList<String> list = new ArrayList<String>();

        ResultSet result = getData();

        try
        {
            while(result.next())
            {
                if (result.getInt("amountBox") <= 2)            //checks if the item has 2 or less boxes available
                {

                    list.add(result.getString(0));

                }
            }
        }catch (Exception e)
        {
            /*
            Testing purposes
            System.out.println("Error from runningOut(): ");
            e.printStackTrace();
            */
        }
        return list;
    }

    //******************************************************************************************************************


}
