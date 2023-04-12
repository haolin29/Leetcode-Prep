package company.FB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ReconstructItinerary {

    public List<String> find(String[][] tickets) {
        LinkedList<String> result = new LinkedList<>();

        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new PriorityQueue<>());
            graph.get(ticket[0]).offer(ticket[1]);
        }

        dfs(result, graph, "JFK");

        return new ArrayList<>(result);
    }

    private void dfs(final LinkedList<String> result, final Map<String, PriorityQueue<String>> graph, final String from) {
        while (graph.containsKey(from) && graph.get(from).size() > 0) {
            dfs(result, graph, graph.get(from).poll());
        }

        result.addFirst(from);
    }
}
