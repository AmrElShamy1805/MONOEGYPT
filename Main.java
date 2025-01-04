import java.util.*;
import java.io.*;
import java.lang.*;
public class Main {
    public static void GetMenu(){
        System.out.println("-----WELCOME TO MONO-EGYPT-----");
        System.out.println("1.Choose trip ");
        System.out.println("2.View Lines ");
        System.out.println("3.Tickets and payment");
        System.out.println("4.Exit (enter -1) ");
    }
    public static void GetLineData (Line linechosen) throws FileNotFoundException
    {
        Scanner sc;
        Station s;
        ArrayList<Station> Stations=new ArrayList<>();
        switch (linechosen.getLineNum())
        {
            case 1 :
                File line1file = new File ("src/Line1.txt");
                sc = new Scanner (line1file);
                int i=0;
                while (sc.hasNextLine())
                {
                    String data = sc.nextLine();
                    String[] splitdata = data.split(" ");
                    String Stname = splitdata[0];
                    String Stloc = splitdata[1];
                    int Stno =1;
                    s=new Station (Stname,Stloc,Stno);
                    Stations.add(s);
                }
                linechosen.setStations(Stations);
                break ;
            case 2 :
                File line2 = new File ("src/Line2.txt");
                sc = new Scanner (line2);
                while (sc.hasNextLine())
                {
                    String data = sc.nextLine();
                    String[] splitdata = data.split(" ");
                    String Stname = splitdata[0];
                    String Stloc = splitdata[1];
                    int Stno =2;
                    s = new Station (Stname,Stloc, Stno);
                    Stations.add(s);
                }
                linechosen.setStations(Stations);
                break ;
            case 3 :
                File line3 = new File ("src/Line3.txt");
                sc = new Scanner (line3);
                while (sc.hasNextLine())
                {
                    String data = sc.nextLine();
                    String[] splitdata = data.split(" ");
                    String Stname = splitdata[0];
                    String Stloc = splitdata[1];
                    int Stno =3;
                    s=new Station(Stname,Stloc,Stno);
                    Stations.add(s);
                }
                linechosen.setStations(Stations);
                break ;

            default : System.out.println("This Line does not exist");

        }
    }
    public static User GetAccess(){
        User MainUser;
        Scanner sc = new Scanner(System.in);
        System.out.println("-------WELCOME BACK!-------");
        System.out.println("1.Sign Up ");
        System.out.println("2.Login ");
        System.out.println("3.Login as a guest");
        while(true){
            MainUser=null;
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            if(choice==1){
                try {
                    MainUser=SignUp();
                } catch (IOException e) {

                }
                break;
            }
            else if(choice==2){
                MainUser=Login();
                break;
            }
            else if(choice==3){
                while(true){
                    System.out.println("Are you sure you want to sign in as a guest? (Some features will be limited): ");
                    String ans = sc.next();
                    if(ans.equalsIgnoreCase("YES")||ans.equalsIgnoreCase("Y")){
                        String guestname;
                        Random r = new Random();
                        int guestid = r.nextInt(0,99000000);
                        guestname="Guest_"+guestid;
                        MainUser = new User(guestname," "," "," ",guestid);
                        break;
                    }
                    else if(ans.equalsIgnoreCase("NO")||ans.equalsIgnoreCase("N")){
                        System.out.println("Please sign in or sign up if you do not wish to sign in as a guest...");
                        break;
                    }
                }
                break;
            }
            else{
                System.out.println("Invalid input");
                continue;
            }
        }
        return MainUser;
    }
    //this method checks if an email was used in another account , if it is return true, else false
    public static boolean isUsed(String email){
        boolean found = false;
        File f = new File("UserSavedData.txt");
        try {
            //Read data from file and compare emails
            ArrayList<User> ExistingUsers = new ArrayList<>();
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                String datafromline = in.nextLine();
                String[] splitdatafromfile = datafromline.split("//");
                String fullname = splitdatafromfile[0];
                String PhoneNumber = splitdatafromfile[1];
                String Email = splitdatafromfile[2];
                String Password = splitdatafromfile[3];
                int ID = Integer.parseInt(splitdatafromfile[4]);
                User u = new User(fullname, PhoneNumber, Email, Password, ID);
                ExistingUsers.add(u);
            }
            for(int i =0;i< ExistingUsers.size();i++){
                if(ExistingUsers.get(i).GetEmail().equals(email)){
                    found=true;
                    break;
                }
            }
            in.close();
        }
        catch(IOException e){
        }
        return found;
    }
    public static User SignUp() throws IOException {
        User u = null;
        Random r = new Random();
        int UserID=r.nextInt(100000000,999999999);
        String fullname;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first name: ");
        String firstname = input.next();
        System.out.println("Enter middle initial(optional,enter _ to skip): ");
        char MiddleIn = input.next().charAt(0);
        if(MiddleIn=='_'){
            System.out.println("Enter surname: ");
            String surname = input.next();
            fullname = firstname+" "+surname;
        }
        else{
            System.out.println("Enter surname: ");
            String surname = input.next();
            fullname = firstname+" "+MiddleIn+". "+surname;
        }
        System.out.println("Enter PhoneNumber(Without spaces , replace spaces with dashes): ");
        String PhoneNumber=input.next();
        System.out.println("Enter email: ");
        String email = input.next();
        System.out.println("Enter Password: ");
        String password = input.next();
        //Checking if the email existed previously (done through a method)
        if(isUsed(email)){
            System.out.println("ERROR! EMAIL IS BEING USED FOR ANOTHER ACCOUNT");
            u=GetAccess();
        }
        else{
            u = new User(fullname,PhoneNumber,email,password,UserID);
            while(!u.CheckEmail()){
                System.out.println("Enter a valid Email: ");
                String Email=input.next();
                u.SetEmail(Email);
                u.CheckEmail();
                email=Email;
            }
            while(!u.CheckPassword()) {
                System.out.println("Enter a valid password: ");
                String Password = input.next();
                u.SetPassword(Password);
                u.CheckPassword();
                password=Password;
            }
            FileWriter file = new FileWriter("UserSavedData.txt",true);
            try{
                file.write(fullname+"//"+PhoneNumber+"//"+email+"//"+password+"//"+UserID+"\n");
            }
            catch(IOException e){
                System.out.println(" ");
            }
            file.close();
        }
        return u;
    }
    public static User Login(){
        User LoginUser=null;
        Scanner input = new Scanner(System.in);
        //Allow user to enter their username and password
        System.out.println("Enter Email: ");
        String email = input.next();
        System.out.println("Enter password: ");
        String password = input.next();
        User logintemp=new User(" "," ",email,password,0);
        boolean valid = false;
        //Checking whether or not the information the user will enter is even valid
        while(valid==false){
            if(logintemp.CheckEmail()&&logintemp.CheckPassword()){
                valid=true;
            }
            else if(logintemp.CheckEmail()&&!logintemp.CheckPassword()){
                System.out.println("Enter a valid password: ");
                password = input.next();
                logintemp.SetPassword(password);
            }
            else if(!logintemp.CheckEmail()&&!logintemp.CheckPassword()){
                System.out.println("Enter a valid email: ");
                email = input.next();
                logintemp.SetEmail(email);
                System.out.println("Enter a valid password: ");
                password = input.next();
                logintemp.SetPassword(password);
            }
            else{
                System.out.println("Enter a valid email: ");
                email = input.next();
                logintemp.SetEmail(email);
            }
        }
        //Import all user saved data from file and place it into objects and then place it in an array list
        File f = new File("UserSavedData.txt");
        try{
            //Read data from file and compare emails and passwords
            ArrayList<User> ExistingUsers = new ArrayList<>();
            boolean found =false;
            Scanner in = new Scanner(f);
            while(in.hasNextLine()){
                String datafromline = in.nextLine();
                String[] splitdatafromfile = datafromline.split("//");
                String fullname = splitdatafromfile[0];
                String PhoneNumber = splitdatafromfile[1];
                String Email  = splitdatafromfile[2];
                String Password = splitdatafromfile[3];
                int ID = Integer.parseInt(splitdatafromfile[4]);
                User u = new User(fullname,PhoneNumber,Email,Password,ID);
                ExistingUsers.add(u);
            }
            in.close();
            for(int i =0;i<ExistingUsers.size();i++){
                if(logintemp.compareTo(ExistingUsers.get(i))==0){
                    LoginUser=ExistingUsers.get(i);
                    String[] splitname = ExistingUsers.get(i).GetName().split(" ");
                    System.out.println("Welcome back, "+splitname[0]);
                    found=true;
                    break;
                }
            }
            if(!found){
                System.out.println("This account does not exist , please create one or sign in as a guest");
                LoginUser=GetAccess();
            }
        }
        catch(FileNotFoundException e){

        }
        catch(IndexOutOfBoundsException e){
            e.printStackTrace();

        }
        return LoginUser;
    }
    public static void ViewLines(ArrayList<Line> lines){
        Scanner input = new Scanner(System.in);
        boolean end=false;
        while(end==false){
            System.out.println("1. Line 1");
            System.out.println("2. Line 2");
            System.out.println("3. Line 3");
            System.out.println("Enter -1 to stop viewing lines");
            System.out.println("Choose which line to view:");
            int choice = input.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Line 1: ");
                    lines.get(0).PreviewStations();
                    break;
                case 2:
                    System.out.println("Line 2: ");
                    lines.get(1).PreviewStations();
                    break;
                case 3:
                    System.out.println("Line 3: ");
                    lines.get(2).PreviewStations();
                    break;
                case -1:
                    end=true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    public static Route SetTrip(ArrayList<Line> lines){
        Route r  = null;
        System.out.println("Choose your starting station and destination station from the following set of stations: ");
        System.out.println("----------------------------------------");
        lines.get(0).PreviewStations();
        System.out.println("----------------------------------------");
        lines.get(1).PreviewStations();
        System.out.println("----------------------------------------");
        lines.get(2).PreviewStations();
        System.out.println("----------------------------------------");
        System.out.println("ANY REPEATED STATION NAMES SIGNIFY THAT THEY ARE INTERSECTION STATIONS");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your starting point: ");
        String StartingSt = input.next();
        Station StartingStation = Station.isExistingStation(StartingSt,lines);
        while(StartingStation==null){
            System.out.println("Non-existent station");
            System.out.println("Enter your starting point: ");
            StartingSt = input.next();
            StartingStation= Station.isExistingStation(StartingSt,lines);
        }
        System.out.println("Enter your Destination point: ");
        String DestinationSt = input.next();
        Station DestinationStation = Station.isExistingStation(DestinationSt,lines);
        while(DestinationStation==null){
            System.out.println("Non-existent station");
            System.out.println("Enter your Destination point: ");
            DestinationSt = input.next();
            DestinationStation = Station.isExistingStation(DestinationSt,lines);
        }
        r = new Route(StartingStation,DestinationStation);
        return r;
    }
    public static Ticket CreateTicket(Route r){
        Railcar c=null;
        boolean valid=false;
        Ticket t=null;
        ArrayList<Integer> seats = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean valid1=false;
        while(valid1==false){
            System.out.println(" Enter the ticket class ");
            String Ticclass = sc.nextLine();
            if(Ticclass.equalsIgnoreCase("First class")){
                t=new Ticket(r,Ticclass);
                valid1=true;
            }
            else if(Ticclass.equalsIgnoreCase("Economy class")){
                t=new Ticket(r,Ticclass);
                valid1=true;
            }
            else if(Ticclass.equalsIgnoreCase("Economy plus class")){
                t=new Ticket(r,Ticclass);
                valid1=true;
            }
            else{
                System.out.println("Invalid class type");
            }
        }
        while(valid==false){
            System.out.println("What version of the monorail would you like to use for the trip? :");
            System.out.println("1.Normal monorail\n2.Speed monorail");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    c = new NormalCar("ADKJXX99100-SSAXM","MONOEGYPT",4000,seats);
                    t.setRailCar(c);
                    valid=true;
                    break;
                case 2:
                    c = new SpeedCar("SP-ADKJXX99100-SSAXM","MONOEGYPT",2000,seats);
                    t.setRailCar(c);
                    valid=true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        while (true)
        {
            System.out.println( " Enter the Seat number:  " );
            int seatNO = sc.nextInt();
            Integer sn = Integer.valueOf(seatNO);
            t.setSeatNum(sn);
            if(seatNO<=0){
                System.out.println("Invalid seat number");
                continue;
            }
            for (int i=0 ; i<c.getSeats().size();i++)
            {
                if (c.getSeats().contains(sn))
                {
                    System.out.println(" This seat is already taken :-) \n Please choose another seat ");
                }
                else if(!c.getSeats().contains(sn)||c.getSeats().isEmpty())
                {
                    c.getSeats().add(sn);
                }
            }
            break;
        }

        return t;
    }
    public static void ViewTicket ( Ticket t )
    {
        System.out.println("--------------------------");
        System.out.println("Ticket ID: "+Ticket.ticketID);
        System.out.println("Route:\nFrom:"+t.getRoute().GetStartingStation().getStationName()+"\nTo:"+t.getRoute().GetDestStation().getStationName());
        System.out.println("Price: "+t.getPrice());
        System.out.println("Rail car ID: "+t.getRailCar().getID());
        System.out.println("Seat number: "+t.getSeatNum());
        System.out.println("Ticket class: "+t.getTicketClass());
        System.out.println("Date of creation: "+t.getDateOfIssuing());
        System.out.println("--------------------------");
    }
    public static void paymentandcheckout(Ticket t){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the amount of money to be paid: ");
        double amount = input.nextDouble();
        if(amount<0){
            System.out.println("Invalid input");
        }
        else if(amount<t.getPrice()){
            System.out.println("This amount is not sufficient enough");
        }
        else if(amount>t.getPrice()){
            System.out.println("Extra money paid , refunding the following amount: "+(amount-t.getPrice()));
        }
        else{
            System.out.println("Enter card information: ");
            String method = input.next();
            System.out.println("Enter the bank you will pay through: ");
            String issuingbank = input.next();
            payment p = new payment(method,amount,issuingbank);
            System.out.println("Payment complete , thank you for your patience\nHere is your reciept: ");
            System.out.println("------------------------------");
            System.out.println(p.toString());
            System.out.println("------------------------------");
        }
    }
    public static void main(String[] args) {
        User MainUser;
        Ticket t = null;
        Route r=null;
        Scanner input = new Scanner(System.in);
        Line line1=new Line(1);
        Line line2=new Line(2);
        Line line3=new Line(3);
        ArrayList<Line> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        try {
            GetLineData(line1);
            GetLineData(line2);
            GetLineData(line3);
            MainUser=GetAccess();
            while(MainUser==null){
                MainUser=GetAccess();
            }
            boolean end=false;
            while(!end){
                GetMenu();
                System.out.println("Enter your choice from the ones given above: ");
                int choice = input.nextInt();
                switch(choice){
                    case 1:
                        System.out.println("You chose to set your trip");
                        r=SetTrip(lines);
                        System.out.println("This is the route: ");
                        r.SetLines(lines);
                        r.RouteInformation();
                        System.out.println("Number of stations passed: "+r.GetNumberOfStations());
                        System.out.println("This Journey will take around "+r.GetTripTime()+" minute(s) ");
                        break;
                    case 2:
                        ViewLines(lines);
                        break;
                    case 3:
                        if(MainUser.GetName().contains("Guest_")){
                            System.out.println("You must Sign up or Log in to access this feature");
                            System.out.println("Would you like to Sign up or Log in: ");
                            String response = input.next();
                            if(response.equalsIgnoreCase("yes")||response.equalsIgnoreCase("y")){
                                MainUser=GetAccess();
                            }
                            else if (response.equalsIgnoreCase("no")||response.equalsIgnoreCase("n")){
                                System.out.println("Returning to menu...");
                            }
                        }
                        else if(r==null){
                            System.out.println("You must set your trip first before trying to view the ticket or paying for it");
                        }
                        else{
                            System.out.println("1.To view and create ticket");
                            System.out.println("2.Proceed to payment and checkout");
                            System.out.println("Enter your choice: ");
                            int choice_ticket=input.nextInt();
                            if(choice_ticket==1){
                                t=CreateTicket(r);
                                Ticket.CalculateTicketPrice(t);
                                ViewTicket(t);
                            }
                            else if(choice_ticket==2){
                                if(t==null){
                                    System.out.println("A ticket must be first created before payment");
                                }
                                else{
                                    paymentandcheckout(t);
                                }
                            }
                        }
                        break;
                    case -1:
                        System.out.println("Thank you for using MONO-EGYPT");
                        end=true;
                        break;
                    default:
                        System.out.println("Invalid input...");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch(IndexOutOfBoundsException e){

        }
    }
}
