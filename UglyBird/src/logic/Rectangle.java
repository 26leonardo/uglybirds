package logic;

public class Rectangle {
    protected int x;        //left high corner
    protected int y;
    protected int width;
    protected int height;

    protected Rectangle(){
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }
    
    protected Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected boolean intersects(Rectangle rect){
        if((Math.abs(((x + width)-(rect.x + rect.width)))) < width){
            if((Math.abs(((y + height)-(rect.y + rect.height)))) < height){
                return true;
            }
        }
        return false;
    }
}
