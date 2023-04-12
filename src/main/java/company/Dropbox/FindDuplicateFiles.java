package company.Dropbox;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateFiles {

    // path: root path
    public List<List<String>> findDuplicates(String root) throws Exception{
        List<List<String>> res = new ArrayList<>();

        if (root == null || root.length() == 0) return res;

        List<String> paths = getAllPaths(root);
        Map<String, List<String>> groups = new HashMap<>();

        for (String path : paths) {
            File file = new File(path);
            String hash = getHash(file, "MD5");
            groups.putIfAbsent(hash, new ArrayList<>());
            groups.get(hash).add(path);
        }

        for (List<String> group : groups.values()) {
            if (group.size() > 1) {
                res.add(group);
            }
        }

        return res;
    }

    private String getHash(final File file, final String algo) {
        return null;
    }

    private List<String> getAllPaths(final String root) {
        List<String> res = new ArrayList<>();

        Deque<String> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            String cur = stack.pop();
            File curFile = new File(cur);
            if (curFile.isFile()) {
                res.add(cur);
            } else if (curFile.isDirectory()) {
                String[] list = curFile.list();
                for (String dir : list) {
                    stack.push(cur + "/" + dir);
                }
            }
        }

        return res;
    }
}
