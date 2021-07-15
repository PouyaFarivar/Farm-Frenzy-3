import java.util.*;

public class Game {
    private Level level ;
    private int turn ;
    private int coins ;
    private LinkedList<Product> products ;
    private LinkedList<Workshop> workshops ;
    private LinkedList<Defender> defenders ;
    private LinkedList<Domestic> domestics ;
    private LinkedList<Predator> predators ;
    private HashMap <String , Integer> productHistory ;
    private HashMap <String , Integer> idTracker ;
    private Well well ;
    private Warehouse warehouse ;
    private Truck truck ;
    private int [][] grassMap ;

    Game (Level level){
        this.level = level ;
        products = new LinkedList<>() ;
        workshops = new LinkedList<>() ;
        defenders = new LinkedList<>() ;
        domestics = new LinkedList<>() ;
        predators = new LinkedList<>() ;
        productHistory = new HashMap<>() ;
        well = new Well() ;
        warehouse = new Warehouse() ;
        truck = new Truck() ;
        turn = 0 ;
        coins = level.getStartingCoins() ;
        grassMap = new int [6][6] ;
        idTracker = new HashMap<>() ;
        int num = 1 ;
        for (String workshopName : this.level.getWorkshops()) {
            Workshop workshop = new Workshop();
            if (workshopName.equals("flourMill")) {
                workshop = new Workshop.FlourMill(0, 0 , num);
            } else if (workshopName.equals("bakery")) {
                workshop = new Workshop.Bakery(0, 0, num);
            } else if (workshopName.equals("iceCreamShop")) {
                workshop = new Workshop.IceCreamShop(0, 0, num);
            } else if (workshopName.equals("milkPacking")) {
                workshop = new Workshop.MilkPacking(0, 0, num);
            } else if (workshopName.equals("sewingFactory")) {
                workshop = new Workshop.SewingFactory(0, 0, num);
            } else if (workshopName.equals("weavingFactory")) {
                workshop = new Workshop.WeavingFactory(0, 0, num);
            } else if (workshopName.equals("henMaker")) {
                workshop = new Workshop.HenMaker(0, 0, num);
            }
            workshops.add(workshop);
            num = num + 1 ;
        }
        for (String domesticName : this.level.getStartingAnimals()){
            Random random = new Random() ;
            Domestic domestic = new Domestic() ;
            int x = random.nextInt(6) + 1 ;
            int y = random.nextInt(6) + 1 ;
            if (domesticName.equals("hen")){
                domestic = new Domestic.Hen(x, y);
            }else if (domesticName.equals("ostrich")){
                domestic = new Domestic.Ostrich(x , y);
            }else if (domesticName.equals("buffalo")) {
                domestic = new Domestic.Buffalo(x, y);
            }
            domestics.add(domestic) ;
            updateIdTracker(domestic.getName());
            domestic.setId(idTracker.get(domestic.getName()));
        }
    }

