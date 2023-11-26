import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class SearchEngine {

    public static List<Data> search(List<Data> dataList, String searchTerm) {
        List<Data> searchResults = new ArrayList<>();

        for (Data data : dataList) {
            if (containsIgnoreCase(data.getName(), searchTerm) ||
                containsIgnoreCase(data.getDate(), searchTerm) ||
                containsIgnoreCase(data.getUploadBy(), searchTerm)||
                containsIgnoreCase(data.getCategory(), searchTerm)) {
                searchResults.add(data);
            }
        }
        return searchResults;
    }

    public static List<Data> classFilter(List<Data> dataList, String searchTerm, String filterString) {
        List<Data> searchResults = new ArrayList<>();

        for (Data data : dataList) {
            if ((containsIgnoreCase(data.getName(), searchTerm) && data.getCategory().equals(filterString) )||
                (containsIgnoreCase(data.getDate(), searchTerm) && data.getCategory().equals(filterString) ) ||
                (containsIgnoreCase(data.getUploadBy(), searchTerm) && data.getCategory().equals(filterString) )) 
                {
                searchResults.add(data);
            }
        }
        return searchResults;
    }

    public static List<Data> browseCategoriesFilter(List<Data> dataList, String filterString) {
        List<Data> searchResults = new ArrayList<>();

        for (Data data : dataList) {
            if (data.getCategory().equals(filterString )||
                data.getCategory().equals(filterString)  ||
                data.getCategory().equals(filterString) ) 
                {
                searchResults.add(data);
            }
        }
        return searchResults;
    }

    public static List<Data> browseByUser(List<Data> dataList, String userName) {
        List<Data> searchResults = new ArrayList<>();

        for (Data data : dataList) {
            if (data.getUploadBy().contains(userName)) 
                searchResults.add(data);
        }
        return searchResults;
    }

    public static List<Data> recent(List<Data> dataList) {
        List<Data> searchResults = new ArrayList<>(dataList);

        // Create a DateTimeFormatter for parsing the date strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        // Use Collections.sort with a custom comparator to sort by date
        Collections.sort(searchResults, Comparator.comparing(data -> LocalDate.parse(data.getDate(), formatter), Comparator.reverseOrder()));

        return searchResults;
    }

    public static List<Data> trending(List<Data> dataList) {
        List<Data> sortedList = new ArrayList<>(dataList);
        // Use Collections.sort with a custom comparator to sort by SE/LE ratio
        Collections.sort(sortedList, Comparator.<Data, Double>comparing(
    data -> {
        int se = Integer.parseInt(data.getSE());
        int le = Integer.parseInt(data.getLE());
        return le != 0 ? (double) se / le : 0.0;
    }, Comparator.reverseOrder()));


        return sortedList;
    }
    


    private static boolean containsIgnoreCase(String str, String searchTerm) {
        return str.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
