 import java.util.ArrayList;

 class SpeedCar extends Railcar{
    public SpeedCar(String ID, String Model , int capacity ,ArrayList<Integer> seats){
        super(ID,Model,capacity,seats);

    }
    @Override
    public String  GetCarDescription() {
        return "High speed MONORAIL car with a lower capacity than usual , holds up to 2000 people at a time" ;
    }
     @Override
     public double GetCarExtraTaxes(){
         return 3;
     }
    @Override
    public String toString(){
        return super.toString()+"\nDescription: "+GetCarDescription();
    }
}
