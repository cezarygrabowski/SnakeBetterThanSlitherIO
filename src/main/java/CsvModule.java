import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * Implements reading and writing to csv file
 */
public class CsvModule {
    /**
     * Reads pairs of user and score from csv file
     * @param csvFile String
     * @return ArrayList
     */
    public static ArrayList<Map.Entry<String, Integer>> readScores(String csvFile){
        int i=0;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Map.Entry<String, Integer>> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] tmpScores = line.split(cvsSplitBy);
                scores.add(i, new AbstractMap.SimpleEntry<>(tmpScores[0], Integer.parseInt(tmpScores[1])));
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    /**
     * Writes pair of user and score to scv file
     * @param user User
     * @param score Integer
     */
    public static void writeToCsv(String user, Integer score){
        try {

            File file = new File("scores.csv");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            StringBuilder sb = new StringBuilder();

            sb.append(user);
            sb.append(',');
            sb.append(Integer.toString(score));
            sb.append('\n');

            fw.write(sb.toString());
            fw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
