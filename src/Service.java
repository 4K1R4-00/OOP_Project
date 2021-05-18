import java.sql.Time;
import java.util.Date;

public class Service extends Item
{

    private Date appointDate;
    private Time appointTime;
    private int appointType;

    Service(String serviceName, double serviceCost, int serviceQuantity)
    {
        super(serviceName, serviceCost, serviceQuantity);
    }

    Service(String serviceName, double serviceCost, Date appointDate, Time appointTime, int appointType, int serviceQuantity)
    {
        super(serviceName, serviceCost, serviceQuantity);
        this.appointDate    =   appointDate;
        this.appointTime    =   appointTime;
        this.appointType    =   appointType;
    }

    public void updateAppointDate(Date updateAppointDate)
    {
        this.appointDate    =   updateAppointDate;
    }

    public void updateAppointTime(Time updateAppointTime)
    {
        this.appointTime    =   updateAppointTime;
    }

    public void updateAppointType(int updateAppointType)
    {
        this.appointType    =   updateAppointType;
    }

    public Date getAppointDate()
    {
        return this.appointDate;
    }

    public Time getAppointTime()
    {
        return this.appointTime;
    }

    public int getAppointType()
    {
        return this.appointType;
    }


}
