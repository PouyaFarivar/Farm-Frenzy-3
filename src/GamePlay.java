public class GamePlay
{
    private int x;
    private int y;

    GamePlay(int x , int y){
        this.x = x ;
        this.y = y ;
    }

    GamePlay(){}

    public void updateLocation(int x, int y){
        this.x = x ;
        this.y = y ;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
