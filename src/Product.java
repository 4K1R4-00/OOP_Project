class Product extends Item
{
    /*
     *  @param      String  productName
     *  @param      double  productCost
     *
     *  @brief
     *  Constructor
     *
     *  @return     none
     */
    public Product(String productName, double productCost)
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
