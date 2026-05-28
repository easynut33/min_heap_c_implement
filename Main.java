import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int INITIAL_CAPACITY = 4;

    static class MinHeap {
        int[] data;
        int size;
        int capacity;

        public MinHeap() {
            size = 0;
            capacity = INITIAL_CAPACITY;
            data = new int[capacity + 1];
        }

        private void resizeHeap() {
            capacity *= 2;

            int[] newData = new int[capacity + 1];

            if (size + 1 >= 0) System.arraycopy(data, 0, newData, 0, size + 1);

            data = newData;
        }

        private void swap(int a, int b) {
            int temp = data[a];
            data[a] = data[b];
            data[b] = temp;
        }

        public void push(int value) {
            if (size == capacity) resizeHeap();

            size++;

            int child = size;
            data[child] = value;

            while (child > 1) {
                int parent = child / 2;

                if (data[parent] <= data[child]) {
                    break;
                }

                swap(parent, child);
                child = parent;
            }
        }

        public int pop() {
            if (size == 0) return 0;

            int result = data[1];

            data[1] = data[size];
            size--;

            int parent = 1;

            while (parent * 2 <= size) {

                int left = parent * 2;
                int right = left + 1;
                int smaller = left;

                if (right <= size && data[right] < data[left]) {
                    smaller = right;
                }

                if (data[parent] <= data[smaller]) {
                    break;
                }

                swap(parent, smaller);
                parent = smaller;
            }

            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("""
                === Minimum Heap Program ===
                Enter a positive number to insert it into the heap.
                Enter 0 to remove and print the smallest number
                Enter the number of operations (n):
                """);

        MinHeap heap = new MinHeap();

        int n = Integer.parseInt(br.readLine());
        int value;

        for (int i = 0; i < n; i++) {
            value = Integer.parseInt(br.readLine());

            if (value == 0) System.out.println(heap.pop());
            else heap.push(value);
        }
    }
}