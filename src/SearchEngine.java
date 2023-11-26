import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    public static List<Data> search(List<Data> dataList, String searchTerm) {
        List<Data> searchResults = new ArrayList<>();

        for (Data data : dataList) {
            if (containsIgnoreCase(data.getName(), searchTerm) ||
                containsIgnoreCase(data.getDate(), searchTerm) ||
                containsIgnoreCase(data.getUploadBy(), searchTerm)) {
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

    private static boolean containsIgnoreCase(String str, String searchTerm) {
        return str.toLowerCase().contains(searchTerm.toLowerCase());
    }

    /*// Example usage:
    public static void main(String[] args) {
        List<Data> dataList = FileParser.parseData();
        List<Data> searchResults = search(dataList, "your_search_term");

        // Process the search results as needed
        for (Data result : searchResults) {
            System.out.println(result.getName());
            // Add more fields as needed
        }
    } */
}
