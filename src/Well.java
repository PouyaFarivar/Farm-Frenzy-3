public class Well extends GamePlay
{
    final int capacity = 5 ;
    private int amount_left;
    final int operationTime = 3;
    int timer ;// - 1 = not filling

    Well(){
        amount_left = 5 ;
        timer = -1 ;
    }

    public void fill(){
        timer = 0 ;
    }
    public void use(){
        amount_left = amount_left - 1 ;
    }

    public int getAmount_left() {
        return amount_left;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setAmount_left(int amount_left) {
        this.amount_left = amount_left;
    }
}
