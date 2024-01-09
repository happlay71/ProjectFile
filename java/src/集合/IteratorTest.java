package 集合;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class IteratorTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("jerry"));
        coll.add(false);

        Iterator iterator = coll.iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // iterator中的remove()
    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("jerry"));
        coll.add(false);

        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            if ("jerry".equals(obj)) {
                iterator.remove();
            }
        }
    }
}
