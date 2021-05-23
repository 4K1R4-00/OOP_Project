class Product extends Item
{
    //  Item type of product
    private int type    =   1;

    Product(String productName, double productCost)
    {
        super(productName, productCost);
    }

    Product(String productName,
            int productQuantity,
            double productCost
            )
    {
        super(productName, productCost, productQuantity);
    }

    public int getType()
    {
        return this.type;
    }
}
