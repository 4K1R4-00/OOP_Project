class Product extends Item
{
    char pGenderType;
    String pCategory;


    Product(String productName, double productCost)
    {
        super(productName, productCost);
    }

    Product(String productName,
            String productCategory,
            int productQuantity,
            double productCost,
            char productGenderType

            )
    {
        super(productName, productCost, productQuantity);
        this.pGenderType    =   productGenderType;
        this.pCategory      =   productCategory;


    }

    public void updateGenderType(char updateItemType)
    {
        this.pGenderType    =   updateItemType;
    }

    public void updateCategory(String updateItemCategory)
    {
        this.pCategory      =   updateItemCategory;
    }

    public char getGenderType()
    {
        return pGenderType;
    }

    public String getCategory()
    {
        return pCategory;
    }




}
