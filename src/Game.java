import javax.xml.namespace.QName;
import java.time.Period;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class Game {
    private Level level ;
    private int turn ;
    private int coins ;
    private LinkedList<Product> products ;
    private LinkedList<Grass> grasses ;
    private LinkedList<Workshop> workshops ;
    private LinkedList<Defender> defenders ;
    private LinkedList<Domestic> domestics ;
    private LinkedList<Predator> predators ;
    private HashMap <String , Integer> productHistory ;
    private Well well ;
    private Warehouse warehouse ;
    private Truck truck ;
    private Product [][] productMap ;
    private Grass [][] grassMap ;

    Game (Level level){
        this.level = level ;
        products = new LinkedList<>() ;
        grasses = new LinkedList<>() ;
        workshops = new LinkedList<>() ;
        defenders = new LinkedList<>() ;
        domestics = new LinkedList<>() ;
        predators = new LinkedList<>() ;
        productHistory = new HashMap<>() ;
        well = new Well() ;
        warehouse = new Warehouse() ;
        truck = new Truck() ;
        turn = 0 ;
        coins = 0 ;
        productMap = new Product[6][6] ;
        grassMap = new Grass[6][6] ;
        for (Workshop workshop : this.level.getWorkshops()){
            this.workshops.add(workshop);
        }
        for (Domestic domestic : this.level.getStartingAnimals()){
            this.domestics.add(domestic);
        }
    }

    public int buyAnimal (String name){//0 = done 1= wrong name 2 = not enough coins
        Animal animal = new Animal() ;
        Random random = new Random() ;
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        if (name.equals("cat")){
            animal = new Defender.Cat(x, y) ;
        }else if (name.equals("dog")){
            animal = new Defender.Dog(x , y) ;
        }else if (name.equals("hen")){
            animal = new Domestic.Hen(x ,y) ;
        }else if (name.equals("ostrich")){
            animal = new Domestic.Ostrich(x , y) ;
        }else if (name.equals("buffalo")){
            animal = new Domestic.Buffalo(x , y) ;
        }else {
            return 1 ;
        }
        if (animal.getPrice_for_purchase() <= coins){
            coins = coins - animal.getPrice_for_purchase() ;
            if (animal.getType() == 1){
                domestics.add((Domestic)animal);
            }else if (animal.getType() == 2){
                defenders.add((Defender) animal);
            }
            return 0 ;
        }else {
            return 2 ;
        }
    }

    public int pickup (int x , int y){// 0 = done 1 = not enough space 2 = no product in given coordinates ;
        boolean done = false ;
        for (Product product : products){
            if (product.getX() == x && product.getY() == y){
                if (product.getSize()<=warehouse.remainingSpace()) {
                    warehouse.getProducts().add(product);
                    warehouse.setCounter(warehouse.getCounter() + product.getSize());
                    products.remove(product);
                    productMap[x-1][y-1] = null;
                    productHistory.put(product.getName() , productHistory.get(product.getName()) + 1);
                }else {
                    return 1;
                }
                done = true ;
                break ;
            }
        }
        if (!done){
            for (Predator predator : predators){
                if (predator.getX() == x && predator.getY() == y){
                    if (predator.getSizeInWarehouse()<=warehouse.remainingSpace()) {
                        warehouse.getPredators().add(predator);
                        warehouse.setCounter(warehouse.getCounter() + predator.getSizeInWarehouse());
                        predators.remove(predator);
                    }else {
                        return 1 ;
                    }
                    done = true ;
                    break;
                }
            }
        }
        if (!done){
            return 2 ;
        }else {
            return 0;
        }
    }

    public int fillWell(){//0 = done 1 = not empty
        if (well.getAmount_left() == 0){
            well.fill();
            return 0 ;
        }else {
            return 1 ;
        }
    }

    public int plantGrass(int x , int y){// 0 = done 1 = coordinate not free
        if (grassMap[x-1][y-1] == null || grassMap[x-1][y-1].getHealth() == 0){
            Grass grass = new Grass(x , y);
            grasses.add(grass) ;
            grassMap[x-1][y-1] = grass ;
            return 0 ;
        }else {
            return 1 ;
        }
    }

    public int buildWorkShop(String name){// 0 = done 1 = u cant build that in this level 2 = no coins 3 = already built
        Workshop workshop = getWorkShopByName(name);
        if (workshop == null){
            return 1 ;
        } else if (workshop.getLevel() == 0){
            if (workshop.getPrice() <= coins){
                coins = coins = workshop.getPrice() ;
                workshop.setLevel(1);
                return 0 ;
            }else {
                return 2 ;
            }
        }else{
            return 3 ;
        }
    }

    public int work(String name){// 0 = done 1 = no such work shop 2 = workshop not built 3 = already working 4 = no entry product
        Workshop workshop = getWorkShopByName(name);
        if (workshop == null){
            return 1 ;
        }else if (workshop.getLevel() == 0){
            return 2 ;
        }else if (workshop.getWorking() >= 0){
            return 3 ;
        }else {
            Product product = getProductFromWareHouse(name) ;
            if (product == null){
                return 4 ;
            }else{
                workshop.setWorking(0);
                warehouse.getProducts().remove(product) ;
                warehouse.setCounter(warehouse.getCounter() - product.getSize());
                return 0 ;

            }
        }
    }

    public int buildCage(int x ,int y){// 0 = done 1 = no predator 2 = already captured
        Predator predator = getPredator(x , y);
        if (predator == null){
            return 1 ;
        }else {
            if (!predator.isCaptured()){
                predator.getCage().setStrength(predator.getCage().getStrength() + 1);
                if (predator.getCage().getStrength() == predator.getCageToStop()){
                    predator.setCaptured(true);
                }
                return 0 ;
            }else {
                return 2 ;
            }
        }

    }

    public int moveToTruckProduct (String productName){// 0 = done 1 = no such product in ware house 2 = not enough space
       Product product =  warehouse.getProduct(productName);
       if (product == null){
           return 1 ;
       }else{
           if (product.getSize()>truck.getLeftSpace()){
               return 2 ;
           }else {
               truck.getProducts().add(product);
               warehouse.getProducts().remove(product);
               truck.setCounter(truck.getCounter() + product.getSize());
               warehouse.setCounter(warehouse.getCounter() - product.getSize());
               return  0 ;
           }
       }
    }

    public int moveToTruckPredator (String predatorName){// 0 = done 1 = no such predator in ware house 2 = not enough space
        Predator predator =  warehouse.getPredator(predatorName);
        if (predator == null){
            return 1 ;
        }else{
            if (predator.getSizeInWarehouse()>truck.getLeftSpace()){
                return 2 ;
            }else {
                truck.getPredators().add(predator);
                warehouse.getPredators().remove(predator);
                truck.setCounter(truck.getCounter() + predator.getSizeInWarehouse());
                warehouse.setCounter(warehouse.getCounter() - predator.getSizeInWarehouse());
                return  0 ;
            }
        }
    }

    public int unloadFromTruckProduct(String name){// 0 = done 1 = sno such product 2 = not enough space in ware house
        Product product = truck.getProduct(name) ;
        if (product == null){
            return 1 ;
        }else {
            if (product.getSize() > warehouse.remainingSpace()){
                return 2 ;
            }else {
                warehouse.getProducts().add(product);
                truck.getProducts().remove(product);
                warehouse.setCounter(warehouse.getCounter() + product.getSize());
                truck.setCounter(truck.getCounter() + product.getSize());
                return 0 ;
            }
        }
    }

    public int unloadFromTruckPredator(String name){// 0 = done 1 = sno such product 2 = not enough space in ware house
        Predator predator = truck.getPredator(name) ;
        if (predator == null){
            return 1 ;
        }else {
            if (predator.getSizeInWarehouse() > warehouse.remainingSpace()){
                return 2 ;
            }else {
                warehouse.getPredators().add(predator);
                truck.getProducts().remove(predator);
                warehouse.setCounter(warehouse.getCounter() + predator.getSizeInWarehouse());
                truck.setCounter(truck.getCounter() + predator.getSizeInWarehouse());
                return 0 ;
            }
        }
    }

    public int truckGo (){// 0 = done 1 = empty 2 = already moving
        if (truck.getTime() == -1){
            if (truck.getCounter() > 0) {
                truck.setTime(0);
                return 0;
            }else {
                return 1 ;
            }
        }else {
            return 2 ;
        }
    }

    public int goTurn(){
        for (Domestic domestic : domestics){
            doTurnDomestic(domestic);
        }
        for (Predator predator : predators){
            doTurnPredator(predator);
        }
        for (Defender defender : defenders){
            doTurnDefender(defender) ;
        }
        for (Product product : products){
            doTurnProduct(product);
        }
        for (Workshop workshop : workshops){
            doTurnWorkShop(workshop);
        }
        doTurnTruck(truck);
        return checkWinLoss();
    }

    public void doTurnDomestic(Domestic domestic){
        //TODO
    }

    public void doTurnPredator(Predator predator){
        //TODO

    }

    public void doTurnDefender(Defender defender){
        //TODO

    }

    public void doTurnProduct(Product product){
        if (product.getTime_present() < product.getGivenTime()){
            product.setTime_present(product.getTime_present() + 1);
        }else if (product.getTime_present() == product.givenTime){
            products.remove(product);
            productMap[product.getX()-1][product.getY()-1] = null ;
        }
    }

    public void doTurnWorkShop (Workshop workshop){
        if (workshop.getLevel() > 0){
            if (workshop.getWorking() < workshop.getOperation_time()){
                workshop.setWorking(workshop.getWorking() + 1);
            }
            if (workshop.getWorking() == workshop.getOperation_time()){
                Product product = new Product() ;
                workshop.setWorking(-1);
                if (workshop.getOutputProduct().equals("flour")){
                    product = new Product.Flour() ;
                }else if (workshop.getOutputProduct().equals("fabric")){
                    product = new Product.Fabric() ;
                }else if (workshop.getOutputProduct().equals("packetMilk")){
                    product = new Product.PacketMilk() ;
                }else if (workshop.getOutputProduct().equals("bread")){
                    product = new Product.Bread() ;
                }else if (workshop.getOutputProduct().equals("clothing")){
                    product = new Product.Clothing() ;
                }else if (workshop.getOutputProduct().equals("iceCream")){
                    product = new Product.IceCream() ;
                }
                addProductToMap(product);
            }
        }
    }

    public void doTurnTruck (Truck truck){
        if (truck.getTime() >= 0){
            truck.setTime(truck.getTime() + 1);
        }
    }

    public int checkWinLoss (){// 0 = not win 1= win
        boolean  win = true ;
        if (level.getGoalCoins() > 0){
            if (coins < level.getGoalCoins()){
                win = false ;
            }
        }
        for (Map.Entry achievement : level.getAnimalAchievement().entrySet() ){
            String name = (String)achievement.getKey() ;
            int num = (int) achievement.getValue() ;
            if (getNumAnimal(name) < num){
                win = false ;
                break;
            }
        }
        for (Map.Entry achievement : level.getProductAchievement().entrySet()){
            String name = (String)achievement.getKey() ;
            int num = (int) achievement.getValue() ;
            if (productHistory.get(name) < num){
                win = false ;
                break;
            }
        }
        if (win){
            return 1 ;
        }else {
            return 0 ;
        }
    }

    public void addProductToMap(Product product){
        int x = 0 ;
        int y = 0 ;
        for (int i = 0 ; i < 6 ; i++){
            for (int j = 0 ; j < 6 ; j++){
                if (productMap[i][j] == null){
                    x = i+1 ;
                    y = j+1 ;
                    break;
                }
            }
            if (x > 0){
                break;
            }
        }
        product.setX(x);
        product.setY(y);
        products.add(product);
        productMap[x-1][y-1] = product ;
    }

    public Workshop getWorkShopByName(String name){
        for (Workshop workshop : workshops){
            if (workshop.getName().equals(name)){
                return workshop ;
            }
        }
        return null ;
    }

    Product getProductFromWareHouse(String name){
        for (Product product : warehouse.getProducts()){
            if(product.getName().equals(name)){
                return product ;
            }
        }
        return null ;
    }

    Predator getPredator(int x , int y){
        for (Predator predator : predators){
            if (predator.getX() == x && predator.getY() == y){
                return predator ;
            }
        }
        return null ;
    }
    public int getNumAnimal (String name){
        int num =0 ;
        for (Domestic domestic : domestics){
            if (domestic.getName().equals(name)){
                num = num + 1 ;
            }
        }
        return num ;
    }

    public Level getLevel() {
        return level;
    }

    public int getTurn() {
        return turn;
    }

    public int getCoins() {
        return coins;
    }
    public void walkCat()
    {
        int mindistance=100;
        int x;
        int y;
        int targetx=0;
        int targety=0;
        for(Defender d : this.defenders)
        {
            if(d.getName().equals("Cat"))
            {
                x=d.getX();
                y=d.getY();
                for(int i=0;i<6;i++)
                    for(int j=0;j<6;j++)
                    {
                        if(productMap[i][j] != null)
                        {
                            if(mindistance > abs(productMap[i][j].getX() - x) + abs(productMap[i][j].getY() - y))
                            {
                                mindistance = abs(productMap[i][j].getX() - x) + abs(productMap[i][j].getY() - y);
                                targetx=i+1;
                                targety=j+1;
                            }

                        }
                    }
                d.setX(targetx);
                d.setY(targety);
            }
        }
    }

    public void walkDomestic()
    {
        int mindistance=100;
        int x;
        int y;
        int targetx=0;
        int targety=0;
        for(Domestic d : this.domestics)
        {
                x=d.getX();
                y=d.getY();
                for(int i=0;i<6;i++)
                    for(int j=0;j<6;j++)
                    {
                        if(grassMap[i][j] != null)
                        {
                            if(mindistance > abs(grassMap[i][j].getX() - x) + abs(grassMap[i][j].getY() - y))
                            {
                                mindistance = abs(grassMap[i][j].getX() - x) + abs(grassMap[i][j].getY() - y);
                                targetx=i+1;
                                targety=j+1;
                            }

                        }
                    }
                d.setX(targetx);
                d.setY(targety);
        }
    }
}

