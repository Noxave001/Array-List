import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class demonstrating the capabilities of SortedArrayList, using water data visualization
 *
 * @author Ace Breaux
 * @version 4-29-2025
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // ---------------------- READING WATER DATA AND UNIQUE YEARS ----------------------

        // "IYWD" --> IsoYearWaterData
        Scanner scanIYWD = new Scanner(new File("IsoYearWaterData.txt"));
        SortedArrayList<YearlyWaterRecord> waterData = new SortedArrayList<>();
        // Temporary SAL for unique year data; later converted to int[]
        SortedArrayList<Integer> yearsSAL = new SortedArrayList<>();

        // Position scanner at the start of the data
        scanIYWD.nextLine();

        // Iterate through file
        while (scanIYWD.hasNextLine()) {
            String[] tempArray = scanIYWD.nextLine().split(",");

            // Correct double values
            for (int i = 1; i < tempArray.length; i++) {
                switch (tempArray[i]) {
                    case ">99" -> tempArray[i] = "100.0";
                    case "<1" -> tempArray[i] = "0.0";
                    case "-" -> tempArray[i] = "-1.0";
                }
            }

            // Detect unique year to add to list of years (if applicable)
            if (!yearsSAL.contains(Integer.parseInt(tempArray[0].substring(3)))) {
                yearsSAL.add(Integer.parseInt(tempArray[0].substring(3)));
            }

            // Create and add record to waterData
            waterData.add(new YearlyWaterRecord(
                    tempArray[0], Double.parseDouble(tempArray[1]),
                    Double.parseDouble(tempArray[2]), Double.parseDouble(tempArray[3]),
                    Double.parseDouble(tempArray[4])));
        }

        // Roundabout way of making the int array of years (converted from SAL<Integer> to int[])
        // Can't use toArray here because it would make an array of Integer[] type, which can't be used
        //      in the GUI and can't be cast to int[]
        // However, this method has the benefit of already being organized & relatively small to copy
        //      manually, so it doesn't have a huge time cost
        int[] years = new int[yearsSAL.size()];
        for (int i = 0; i < yearsSAL.size(); i++) {
            years[i] = yearsSAL.get(i);
        }

        // ---------------------- READING COUNTRIES AND ISO CODES ----------------------

        // "CAC" --> Countries and Codes
        Scanner scanCAC = new Scanner(new File("CountriesAndIsoCodes.txt"));
        int countryCount = scanCAC.nextInt();
        String[] countries = new String[countryCount];
        String[] isoCodes = new String[countryCount];

        // Position scanner at the start of the data
        scanCAC.nextLine();
        scanCAC.nextLine();

        // Fill arrays
        for (int i = 0; i < countryCount; i++) {
            String[] tempArray = scanCAC.nextLine().split("#");
            countries[i] = tempArray[0];
            isoCodes[i] = tempArray[1];
        }

        // ---------------------- CREATING GUI ----------------------

        WaterComparisonGui gui = new WaterComparisonGui(waterData, countries, isoCodes, years);
    }
}
