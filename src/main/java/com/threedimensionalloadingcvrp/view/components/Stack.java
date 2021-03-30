package com.threedimensionalloadingcvrp.view.components;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Stack containing all 3D shapes.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Stack extends Group {
    /** Current x-Rotation. */
    private Rotate rx;

    /** Current y-Rotation. */
    private Rotate ry;

    /** Current z-Rotation. */
    private Rotate rz;

    /** Current Scale Factor. */
    private Scale scale;

    /**
     * Instantiates a new Stack.
     */
    public Stack() {
        super();
        rx = new Rotate();
        ry = new Rotate();
        rz = new Rotate();
        rx.setAxis(Rotate.X_AXIS);
        ry.setAxis(Rotate.Y_AXIS);
        rz.setAxis(Rotate.Z_AXIS);
        scale = new Scale();
        getTransforms().addAll(rx, rz, ry, scale);
    }

    /**
     * Reset the 3D area.
     *
     * @param width  the width
     * @param height the height
     */
    public void reset(double width, double height) {
        this.setTranslateX(width/3);
        this.setTranslateY(height/3);
        this.rx.setAngle(30.0);
        this.ry.setAngle(-140);
        this.rz.setAngle(0);

        this.scale.setX(4.0);
        this.scale.setY(4.0);
        this.scale.setZ(4.0);
    }
}
