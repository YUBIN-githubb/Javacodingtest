
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemManager {
    private static List<ProblemData> problems;

    public static List<ProblemData> getAllProblems() {
        if (problems == null) {
            loadProblemData();
        }
        return problems;
    }

    public static ProblemData getProblemData(String title) {
        if (problems == null) {
            loadProblemData();
        }
        for (ProblemData problem : problems) {
            if (problem.getTitle().equals(title)) {
                return problem;
            }
        }
        return null;
    }

    private static void loadProblemData() {
        problems = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("problemData.csv"));
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount > 1 && lineCount < 7) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String title = parts[0];
                        String description = parts[1];
                        String input = parts[2];
                        String output = parts[3];
                        ProblemData problem = new ProblemData(title, description, input, output);
                        problems.add(problem);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveProblemData(String problemData) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("problemData.csv"));
            writer.write(problemData);
            writer.close();
            loadProblemData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getAllProblemTitles() {
        if (problems == null) {
            loadProblemData();
        }
        String[] titles = new String[problems.size()];
        for (int i = 0; i < problems.size(); i++) {
            titles[i] = problems.get(i).getTitle();
        }
        return titles;
    }

    public static String getProblemDescription(String title) {
        if (problems == null) {
            loadProblemData();
        }
        for (ProblemData problem : problems) {
            if (problem.getTitle().equals(title)) {
                return problem.getDescription();
            }
        }
        return null;
    }
}