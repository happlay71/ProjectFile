package 集合;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {
    @Test
    public void test1() {
        TreeSet set = new TreeSet();  // 不能添加不同类的对象

        set.add(34);
        set.add(-34);
        set.add(43);
        set.add(11);
        set.add(8);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void Test2() {
//        Comparator com = new Comparator() {
//            // 按照年龄从小到大排列
//            @Override
//            public int compare(Object o1, Object o2) {
//                if (o1 instanceof User && o2 instanceof User) {
//                    User u1 = (User)o1;
//                    User u2 = (User)o2;
//                }
//                return 0;
//            }
//        };

//        TreeSet set = new TreeSet(com);
    }
}
