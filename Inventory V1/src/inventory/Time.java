package inventory;

/**
 * Keeps track of the time of selected product
 *
 * Created by RichtXO on 5/9/16.
 */
import java.util.Date;

public class Time
{
    private Date begin;

    //Constructor
    public Time()
    {
        begin = new Date();
    }

    //**********************************************************
    //Accessor method

    //Return begin time as a String
    public String getBeginning()
    {
        return begin.toString();
    }

    //Modifier method
    public void changeTime(Date x) { begin = x; }

    //**********************************************************

}
