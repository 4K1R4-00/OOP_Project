/*
 *  @authors
 *  AKMAL 'AISY BIN RUDY                        52215220045
 *  NUR ARIFA BINTI NOR AZLAN                   52215220050
 *  DANISH IMRAN BIN MOHD ARIF ARCHI            52215220060
 *  MOHD FAIZ BIN RADZI                         52215220049
 *
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Service extends Item
{
    private LocalDate appointmentDate;
    private DateTimeFormatter dateFormat    =   DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private int appointmentType;

    public Service() {}

    /*
     *  @param      String  serviceName
     *  @param      double  serviceCost
     *
     *  @brief
     *
     *  @return     none
     */
    public Service(String serviceName, double serviceCost)
    {
        super(serviceName, serviceCost);
    }

    /*
     *  @param      String  serviceName
     *  @param      double  serviceCost
     *  @param      int     appointmentType
     *
     *  @brief
     *
     *  @return     none
     */
    public Service(String serviceName, double serviceCost, int appointmentType)
    {
        super(serviceName, serviceCost);

        this.appointmentType    =   appointmentType;
    }

    /*
     *  @param      String      serviceName
     *  @param      double      serviceCost
     *  @param      LocalDate   appointDate
     *  @param      int         appointType
     *  @param      int         serviceQuantity
     *
     *  @brief
     *
     *  @return     none
     */
    public Service(String serviceName, double serviceCost, LocalDate appointDate, int appointType, int serviceQuantity)
    {
        super(serviceName, serviceCost);
    }

    /*
     *  @param      int     setAppointmentType
     *
     *  @brief
     *  Sets the service appointment type
     *
     *  @return     void
     */
    public void setServiceAppointmentType(int setAppointmentType)
    {
        this.appointmentType    =   setAppointmentType;
    }

    /*
     *  @param      String  setAppointmentDate
     *
     *  @brief
     *  Sets the service appointment date
     *
     *  @return     void
     */
    public void setServiceAppointmentDate(String setAppointmentDate)
    {
        this.appointmentDate    =   LocalDate.parse(setAppointmentDate, this.dateFormat);
    }

    /*
     *  @param      LocalDate  setAppointmentDate
     *
     *  @brief
     *  Sets the service appointment date, method overriding
     *
     *  @return     void
     */
    public void setServiceAppointmentDate(LocalDate setAppointmentDate)
    {
        this.appointmentDate    =   setAppointmentDate;
    }

    /*
     *  @param      void
     *
     *  @brief
     *
     *  @return     LocalDate
     */
    public LocalDate getServiceAppointmentDate()
    {
        return this.appointmentDate;
    }

    /*
     *  @params     void
     *
     *  @brief
     *
     *  @return     int
     */
    public int getServiceAppointmentType()
    {
        return this.appointmentType;
    }

}
