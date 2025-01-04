import java.util.*;
import java.io.*;
public class User implements Comparable{
    private String fullname;
    private String PhoneNumber;
    private String EmailAddress;
    private String Password;
    private int UserId;
    public User(String fullname,String PhoneNumber,String EmailAddress,String Password,int UserId){
        this.fullname=fullname;
        this.PhoneNumber=PhoneNumber;
        SetEmail(EmailAddress);
        SetPassword(Password);
        this.UserId=UserId;
    }
    public void SetName(String fullname){
        this.fullname=fullname;
    }
    public void SetPhoneNumber(String PhoneNumber){
        this.PhoneNumber=PhoneNumber;
    }
    public void SetEmail(String EmailAddress){
        this.EmailAddress=EmailAddress;

    }
    public void SetPassword(String Password){
        this.Password=Password;
    }
    public String GetName(){
        return fullname;
    }
    public String GetPhoneNumber(){
        return PhoneNumber;
    }
    public String GetEmail(){
        return EmailAddress;
    }
    public String GetPassword(){
        return Password;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public boolean CheckEmail(){
        boolean valid =true;
        //Check whether or not the format of the email is correct , given that the email cannot start with any special characters or digits
        if(Character.isDigit(EmailAddress.charAt(0))||!Character.isLetterOrDigit(EmailAddress.charAt(0))){
            System.out.println("Email address invalid , an email cannot start with special characters or digits");
            valid=false;
        }
        else if(!EmailAddress.contains("@")){
            System.out.println("Email address invalid , an email cannot be missing '@' or cannot contain a space");
            valid=false;
        }
        else{

            File f = new File("src/EMAILS.txt");
            try{
                ArrayList<String> domains = new ArrayList<>();
                boolean found = false;
                String[] emailsplit = EmailAddress.split("@");
                Scanner s = new Scanner(f);
                while(s.hasNextLine()){
                    domains.add(s.nextLine());
                }
                if(domains.contains(emailsplit[1])){
                    found=true;
                }
                if(found=false){
                    System.out.println("Email address invalid , please enter an email domain that exists");
                    valid=false;
                }

            }
            catch(IOException e){

            }

        }
        return valid;
    }
    public boolean CheckPassword(){
        //Password cannot contain spaces and must have at least 2 special characters , 3 uppercase letters , and 2 digits , and must have a length of at least 8 characters and no more than 25 characters
        boolean valid=true;
        int spcount=0,uppercount=0,digitcount=0;
        if(Password.length()<8||Password.length()>25){
            System.out.println("Invalid Password , password has violated the restriction on password length");
            valid=false;
        }
        else{
            for(int i =0;i<Password.length();i++){
                if(!Character.isLetterOrDigit(Password.charAt(i))){
                    spcount++;
                }
                else if(Character.isLetter(Password.charAt(i))&&Character.isUpperCase(Password.charAt(i))){
                    uppercount++;
                }
                else if(Character.isDigit(Password.charAt(i))){
                    digitcount++;
                }
            }
        }
        if(spcount<2){
            System.out.println("Invalid Password , password has violated the restriction on special character count");
            valid=false;
        }
        else if(uppercount<3){
            System.out.println("Invalid Password , password has violated the restriction on uppercase letter count");
            valid=false;
        }
        else if (digitcount<2){
            System.out.println("Invalid Password , password has violated the restriction on digit count");
            valid=false;
        }
        else if(Password.contains(" ")){
            System.out.println("Invalid Password , password has violated the restriction on spaces");
            valid=false;
        }
        return valid;
    }
    @Override
    public int compareTo(Object u) {
        int val;
        User u1 = (User) u;
        if(u1.EmailAddress.equals(EmailAddress)&&u1.Password.equals(Password)) {
            val=0;
        }
        else {
            val = -1;
        }
        return val;
    }
    public String toString(){
        return "\nFull Name: "+fullname+"\nPhone Number: "+PhoneNumber+"\nEmail: "+EmailAddress+"\nUser ID: "+UserId;
    }



}
