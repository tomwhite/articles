import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Run {
  
  private static Random r = new Random();
  
  static class CounterNode {
    int count;
    
    public void increment() {
      count++;
    }
    
  }
  
  public static void run(int numberOfNodes, int numberOfReplicas, int numberOfItems, int repeat) throws NoSuchAlgorithmException {
    for (int i = 0; i < repeat; i++) {
      run(numberOfNodes, numberOfReplicas, numberOfItems);
    }
  }
  
  public static void run(int numberOfNodes, int numberOfReplicas, int numberOfItems) throws NoSuchAlgorithmException {
    
    List<CounterNode> nodes = new ArrayList<CounterNode>();
    for (int i = 0; i < numberOfNodes; i++) {
      nodes.add(new CounterNode());
    }
    
    ConsistentHash<CounterNode> consistentHash =
      new ConsistentHash<CounterNode>(new Md5HashFunction(), numberOfReplicas, nodes);
    
    double sd = run(nodes, consistentHash, numberOfItems);

    System.out.printf("%s,%s,%s,%s\n", numberOfNodes, numberOfReplicas, numberOfItems, sd);
  }
  
  public static double run(List<CounterNode> nodes, ConsistentHash<CounterNode> consistentHash, int numberOfItems) {
    
    int numberOfNodes = nodes.size();
    
    int itemsPerNode = numberOfItems / numberOfNodes;
    
    for (int i = 0; i < numberOfItems; i++) {
      consistentHash.get(r.nextLong()).increment();
    }
    
    double x = 0;
    double x2 = 0;
    for (CounterNode node : nodes) {
      x += node.count;
      x2 += node.count * node.count;
    }
    
    x = x/numberOfNodes;
    x2 = x2/numberOfNodes;
    
    double sd = Math.sqrt(x2 - x * x);
    return 100 * sd / itemsPerNode;
  }
  
  public static void main(String[] args) throws Exception {
    int numberOfNodes = 10;
    int numberOfItems = 10000;
    int repeat = 10;
    
    System.out.printf("%s,%s,%s,%s\n", "nodes", "replicas", "items", "sd");
    
    run(numberOfNodes, 1, numberOfItems, repeat);
    run(numberOfNodes, 2, numberOfItems, repeat);
    run(numberOfNodes, 5, numberOfItems, repeat);
    run(numberOfNodes, 10, numberOfItems, repeat);
    run(numberOfNodes, 20, numberOfItems, repeat);
    run(numberOfNodes, 50, numberOfItems, repeat);
    run(numberOfNodes, 100, numberOfItems, repeat);
    run(numberOfNodes, 200, numberOfItems, repeat);    
    run(numberOfNodes, 500, numberOfItems, repeat);

  }
}
