import java.io.*;

public class AdvanceCopy {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("please give two arguments only");
            return;
        }

        String sourcePath = args[0];
        String destinationPath = args[1];

        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);

        try {
            if (!sourceFile.exists()) {
                System.out.println("source file or directory doesn't exist");
                return;
            }

            copy(sourceFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void copy(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                destination.mkdirs();
            }

            String[] files = source.list();
            if (files != null) {
                for (String file : files) {
                    File srcFile = new File(source, file);
                    File dstnFile = new File(destination, file);
                    copy(srcFile, dstnFile);
                }
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(source));
                 PrintWriter writer = new PrintWriter(destination)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
