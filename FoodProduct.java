import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FoodProduct extends Product {
    List<String> territorialUnits;
    List<Double[]> pricesList;

    private FoodProduct(String name, List<String> territorialUnits, List<Double[]> pricesList) {
        setName(name);
        this.territorialUnits = territorialUnits;
        this.pricesList = pricesList;
    }

    public static FoodProduct fromCsv(Path path) {
        String name;
        List<String> territorialUnits = new ArrayList<>();
        List<Double[]> prices = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine(); // Odczytujemy nazwę produktu
            scanner.nextLine(); // Pomijamy nagłówek tabeli

            // Odczytujemy kolejne linie, parsujemy nazwę prowincji i ceny dla kolejnych miesięcy
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                // Pierwszy element to nazwa prowincji
                territorialUnits.add(parts[0]);

                // Parsowanie cen dla kolejnych miesięcy
                Double[] monthlyPrices = new Double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    monthlyPrices[i - 1] = Double.parseDouble(parts[i].replace(",","."));
                }
                prices.add(monthlyPrices);
            }

            scanner.close();

            return new FoodProduct(name, territorialUnits, prices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getPrice(int year, int month) {
        if (year < 2010 || year > 2022 || month < 1 || month > 12) {
            throw new IndexOutOfBoundsException("Year or month out of range");
        }

        int index = (year - 2010) * 12 + (month - 1);

        double totalPrice = 0;
        int count = 0;
        for (Double[] prices : pricesList) {
            if (prices[index] != null) {
                totalPrice += prices[index];
                count++;
            }
        }
        if (count == 0) {
            throw new IndexOutOfBoundsException("No data available for the specified month and year");
        }
        return totalPrice / count;
    }

    public double getPrice(int year, int month, String province) {
        if (year < 2010 || year > 2022 || month < 1 || month > 12) {
            throw new IndexOutOfBoundsException("Year or month out of range");
        }

        int index = (year - 2010) * 12 + (month - 1);

        int provinceIndex = territorialUnits.indexOf(province.toUpperCase());
        if (provinceIndex == -1) {
            throw new IndexOutOfBoundsException("Province not found");
        }

        Double[] prices = pricesList.get(provinceIndex);
        if (prices[index] == null) {
            throw new IndexOutOfBoundsException("No data available for the specified month and year");
        }
        return prices[index];
    }
}
