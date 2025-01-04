import java.util.ArrayList;

public abstract class Railcar
{
	protected String ID;
	protected String Model;
	protected int Capacity;
	protected Line carline;
	protected ArrayList <Integer> seats ;
	public Railcar(String iD, String model, int capacity,ArrayList<Integer> seats ) {
		super();
		ID = iD;
		Model = model;
		Capacity = capacity;
		this.seats=seats;
	}
	public ArrayList<Integer> getSeats() {
		return seats;
	}
	public void setSeats(ArrayList<Integer> seats) {
		this.seats = seats;
	}
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		this.Model = model;
	}

	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int capacity) {
		this.Capacity = capacity;
	}
	public abstract String GetCarDescription();
	public abstract double GetCarExtraTaxes();

	public String toString() {
		return "RailCar [ID=" + ID + ", Model=" + Model + ", Capacity=" + Capacity + "]";
	}
}


