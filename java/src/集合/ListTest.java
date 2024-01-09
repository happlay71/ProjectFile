package 集合;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    @Test
    public void Test1() {
        ArrayList list = new ArrayList();

        list.add(123);
        list.add(456);
        list.add("AA");

        System.out.println(list);

        // void add(int index, Object ele)
        list.add(1, "BB");
        System.out.println(list);

        // boolean addAll(int index, Collection eles)
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);  // 结尾添加
        System.out.println(list);

        // Object get(int index)
        System.out.println(list.get(1));
    }

    @Test
    public void Test2() {
        ArrayList list = new ArrayList();

        list.add(123);
        list.add(456);
        list.add("AA");

        // int indexOf(Object obj)
        int index = list.indexOf(456);
        System.out.println(index);

        // int lastIndexOf(Object obj)
        System.out.println(list.lastIndexOf(456));

        // Object remove(int index)
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);

        // Object set(int index, Object ele)
        list.set(1, "CC");
        System.out.println(list);

        // List subList(int fromIndex, int toIndex)
        List subList = list.subList(0, 2);
        System.out.println(subList);

    }

    // 遍历
    @Test
    public void Test3() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

        // 方式一：iterator迭代器
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 方式二：增强for循环
        for (Object object : list) {
            System.out.println(object);
        }

        // 方式三：普通for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
