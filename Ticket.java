import java.util.Date;

public class Ticket {

    public static int ticketID=0;
    private double price;
    private int seatNum;
    private String ticketClass;
    private  Route route ;
    private Date dateOfIssuing;
    private Railcar c;


    public Ticket(Route route, String ticketClass) {
        this.route = route;
        this.ticketClass = ticketClass;
        this.dateOfIssuing = new Date();
        ticketID++;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setRailCar(Railcar c) {
        this.c = c;
    }
    public Railcar getRailCar(){
        return c;
    }

    public double getPrice() {
        return price;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public Route getRoute() {
        return route;
    }

    public Date getDateOfIssuing() {
        return dateOfIssuing;
    }
    public static void CalculateTicketPrice ( Ticket t  )
    {
        double stationprice=1;
        switch (t.getTicketClass())
        {
            case "First class" :
                stationprice  = 4.50 ;
                break ;
            case "Economy class" :
                stationprice  = 3.0 ;
                break ;
            case "Economy plus class" :
                stationprice  = 2.25 ;
                break ;
            default: stationprice=0;
        }
        t.setPrice(t.getRoute().GetNumberOfStations()*stationprice+t.getRailCar().GetCarExtraTaxes());
    }




    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", price=" + price +
                ", seatNum=" + seatNum +
                ", ticketClass='" + ticketClass + '\'' +
                ", route=" + route +
                ", dateOfIssuing=" + dateOfIssuing +
                '}';
    }
}
