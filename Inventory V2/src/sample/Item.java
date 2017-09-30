package sample;

/**
 * Created by RichtXO on 8/3/16.
 */

import javafx.beans.property.*;


public class Item
{
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty price;
    private final SimpleIntegerProperty amount;
    private final SimpleIntegerProperty amountBox;
    private final SimpleDoubleProperty charge;



    public Item(String nameInfo, double priceInfo, int amountInfo, int amountBoxInfo, double chargeInfo)
    {
        this.name = new SimpleStringProperty(nameInfo);
        this.price = new SimpleDoubleProperty(priceInfo);
        this.amount = new SimpleIntegerProperty(amountInfo);
        this.amountBox = new SimpleIntegerProperty(amountBoxInfo);
        this.charge = new SimpleDoubleProperty(chargeInfo);
    }

    //Accessor Methods
    public String getName()
    {
        return name.get();
    }

    public double getPrice() { return price.get(); }

    public int getAmount() { return amount.get(); }

    public int getAmountBox() { return amountBox.get(); }

    public double getCharge() { return charge.get(); }



    //Property Values
    public SimpleStringProperty nameProperty() { return name; }

    public SimpleDoubleProperty priceProperty() { return price; }

    public SimpleIntegerProperty amountProperty() { return amount; }

    public SimpleIntegerProperty amountBoxProperty() { return amountBox; }

    public SimpleDoubleProperty chargeProperty() { return charge; }



    //Modifier Methods
    public void setName(String nameInfo)
    {
        name.set(nameInfo);
    }

    public void setPrice(double priceInfo) { price.set(priceInfo); }

    public void setAmount(int amountInfo) { amount.set(amountInfo); }

    public void setAmountBox(int amountBoxInfo) { amountBox.set(amountBoxInfo); }

    public void setCharge(double chargeInfo) { charge.set(chargeInfo); }


}
