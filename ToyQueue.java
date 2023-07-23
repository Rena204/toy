import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class ToyQueue {
    private PriorityQueue<Toy> toyQueue;

    public ToyQueue(String[] ids, String[] names, int[] frequencies) {
        if (ids.length < 3 || ids.length != names.length || ids.length != frequencies.length) {
            throw new IllegalArgumentException("Input arrays must have a minimum length of 3 and the same length.");
        }

        toyQueue = new PriorityQueue<>();

        for (int i = 0; i < ids.length; i++) {
            String id = ids[i];
            String name = names[i];
            int frequency = frequencies[i];

            Toy toy = new Toy(id, name, frequency);
            toyQueue.offer(toy);
        }
    }

    public void getToys(int count) {
        try (FileWriter writer = new FileWriter("output.txt", true)) {
            for (int i = 0; i < count; i++) {
                Toy toy = toyQueue.poll();
                if (toy != null) {
                    System.out.println(toy);
                    writer.write(toy.toString() + "\n");
                } else {
                    System.out.println("No more toys in the queue.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] ids = {"1", "2", "3"};
        String[] names = {"Toy 1", "Toy 2", "Toy 3"};
        int[] frequencies = {3, 1, 2};

        ToyQueue toyQueue = new ToyQueue(ids, names, frequencies);
        toyQueue.getToys(10);
    }
}

class Toy implements Comparable<Toy> {
    private String id;
    private String name;
    private int frequency;

    public Toy(String id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Toy other) {
        return Integer.compare(this.frequency, other.frequency);
    }

    @Override
    public String toString() {
        return "Toy [id=" + id + ", name=" + name + ", frequency=" + frequency + "]";
    }
}
