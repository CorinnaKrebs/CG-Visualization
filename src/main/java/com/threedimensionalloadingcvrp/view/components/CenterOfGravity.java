package com.threedimensionalloadingcvrp.view.components;

import javafx.scene.shape.Sphere;
import lombok.EqualsAndHashCode;

/**
 * The Center of gravity.
 */
@EqualsAndHashCode(callSuper = true)
public class CenterOfGravity extends Sphere {

    /**
     * Instantiates the Sphere (Center of gravity).
     *
     * @param diameter the diameter of the sphere.
     */
    public CenterOfGravity(final int diameter) {
        super(diameter);
        setPosition(0, 0, 0);
    }

    /**
     * Sets position of the Sphere.
     *
     * @param x the x-Position
     * @param y the y-Position
     * @param z the z-Position
     */
    public void setPosition(int x, int y, int z) {
        setTranslateZ(x);
        setTranslateX(-y);
        setTranslateY(-z);
    }
}
