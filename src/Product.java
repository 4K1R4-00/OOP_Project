/*
 *  @authors
 *  AKMAL 'AISY BIN RUDY                        52215220045
 *  NUR ARIFA BINTI NOR AZLAN                   52215220050
 *  DANISH IMRAN BIN MOHD ARIF ARCHI            52215220060
 *  MOHD FAIZ BIN RADZI                         52215220049
 *
 */

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
