class Product extends Item
{
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
}
