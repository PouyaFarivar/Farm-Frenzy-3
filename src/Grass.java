public class Grass extends GamePlay
{
    private int health ;

    Grass(int x  , int y ){
        super(x , y);
        health = 5 ;
    }

    void getEaten(){
        health = health - 5 ;
    }

    public int getHealth() {
        return health;
    }
}
