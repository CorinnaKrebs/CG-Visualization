package com.threedimensionalloadingcvrp.model;

import javafx.beans.property.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;


/**
 * The Item (3D Cuboid).
 */
public class Item extends Box {

    /** The length of the Item. */
    private final IntegerProperty l;

    /** The width of the Item. */
    private final IntegerProperty w;

    /** The height of the Item. */
    private final IntegerProperty h;

    /** The mass of the Item. */
    private final DoubleProperty mass;

    /** The x-Position of the Item. */
    private IntegerProperty x;

    /** The y-Position of the Item. */
    private IntegerProperty y;

    /** The z-Position of the Item. */
    private IntegerProperty z;

    /** The color of the Item. */
    private final Property<Color> color;


    /**
     * Instantiates a new Item.
     *
     * @param l     the length
     * @param w     the width
     * @param h     the height
     * @param mass  the mass
     * @param x     the x-Position
     * @param y     the y-Position
     * @param z     the z-Position
     * @param color the color
     */
    public Item(final int l, final int w, final int h,
                double mass, int x, int y, int z, Color color) {
        super(w, h, l);
        this.l = new SimpleIntegerProperty(l);
        this.w = new SimpleIntegerProperty(w);
        this.h = new SimpleIntegerProperty(h);
        this.mass = new SimpleDoubleProperty(mass);
        this.color = new SimpleObjectProperty<>(color);
        setColor(color);
        setPosition(x, y, z);
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(final Color color) {
        // Create and set Material
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        material.setSpecularColor(color);
        this.setMaterial(material);
    }

    /**
     * Sets position.
     *
     * @param x the x-Position
     * @param y the y-Position
     * @param z the z-Position
     */
    public void setPosition(final int x, final int y, final int z) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.z = new SimpleIntegerProperty(z);
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * Sets position.
     */
    public void setPosition() {
        setX(x.get());
        setY(y.get());
        setZ(z.get());
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(final double x) {
        this.setTranslateZ(x + this.getDepth() * 0.5);
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(final double y) {
        this.setTranslateX(-y - this.getWidth() * 0.5);
    }

    /**
     * Sets z.
     *
     * @param z the z
     */
    public void setZ(final double z) {
        this.setTranslateY(-z - this.getHeight() * 0.5);
    }


    /**
     * Gets length.
     *
     * @return the l
     */

    public int getL() {
        return l.get();
    }

    /**
     * Gets width.
     *
     * @return the w
     */
    public int getW() {
        return w.get();
    }

    /**
     * Gets height.
     *
     * @return the h
     */
    public int getH() {
        return h.get();
    }

    /**
     * Gets mass.
     *
     * @return the mass
     */
    public double getMass() {
        return mass.get();
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x.get();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y.get();
    }

    /**
     * Gets z.
     *
     * @return the z
     */
    public int getZ() {
        return z.get();
    }

    /**
     * L property integer property.
     *
     * @return the integer property
     */
// Property Getter
    public IntegerProperty lProperty() {
        return l;
    }

    /**
     * W property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty wProperty() {
        return w;
    }

    /**
     * H property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty hProperty() {
        return h;
    }

    /**
     * Mass property double property.
     *
     * @return the double property
     */
    public DoubleProperty massProperty() {
        return mass;
    }

    /**
     * X property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty xProperty() {
        return x;
    }

    /**
     * Y property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty yProperty() {
        return y;
    }

    /**
     * Z property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty zProperty() {
        return z;
    }

    /**
     * Gets color property.
     *
     * @return the color property
     */
    public Property<Color> getColorProperty() {
        return color;
    }


    /**
     * Sets length.
     *
     * @param l the l
     */
// Setter
    public void setL(final int l) {
        this.l.set(l);
        this.setDepth(l);
        setPosition();
    }

    /**
     * Sets width.
     *
     * @param w the w
     */
    public void setW(final int w) {
        this.w.set(w);
        this.setWidth(w);
        setPosition();
    }

    /**
     * Sets height.
     *
     * @param h the h
     */
    public void setH(final int h) {
        this.h.set(h);
        this.setHeight(h);
        setPosition();
    }

    /**
     * Sets mass.
     *
     * @param mass the mass
     */
    public void setMass(final double mass) {
        this.mass.set(mass);
    }
}
