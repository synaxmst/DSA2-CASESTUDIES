
class Node {
    int score, height;
    Node left, right;

    Node(int score) {
        this.score = score;
        height = 1;
    }
}

public class QuizClashAVL {

    static int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    static int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node insert(Node node, int score) {

        if (node == null)
            return new Node(score);

        if (score < node.score)
            node.left = insert(node.left, score);
        else if (score > node.score)
            node.right = insert(node.right, score);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && score < node.left.score)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 && score > node.right.score)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 && score > node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 && score < node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.score + " ");
            inorder(root.right);
        }
    }

    static int countNodes(Node root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static void main(String[] args) {

        int scores[] = {
            820, 540, 910, 770, 880,
            460, 990, 600, 730, 950, 510
        };

        Node root = null;

        for (int s : scores)
            root = insert(root, s);

        System.out.println("QuizClash Leaderboard");
        System.out.println("---------------------");

        System.out.print("Scores in Sorted Order: ");
        inorder(root);

        System.out.println("\n\nRoot Node : " + root.score);
        System.out.println("Total Players : " + countNodes(root));
        System.out.println("Tree Height : " + root.height);
    }
}

