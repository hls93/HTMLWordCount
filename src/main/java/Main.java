import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        //using Jsoup, I am connecting to the url and parsing through the html after asking for user input
        System.out.println("Please enter a URL");
        String webPage = scanner.nextLine();

        String html = Jsoup.connect(webPage).get().html();

        Document doc = Jsoup.parse(String.valueOf(html));

        //extracting the text from the body of the web-page
        String text = doc.body().text();

        //creating a new list to show the words on the web-page and sorting it
        List<String> list = Arrays.asList(text.split(" "));
        Collections.sort(list);

        //creating a hashMap to store the word and its frequency
        HashMap<String, Integer> map = new HashMap<>();

        //looping over the list. If the word is found multiple times (contains key) we are adding 1 to the value of count, then adding it to the map. If not, count equals one.
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).toLowerCase();
            int count;
            if (map.containsKey(s)) {
                count = map.get(s) + 1;
            } else {
                count = 1;
            }

            map.put(s, count);

        }
        //now sorting the list by frequency and limiting it to print the top 25
        map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(25)
                .forEach(System.out::println);

    }
}
