package utils;

import interfaces.Strategy;
import models.enums.SelectionPolicy;
import strategies.ShortestQueueStrategy;
import strategies.ShortestTimeStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class StrategyDictionary {
    static Map<SelectionPolicy, Supplier<Strategy>> map = init();

    private StrategyDictionary() {

    }

    private static Map<SelectionPolicy, Supplier<Strategy>> init() {
        var map = new HashMap<SelectionPolicy, Supplier<Strategy>>();

        map.put(SelectionPolicy.SHORTEST_TIME, ShortestTimeStrategy::new);
        map.put(SelectionPolicy.SHORTEST_QUEUE, ShortestQueueStrategy::new);

        return map;
    }

    public static Strategy get(SelectionPolicy policy) {
        var supp = map.get(policy);
        return supp.get();
    }
}
