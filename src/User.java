public class User {
    private String userName ;
    private String password ;
    private int coin ;
    private int maximumLevel ;

    User (String userName , String password ){
        this.userName = userName ;
        this.password = password ;
    }

    User (){}

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
