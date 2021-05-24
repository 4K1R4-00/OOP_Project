import java.util.Date;
import java.text.SimpleDateFormat;

public class Service extends Item
{
    private Date appointmentDate;
    private int appointmentType;

    public Service() {}

    public Service(String serviceName, double serviceCost)
    {
        super(serviceName, serviceCost);
    }

    Service(String serviceName, double serviceCost, Date appointDate, int appointType, int serviceQuantity)
    {
        super(serviceName, serviceCost);

    }

    Service(String serviceName, double serviceCost, int appointmentType)
    {
        super(serviceName, serviceCost);

        this.appointmentType    =   appointmentType;
    }

    public void setServiceAppointmentType(int setAppointmentType)
    {
        this.appointmentType    =   setAppointmentType;
    }

    public void setServiceAppointmentDate(Date setAppointmentDate)
    {
        this.appointmentDate    =   setAppointmentDate;
    }

    public void setServiceAppointmentDate(String setAppointmentDate)
    {
        try
        {
            this.appointmentDate    =   new SimpleDateFormat("dd/MM/yyyy").parse(setAppointmentDate);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Date getServiceAppointmentDate()
    {
        return this.appointmentDate;
    }

    public int getServiceAppointmentType()
    {
        return this.appointmentType;
    }

}
