
import java.util.ArrayList;

public class Station implements Comparable
{
	private String StationName;
	private String StationLocation;
	private int LineNum;
		public Station(String stationName){
			StationName=stationName;
		}
		public Station(String stationName, String stationLocation, int lineNum) {
			super();
			StationName = stationName;
			StationLocation = stationLocation;
			LineNum = lineNum;
		}

		public String getStationName() {
			return StationName;
		}

		public void setStationName(String stationName) {
			this.StationName = stationName;
		}

		public String getStationLocation() {
			return StationLocation;
		}

		public void setStationLocation(String stationLocation) {
			this.StationLocation = stationLocation;
		}

		public int getLineNum() {
			return LineNum;
		}

		public void setLineNum(int lineNum) {
			this.LineNum = lineNum;
		}
	public static Station isExistingStation(String Station,ArrayList<Line> lines){
		Station s = null;
		boolean found=false;
		for(int i =0;i<lines.get(0).getStations().size();i++){
			if(lines.get(0).getStations().get(i).getStationName().equalsIgnoreCase(Station)){
				s=lines.get(0).getStations().get(i);
				break;
			}
		}
		for(int j=0;j<lines.get(1).getStations().size();j++){
			if(found==true){
				break;
			}
			if(lines.get(1).getStations().get(j).getStationName().equalsIgnoreCase(Station)){
				s=lines.get(1).getStations().get(j);
			}
		}
		for(int z=0;z<lines.get(2).getStations().size();z++){
			if(found==true){
				break;
			}
			if(lines.get(2).getStations().get(z).getStationName().equalsIgnoreCase(Station)){
				s=lines.get(2).getStations().get(z);
			}
		}

		return s;
	}

		public String toString() {
			return "Station [StationName=" + StationName + ", StationLocation=" + StationLocation + ", LineNum=" + LineNum
					+ "]";
		}

	@Override
	public int compareTo(Object o) {
		Station s = (Station) o;
		if(this.StationName.equals(s.StationName)){
			return 0;
		}
		else{
			return -1;
		}
	}
}


