package airbnb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDict {

    // input: [wrt, wrf]
    // output: tf

    public String getOrder(String[] words) {
        // build graph
        // edge is order
        // every char is a node in graph
        // topo sort

        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // init map
        for (String word : words) {
            for (char c : word.toCharArray()) {
                map.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // visited edge
        Set<String> seen = new HashSet<>();

        // build graph
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            for (int j = 0; j < word1.length() && j < word2.length(); j++) {
                char from = word1.charAt(j);
                char to = word2.charAt(j);

                if (from == to) continue;

                String edge = from + to + "";

                if (!seen.contains(edge)) {
                    seen.add(edge);
                    map.get(from).add(to);
                    indegree.put(to, indegree.get(to) + 1);
                    break;
                }
            }
        }

        Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (char key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                q.offer(key);
            }
        }

        // bfs
        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);

            for (char next : map.get(c)) {
                indegree.put(next, indegree.get(next) - 1);

                if (indegree.get(next) == 0) {
                    q.offer(next);
                }
            }
        }


        // cycle
        return sb.length() == indegree.size() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        AlienDict alienDict = new AlienDict();

        String[] words = {"wtr", "wtf"};

        System.out.println(alienDict.getOrder(words));
    }
}
