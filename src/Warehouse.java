import java.util.ArrayList;

public class Warehouse extends GamePlay
{
    int counter;
    final int capacity=30;
    ArrayList<Product> product;
    ArrayList<Animal>  animal;
    public Warehouse(Product p,Animal a)
    {
        this.product=new ArrayList<Product>();
        this.animal=new ArrayList<Animal>();
        this.animal.add(a);
        this.product.add(p);
    }
    public void SellGoods(){}
}
