package model;

import java.util.Collections;
import java.util.TreeMap;

public class Storage {
    public final TreeMap<Long, Bid> buyStorage = new TreeMap<>();
    public final TreeMap<Long, Bid> saleStorage = new TreeMap<>(Collections.reverseOrder());
}
