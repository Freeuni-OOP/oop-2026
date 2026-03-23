import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<String> list = ListFactory.getList();

        list.add("1");
        list.add("2");
        System.out.println(list.get(0));
    }
}
