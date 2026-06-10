package demeter;

import demeter.bean.Customer;

public class BadOrderService {

    void process(Customer customer) {
        String city = customer.getAddress().getCity();
        System.out.println(city);
    }
}
