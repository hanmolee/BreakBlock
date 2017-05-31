package kcci.surfaceviewtest;

/**
 * Created by User on 2017-05-31.
 */

public class block extends Entity {

    private int durablity = 1;

    public block(int durablity) {
        super(255);
        this.durablity = durablity;
    }
}
