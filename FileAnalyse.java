import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyse {

    /**
     * Get the regular expression of the actor's name
     */
    private static Pattern pattern = Pattern.compile(", \"name\": \"([a-zA-Z0-9\\s]+)\",");

    /**
     * Parse the actor's name from the file and construct the map
     *
     * @param fileName file's name
     * @throws IOException
     */
    public Graph analyseFileAndBuildGraph(String fileName) throws IOException {
        Graph graph = new AdjacencyGraphImpl();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while ((line = reader.readLine()) != null && line.trim().length() > 0) {
            List<String> actors = getActorFromLine(line);
            for (int i = 0; i < actors.size() - 1; i++) {
                String sourceName = actors.get(i);
                for (int j = 1; j < actors.size(); j++) {
                    String targetName = actors.get(j);
                    graph.addNodePair(sourceName, targetName);
                }
            }
        }
        return graph;
    }

    /**
     * Take out the actor's name in a row
     *
     * @param line line
     * @return actor's name
     */
    private List<String> getActorFromLine(String line) {
        List<String> actors = new ArrayList<>();
        line = line.replace("\"\"", "\"");
        line = line.replace("\"[", "[");
        line = line.replace("]\"", "]");
        line = line.substring(line.indexOf("["), line.indexOf("]"));
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            actors.add(matcher.group(1).trim());
        }
        return actors;
    }






}
