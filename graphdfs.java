// Nama : Laurentia Maria Olivianica
// Nim  : 052666739
// Prodi: Sistem Informasi

import java.util.*;

public class graphdfs {
    private int vertices; // jumlah node
    private LinkedList<Integer>[] adj; // adjacency list

    @SuppressWarnings("unchecked")
    public graphdfs(int v) {
        this.vertices = v;
        this.adj = (LinkedList<Integer>[]) new LinkedList[v]; // perbaikan generics

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Menambahkan edge antar node (arah satu arah)
    public void addEdge(int v, int w) {
        if (v < 0 || v >= vertices || w < 0 || w >= vertices) {
            System.out.println("Error: indeks node di luar batas.");
            return;
        }
        adj[v].add(w);
    }

    // DFS rekursif dengan pencarian target n
    public boolean DFS(int current, int target, boolean[] visited, List<Integer> path) {
        visited[current] = true;
        path.add(current);

        if (current == target) {
            return true; // target ditemukan
        }

        for (int neighbor : adj[current]) {
            if (!visited[neighbor]) {
                if (DFS(neighbor, target, visited, path)) {
                    return true;
                }
            }
        }

        // backtracking
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {

        graphdfs graph = new graphdfs(8);

        // Menambahkan edge sesuai struktur
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(4, 7);

        int target = 7;        // node a8
        int startNode = 0;     // node a1
        boolean[] visited = new boolean[8];
        List<Integer> path = new ArrayList<>();

        System.out.println("Proses pencarian DFS untuk node a8 mulai dari a1:");

        if (graph.DFS(startNode, target, visited, path)) {
            System.out.print("Node a8 ditemukan! Jalur: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print("a" + (path.get(i) + 1));
                if (i < path.size() - 1) System.out.print(" -> ");
            }
            System.out.println();
        } else {
            System.out.println("Node a8 tidak ditemukan.");
        }
    }
}
