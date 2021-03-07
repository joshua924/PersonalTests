package lc.sz1288;

import java.util.*;

public class SerDeNaryTree {
    public String serialize(Node root) {
        List<String> list = new LinkedList<>();
        serialize(root, list);
        return String.join(",", list);
    }

    private void serialize(Node root, List<String> list) {
        if (root == null) {
            return;
        } else {
            list.add(String.valueOf(root.val));
            list.add(String.valueOf(root.children.size()));
            for (Node child : root.children) {
                serialize(child, list);
            }
        }
    }

    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] ss = data.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(ss));
        return deserialize(q);
    }

    private Node deserialize(Queue<String> q) {
        int val = Integer.parseInt(q.poll());
        int size = Integer.parseInt(q.poll());
        List<Node> children = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            children.add(deserialize(q));
        }
        return new Node(val, children);
    }

    public static void main(String[] args) {
        SerDeNaryTree sd = new SerDeNaryTree();
        Node root = new Node(1, Arrays.asList(
                new Node(3, Arrays.asList(new Node(5), new Node(6))),
                new Node(2),
                new Node(4)
        ));
        String string = sd.serialize(root);
        System.out.println(string);
        System.out.println(sd.deserialize(string));
    }
}
