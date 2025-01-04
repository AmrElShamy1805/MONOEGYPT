import java.util.*;
public class Route {
    private Station StartingStation;
    private Station DestStation;
    private ArrayList<Line> lines = new ArrayList<>();

    public Route(Station startingStation, Station DestStation) {
        StartingStation = startingStation;
        this.DestStation = DestStation;
    }

    public Station GetStartingStation() {
        return StartingStation;
    }

    public Station GetDestStation() {

        return DestStation;
    }

    public ArrayList<Line> GetLines() {

        return lines;
    }

    public void SetStartingStation() {

        this.StartingStation = StartingStation;
    }

    public void setDestStation(Station destStation) {

        DestStation = destStation;
    }

    public void SetLines(ArrayList<Line> lines) {

        this.lines = lines;
    }

    private ArrayList<Line> FindAppropriateLines() {
        ArrayList<Line> lineanswrs = new ArrayList<>(2);
        for (int i = 0; i < lines.size(); i++) {
            //checks if the stations are on the same line
            if (lines.get(i).getStations().contains(StartingStation) && lines.get(i).getStations().contains(DestStation)) {
                lineanswrs.add(lines.get(i));
                lineanswrs.add(lines.get(i));
                break;
            }
            //checks if the stations are not on the same line
            else if (lines.get(i).getStations().contains(StartingStation) && !lines.get(i).getStations().contains(DestStation)) {
                //Looks for the location of the destination station given that the starting station is on the second line
                if (i == 1) {
                    Line previousline = lines.get(i - 1);
                    Line nextline = lines.get(i + 1);
                    //checks if the location is on the 1st line
                    if (previousline.getStations().contains(DestStation)) {
                        lineanswrs.add(lines.get(i));
                        lineanswrs.add(previousline);
                        break;
                    }
                    //checks if the location on the 3rd line
                    else if (nextline.getStations().contains(DestStation)) {
                        lineanswrs.add(lines.get(i));
                        lineanswrs.add(nextline);
                        break;

                    }
                }
                //
                else if (i == 2) {
                    Line[] previouslines = new Line[2];
                    previouslines[0] = lines.get(i - 2);
                    previouslines[1] = lines.get(i - 1);
                    if (previouslines[0].getStations().contains(DestStation)) {
                        lineanswrs.add(lines.get(i));
                        lineanswrs.add(previouslines[0]);
                        break;
                    } else if (previouslines[1].getStations().contains(DestStation)) {
                        lineanswrs.add(lines.get(i));
                        lineanswrs.add(previouslines[1]);
                        break;
                    }
                } else {
                    Line[] nextlines = new Line[2];
                    nextlines[0] = lines.get(i + 1);
                    nextlines[1] = lines.get(i + 2);
                    if (nextlines[0].getStations().contains(DestStation)) {
                        lineanswrs.add(lines.get(i));
                        lineanswrs.add(nextlines[0]);
                        break;
                    } else if (nextlines[1].getStations().contains(DestStation)) {
                        lineanswrs.add(lines.get(i));
                        lineanswrs.add(nextlines[1]);
                        break;
                    }

                }
            }

        }
        return lineanswrs;

    }

