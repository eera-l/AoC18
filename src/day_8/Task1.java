package day_8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 09/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task1 {

    private List<String> lines;
    private ArrayList<Node> nodes = new ArrayList<>();
    private int totalMetadata = 0;
    private Node currentNode;

    public static void main(String[] args) {

        Task1 task1 = new Task1();

        try {

            task1.lines = Files.readAllLines(new File("day_8.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (String line : task1.lines) {
            System.out.println(line);
        }

        task1.fillNodes();

    }

    private void fillNodes() {

        String[] numStrings = lines.get(0).split(" ");
        int[] numbers = new int[numStrings.length];

        for (int i = 0; i < numStrings.length; i++) {
            numbers[i] = Integer.parseInt(numStrings[i]);
        }
        int i = 0;
        while (i < numbers.length - 2) {

            if (nodes.size() > 0) {
                currentNode = nodes.get(nodes.size() - 1);
            }

            if (currentNode == null || (currentNode != null && currentNode.children > 0)) {
                currentNode = new Node(numbers[i], numbers[i + 1], i, i == 0 ? null : nodes.get(nodes.size() - 1));
                nodes.add(currentNode);
                if (currentNode.children == 0) {
                    currentNode.metadataIndex = currentNode.startIndex + 2;
                }
                i += 2;

            } else if (currentNode.children == 0) {
                int j;
                for (j = currentNode.metadataIndex; j < currentNode.metadataIndex + currentNode.metadata; j++) {
                    totalMetadata += numbers[j];
                }

                if (currentNode.parent != null) {
                    if (currentNode.parent.children > 0) {
                        currentNode.parent.children--;
                    }

                    if (currentNode.parent.children == 0) {
                        currentNode.parent.metadataIndex = j;
                    }

                    while (currentNode.parent != null && currentNode.parent.children == 0) {
                        currentNode = currentNode.parent;
                    }
                }
                nodes.remove(nodes.size() - 1);

                i = j;
            }
        }
        System.out.println(totalMetadata);
    }

    private class Node {

        private Node parent;
        private int children;
        private int metadata;
        private int startIndex;
        private int metadataIndex;

        public Node(int c, int m, int s, Node p) {
            children = c;
            metadata = m;
            startIndex = s;
            parent = p;
        }

        @Override
        public String toString() {
            return "Num of children: " + children + ", metadata: " + metadata + ", start index: " + startIndex;
        }
    }
}
