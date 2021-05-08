class Product extends Item
{
    char pGenderType;
    String pCategory;
    int pQuantity;

    Product(){

    }

    Product(char pGenderType, String pCategory, int pQuantity){
        this.pGenderType    =   pGenderType;
        this.pCategory      =   pCategory;
        this.pQuantity      =   pQuantity;
    }

    public void updateGenderType(char updateItemType){
        this.pGenderType    =   updateItemType;
    }

    public void updateCategory(String updateItemCategory){
        this.pCategory      =   updateItemCategory;
    }

    public void updateQuantity(int updateQuantity){
        this.pQuantity      =   updateQuantity;
    }

    public char getGenderType(){
        return pGenderType;
    }

    public String getCategory(){
        return pCategory;
    }

    public int getpQuantity(){
        return pQuantity;
    }



}