    public int buyAnimal (String name){//0 = done 1= wrong name 2 = not enough coins
        Animal animal = new Animal() ;
        Random random = new Random() ;
        int x = random.nextInt(6) + 1;
        int y = random.nextInt(6) + 1;
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
            updateIdTracker(animal.getName());
            animal.setId(idTracker.get(animal.getName()));
            return 0 ;
        }else {
            return 2 ;
        }
    }

    public int pickup (int x , int y){// 0 = done 1 = not enough space 2 = no product in given coordinates 3 = predator not captured
        boolean done = false ;
        for (Product product : products){
            if (product.getX() == x && product.getY() == y){
                if (product.getSize()<=warehouse.remainingSpace()) {
                    warehouse.getProducts().add(product);
                    warehouse.setCounter(warehouse.getCounter() + product.getSize());
                    products.remove(product);
                    if (productHistory.containsKey(product.getName())) {
                        productHistory.put(product.getName(), productHistory.get(product.getName()) + 1);
                    }else {
                        productHistory.put(product.getName() , 1);
                    }
                }else {
                    return 1;
                }
                done = true ;
                break ;
            }
        }
        if (!done){
            for (Predator predator : predators){
                if (predator.getX() == x && predator.getY() == y) {
                    if (predator.isCaptured()) {
                        if (predator.getSizeInWarehouse() <= warehouse.remainingSpace()) {
                            warehouse.getPredators().add(predator);
                            warehouse.setCounter(warehouse.getCounter() + predator.getSizeInWarehouse());
                            predators.remove(predator);
                        } else {
                            return 1;
                        }
                        done = true;
                        break;
                    }else {
                        return 3 ;
                    }
                }
            }
        }
        if (!done){
            return 2 ;
        }else {
            return 0;
        }
    }

    public int fillWell(){//0 = done 1 = not empty 2 = already filling
        if (well.getTimer() >= 0){
            return 2 ;
        }else {
            if (well.getAmount_left() == 0) {
                well.fill();
                return 0;
            } else {
                return 1;
            }
        }
    }

    public int plantGrass(int x , int y){// 0 = done 1 = not enough water
        if (x > 6 || x < 1 || y > 6 || y < 1){
            return 2 ;
        }else {
            if (well.getAmount_left() > 0) {
                grassMap[x - 1][y - 1] = grassMap[x - 1][y - 1] + 1;
                well.setAmount_left(well.getAmount_left() - 1);
                return 0;
            } else {
                return 1;
            }
        }
    }

    public int buildWorkShop(String name){// 0 = done 1 = u cant build that in this level 2 = no coins 3 = already built
        Workshop workshop = getWorkShopByName(name);
        if (workshop == null){
            return 1 ;
        } else if (workshop.getLevel() == 0){
            if (workshop.getPrice() <= coins){
                coins = coins - workshop.getPrice() ;
                workshop.setLevel(1);
                return 0 ;
            }else {
                return 2 ;
            }
        }else{
            return 3 ;
        }
    }

    public int upgradeWorkShop (String name){// 0 = done 1 = cant build in dis level 2 = not enough coins 3 = already upgraded 4 = not level 1
        Workshop workshop = getWorkShopByName(name);
        if (workshop == null){
            return 1 ;
        } else if (workshop.getLevel() == 1){
            if (workshop.getPrice()*2 <= coins){
                coins = coins - workshop.getPrice()*2 ;
                workshop.setLevel(2);
                return 0 ;
            }else {
                return 2 ;
            }
        }else if (workshop.getLevel() == 2){
            return 3 ;
        }else {
            return 4 ;
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
            if (workshop.getLevel() == 1) {
                Product product = getProductFromWareHouse(workshop.getEntryProduct());
                if (product == null) {
                    return 4;
                } else {
                    workshop.setWorking(0);
                    workshop.setNumInside(1);
                    warehouse.getProducts().remove(product);
                    warehouse.setCounter(warehouse.getCounter() - product.getSize());
                    return 0;
                }
            }else{
                if (findNumProductWarehouse(workshop.getEntryProduct()) == 0){
                    return 4 ;
                }else if (findNumProductWarehouse(workshop.getEntryProduct()) == 1){
                    Product product = getProductFromWareHouse(workshop.getEntryProduct());
                    products.remove(product);
                    warehouse.setCounter(warehouse.getCounter() - product.getSize());
                    workshop.setWorking(0);
                    workshop.setNumInside(1);
                    return 0 ;
                }else{
                    Product product = getProductFromWareHouse(workshop.getEntryProduct());
                    products.remove(product);
                    warehouse.setCounter(warehouse.getCounter() - product.getSize());
                    product = getProductFromWareHouse(workshop.getEntryProduct());
                    products.remove(product);
                    warehouse.setCounter(warehouse.getCounter() - product.getSize());
                    workshop.setWorking(0);
                    workshop.setNumInside(2);
                    return 0 ;
                }
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
                    predator.setTime_captured(0);
                }
                return 0 ;
            }else {
                return 2 ;
            }
        }

    }

    public int moveToTruckProduct (String productName){// 0 = done 1 = no such product in ware house 2 = not enough space 3= under move
       if (truck.getTime() == -1) {
           Product product = warehouse.getProduct(productName);
           if (product == null) {
               return 1;
           } else {
               if (product.getSize() > truck.getLeftSpace()) {
                   return 2;
               } else {
                   truck.getProducts().add(product);
                   warehouse.getProducts().remove(product);
                   truck.setCounter(truck.getCounter() + product.getSize());
                   warehouse.setCounter(warehouse.getCounter() - product.getSize());
                   return 0;
               }
           }
       }else {
           return 3 ;
       }
    }

    public int moveToTruckPredator (String predatorName){// 0 = done 1 = no such predator in ware house 2 = not enough space 3= under move
        if (truck.getTime() == - 1) {
            Predator predator = warehouse.getPredator(predatorName);
            if (predator == null) {
                return 1;
            } else {
                if (predator.getSizeInWarehouse() > truck.getLeftSpace()) {
                    return 2;
                } else {
                    truck.getPredators().add(predator);
                    warehouse.getPredators().remove(predator);
                    truck.setCounter(truck.getCounter() + predator.getSizeInWarehouse());
                    warehouse.setCounter(warehouse.getCounter() - predator.getSizeInWarehouse());
                    return 0;
                }
            }
        }else{
            return 3 ;
        }
    }

    public int unloadFromTruckProduct(String name){// 0 = done 1 = sno such product 2 = not enough space in ware house 3= under move
        if (truck.getTime() == -1 ) {
            Product product = truck.getProduct(name);
            if (product == null) {
                return 1;
            } else {
                if (product.getSize() > warehouse.remainingSpace()) {
                    return 2;
                } else {
                    warehouse.getProducts().add(product);
                    truck.getProducts().remove(product);
                    warehouse.setCounter(warehouse.getCounter() + product.getSize());
                    truck.setCounter(truck.getCounter() + product.getSize());
                    return 0;
                }
            }
        }else {
            return 3  ;
        }
    }

    public int unloadFromTruckPredator(String name){// 0 = done 1 = sno such product 2 = not enough space in ware house 3= truck moving
        if (truck.getTime() == -1) {
            Predator predator = truck.getPredator(name);
            if (predator == null) {
                return 1;
            } else {
                if (predator.getSizeInWarehouse() > warehouse.remainingSpace()) {
                    return 2;
                } else {
                    warehouse.getPredators().add(predator);
                    truck.getProducts().remove(predator);
                    warehouse.setCounter(warehouse.getCounter() + predator.getSizeInWarehouse());
                    truck.setCounter(truck.getCounter() + predator.getSizeInWarehouse());
                    return 0;
                }
            }
        }else {
            return 3 ;
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
        LinkedList<Domestic> removalDomestics = new LinkedList<>() ;
        for (Domestic domestic : domestics){
            int check = doTurnDomestic(domestic) ;
            if (check== 1){
                removalDomestics.add(domestic);
            }
        }

        for (Domestic domestic : removalDomestics){
            domestics.remove(domestic);
        }
        LinkedList<Defender> removalDefenders = new LinkedList<>() ;
        for (Defender defender : defenders){
            int check = doTurnDefender(defender);
            if (check == 1){
                removalDefenders.add(defender);
            }
        }
        for (Defender defender : removalDefenders){
            defenders.remove(defender);
        }
        LinkedList<Predator> removalPredators = new LinkedList<>() ;
        for (Predator predator : predators){
            int check = doTurnPredator(predator);
            if (check == 1){
                removalPredators.add(predator);
            }
        }
        for (Predator predator : removalPredators){
            predators.remove(predator);
        }

        LinkedList<Product> removalProducts = new LinkedList<>();
        for (Product product : products){
            int check = doTurnProduct(product);
            if (check == 1){
                removalProducts.add(product);
            }
        }
        for (Product product : removalProducts){
            products.remove(product);
        }

        for (Workshop workshop : workshops){
            doTurnWorkShop(workshop);
        }
        doTurnTruck(truck);
        doTurnWell(well);
        spawnPredators(level);
        turn = turn + 1 ;
        return checkWinLoss();
    }

    public int doTurnDomestic(Domestic domestic){// 0 = none 1= remove
        if (domestic.getProductTimer() < domestic.getTimeProduce()) {
            domestic.setProductTimer(domestic.getProductTimer() + 1);
        }
        if (domestic.getProductTimer() == domestic.getTimeProduce()) {
            Product product = domestic.produce() ;
            products.add(product) ;
            domestic.setProductTimer(0);
            updateIdTracker(product.getName());
            product.setId(idTracker.get(product.getName()));
        }
        if (domestic.getLives() <= 5) {
            if (anyGrass() == 1) {
                if (checkCoordinateGrass(domestic.getX(), domestic.getY()) == 1) {
                    grassMap[domestic.getX() - 1][domestic.getY() - 1] = grassMap[domestic.getX() - 1][domestic.getY() - 1] - 1;
                    domestic.setLives(10);
                } else {
                    walkDomestic(domestic);
                }
            }else {
                move(domestic);
            }
        }else{
            move(domestic);
        }
        domestic.setLives(domestic.getLives() - 1);
        if(domestic.getLives() == 0){
            return 1 ;
        }
        return 0 ;
    }

    public int doTurnPredator(Predator predator){// 0 = done 1= remove
        move(predator);
        if (predator.isCaptured() == false) {
            Domestic domestic = checkCoordinateDomestic(predator.getX(), predator.getY());
            if (domestic != null) {
                domestics.remove(domestic);
            }
        }else {
            predator.setTime_captured(predator.getTime_captured() + 1);
            if (predator.getTime_captured() == predator.timeToRun) {
                return 1;
            }
        }

        return 0 ;
    }

    public int doTurnDefender(Defender defender) {// 0 =none 1 = remove
        if (defender.getName().equals("cat")) {
            if (defender.getProducts().size() == 0) {
                if (products.size() > 0) {
                    Product product = checkCoordinateProduct(defender.getX(), defender.getY());
                    if (product == null) {
                        walkDefender(defender);
                    } else {
                        defender.getProducts().add(product);
                        products.remove(product);
                        product.setAvailable(2);
                    }
                }else {
                    move(defender);
                }
            }else {
                if (defender.getX() == 3 && defender.getY() == 6){//coordinate of warehouse
                    if (defender.getProducts().getFirst().getSize() < warehouse.remainingSpace()){
                        defender.getProducts().getFirst().setAvailable(0);
                        warehouse.getProducts().add(defender.getProducts().getFirst());
                        defender.getProducts().removeFirst();
                    }
                } else {
                    goTo(defender , 3, 6);
                }
            }
            return 0 ;
        }
        if (defender.getName().equals("dog")){
            if (predators.size() == 0){
                move(defender);
            }else {
                Predator predator = checkCoordinatePredator(defender.getX() , defender.getY()) ;
                if (predator == null) {
                    walkDefender(defender);
                }else {
                    predators.remove(predator);
                    return 1 ;
                }
            }
        }
        return 0 ;
    }

    public int doTurnProduct(Product product){// 0 = none 1 = remove
        if (product.getTime_present() < product.getGivenTime()){
            product.setTime_present(product.getTime_present() + 1);
        }
        if (product.getTime_present() == product.getGivenTime()){
            return  1;
        }
        return 0 ;
    }

    public void doTurnWorkShop (Workshop workshop){
        if (workshop.getLevel() > 0){
            if (workshop.getWorking() >= 0){
                workshop.setWorking(workshop.getWorking() + 1);
            }
            if (workshop.getWorking() >= workshop.getOperation_time()/(workshop.getLevel() - workshop.getNumInside() + 1)){
                if (workshop.getNumInside() == 1) {
                    workshop.setWorking(-1);
                    if (!workshop.getOutputProduct().equals("hen")) {
                        Product product = new Product();
                        if (workshop.getOutputProduct().equals("flour")) {
                            product = new Product.Flour(0, 0);
                        } else if (workshop.getOutputProduct().equals("fabric")) {
                            product = new Product.Fabric(0, 0);
                        } else if (workshop.getOutputProduct().equals("packetMilk")) {
                            product = new Product.PacketMilk(0, 0);
                        } else if (workshop.getOutputProduct().equals("bread")) {
                            product = new Product.Bread(0, 0);
                        } else if (workshop.getOutputProduct().equals("clothing")) {
                            product = new Product.Clothing(0, 0);
                        } else if (workshop.getOutputProduct().equals("iceCream")) {
                            product = new Product.IceCream(0, 0);
                        }
                        updateIdTracker(product.getName());
                        product.setId(idTracker.get(product.getName()));
                        addWorkshopOutputToMap(workshop , product);
                        addProductToMap(product);
                    }    else {
                        Domestic domestic = new Domestic.Hen(0 , 0 );
                        updateIdTracker("hen");
                        domestic.setId(idTracker.get(domestic.getName()));
                        addWorkshopOutputToMap(workshop , domestic);
                        domestics.add(domestic);
                    }

                }else {
                    if (!workshop.getOutputProduct().equals("hen")) {
                        Product product1 = new Product();
                        Product product2 = new Product();
                        if (workshop.getOutputProduct().equals("flour")) {
                            product1 = new Product.Flour(0, 0);
                            product2 = new Product.Flour(0, 0);
                        } else if (workshop.getOutputProduct().equals("fabric")) {
                            product1 = new Product.Fabric(0, 0);
                            product2 = new Product.Fabric(0, 0);
                        } else if (workshop.getOutputProduct().equals("packetMilk")) {
                            product1 = new Product.PacketMilk(0, 0);
                            product2 = new Product.PacketMilk(0, 0);
                        } else if (workshop.getOutputProduct().equals("bread")) {
                            product1 = new Product.Bread(0, 0);
                            product2 = new Product.Bread(0, 0);
                        } else if (workshop.getOutputProduct().equals("clothing")) {
                            product1 = new Product.Clothing(0, 0);
                            product2 = new Product.Clothing(0, 0);
                        } else if (workshop.getOutputProduct().equals("iceCream")) {
                            product1 = new Product.IceCream(0, 0);
                            product2 = new Product.IceCream(0, 0);
                        }
                        addProductToMap(product1);
                        updateIdTracker(product1.getName());
                        product1.setId(idTracker.get(product1.getName()));
                        addWorkshopOutputToMap(workshop , product1);
                        addProductToMap(product2);
                        updateIdTracker(product2.getName());
                        product2.setId(idTracker.get(product2.getName()));
                        addWorkshopOutputToMap(workshop , product2);
                    }else {
                        Domestic domestic1 = new Domestic.Hen(0 , 0);
                        Domestic domestic2 = new Domestic.Hen(0 , 0);
                        updateIdTracker("hen");
                        domestic1.setId(idTracker.get("hen"));
                        addWorkshopOutputToMap(workshop , domestic1);
                        domestics.add(domestic1);
                        updateIdTracker("hen");
                        domestic2.setId(idTracker.get("hen"));
                        domestics.add(domestic2);
                        addWorkshopOutputToMap(workshop , domestic2);
                    }
                    workshop.setWorking(-1);

                }
            }
        }
    }

    public void doTurnTruck (Truck truck){
        if (truck.getTime() >= 0){
            truck.setTime(truck.getTime() + 1);
        }
        if (truck.getTime() == truck.operation_time){
            int cash = 0 ;
            for (Product product : truck.getProducts()){
                cash = cash + product.getPrice();
            }
            for (Predator predator : truck.getPredators()){
                cash = cash + predator.getPrice_for_sale();
            }
            truck.getProducts().clear();
            truck.getPredators().clear();
            truck.setCounter(0);
            truck.setTime(-1);
            coins = coins + cash ;
            System.out.println("Truck came back .");
        }
    }

    public void doTurnWell (Well well){
        if (well.getTimer() >= 0){
            well.setTimer(well.getTimer() + 1);
            if (well.getTimer() == well.operationTime){
                well.setAmount_left(well.capacity);
                well.setTimer(-1);
                System.out.println("Well filled .");
            }
        }
    }

    public void spawnPredators(Level level){
        int x = 0 ;
        int y = 0 ;
        Random random = new Random();
        for (Map.Entry incoming : level.getPredators().entrySet()){
            if ((int)incoming.getValue() == turn){
                x = random.nextInt(6) + 1 ;
                y = random.nextInt(6) + 1 ;
                Predator predator = new Predator();
                if (((String)incoming.getKey()).equals("lion")){
                    predator = new Predator.Lion(x , y) ;
                }else if (((String)incoming.getKey()).equals("bear")){
                    predator = new Predator.Bear(x , y) ;
                }else if (((String)incoming.getKey()).equals("tiger")){
                    predator = new Predator.Tiger(x , y) ;
                }
                predators.add(predator);
                updateIdTracker(predator.getName());
                predator.setId(idTracker.get(predator.getName()));
            }
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
            if (productHistory.get(name) == null || productHistory.get(name) < num){
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
                if (checkCoordinateProduct(i+1 , j+1) == null){
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
    public void walkDefender(Defender d) {
        int mindistance=100;
        int x;
        int y;
        int targetx=0;
        int targety=0;
        boolean movement = false ;
        if(d.getName().equals("cat")){
            x=d.getX();
            y=d.getY();
                for(Product product : products) {
                    if (mindistance > Math.abs(product.getX() - x) + Math.abs(product.getY() - y)) {
                        mindistance = Math.abs(product.getX() - x) + Math.abs(product.getY() - y);
                        targetx = product.getX() ;
                        targety = product.getY() ;
                    }
                }
        }else if (d.getName().equals("dog")) {
            x = d.getX();
            y = d.getY();
            for (Predator predator : predators) {
                if (mindistance > Math.abs(predator.getX() - x) + Math.abs(predator.getY() - y)) {
                    mindistance = Math.abs(predator.getX() - x) + Math.abs(predator.getY() - y);
                    targetx = predator.getX();
                    targety = predator.getY();
                }
            }
        }
        if (d.getX() < targetx) {
            d.setX(d.getX() + 1);
            movement = true;
        } else if (d.getX() > targetx) {
            d.setX(d.getX() - 1);
            movement = true;
        }
        if (movement == false) {
            if (d.getY() < targety) {
                d.setY(d.getY() + 1);
                movement = true;
            } else if (d.getY() > targety) {
                d.setY(d.getY() - 1);
                movement = true;
            }
        }

    }

    public void walkDomestic(Domestic d)
    {
        int mindistance=100;
        int x;
        int y;
        int targetx=0;
        int targety=0;
        boolean movement = false ;
        x=d.getX();
        y=d.getY();
        for(int i=0;i<6;i++) {
            for (int j = 0; j < 6; j++) {
                if (grassMap[i][j] > 0) {
                    if (mindistance > Math.abs(i + 1 - x) + Math.abs(j + 1 - y)) {
                        mindistance = Math.abs(i + 1 - x) + Math.abs(j + 1 - y);
                        targetx = i + 1;
                        targety = j + 1;
                    }
                }
            }
        }
        if (d.getX() < targetx) {
            d.setX(d.getX() + 1);
            movement = true;
        } else if (d.getX() > targetx) {
            d.setX(d.getX() - 1);
            movement = true;
        }
        if (movement == false) {
            if (d.getY() < targety) {
                d.setY(d.getY() + 1);
                movement = true;
            } else if (d.getY() > targety) {
                d.setY(d.getY() - 1);
                movement = true;
            }
        }
    }

    public void move (Animal animal){
        Random random = new Random() ;
        int dor = animal.getSpeed() ;
        while (dor > 0) {
            int dir = random.nextInt(4) + 1; // 1= +x 2 = +y 3 = -x 4 = -y
            switch (dir){
                case 1 :
                    if (animal.getX() <= 5) {
                        animal.setX(animal.getX() + 1);
                        dor = dor - 1 ;
                    }
                    break;
                case 2 :
                    if (animal.getY() <= 5){
                        animal.setY(animal.getY() + 1);
                        dor = dor - 1 ;
                    }
                    break;
                case 3 :
                    if (animal.getX() >= 2){
                        animal.setX(animal.getX() - 1);
                        dor = dor - 1 ;
                    }
                    break;
                case 4 :
                    if (animal.getY() >= 2){
                        animal.setY(animal.getY() - 1);
                        dor = dor - 1 ;
                    }
                    break;
            }
        }

    }
    public void plot () {
        boolean grasses = false ;
        System.out.println("Turn : " + turn);
        System.out.println("Coins : " + coins);
        System.out.println("Well : " + well.getAmount_left());
        System.out.println("Grasses : ");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(grassMap[i][j] + " ");
                if (grassMap[i][j] > 0){
                    grasses = true ;
                }
            }
            System.out.println();
        }
        System.out.println("Domestic animals : ");
        for (Domestic domestic : domestics){
            System.out.println(domestic.getName() + " " + domestic.getId() + " " + domestic.getLives()*10 + "%" + " [" + domestic.getX() + " " + domestic.getY() + "]");
        }
        System.out.println("Predator animals : ");
        for (Predator predator : predators){
            System.out.println(predator.getName() + " " + predator.getId() + " " + (predator.getCageToStop() - predator.getCage().getStrength()) + " [" + predator.getX() + " " + predator.getY() + "]");
        }
        System.out.println("Defender animals : ");
        for (Defender defender : defenders){
            System.out.println(defender.getName() + " " + defender.getId() + " [" + defender.getX() + " " + defender.getY() + "]");
        }
        System.out.println("Products : ");
        for (Product product : products){
            System.out.println(product.getName() + " " + product.getId() + " [" + product.getX() + " " + product.getY() + "]");
        }
        System.out.println("Ware house : ");
        for (Product product : warehouse.getProducts()){
            System.out.println(product.getName() + " " + product.getId());
        }
        for (Predator predator : warehouse.getPredators()){
            System.out.println(predator.getName() + " " + predator.getId());
        }
        System.out.println("Truck : ");
        for (Product product : truck.getProducts()){
            System.out.println(product.getName() + " " + product.getId());
        }
        for (Predator predator : truck.getPredators()){
            System.out.println(predator.getName() + " " + predator.getId());
        }
        System.out.println("Achievements : ");
        for (Map.Entry achievement : level.getAnimalAchievement().entrySet()){
            System.out.println(achievement.getKey() + ":" + getNumAnimal((String)achievement.getKey()) + "/" +achievement.getValue());
        }
        for (Map.Entry achievement : level.getProductAchievement().entrySet()){
            System.out.println(achievement.getKey() + ":" + productHistory.get((String)achievement.getKey()) + "/" +achievement.getValue());
        }
        if (level.getGoalCoins()>0){
            System.out.println("Coins : " + coins + "/" + level.getGoalCoins());
        }
        if (grasses == false){
            System.out.println("WARNING : No grass on the field.");
        }
    }

    public Product checkCoordinateProduct(int x , int y){
        for (Product product : products){
            if (product.getY() == x && product.getY() == y){
                return product ;
            }
        }
        return null ;
    }

    public Predator checkCoordinatePredator(int x , int y){
        for (Predator predator : predators){
            if (predator.getY() == x && predator.getY() == y){
                return predator ;
            }
        }
        return null ;
    }

    public Domestic checkCoordinateDomestic(int x , int y){
        for (Domestic domestic : domestics){
            if (domestic.getY() == x && domestic.getY() == y){
                return domestic ;
            }
        }
        return null ;
    }

    public int checkCoordinateGrass (int x , int y) {//0 = no grass 1 = grass
        if (grassMap[x-1][y-1] > 0) {
            return 1;
        }else {
            return 0 ;
        }
    }

    public int giveMedal (){// 1=gold 2= silver 3=bronze 4= none
        int prize = 4 ;
        for (int i = 0 ; i < 3 ; i++){
            if (turn < level.getRewardTimes().get(i)){
                prize = i + 1 ;
                break;
            }
        }
        return prize ;
    }

    public void updateIdTracker(String name){
        if (idTracker.containsKey(name)){
            idTracker.put(name , idTracker.get(name) + 1);
        }else {
            idTracker.put(name , 1);
        }
    }

    public int anyGrass (){// 0 = no grass on map 1 = grass on map
        for (int i = 0 ; i < 6 ; i++){
            for (int j = 0 ; j< 6 ; j++){
                if (grassMap[i][j] > 0){
                    return 1 ;
                }
            }
        }
        return 0 ;
    }

    public void goTo (Animal d, int x, int y){
        boolean movement = false  ;
        if (d.getX() < x) {
            d.setX(d.getX() + 1);
            movement = true;
        } else if (d.getX() > x) {
            d.setX(d.getX() - 1);
            movement = true;
        }
        if (movement == false) {
            if (d.getY() < y) {
                d.setY(d.getY() + 1);
                movement = true;
            } else if (d.getY() > y) {
                d.setY(d.getY() - 1);
                movement = true;
            }
        }

    }

    public int findNumProductWarehouse (String name){
        int num = 0 ;
        for (Product product : warehouse.getProducts()){
            if (product.getName().equals(name)){
                num = num + 1 ;
            }
        }
        return num ;
    }

    public void addWorkshopOutputToMap (Workshop workshop, GamePlay gamePlay){
        switch (workshop.getId()){
            case 1 :
                gamePlay.setX(1);
                gamePlay.setY(1);
                break;
            case 2 :
                gamePlay.setX(1);
                gamePlay.setY(3);
                break;
            case 3 :
                gamePlay.setX(1);
                gamePlay.setY(5);
                break;
            case 4 :
                gamePlay.setX(6);
                gamePlay.setY(1);
                break;
            case 5 :
                gamePlay.setX(6);
                gamePlay.setY(3);
                break;
            case 6 :
                gamePlay.setX(6);
                gamePlay.setY(5);
                break;
        }
    }


}

