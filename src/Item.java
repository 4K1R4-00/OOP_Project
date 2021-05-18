/*
 *  The item class contains the basic item structure for the inheritence base.
 *
 *  The Item class also form the basis of the product and service class.
 *
 */

class Item
{
    //  Item name
    private String name;

    //  Item cost
    private double cost;

    //  Item quantity
    private int quantity;

    //  Default constructor
    Item () {}

    Item(String itemName, double itemCost)
    {
        this.name       =   itemName;
        this.cost       =   itemCost;
    }

    Item (String itemName, double itemCost, int quantity)
    {
        this.name       =   itemName;
        this.cost       =   itemCost;
        this.quantity   =   quantity;
    }


    /*
     *  @param  float updateItemCost
     *
     *  @return void
     */
    public void updateCost(double updatedItemCost)
    {
        this.cost   =   updatedItemCost;
    }

    public void updateName(String updateItemName)
    {
        this.name   =   updateItemName;
    }

    public void updateQuantity(int quantity)
    {
        this.quantity   =   quantity;
    }

    public double getCost()
    {
        return this.cost;
    }

    public String getName()
    {
        return this.name;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

}
