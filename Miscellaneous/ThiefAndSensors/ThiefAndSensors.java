import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class ThiefAndSensors {
    public static void main(String[] args) {
        new Solver().run();
    }
}

class Solver {
    private class Sensor {
        int x, y, r;

        Sensor(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    private int numberOfSensors;
    private int height, width;
    private Sensor[] sensors;
    private List<Integer>[] adj;

    public void run() {
        final Scanner scanner = new Scanner(System.in);
        numberOfSensors = scanner.nextInt();
        width = scanner.nextInt();
        height = scanner.nextInt();
        sensors = new Sensor[numberOfSensors + 1];
        adj = new ArrayList[numberOfSensors + 1];
        for (int i = 1; i <= numberOfSensors; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int r = scanner.nextInt();
            sensors[i] = new Sensor(x, y, r);
            adj[i] = new ArrayList<Integer>();
        }
        createAdjacencyGraph();
        System.out.println(isPathBlocked() ? "Trapped!" : "Escape possible!");
    }

    private void createAdjacencyGraph() {
        for (int i = 1; i <= numberOfSensors; i++) {
            for (int j = i + 1; j <= numberOfSensors; j++) {
                if (intersect(i, j)) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        for (int i = 1; i <= numberOfSensors; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adj[i].size(); j++) {
                System.out.print(adj[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    private boolean intersect(int i, int j) {
        int deltaX = Math.abs(sensors[i].x - sensors[j].x);
        int deltaY = Math.abs(sensors[i].y - sensors[j].y);
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance <= 1.0 * (sensors[i].r + sensors[j].r);
    }

    private boolean isPathBlocked() {
        List<Integer> topSensors = getTopSensors();
        System.out.println("Top: " + topSensors);
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[numberOfSensors + 1];
        for (int sensorId : topSensors) {
            queue.add(sensorId);
            visited[sensorId] = true;
        }
        while (!queue.isEmpty()) {
            int sensorId = queue.remove();
            System.out.println("Inspecting: " + sensorId);
            if (sensors[sensorId].r >= sensors[sensorId].y) {
                return true;
            }
            for (int i = 0; i < adj[sensorId].size(); i++) {
                int id = adj[sensorId].get(i);
                if (!visited[id]) {
                    queue.add(id);
                    visited[id] = true;
                }
            }
        }
        return false;
    }

    private List<Integer> getTopSensors() {
        List<Integer> topSensors = new ArrayList<Integer>();
        for (int i = 1; i <= numberOfSensors; i++) {
            if (sensors[i].y + sensors[i].r >= height) {
                topSensors.add(i);
            }
        }
        return topSensors;
    }
}
