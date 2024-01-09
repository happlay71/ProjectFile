package 集合;

import org.junit.Test;

import java.util.*;

public class CollectionTest {
    @Test
    public void test1() {
        Collection coll = new ArrayList();
        // add(Object e)
        coll.add("AA");
        coll.add(123);
        coll.add(new Date());

        // size()
        System.out.println(coll.size());

        // addAll()
        Collection coll1 = new ArrayList();
        coll1.add("bb");
        coll1.add(234);
        coll.add(coll1);  // 用add()添加会将另一个集合作为一个整体添加到该集合中
        System.out.println(coll);

        // clear()
        coll1.clear();

        // isEmpty()
        System.out.println(coll1.isEmpty());

        // contains(Object obj)
        coll.add(new String("Tom"));
        System.out.println(coll.contains(new String("Tom")));

        // containsAll(Collection coll1)
        Collection coll2 = Arrays.asList(123);
        System.out.println(coll.containsAll(coll2));
    }

    @Test
    public void test2(){
        // remove(Object obj)
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("jerry"));
        coll.add(false);
        coll.remove(123);
        System.out.println(coll);

        // removeAll(Collection coll1)
        Collection coll1 = Arrays.asList(123, 456);
        coll.removeAll(coll1);
        System.out.println(coll);
    }

    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("jerry"));
        coll.add(false);

        // retainAll(Collection coll1)
        Collection coll1 = Arrays.asList(123, 456, 789);
        coll.retainAll(coll1);
        System.out.println(coll);
    }

    @Test
    public void test4(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("jerry"));
        coll.add(false);

        // hashCode()
        System.out.println(coll.hashCode());

        // 集合--->数组 toArray()
        Object[] arr = coll.toArray();
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        // 数组--->集合：调用Arrays类的静态方法asList()
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);
        List arr1 = Arrays.asList(123, 456);
        System.out.println(arr1);
    }
}
