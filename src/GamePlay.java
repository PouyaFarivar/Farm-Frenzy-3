public class GamePlay
{
    private double x;
    private double y;

    GamePlay(int x , int y){
        this.x = x ;
        this.y = y ;
    }

    GamePlay(){}

    public void updateLocation(double x, double y){
        this.x = x ;
        this.y = y ;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
