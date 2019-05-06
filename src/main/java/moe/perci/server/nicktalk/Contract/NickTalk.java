package moe.perci.server.nicktalk.Contract;

import moe.perci.server.nicktalk.Config.AccountConfig;
import moe.perci.server.nicktalk.Config.AddressConfig;
import moe.perci.server.nicktalk.Config.VnsConfig;
import moe.perci.server.nicktalk.Vns.RpcApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.abi.*;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import rx.Observable;
import rx.functions.Func1;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static moe.perci.server.nicktalk.Config.AccountConfig.credentials;
import static moe.perci.server.nicktalk.Vns.RpcApi.rpcPrefix;
import static org.web3j.tx.Contract.staticExtractEventParameters;

@RestController
@RequestMapping("NickTalk")
public class NickTalk {
    public static moe.perci.server.nicktalk.Contract.Origin.NickTalk nickTalk;
    public static RemoteCall<TransactionReceipt> transactionReceiptRemoteCall;
    public static CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture;
    public static  Observable<moe.perci.server.nicktalk.Contract.Origin.NickTalk.NewMessageEventResponse> observable;

    public static void init() {
        nickTalk = moe.perci.server.nicktalk.Contract.Origin.NickTalk.load(AddressConfig.NickTalkContractAddress.toString(), RpcApi.web3j, credentials, RpcApi.gasPrice, VnsConfig.deployContractGasLimit);
    }

