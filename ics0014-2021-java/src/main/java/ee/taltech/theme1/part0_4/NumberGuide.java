package ee.taltech.theme1.part0_4;

import java.math.BigDecimal;
import java.math.BigInteger;

//read more: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
public class NumberGuide {

    //Numbers can be represented as primitives (byte, short, int..) or objects (Byte, Short, Integer...)

    //In ICS0014 we will be using intermittently (we do not care about the difference)
    //primitives are faster as they take less memory, objects allow to represent the absence of data (nulls)

    //numbers are represented as buckets, the bigger bucket you need, the bigger type of numbers you use
    //when we count on people, we need ints, however database id's are usually longs

    private byte aByte; //not used in ICS0014
    private short aShort; //not used in ICS0014
    private char aChar; //not used in ICS0014
    private int anInt;
    private float aFloat; //not used in ICS0014
    private long aLong;
    private double aDouble;
    private Byte byteObj; //not used in ICS0014
    private Short shortObj; //not used in ICS0014
    //integer has 2^32 (32-bit) data, so min is -2^31 and max is 2^31 -1
    private Integer integer;
    private Float floatObj; //not used in ICS0014
    //integer has 2^64 (64-bit) data, so min is -2^63 and max is 2^63 -1
    private Long longObj;
    //double represents floating point numbers it is 64-bit IEEE 754 (google it)
    //however don't use double for precision calculation as 2-bit system has problem with 10-style math
    //use bigInteger/bigDecimal instead, bigXyz can hold any number
    private Double doubleObj;
    private BigInteger bigInteger;
    private BigDecimal bigDecimal;

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);

        long aLong = 123;
        int anInt = (int) aLong; //this is known as casting

        BigDecimal bigDecimal = new BigDecimal("1.22");
        System.out.println(bigDecimal);
        //when casting you might loose precision: double casted to int looses decimal data
    }
}
