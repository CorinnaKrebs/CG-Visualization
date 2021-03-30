package com.threedimensionalloadingcvrp.view.components;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Axes to create Coordinate Planes.
 */
@EqualsAndHashCode(callSuper = true)
public class Axes extends Group {
    /** Current Dimension of Axes. */
    @Getter
    private static int dim;

    /** The Left Plane. */
    private static Box left;

    /** The Back/Right Plane. */
    private static Box back;

    /** The Ground Plane. */
    private static Box ground;

    /**
     * Instantiates new Axes.
     *
     * @param dim the dimension of the plane.
     */
    public Axes(final int dim) {
        super();
        createAxes(dim);

        // Create light transparent gray material
        PhongMaterial material = new PhongMaterial();
        material.setSpecularColor(new Color(0.75, 0.75, 0.75, 0.25));
        material.setDiffuseColor(new Color(0.75, 0.75, 0.75, 0.75));

        left.setMaterial(material);
        back.setMaterial(material);
        ground.setMaterial(material);

        getChildren().add(left);
        getChildren().add(back);
        getChildren().add(ground);

    }

    /**
     * Create axes.
     *
     * @param max the maximum border of an item.
     */
    public static void createAxes(int max) {
        dim = (int) (max * 1.25);

        // Left Plane
        left = new Box(0, dim, dim);
        left.setTranslateX(0);
        left.setTranslateY(-dim * 0.5);
        left.setTranslateZ(dim * 0.5);

        // Back Plane
        back = new Box(dim, dim, 0);
        back.setTranslateX(-dim * 0.5);
        back.setTranslateY(-dim * 0.5);
        back.setTranslateZ(0);

        // Ground Plane
        ground = new Box(dim, 0, dim);
        ground.setTranslateX(-dim * 0.5);
        ground.setTranslateY(0);
        ground.setTranslateZ(dim * 0.5);
    }
}
