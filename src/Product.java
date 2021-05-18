class Product extends Item
{
    char pGenderType;
    String pCategory;
    int pQuantity       =   1;

    Product(String productName, double productCost)
    {
        super(productName, productCost);
    }

    Product(String productName,
            String productCategory,
            double productCost,
            char productGenderType,
            int productQuantity
            )
    {
        super(productName, productCost);
        this.pGenderType    =   productGenderType;
        this.pCategory      =   productCategory;
        this.pQuantity      =   productQuantity;
    }

    public void updateGenderType(char updateItemType)
    {
        this.pGenderType    =   updateItemType;
    }

    public void updateCategory(String updateItemCategory)
    {
        this.pCategory      =   updateItemCategory;
    }

    public void updateQuantity(int updateQuantity)
    {
        this.pQuantity      =   updateQuantity;
    }

    public char getGenderType()
    {
        return pGenderType;
    }

    public String getCategory()
    {
        return pCategory;
    }

    public int getpQuantity()
    {
        return pQuantity;
    }



}
