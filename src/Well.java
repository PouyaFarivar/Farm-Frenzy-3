public class Well extends GamePlay
{
    final int capacity = 5 ;
    private int amount_left;
    final int operationTime = 3;

    Well(){
        amount_left = 5 ;
    }

    public void fill(){
        amount_left = 5  ;
    }
    public void use(){
        amount_left = amount_left - 1 ;
    }

    public int getAmount_left() {
        return amount_left;
    }
}
