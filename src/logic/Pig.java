package logic;

import java.awt.geom.Rectangle2D;

public class Pig extends Rectangle2D{

    @Override
    public void setRect(double x, double y, double w, double h) {
    }

    @Override
    public int outcode(double x, double y) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getWidth() {
        // TODO Auto-generated method stub
        return 50;
    }

    @Override
    public double getHeight() {
        // TODO Auto-generated method stub
        return 50;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
