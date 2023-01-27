package homework;

import java.util.*;

public class CustomerService {

    private final TreeMap<Customer, String> map = new TreeMap<>(new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            if (o1.getScores() > o2.getScores()) {
                return 1;
            } else if (o1.getScores() < o2.getScores()) {
                return -1;
            } else {
                return 0;
            }
        }
    });


    public Map.Entry<Customer, String> getSmallest() {
        Customer customer = new Customer(map.firstEntry().getKey().getId(), map.firstEntry().getKey().getName(),
                map.firstEntry().getKey().getScores());
        return Map.entry(customer, map.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return map.higherEntry(customer) != null ? Map.entry(new Customer(map.higherEntry(customer).getKey().getId(), map.higherEntry(customer).getKey().getName(),
                map.higherEntry(customer).getKey().getScores()), map.higherEntry(customer).getValue()) : null;
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }

}