    private ArrayList<Station> GetStationsOnRoute() {
        ArrayList<Line> linesneeded = FindAppropriateLines();
        ArrayList<Station> StationsOnRoute = new ArrayList<>();
        int size1 = linesneeded.get(0).getStations().size();
        int size2 = linesneeded.get(1).getStations().size();
        //starting station index
        int startindex = -1;
        //ending station index
        int endindex = -1;
        //Index for the intersection point at the starting line
        int index1 = -1;
        //Index for the intersection point at the ending line
        int index2 = -1;
        //checking if the destination and the starting point are not on the same line
        if (!linesneeded.get(0).equals(linesneeded.get(1))) {
            //Getting the index for the intersection point on the line with the starting point
            for (int i = 0; i < size1; i++) {
                for (int j = 0; j < size2; j++) {
                    try {
                        if (linesneeded.get(0).getStations().get(i).compareTo(linesneeded.get(1).getStations().get(j)) == 0) {
                            index1 = i;
                        }
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
            //Getting the index for the intersection point on the line with the destination point
            for (int z = 0; z < size2; z++) {
                for (int y = 0; y < size1; y++) {
                    try {
                        if (linesneeded.get(1).getStations().get(z).compareTo(linesneeded.get(0).getStations().get(y)) == 0) {
                            index2 = z;
                        }
                    } catch (IndexOutOfBoundsException e) {

                    }
                }
            }
            //Getting the index for the starting station given that both the starting station and the destination station are not on the same line
            for (int count1 = 0; count1 < linesneeded.get(0).getStations().size(); count1++) {
                if (linesneeded.get(0).getStations().get(count1).compareTo(StartingStation) == 0) {
                    startindex = count1;
                }
            }
            //Getting the index for the destination station given that both the starting station and the destination station are not on the same line
            for (int count = 0; count < linesneeded.get(1).getStations().size(); count++) {
                if (linesneeded.get(1).getStations().get(count).compareTo(DestStation) == 0) {
                    endindex = count;
                }
            }
            //Outputting the station names starting from the starting station to the intersection station given that the starting station is after the intersection
            if (startindex > index1) {
                for (int x = startindex; x >= index1; x--) {
                    StationsOnRoute.add(linesneeded.get(0).getStations().get(x));
                }
            } else {
                for (int x = startindex; x <= index1; x++) {
                    StationsOnRoute.add(linesneeded.get(0).getStations().get(x));
                }
            }
            //checking whether the intersection station lies before or after the destination station
            if (index2 > endindex) {
                //returns the names of all the stations on the route from the intersection to the destination but backwards
                for (int k = index2; k >= endindex; k--) {
                    //System.out.println(linesneeded.get(1).getStations().get(k).getStationName());
                    StationsOnRoute.add(linesneeded.get(1).getStations().get(k));
                }
            }
            //returns the names of all the stations on the route from the intersection to the destination but forwards
            else {
                for (int k = index2; k <= endindex; k++) {
                    //System.out.println(linesneeded.get(1).getStations().get(k).getStationName());
                    StationsOnRoute.add(linesneeded.get(1).getStations().get(k));
                }
            }
        } else {
            //Finds the index for the starting station given that both the starting station and the ending station are on the same line
            for (int count1 = 0; count1 < linesneeded.get(0).getStations().size(); count1++) {
                if (linesneeded.get(0).getStations().get(count1).compareTo(StartingStation) == 0) {
                    startindex = count1;
                }
            }
            //Finds the index for the ending station given that both the starting station and the ending station are on the same line
            for (int count2 = 0; count2 < linesneeded.get(0).getStations().size(); count2++) {
                if (linesneeded.get(0).getStations().get(count2).compareTo(DestStation) == 0) {
                    endindex = count2;
                }
            }
            //Checks if the starting station is after the ending station on the line
            if (startindex > endindex) {
                for (int x = startindex; x >= endindex; x--) {
                    //System.out.println(linesneeded.get(0).getStations().get(x).getStationName());
                    StationsOnRoute.add(linesneeded.get(0).getStations().get(x));
                }
            }
            //Checks if the starting station is before the ending station on the line
            else {
                for (int x = startindex; x <= endindex; x++) {
                    //System.out.println(linesneeded.get(0).getStations().get(x).getStationName());
                    StationsOnRoute.add(linesneeded.get(0).getStations().get(x));
                }
            }
        }
        return StationsOnRoute;
    }

    public void RouteInformation() {
        ArrayList<Station> AllStationsOnRoute = GetStationsOnRoute();
        ArrayList<Line> LinesUsedOnRoute = FindAppropriateLines();
        int numofstationsonstartingline = 0;
        int numofstationsonendingline = 0;
        if (LinesUsedOnRoute.get(0).getLineNum() != LinesUsedOnRoute.get(1).getLineNum()) {
            for (int i = 0; i < AllStationsOnRoute.size(); i++) {
                if (AllStationsOnRoute.get(i).getLineNum() == LinesUsedOnRoute.get(0).getLineNum()) {
                    numofstationsonstartingline++;
                }
                else if (AllStationsOnRoute.get(i).getLineNum() == LinesUsedOnRoute.get(1).getLineNum()) {
                    numofstationsonendingline++;
                }
            }
            System.out.println("Line "+LinesUsedOnRoute.get(0).getLineNum());
            for (int j = 0; j < numofstationsonstartingline; j++) {
                if (j == 0) {
                    System.out.println("STARTING STATION: " + AllStationsOnRoute.get(j).getStationName());
                }
                else if (j > 0 && j < numofstationsonstartingline - 1) {
                    System.out.println(AllStationsOnRoute.get(j).getStationName());
                }
                else {
                    System.out.println("INTERSECTION STATION: " + AllStationsOnRoute.get(j).getStationName());
                }
            }
            System.out.println("Line "+LinesUsedOnRoute.get(1).getLineNum());
            for(int x=AllStationsOnRoute.size()-numofstationsonendingline+1;x<AllStationsOnRoute.size();x++){
                if(x==AllStationsOnRoute.size()-1){
                    System.out.println("DESTINATION STATION: "+AllStationsOnRoute.get(x).getStationName());
                }
                else{
                    System.out.println(AllStationsOnRoute.get(x).getStationName());
                }


            }
        }
        else {
            for (int i = 0; i < AllStationsOnRoute.size(); i++) {
                if (i == 0) {
                    System.out.println("STARTING STATION: " + AllStationsOnRoute.get(i).getStationName());
                }
                else if (i > 0 && i < AllStationsOnRoute.size() - 1) {
                    System.out.println(AllStationsOnRoute.get(i).getStationName());

                }
                else{
                    System.out.println("DESTINATION STATION: "+AllStationsOnRoute.get(i).getStationName());
                }

            }


        }

    }
    public int GetTripTime(){
        return GetStationsOnRoute().size()*(150/60);
    }
    public int GetNumberOfStations(){
        return GetStationsOnRoute().size()-1;
    }

    @Override
    public String toString() {
        return "Route{" +
                "StartingStation=" + StartingStation +
                ", DestStation=" + DestStation +
                ", lines=" + lines +
                '}';
    }
}



