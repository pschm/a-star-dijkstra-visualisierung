package json_import_avs;

import java.util.List;

public class MeshNode {

    private final int id;
    private final UnityPos pos;
    private final List<Integer> nextNodes;

    public MeshNode(int id, UnityPos pos, List<Integer> nextNodes) {
        this.id = id;
        this.pos = pos;
        this.nextNodes = nextNodes;
    }

    public int getId() {
        return id;
    }

    public UnityPos getPos() {
        return pos;
    }

    public List<Integer> getNextNodes() {
        return nextNodes;
    }

    @Override
    public String toString() {
        return id + " " + pos;
    }
}
