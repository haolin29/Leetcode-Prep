package airbnb;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {

    // create("/a",1)
    // get("/a") 得到 1
    // create("/a/b",2)
    // get("/a/b") 得到 2
    // create("/c/d",1) Error，因为它的上一级“/c”并不存在
    // get("/c") //Error,因为“/c”不存在

    Map<String, Integer> pathMap = new HashMap<>();
    Map<String, Runnable> callbackMap = new HashMap<>();

    public FileSystem() {
        // root
        pathMap.put("", 0);
    }

    public boolean create(String path, int val) {
        if (pathMap.containsKey(path)) {
            return false;
        }

        // check parent
        int lastSlash = path.lastIndexOf("/");
        if (!pathMap.containsKey(path.substring(0, lastSlash))) {
            return false;
        }

        pathMap.put(path, val);
        return true;
    }

    // nullable
    public Integer get(String path) {
        return pathMap.get(path);
    }

    // update
    public boolean set(String path, int val) {
        if (!pathMap.containsKey(path)) {
            return false;
        }

        pathMap.put(path, val);

        // run callback
        String curPath = path;

        while (curPath.length() > 0) {
            if (callbackMap.containsKey(curPath)) {
                callbackMap.get(curPath).run();
            }

            curPath = curPath.substring(0, curPath.lastIndexOf("/"));
        }
        return true;
    }

    public boolean watch(String path, Runnable trigger) {
        if (!pathMap.containsKey(path)) {
            return false;
        }

        callbackMap.put(path, trigger);
        return true;
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.create("/a", 1));
        System.out.println(fs.get("/a"));
        System.out.println(fs.create("/a/b", 2));
        System.out.println(fs.watch("/a/b", () -> System.out.println("Hello, ab")));
        System.out.println(fs.watch("/a", () -> System.out.println("Hello, a")));
        System.out.println(fs.set("/a/b", 3));
        System.out.println(fs.create("/c/d", 4));
    }
}
