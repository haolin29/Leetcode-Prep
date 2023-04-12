package company.FB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {

    class Tuple {
        String parent;
        double k;

        public Tuple(String parent, double k) {
            this.parent = parent;
            this.k = k;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Tuple> parents = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double k = values[i];

            if (!parents.containsKey(a) && !parents.containsKey(b)) {
                parents.put(a, new Tuple(b, k));
                parents.put(b, new Tuple(b,1.0));
            } else if (!parents.containsKey(a)) {
                parents.put(a, new Tuple(b, k));
            } else if (!parents.containsKey(b)) {
                parents.put(b, new Tuple(a, 1 / k));
            } else {
                Tuple parentA = find(a, parents);
                Tuple parentB = find(b, parents);

                if (parentA != parentB) {
                    parentA.parent = parentB.parent;
                    parentA.k *= (parentB.k * k);
                }
            }
        }

        double[] result = new double[queries.size()];
        int index = 0;
        for (List<String> query : queries) {
            String a = query.get(0);
            String b = query.get(1);

            if (!parents.containsKey(a) || !parents.containsKey(b)) {
                result[index] = -1.0;
                continue;
            }

            Tuple parentA = find(a, parents); // a / rA
            Tuple parentB = find(b, parents); // b / rB

            if (!parentA.parent.equals(parentB.parent)) {
                result[index] = -1.0;
            } else {
                result[index] = parentA.k / parentB.k;
            }

            index++;
        }

        return result;

    }

    private Tuple find(final String key, final Map<String, Tuple> parents) {
        if (!parents.get(key).parent.equals(key)) {
            Tuple tuple = find(parents.get(key).parent, parents);
            parents.get(key).parent = tuple.parent;
            parents.get(key).k *= tuple.k;
        }

        return parents.get(key);
    }
}
