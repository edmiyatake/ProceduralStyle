import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Five {
    public static List<Character> data = new ArrayList<>();
    public static List<String> words = new ArrayList<>();
    public static List<List<Object>> word_freq = new ArrayList<>();

    public static void main(String[] args) {
        String path_to_file = args[1];
        read_file(path_to_file);
        filter_chars_and_normalize();
        scan();
        remove_stop_words();
        frequencies();
        sort();

        int limit = Math.min(25, word_freq.size());
        for (int i = 0; i < limit; i++) {
            List<Object> tf = word_freq.get(i);
            System.out.println(tf.get(0) + " - " + tf.get(1));
        }
    }

    private static void read_file(String path_to_file) {
        try (FileReader reader = new FileReader(path_to_file)){
            int ch;
            while ((ch = reader.read()) != -1){
                data.add((char) ch);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void filter_chars_and_normalize() {
        for (int i = 0; i < data.size(); i++) {
            char c = data.get(i);
            if (!Character.isLetterOrDigit(c)) {
                data.set(i, ' ');
            } else {
                data.set(i, Character.toLowerCase(c));
            }
        }
    }

    private static void scan() {
        StringBuilder sb = new StringBuilder();
        for (char c : data) {
            sb.append(c);
        }
        String dataStr = sb.toString();

        // Split on whitespace and add to words list
        words.addAll(Arrays.asList(dataStr.split("\\s+")));
    }

    private static void remove_stop_words() {
        Set<String> stopWords = new HashSet<>();

        // Read stop words from file
        try (BufferedReader reader = new BufferedReader(new FileReader("stopwords.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                stopWords.addAll(Arrays.asList(line.split(",")));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Add single-letter words a–z
        for (char c = 'a'; c <= 'z'; c++) {
            stopWords.add(String.valueOf(c));
        }

        // Remove stop words from words list
        Iterator<String> iter = words.iterator();
        while (iter.hasNext()) {
            if (stopWords.contains(iter.next())) {
                iter.remove();
            }
        }
    }

    private static void frequencies() {
        for (String w : words) {
            boolean found = false;
            for (List<Object> pair : word_freq) {
                if (pair.get(0).equals(w)) {
                    pair.set(1, (Integer) pair.get(1) + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                word_freq.add(new ArrayList<>(Arrays.asList(w, 1)));
            }
        }
    }

    private static void sort() {
        word_freq.sort((a, b) -> Integer.compare((Integer) b.get(1), (Integer) a.get(1)));
    }
}
