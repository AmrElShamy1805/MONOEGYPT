import java.util.ArrayList;

public class NormalCar extends Railcar {
        public NormalCar(String ID, String Model , int capacity , ArrayList<Integer> seats){
            super(ID,Model,capacity,seats);
        }
        @Override
        public String  GetCarDescription() {
            return "Default monorail car also quite fast but not as fast as the speed car , has a relatively normal capacity tolerance" ;
        }
        @Override
        public double GetCarExtraTaxes(){
            return 1.5;
        }
        @Override
        public String toString(){
            return super.toString()+"\nDescription: "+GetCarDescription();
        }
}
