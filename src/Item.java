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
    private float cost;

    //  Default constructor
    Item () {}

    Item (String item_name, float item_cost)
    {
        this.name   =   item_name;
        this.cost   =   item_cost;
    }

    /*
     *  @param  float update_item_cost
     *
     *  @return void
     */
    public void updateCost(float updatedItemCost)
    {
        this.cost   =   updatedItemCost;
    }
}
