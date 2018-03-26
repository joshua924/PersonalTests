package lc.sz1288;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateFileInFileSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<File>> duplicates = new HashMap<>();
        for (String path : paths) {
            String[] pathAndFiles = path.split(" ");
            String dir = pathAndFiles[0];
            for (int i = 1; i < pathAndFiles.length; i++) {
                String[] arr = pathAndFiles[i].split("\\(");
                String fileName = arr[0];
                String s = arr[1];
                String content = s.substring(0, s.length() - 1);
                List<File> list = duplicates.getOrDefault(content, new ArrayList<>());
                list.add(new File(dir, fileName));
                duplicates.put(content, list);
            }
        }
        return duplicates.values().stream()
                .filter(list -> list.size() > 1)
                .map(File::printFunction)
                .collect(Collectors.toList());
    }

    private static class File {
        private String path;
        private String fileName;

        private File(String path, String fileName) {
            this.path = path;
            this.fileName = fileName;
        }

        static String getFullName(File file) {
            return file.path + "/" + file.fileName;
        }

        static List<String> printFunction(List<File> files) {
            return files.stream().map(File::getFullName).collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        DuplicateFileInFileSystem df = new DuplicateFileInFileSystem();
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        System.out.println(df.findDuplicate(paths));
    }
}
