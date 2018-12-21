package POProject.core.utils;

public class Utils {

    public static <T> T requireNonNull(T obj){
        if(obj == null)
            throw new NullPointerException();
        return obj;
    }

}
