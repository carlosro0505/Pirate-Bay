import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {

  

    public static List<Data> parseData() throws FileNotFoundException {
                StringBuilder content = new StringBuilder();
        File file = new File("res/data.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine()).append("\n");
        }

        scanner.close();
        List<Data> dataList = new ArrayList<>();

        // Split the raw data into lines
        String[] lines = content.toString().split("\n");

        for (String line : lines) {
            // Split each line into individual components
            String[] components = line.split(" ");

            // Create a new Data object and add it to the list
            Data data = new Data(
                    components[0], // category
                    components[1], // name
                    components[2], // date
                    components[3], // size
                    components[4], // SE
                    components[5], // LE
                    components[6]  // uploadBy
            );

            // Set additional properties
            data.setDescription(components[7]);
            data.setID(Integer.parseInt(components[8]));

            // Add the Data object to the list
            dataList.add(data);
        }

        return dataList;
    }
}
