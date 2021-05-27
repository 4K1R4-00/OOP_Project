/*
 *  @authors
 *  AKMAL 'AISY BIN RUDY                        52215220045
 *  NUR ARIFA BINTI NOR AZLAN                   52215220050
 *  DANISH IMRAN BIN MOHD ARIF ARCHI            52215220060
 *  MOHD FAIZ BIN RADZI                         52215220049
 *  
 *  @brief
 *  The item class contains the basic item structure for the inheritence base.
 *  The Item class also form the basis of the product and service class.
 */

class Item
{
    //  Item name
    private String name;

    //  Item cost
    private double cost;

    //  Item quantity
    private int quantity    =   1;

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
     *  @brief
     *  set the cost of the item.
     *
     *  @return void
     */
    public void setCost(double updatedItemCost)
    {
        this.cost   =   updatedItemCost;
    }

    /*
     *  @param float updateItemName
     *
     *  @brief
     *  set the item name.
     *
     *  @return void
     */
    public void setName(String updateItemName)
    {
        this.name   =   updateItemName;
    }

    public void setQuantity(int quantity)
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
