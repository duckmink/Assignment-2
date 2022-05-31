package a2_2001040134;

/**
 * @overview PCFactory is a simple Factory class to create PC objects,
 *          and it has only one instance
 * @author Minh
 * 
 */
public class PCFactory {
    
    /**A single object of this class */
    private static PCFactory instance;

    /**
     * @effects initialise this to be an empty object
     */
    private PCFactory() {}

    public static synchronized PCFactory getInstance() {
        if (instance == null) {
            instance = new PCFactory();
        }
        return instance;
    }

    /**
     * @effects
     *          create a new PC and return it
     */
    public static PC createPC(String m, int y,String mn, Set<String> comps) {
        PC pc = new PC(m,y,mn,comps);
        return pc;
    }
}
