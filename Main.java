package GUI1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/GUI1/Stacje.txt"));
            List<String> listaStacji = new ArrayList<>();
            while (scanner.hasNextLine()) {
                listaStacji.add(scanner.nextLine());
            }
            scanner.close();

            Diagram diagram = new Diagram();

            Random random = new Random();
            Set<String> usedNames = new HashSet<>(); // Śledzi uzywane nazwy
            for (int i = 0; i < 4; i++) {
                String name;
                do {
                    name = listaStacji.get(random.nextInt(listaStacji.size()));
                } while (usedNames.contains(name)); // generate new name until it is unique
                usedNames.add(name); // dodaje nazwe do uzywanych nazw
                double x = random.nextDouble() * 10;
                double y = random.nextDouble() * 10;
                diagram.addNode(name, x, y);
            }
            List<Node> nodes = diagram.getNodes();
            for (int i = 0; i < nodes.size(); i++) {
                Node node1 = nodes.get(i);
                for(int j = i + 1; j < nodes.size(); j++){
                    Node node2 = nodes.get(j);
                        diagram.addPolaczenia(node1, node2);
                    }
                }

            for(Node node : diagram.getNodes()){
                System.out.println("Nazwa Stacji: "+ node.getName() + ", x: " + node.getX() + ", y: "  + node.getY());
            }
            for(Polaczenia polaczenia : diagram.getPolaczenias()){
                System.out.println("Polaczenie między " + polaczenia.getNode1().getName() + " i " + polaczenia.getNode2().getName());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku ");
        }
    }

    static class Diagram {
        private List<Node> nodes;
        private List<Polaczenia> polaczenias;

        public Diagram() {
            nodes = new ArrayList<>();
            polaczenias = new ArrayList<>();
        }

        public void addNode(String name, double x, double y) {
            Node node = new Node(name, x, y);
            nodes.add(node);
        }

        public void addPolaczenia(Node node1, Node node2) {
            Polaczenia polaczenie = new Polaczenia(node1, node2);
            polaczenias.add(polaczenie);
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public List<Polaczenia> getPolaczenias() {
            return polaczenias;
        }
    }

    static class Node {
        private String name;
        private double x;
        private double y;

        public Node(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    static class Polaczenia {
        private Node node1;
        private Node node2;

        public Polaczenia(Node node1, Node node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        public Node getNode1() {
            return node1;
        }

        public Node getNode2() {
            return node2;
        }
    }
}

