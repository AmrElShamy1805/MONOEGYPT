
import java.util.*;
import java.io.*;

public class Line
{
		private ArrayList <Station> Stations;
		private int lineNum ;
		public Line(int lineNum) {
			super();
			this.lineNum = lineNum;
		}
		public int getLineNum() 
		{
			return lineNum;
		}
		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}
		public ArrayList<Station> getStations() {
			return Stations;
		}
		public void setStations(ArrayList<Station> stations)
		{
			this.Stations = stations;
		}
		public void PreviewStations() 
		{
			for (int i=0 ; i<Stations.size() ; i++)
				{
					System.out.println((i+1)+"."+Stations.get(i).getStationName());
				}


			}
		public String toString() {
			return "Line [Stations=" + Stations.size() + ", lineNum=" + lineNum + "]";
		}
		
	}


