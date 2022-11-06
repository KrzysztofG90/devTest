package model;

import java.util.List;

public class Graph {
    private List<Integer> vertices;

    public Graph(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Graph{");
        sb.append("vertices=").append(vertices);
        sb.append('}');
        return sb.toString();
    }
}
