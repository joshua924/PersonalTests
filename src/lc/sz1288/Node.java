package lc.sz1288;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Node {
    int val;
    List<Node> children;

    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}
