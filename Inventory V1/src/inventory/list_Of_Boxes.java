package inventory;

/**
 * A list of all the boxes in the inventory
 * Created by RichtXO on 5/18/16.
 */
import java.util.ArrayList;
import java.util.Arrays;

public class list_Of_Boxes
{

    private ArrayList<Box> list;

    //Constructor
    public list_Of_Boxes()
    {
        list = new ArrayList<Box>();
    }
    //*****************************************************************

    //Accessor Methods
    /**
     * @param x  the position of the product in the list
     * @return  the desired box
     */

    public Box getBox(int x)
    {
        return list.get(x);
    }

    /**
     * @return  the entire inventory list
     */
    public ArrayList<Box> getAll()
    {
        return list;
    }

    /**
     * @return the size of list
     */
    public int size(){return list.size(); }

    //*****************************************************************

    //Modifier Methods
    public void changeInventory(ArrayList<Box> x) { list = x; }

    public void changeBox(Box x, int pos)
    {
        list.remove(pos);
        list.add(pos, x);
    }

    //*****************************************************************

    //Add more box
    public void addBox(Box x)
    {
        list.add(x);
    }

    //Remove desired box
    public void removeBox(int x) { list.remove(x); }

    //*****************************************************************


    /**                      ---MAY NOT NEED---                ***********
    /**
     * Sorting the array from the smallest amount in stock to the largest
     * By using the selecting sorting array
     * ***Prototype***
     *
     * SORTING TOO LONG...
     *
    public void decreasingSort()
    {
        //Sorting it from least to greatest
        for (int i = 0; i < list.size() - 1; i++)
        {
            int minIndex = i;
            Box min = list.get(i);

            for (int j = i + 1; j < list.size(); j++)
            {
                if (list.get(j).getAmount() < min.getAmount())
                {
                    minIndex = j;
                    min = list.get(j);
                }

                //Swapping
                Box temp = list.get(minIndex);
                list.remove(minIndex);
                list.add(minIndex, list.get(j));
                list.remove(j);
                list.add(j, temp);
            }
        }
    }

    /**
     * Using ArraySort in the Array class
     *      ***Don't know how long it would take...***
     *
    public void Arraysort()
    {
        Box[] x = new Box[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            x[i] = list.get(i);
        }

        Arrays.sort(x);
    }
    */

    //*******************************************************************************************************

    /**
     * Find any duplicated boxes.
     * If do, it would add the amounts from both boxes, removes the 2nd box
     * Assuming the other fields are the same
     */
    public void FindDuplicates()
    {
        for (int i = 0; i < list.size(); i++)
        {
            for (int j = i + 1; j < list.size(); j++)
            {
                //if found the duplicated box
                if (list.get(i).getName().equals(list.get(j).getName()))
                {
                    int oriAmount = list.get(i).getAmount();                    //The amount from the first box
                    int oriAmountInBox = list.get(i).getAmtInBox();
                    int total = oriAmount + list.get(j).getAmount();            //Adding the amounts from the first box and the second box (if found)
                    int totalInBox = oriAmountInBox + list.get(j).getAmtInBox();
                    list.get(i).changeAmount(total);                            //Modifying the first box's amount to the total
                    list.get(i).changeAmtInBox(totalInBox);                     //Modifying the first box's amount in the box to the totalInBox

                    list.remove(j);                                             //removing the 2nd box
                    j--;
                }
            }
        }
    }
}
