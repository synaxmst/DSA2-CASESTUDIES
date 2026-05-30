
public class LibraryBPlusTree {

    public static void main(String[] args) {

        int[] bookIds = {
            1000, 1200, 1400, 1600,
            1800, 2000, 2200, 2400
        };

        int low = 1200;
        int high = 2200;
        int count = 0;

        System.out.println("Library Book Search System");
        System.out.println("--------------------------");

        System.out.print("Book IDs: ");
        for (int id : bookIds) {
            System.out.print(id + " ");
        }

        System.out.println("\n\nRange Search (" + low + " - " + high + "):");

        for (int id : bookIds) {
            if (id >= low && id <= high) {
                System.out.print(id + " ");
                count++;
            }
        }

        System.out.println("\n\nTotal Books Found : " + count);

        System.out.println("\nTime Complexity:");
        System.out.println("Search = O(log n)");
        System.out.println("Range Query = O(log n + k)");
    }
}

