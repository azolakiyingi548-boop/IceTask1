//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
public class Main {
    public static void main(String[] args) {
        int [][] sales = {{100, 150, 70}, {88, 92, 103}, {75, 45, 90}, {65, 95, 175}};
        String [] quarters = {"Q1", "Q2", "Q3", "Q4"};
        String [] brands = {"NIKE", "ADIDAS", "REEBOK"};

        int numBrands = brands.length;
        int [] totals = new int[numBrands];
        double [] averages = new double[numBrands];
        int[] min = new int[numBrands];
        int [] max = new int[numBrands];

        String fileName = "ShoeSalesReport.txt";

        for(int brand = 0; brand < numBrands; brand++){
            min[brand] = sales[0][brand];
            max[brand] = sales[0][brand];
        }

        for(int brand = 0; brand < numBrands; brand++){
            for(int quarter = 0; quarter < sales.length; quarter++){
                int value = sales[quarter][brand];
                totals[brand] += value;

                if (value < min[brand]){
                    min[brand] = value;
                }
                if (value > max[brand]){
                    max[brand] = value;
                }
            }
            averages[brand] = (double) totals[brand] / sales.length;
        }
        //WRITE DATA TO FILE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write("ULTIMATE SHOE SALES");
            writer.newLine();
            writer.write("-------------------------------------------------------------");
            writer.newLine();

            writer.write(String.format("%-15s", "QUARTER"));
            for (String brand : brands) {
                writer.write(String.format("%-15s", brand));
            }
            writer.newLine();
            writer.write("-------------------------------------------------------------------");
            writer.newLine();

            for (int quarter = 0; quarter < sales.length; quarter++) {
                writer.write(String.format("%-15s", quarters[quarter]));
                for (int brand = 0; brand < sales[quarter].length; brand++) {
                    writer.write(String.format("%-15d", sales[quarter][brand]));
                }
                writer.newLine();
            }

            writer.write("-------------------------------------------------------------------");
            writer.newLine();
            writer.write(String.format("%-15s", "TOTAL:"));
            for (int totalValue : totals) {
                writer.write(String.format("%-15d", totalValue));
            }
            writer.newLine();

            writer.write(String.format("%-15s", "AVERAGE:"));
            for (double average : averages) {
                writer.write(String.format("%-15.1f", average));
            }
            writer.newLine();

            writer.write(String.format("%-15s", "MIN:"));
            for (int minValue : min) {
                writer.write(String.format("%-15d", minValue));
            }
            writer.newLine();

            writer.write(String.format("%-15s", "MAX:"));
            for (int maxValue : max) {
                writer.write(String.format("%-15d", maxValue));
            }
            writer.newLine();
            writer.write("-------------------------------------------------------------------");
            writer.newLine();

            System.out.println("Data successfully written to " + fileName);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // READ DATA FROM FILE
        System.out.println();
        System.out.println("READING DATA FROM FILE");
        System.out.println("===============================================================");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}