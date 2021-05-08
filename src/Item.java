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

    Item (String itemName, float itemCost){
        this.name   =   itemName;
        this.cost   =   itemCost;
    }

    /*
     *  @param  float updateItemCost
     *
     *  @return void
     */
    public void updateCost(float updatedItemCost){
        this.cost   =   updatedItemCost;
    }

    public void updateName(String updateItemName){
        this.name   =   updateItemName;
    }

    public float getCost(){
        return this.cost;
    }

    public String getName(){
        return this.name;
    }
}
