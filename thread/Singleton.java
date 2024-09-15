package src.com.pack.thread;

import java.io.Serializable;

// or just create enum class for Singleton
/*public enum MySingleton {
    INSTANCE;
}*/

public class Singleton implements Serializable {
    private static final long serialVersionUID = 3119105548371608200L;
    private static final Singleton singleton = new Singleton();
    private Singleton() {
        //Prevent Singleton new Reflection
        if( singleton != null ) {
            throw new InstantiationError( "Creating of this object is not allowed." );
        }
    }
    public static Singleton getInstance(){
        return singleton;
    }

    //Prevent Singleton Pattern From Cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this class is not allowed");
    }

    //Prevent Singleton Pattern From serializing and de-serializing
    protected Object readResolve() {
        return singleton;
    }

    public static void main (String[] ars) {
        Singleton s1 = new Singleton();
        Singleton s2 = new Singleton();
        /*Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();*/
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
    }
}
