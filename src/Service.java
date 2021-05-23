import java.sql.Time;
import java.util.Date;

public class Service extends Item
{

    Service(String serviceName, double serviceCost)
    {
        super(serviceName, serviceCost);
    }

    Service(String serviceName, double serviceCost, Date appointDate, Time appointTime, int appointType, int serviceQuantity)
    {
        super(serviceName, serviceCost);

    }

}