    @GetMapping("ListenNewMessageEvent")
    public HashMap<String, Object> listenNewMessageEvent(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");

        try {
            Event event = new Event("newMessage",
                    Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                    Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));

            List<String> _address = new ArrayList<>();
            _address.add(AddressConfig.NickTalkContractAddress.toString());

            EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, _address);
            filter.addSingleTopic(EventEncoder.encode(event));
            RpcApi.web3j.ethLogObservable(filter).map(new Func1<Log, moe.perci.server.nicktalk.Contract.Origin.NickTalk.NewMessageEventResponse>() {
                @Override
                public moe.perci.server.nicktalk.Contract.Origin.NickTalk.NewMessageEventResponse call(Log log) {
                    EventValues eventValues = staticExtractEventParameters(event, log);
                    moe.perci.server.nicktalk.Contract.Origin.NickTalk.NewMessageEventResponse typedResponse = new moe.perci.server.nicktalk.Contract.Origin.NickTalk.NewMessageEventResponse();
                    typedResponse.log = log;
                    typedResponse._to = (Address) eventValues.getIndexedValues().get(0);
                    typedResponse._from = (Address) eventValues.getIndexedValues().get(1);
                    typedResponse._content = (Utf8String) eventValues.getNonIndexedValues().get(0);
                    typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(1);

                    System.out.println(typedResponse.toString());
                    return typedResponse;
                }
            });
            result.put("code", 1);
            result.put("data", "");
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        } catch (Error e) {
            result.put("msg", e.toString());
        }

        return result;
    }

    @GetMapping("sendMessage")
    public HashMap<String, Object> sendMessage(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");

        try {
            Address _toAddress = new Address(request.getParameter("toAddress"));
            Utf8String _content = new Utf8String(request.getParameter("content"));
            Uint8 _clientVersion = new Uint8(new BigInteger(request.getParameter("version")));
            Function function = new Function(
                    "sendMsg",
                    Arrays.<Type>asList(_toAddress, _content, _clientVersion),
                    Collections.<TypeReference<?>>emptyList());

            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(
                    RpcApi.nonce,
                    RpcApi.gasPrice,
                    VnsConfig.deployContractGasLimit,
                    AddressConfig.NickTalkContractAddress.toString(),
                    encodedFunction
            );

            String transactionHash = RpcApi.sendRawTransaction(rpcPrefix, credentials, rawTransaction);

            result.put("code", 1);
            result.put("data", transactionHash);
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        } catch (Error e) {
            result.put("msg", e.toString());
        }

        return result;
    }

    @GetMapping("getVersion1")
    public HashMap<String, Object> getVersion1(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");

        try {

            Function function = new Function("clientVersion",
                    Arrays.<Type>asList(),
                    Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
            String encodedFunction = FunctionEncoder.encode(function);

            Transaction transaction = Transaction.createEthCallTransaction(AccountConfig.vnsWalletAddress, AddressConfig.NickTalkContractAddress.toString(), encodedFunction);
            Request<?, EthCall> myRequest = new Request<>("vns_call", Arrays.asList(transaction, DefaultBlockParameterName.LATEST), RpcApi.web3jService, EthCall.class);
            EthCall ethCall = myRequest.send();
            String value = ethCall.getValue();

            List<Type> values = FunctionReturnDecoder.decode(value, function.getOutputParameters());

            RemoteCall<Address> remoteCall =  new RemoteCall(() -> {
                return !values.isEmpty() ? (Type)values.get(0) : null;
            });

            result.put("code", 1);
            result.put("data", remoteCall.send());
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        } catch (Error e) {
            result.put("msg", e.toString());
        }

        return result;
    }

    @GetMapping("setVersion1")
    public HashMap<String, Object> setVersion1(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");

        try {
            Uint8 _clientVersion = new Uint8(new BigInteger(request.getParameter("version")));

            Function function = new Function(
                    "setVersion",
                    Arrays.<Type>asList(_clientVersion),
                    Collections.<TypeReference<?>>emptyList());

            String encodedFunction = FunctionEncoder.encode(function);
            RawTransaction rawTransaction = RawTransaction.createTransaction(
                    RpcApi.nonce,
                    RpcApi.gasPrice,
                    VnsConfig.deployContractGasLimit,
                    AddressConfig.NickTalkContractAddress.toString(),
                    encodedFunction
            );

            String transactionHash = RpcApi.sendRawTransaction(rpcPrefix, credentials, rawTransaction);

            result.put("code", 1);
            result.put("data", transactionHash);
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        } catch (Error e) {
            result.put("msg", e.toString());
        }

        return result;
    }

    @GetMapping("getNewMessageEvents2")
    public HashMap<String, Object> getVersion2(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");

        try {

            TransactionReceipt transactionReceipt = RpcApi.getTransactoinReceiptByTransactionHash(request.getParameter("hash"));
            List<moe.perci.server.nicktalk.Contract.Origin.NickTalk.NewMessageEventResponse> newMessageEventResponses = nickTalk.getNewMessageEvents(transactionReceipt);
            result.put("code", 1);
            result.put("data", newMessageEventResponses);
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        } catch (Error e) {
            result.put("msg", e.toString());
        }

        return result;
    }

    @GetMapping("setVersion2")
    public HashMap<String, Object> setVersion2(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");

        try {
            nickTalk = moe.perci.server.nicktalk.Contract.Origin.NickTalk.load(AddressConfig.NickTalkContractAddress.toString(), RpcApi.web3j, credentials, RpcApi.gasPrice, VnsConfig.deployContractGasLimit);

            transactionReceiptRemoteCall = nickTalk.setVersion(new Uint8(new BigInteger(request.getParameter("version"))));

         transactionReceiptCompletableFuture = transactionReceiptRemoteCall.sendAsync();


            result.put("code", 1);
            result.put("data", 1);
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        } catch (Error e) {
            result.put("msg", e.toString());
        }

        return result;
    }

    @GetMapping("transactionReceiptRemotecall")
    public HashMap<String, Object> transactionReceiptRemotecall() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");
      try {
          transactionReceiptCompletableFuture.thenAccept(transactionReceipt -> {

              System.out.println(transactionReceipt.getTransactionHash());
              result.put("transactionHash", transactionReceipt.getTransactionHash());

          }).exceptionally(transactionReceipt  -> {
              result.put("msg2", "failed");
              result.put("why", transactionReceipt.getCause());
              return null;
          });
          // return transactionReceiptRemoteCall.observab;
      } catch (Exception e) {
          result.put("msge", e.getMessage());
      }

      return result;
    }



    @GetMapping("sendMsg2")
    public HashMap<String, Object> sendMsg2(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("msg", "");
        result.put("data", "");

        try {
            nickTalk = moe.perci.server.nicktalk.Contract.Origin.NickTalk.load(AddressConfig.NickTalkContractAddress.toString(), RpcApi.web3j, credentials, RpcApi.gasPrice, VnsConfig.deployContractGasLimit);

            observable = nickTalk.newMessageEventObservable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST);

            observable.subscribe(typedResponse->{
                System.out.println(typedResponse._to);
                System.out.println(typedResponse._from);
                System.out.println(typedResponse._content);
                System.out.println(typedResponse._timestamp);
            });
            result.put("code", 1);
            result.put("data", observable.toString());
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        } catch (Error e) {
            result.put("msg", e.toString());
        }

        return result;
    }
}
