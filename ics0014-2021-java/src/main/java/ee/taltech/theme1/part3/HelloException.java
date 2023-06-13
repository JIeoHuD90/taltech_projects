package ee.taltech.theme1.part3;

public class HelloException {

    public static void main(String[] args) throws Exception {
        //todo 3.1 fix this exception
        caseOne();

        //todo 3.2 fix this exception
        caseTwo();

        //todo 3.3 a bit hardcore, but can you catch Exception?
        // you have to uncomment this line
//        catchMe();

        //todo 3.4 What to do with exception once it's caught? It might prove useful for second part of the course.
        // or at least at work
        // I doubt we will be catching Exceptions anytime soon
    }

    public static void caseOne(){
        Bank bank = HelloException.getBank();
        System.out.println(bank.getFreeMoney());
    }

    public static void caseTwo(){
        Bank bank = HelloException.getBank();
        System.out.println(bank.getFreeMoney() + bank.getFreeMoney());
    }

    public static void catchMe() throws Exception {
        throw new Exception("I am super friendly");
    }

    public static Bank getBank(){
        return null;
    }
}
