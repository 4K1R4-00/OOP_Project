import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Service extends Item
{
    private LocalDate appointmentDate;
    private DateTimeFormatter dateFormat    =   DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private int appointmentType;

    public Service() {}

    public Service(String serviceName, double serviceCost)
    {
        super(serviceName, serviceCost);
    }

    Service(String serviceName, double serviceCost, LocalDate appointDate, int appointType, int serviceQuantity)
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

    public void setServiceAppointmentDate(String setAppointmentDate)
    {
        this.appointmentDate    =   LocalDate.parse(setAppointmentDate, this.dateFormat);
    }

    public LocalDate getServiceAppointmentDate()
    {
        return this.appointmentDate;
    }

    public int getServiceAppointmentType()
    {
        return this.appointmentType;
    }

}
