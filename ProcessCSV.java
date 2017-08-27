/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ProcessCSV {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        
        for(CSVRecord currentRow : parser){
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
      public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {

        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        if(currentTemp == -9999){
            return smallestSoFar;
        }

        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {

            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
          if (currentTemp < smallestTemp) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord coldestInManyDays() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            lowestSoFar = getSmallestOfTwo(currentRow, lowestSoFar);
        }
        //The largestSoFar is the answer
        return lowestSoFar;
    }
    
    
     public void testcoldestHourInFile () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEST"));
    }
    
     public void testColdestInManyDays () {
        CSVRecord result = coldestInManyDays();
        System.out.println("Coldest was " + result.get("TemperatureF") +
                   " at " + result.get("DateUTC"));
    }      
    
    
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

        public CSVRecord lowestHumidityInManyDays() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            lowestSoFar = getLowerHumidity(currentRow, lowestSoFar);
        }
        
        return lowestSoFar;
    }
    
        public CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getLowerHumidity(currentRow, lowestSoFar);
        }
        //The largestSoFar is the answer
        return lowestSoFar;
    }
    
        public CSVRecord getLowerHumidity (CSVRecord currentRow, CSVRecord smallestSoFar) {

         String humidity = currentRow.get("Humidity");
         
         if(humidity.equals("N/A")){
             return smallestSoFar;
         }
            
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double smallestHumidity  = Double.parseDouble(smallestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentHumidity < smallestHumidity) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
      public void testlowestHumidityInFile () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("TimeEST"));
    }
    
    public void testLeastHumidInManyDays () {
        CSVRecord result = lowestHumidityInManyDays();
        System.out.println("Lowest Humidity was " + result.get("Humidity") +
                   " at " + result.get("DateUTC"));
    }      
    
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
}
