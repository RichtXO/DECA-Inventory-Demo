package inventory;

/**
 * Everything about the box
 *
 * Created by RichtXO on 5/17/16.
 */
public class Box
{
    private String name;
    private double priceBox;
    private double priceBoxWTax;                // Price of the box with tax - calculates based on NY state tax
    private int amtInBox;
    private int amount;                         //amount of boxes in the inventory
    private double price;                       //the price the seller pays to get the unit
    private double charge;                   //the price of what the seller want to make - user inputted
    private double profit;                      //How much money the seller earns
    private Time sellingTime;                   //Off for another time

    //Constructor
    public Box(String x, double priceOfBox, double tax, int inBox, int numBox, double paid, double selling, double earned)
    {
        name = x;
        priceBox = priceOfBox;
        priceBoxWTax = tax;
        amtInBox = inBox;
        amount = numBox;
        price = paid;
        charge = selling;
        profit = earned;

        sellingTime = new Time();
    }

    //*****************************************************************

    //Accessor Methods
    public String getName()
    {
        return name;
    }

    public double getPriceBox() { return priceBox; }

    public double getPriceBoxWTax() { return priceBoxWTax; }

    public int getAmtInBox() { return amtInBox; }

    public int getAmount() { return amount; }

    public double getPrice()
    {
        return price;
    }

    public double getCharge()
    {
        return charge;
    }

    public double getProfit() { return profit; }

    public Time getSellingTime() { return sellingTime; }

    //*****************************************************************

    //Modifier Methods
    public void changeName(String x) { name = x; }

    public void changePriceBox(double x) { priceBox = x; }

    public void changePriceTax(double x) { priceBoxWTax = x; }

    public void changeAmtInBox(int x) { amtInBox = x; }

    public void changeAmount(int x) { amount = x; }

    public void changePrice(double x) { price = x; }

    public void changeCharge(double x) { charge = x; }

    public void changeProfit(double x) { profit = x; }

    public void changeTime(Time x) { sellingTime = x; }

    //*****************************************************************

    /**
     * Calculates amount left at end of the month
     * @param num the amount to be sold
     */
    public void soldRemain(int num){ amount -= num; }       //Needs to be done at the end of the month to see for next month


    /**
     * Amount left for the beginning of next month
     * @param num the amount to be restocked
     */
    public void restocking(int num)
    {
        amount += num;
    }       //Use for the beginning of the next month

    /**
     * Checks if the seller is making any profit with his new selling Price
     * @return true if sellPrice is less than price
     *      false if sellPrice is more than price
     */
    public boolean isMakingProfit()
    {
        if (charge > price)
            return true;
        return false;
    }


    /**
     * Checks how much the seller is profiting. But it calls isMakingProfit() first
     * @return the difference of the sellPrice and price
     *      Positive if making profit
     *      Negative if not making profit
     *
     *    **Use the Math.round() method to round to the hundredth place when calling this method**
     */
    public double profit()
    {
        return charge - price;
    }


    /**
     * Finds the difference between the actual profit and the expected profit
     * @param actualProfit  the actual profit the seller made real time
     * @return the difference between the 2 profits
     *
     *      **Use the Math.round() method to round to the hundredth place**
     */
    public double diffProfit(double actualProfit)
    {
        return profit() - actualProfit;
    }



}
