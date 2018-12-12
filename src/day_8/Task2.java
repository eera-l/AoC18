package day_8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 09/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task2 {

    private List<String> lines;
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Node> savedNodes = new ArrayList<>();
    private Node currentNode;
    private int rootValue;

    public static void main(String[] args) {

        Task2 task2 = new Task2();

        try {

            task2.lines = Files.readAllLines(new File("day_8.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        task2.fillNodes();

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
                savedNodes.add(currentNode);
                if (currentNode.children == 0) {
                    currentNode.metadataIndex = currentNode.startIndex + 2;

                    int j;
                    for (j = currentNode.metadataIndex; j < currentNode.metadataIndex + currentNode.metadata; j++) {
                        currentNode.totalMetadata += numbers[j];
                    }
                }
                i += 2;
            } else if (currentNode.children == 0) {
                int j;
                for (j = currentNode.metadataIndex; j < currentNode.metadataIndex + currentNode.metadata; j++) {
                    currentNode.metadataEntries.add(numbers[j]);
                }

                if (currentNode.parent != null) {

                    currentNode.parent.childrenStartIndexes.add(savedNodes.indexOf(currentNode));

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
        System.out.println(countRootValue(savedNodes.get(0)));
    }

    private int countRootValue(Node node) {

        if (node.startChildren == 0) {
            rootValue += node.totalMetadata;
        } else {

            for (int j = 0; j < node.metadataEntries.size(); j++) {

                if (node.metadataEntries.get(j) <= node.startChildren) {
                    countRootValue(savedNodes.get(node.childrenStartIndexes.get(node.metadataEntries.get(j) - 1)));
                }

            }
        }
        return rootValue;
    }

    private class Node {

        private Node parent;
        private int children;
        private int metadata;
        private int totalMetadata;
        private int startIndex;
        private int metadataIndex;
        private int startChildren;
        ArrayList<Integer> childrenStartIndexes = new ArrayList<>();
        ArrayList<Integer> metadataEntries = new ArrayList<>();

        public Node(int c, int m, int s, Node p) {
            children = c;
            metadata = m;
            startIndex = s;
            parent = p;
            startChildren = c;
        }

        @Override
        public String toString() {
            return "Num of children: " + startChildren + ", metadata: " + metadata + ", start index: " + startIndex
                    + " " + Arrays.toString(childrenStartIndexes.toArray()) + ", tot md: " + totalMetadata +
                    " " + Arrays.toString(metadataEntries.toArray());
        }
    }
}
