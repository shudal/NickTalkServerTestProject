package moe.perci.server.nicktalk.Config;

import java.math.BigInteger;

public class VnsConfig {
    public static BigInteger deployContractGasPrice = new BigInteger("22000");
    public static BigInteger deployContractGasLimit = new BigInteger("8000000");

    public static BigInteger normalTransactionGasLimit = new BigInteger("60000");

    public static BigInteger bancorGasPriceLimit = NumberConfig.tens9.multiply(new BigInteger("300"));
}
