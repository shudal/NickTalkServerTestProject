package moe.perci.server.nicktalk.Vns;

import moe.perci.server.nicktalk.Config.NumberConfig;
import moe.perci.server.nicktalk.Config.UrlConfig;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class RpcApi {
    public static String rpcPrefix;

    public static boolean disPlayMsg;

    public static BigInteger nonce;

    public static BigInteger gasPrice;


    public static Web3jService web3jService;
    public static Web3j web3j;

    public static void init() {
        rpcPrefix = "vns";
        web3jService = new HttpService(UrlConfig.vnsNodeIp);

        disPlayMsg = true;
        gasPrice = new BigInteger("0");
        nonce = new BigInteger("0");

        web3j = Web3j.build(web3jService);
    }

    public static void getNonce(String address) throws IOException {
        String tag = "pending";

        Request<?, EthGetTransactionCount> request = new Request<>(rpcPrefix + "_getTransactionCount", Arrays.asList(address, DefaultBlockParameter.valueOf(tag).getValue()), web3jService, EthGetTransactionCount.class);
        EthGetTransactionCount ethGetTransactionCount = request.send();

        nonce = ethGetTransactionCount.getTransactionCount();

        if (disPlayMsg) {
            System.out.println("nonce = " + nonce);
        }
    }

    public static void getGasPrice() throws IOException{
        Request<?, EthGasPrice> request = new Request<>(rpcPrefix + "_gasPrice", Collections.emptyList(), web3jService, EthGasPrice.class);

        EthGasPrice ethGasPrice = request.send();
        gasPrice = ethGasPrice.getGasPrice();

        if (gasPrice.compareTo(NumberConfig.minGasPrice) == -1) {
            gasPrice =  NumberConfig.minGasPrice;
        }

        if (disPlayMsg) {
            System.out.println("VnsApi, getGasPrice, gasPrice=" + gasPrice.toString());
        }
    }

    public static String sendRawTransaction(String rpcPrefix, Credentials credentials, RawTransaction rawTransaction) throws IOException{
        byte[] signedRawTransactionBytes = TransactionEncoder.signMessage(rawTransaction, credentials);
        String signedRawTransaction = Numeric.toHexString(signedRawTransactionBytes);
        Request<?, EthSendTransaction> request1 = new Request<>(rpcPrefix + "_sendRawTransaction", Arrays.asList(signedRawTransaction), RpcApi.web3jService, EthSendTransaction.class);

        EthSendTransaction ethSendTransaction = request1.send();

        if (ethSendTransaction.hasError()) {
            throw new Error(ethSendTransaction.getError().getMessage());
        }

        return ethSendTransaction.getTransactionHash();
    }

    public static TransactionReceipt getTransactoinReceiptByTransactionHash(String transactionHash) throws IOException {
        Request<?, EthGetTransactionReceipt> request = new Request<>(rpcPrefix + "_getTransactionReceipt", Arrays.asList(transactionHash), web3jService, EthGetTransactionReceipt.class);
        EthGetTransactionReceipt ethGetTransactionReceipt = request.send();

        if (ethGetTransactionReceipt.getTransactionReceipt().isPresent()) {
            return ethGetTransactionReceipt.getTransactionReceipt().get();
        }

        return null;
    }
}
