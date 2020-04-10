package ru.l2.gameserver.geodata;


import ru.l2.commons.geometry.Shape;

public interface GeoCollision {
    Shape getShape();

    byte[][] getGeoAround();

    void setGeoAround(byte[][] geo);

    boolean isConcrete();
}