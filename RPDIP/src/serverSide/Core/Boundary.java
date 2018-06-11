package serverSide.Core;

/**
 * Class Boundary
 *
 * @author Felipe Akrios
 * @since 25/10/2014
 *
 * @version 1.0
 */
public class Boundary {

    /* Variables */
    private double min;
    private double max;
    private double value;

    /**
     * Main classifier
     * @param min
     * @param max
     */
    public Boundary(double min, double max) {
        this(min, max, Math.abs((min - max) / 2));
    }

    /**
     * Designated Constructor
     * @param min
     * @param max
     * @param value
     */
    public Boundary(double min, double max, double value) {
        super();
        this.min = min;
        this.max = max;
        this.value = value;
    }

    /**
     * GET of value
     * @return 
     */
    public double getValue() {
        return value;
    }

    /**
     * SET of value
     * @param value
     */
    public void setValue(double value) {
        if (value > max) {
            this.value = max;
        } else if (value < min) {
            this.value = min;
        } else {
            this.value = value;
        }
    }

    /**
     * GET of min
     * @return 
     */
    public double getMin() {
        return min;
    }

    /**
     * GET of max
     * @return 
     */
    public double getMax() {
        return max;
    }

    @Override
    public String toString() {
        return this.getValue() + "";
    }
}
