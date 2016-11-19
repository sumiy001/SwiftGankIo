package com.rdc.sumiy.swiftgankio.utils;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sumiy on 2016/8/13.
 */
public class CallableContainer {
    public  static  Map<Integer,Callable>  callableContainer = new HashMap();
    public  static Callable getCallabble(Integer key){
        Callable callable = callableContainer.get(key);
        return callable;
    }
    public static void addCallabble(Integer key,Callable callable){
            callableContainer.put(key, callable);
    }
}
