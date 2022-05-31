package a2_2001040134;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;

/**
 * @overview PCFactory is a simple Factory class to create PC objects,
 *          and it has only one instance
 * @author Minh
 * 
 */
public class PCFactory {
    
    /**A single object of this class */
    @DomainConstraint(type = "PCFactory", mutable = false, optional = false)
    private static PCFactory instance;

    /**
     * @effects initialise this to be an empty object
     */
    @DOpt(type = OptType.Constructor)
    @AttrRef("instance")
    private PCFactory() {}

    /**
     * @effects getInstance for PCFactory
     */
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
    public PC createPC(String m, int y,String mn, Set<String> comps) {
        PC pc = new PC(m,y,mn,comps);
        return pc;
    }
}
