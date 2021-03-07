package lc.sz1288;

import java.util.*;
import java.util.function.Consumer;

/**
 * Design an in-memory file system to simulate the following functions:
 * <p>
 * ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this
 * directory. Your output (file and directory names together) should be in lexicographic order.
 * <p>
 * mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well
 * . This function has void return type.
 * <p>
 * addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to
 * append given content to original content. This function has void return type.
 * <p>
 * readContentFromFile: Given a file path, return its content in string format.
 */
public class FileSystem {
    private Node root = new Node();
    private Map<Node, Consumer<String>> callbacks = new HashMap<>();

    public List<String> ls(String path) {
        Node node = root;
        List<String> res = new ArrayList<>();
        if (!path.equals("/")) {
            String[] tokens = path.split("/");
            for (int i = 1; i < tokens.length; i++) {
                String token = tokens[i];
                node = node.subNodes.get(token);
            }
            if (!node.isDir) {
                res.add(tokens[tokens.length - 1]);
                return res;
            }
        }
        res.addAll(node.subNodes.keySet());
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        Node node = root;
        String[] tokens = path.split("/");
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (!node.subNodes.containsKey(token)) {
                Node dir = new Node();
                dir.isDir = true;
                node.subNodes.put(token, dir);
            }
            node = node.subNodes.get(token);
        }
    }

    public void addContentToFile(String path, String content) {
        Node node = root;
        if (callbacks.containsKey(node)) {
            callbacks.get(node).accept(path);
        }
        String[] tokens = path.split("/");
        for (int i = 1; i < tokens.length - 1; i++) {
            String token = tokens[i];
            if (!node.subNodes.containsKey(token)) {
                Node dir = new Node();
                dir.isDir = true;
                node.subNodes.put(token, dir);
            }
            node = node.subNodes.get(token);
            if (callbacks.containsKey(node)) {
                callbacks.get(node).accept(path);
            }
        }
        String fileName = tokens[tokens.length - 1];
        if (node.subNodes.containsKey(fileName)) {
            node = node.subNodes.get(fileName);
            if (callbacks.containsKey(node)) {
                callbacks.get(node).accept(path);
            }
            node.content += content;
        } else {
            Node file = new Node();
            file.content = content;
            file.isDir = false;
            node.subNodes.put(fileName, file);
        }
    }

    public String readContentFromFile(String path) {
        Node node = root;
        String[] tokens = path.split("/");
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (!node.subNodes.containsKey(token)) {
                return "";
            }
            node = node.subNodes.get(token);
        }
        return node.content;
    }

    public void watch(String path, Consumer<String> consumer) {
        Node node = root;
        String[] tokens = path.split("/");
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (!node.subNodes.containsKey(token)) {
                return;
            }
            node = node.subNodes.get(token);
        }
        callbacks.put(node, consumer);
    }

    public static class Node {
        private Map<String, Node> subNodes = new HashMap<>();
        private boolean isDir = false;
        private String content = "";
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.mkdir("/com/amazon/fno/zhoushua");
        fs.mkdir("/com/amazon/qingxiao");
        fs.watch("/com/amazon/fno/zhoushua", s -> System.out.println("Hey yo yo, you modified " + s));
        System.out.println(fs.ls("/com/amazon"));
        fs.addContentToFile("/com/amazon/fno/zhoushua/file", "zhe shi yi ge wen jian!");
        System.out.println(fs.ls("/com/amazon/fno/zhoushua"));
        System.out.println(fs.readContentFromFile("/com/amazon/fno/zhoushua/file"));
        System.out.println(fs.readContentFromFile("/com/amazon/fno/zhoushua/file2"));
    }
}
