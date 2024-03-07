package 集合;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsTest {
    @Test
    public void test1() {
        List list = new ArrayList<>();
        list.add(123);
        list.add(43);
        list.add(75);
        list.add(-23);
        list.add(12);
        list.add(0);

        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);
    }
}
