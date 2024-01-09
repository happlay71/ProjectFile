package happlay.util;

import java.util.HashMap;

// 同一返回结果
public class R extends HashMap<String,Object> {
    public static R OK(){
        R r = new R();
        r.put("code",200);
        r.put("msg","成功");
        return r;
    }

    public static R OK(Object o){
        R r = R.OK();
        r.put("data",o);
        return r;
    }

    public static R Error(String msg){
        R r = new R();
        r.put("code",600);
        r.put("msg",msg);
        return r;
    }

    public static R TokenError(){
        R r = new R();
        r.put("code",2);
        r.put("msg","无token或token解析失败");
        return r;
    }
}
