package 集合;

import org.junit.Test;

import java.util.*;

public class MapTest {

    @Test
    public void test4() {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 56);

        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Collection values = map.values();
        for (Object obj : values) {
            System.out.println(obj);
        }

        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()) {
            Object obj = iterator1.next();
            System.out.println(obj);

            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }
    }

    @Test
    public void test3() {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 56);

        System.out.println(map.get(45));

        boolean isExist = map.containsKey("BB");
        System.out.println(isExist);

        // 若多个值相同，则返回第一个
        isExist = map.containsValue(123);
        System.out.println(isExist);

        System.out.println(map.size());

        System.out.println(map.isEmpty());

        Map map1 = new HashMap();
        map1.put("AA", 123);
        map1.put(45, 123);
        map1.put("BB", 56);

        System.out.println(map.equals(map1));
    }

    @Test
    public void test2() {
        Map map = new HashMap();
        // 添加
        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 56);
        // 修改
        map.put("AA", 87);

        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("CC", 123);
        map1.put("DD", 123);

        map.putAll(map1);

        System.out.println(map);

        // 移除
        Object value = map.remove("CC");
        System.out.println(value);
        System.out.println(map);

        // 清空
        map.clear();
        System.out.println(map);
    }

    @Test
    public void  test1() {
        Map map = new HashMap();
        map.put(123, "AA");
        map.put(345, "BB");
        map.put(12, "Cc");

        System.out.println(map);
    }
}
