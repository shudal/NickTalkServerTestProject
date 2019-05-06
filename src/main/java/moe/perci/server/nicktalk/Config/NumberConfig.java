package moe.perci.server.nicktalk.Config;

import java.math.BigInteger;

public class NumberConfig {

    public static BigInteger tens18 = new BigInteger("1000000000000000000");
    public static BigInteger tens9  = new BigInteger("1000000000");
    public static BigInteger minGasPrice = new BigInteger("80").multiply(tens9);
}
