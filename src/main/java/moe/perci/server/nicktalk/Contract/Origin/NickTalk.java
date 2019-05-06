package moe.perci.server.nicktalk.Contract.Origin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
public class NickTalk extends Contract {
    private static final String BINARY = "0x6001805460a060020a60ff0219167401000000000000000000000000000000000000000017905560c0604052601760808190527f4e656564207570646174652074686520636c69656e742e00000000000000000060a0908152610065916002919061008c565b506003805461ffff19166101f417905560008054600160a060020a03191633179055610127565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100cd57805160ff19168380011785556100fa565b828001600101855582156100fa579182015b828111156100fa5782518255916020019190600101906100df565b5061010692915061010a565b5090565b61012491905b808211156101065760008155600101610110565b90565b610ce0806101366000396000f3006080604052600436106100b95763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663044c1d4d81146100be5780631124fb84146100e957806331b586801461010757806332a48f0c146101335780633f055b52146101bd5780635e35359e1461022957806379ba5097146102535780638da5cb5b14610268578063d4ee1d9014610299578063eb544aaf146102ae578063f2fde38b14610307578063f78203a714610328575b600080fd5b3480156100ca57600080fd5b506100d3610343565b6040805160ff9092168252519081900360200190f35b3480156100f557600080fd5b5061010561ffff60043516610364565b005b34801561011357600080fd5b5061011c610390565b6040805161ffff9092168252519081900360200190f35b34801561013f57600080fd5b5061014861039a565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561018257818101518382015260200161016a565b50505050905090810190601f1680156101af5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101c957600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610105958335600160a060020a03169536956044949193909101919081908401838280828437509497505050923560ff16935061042592505050565b34801561023557600080fd5b50610105600160a060020a03600435811690602435166044356107a7565b34801561025f57600080fd5b506101056108ae565b34801561027457600080fd5b5061027d610936565b60408051600160a060020a039092168252519081900360200190f35b3480156102a557600080fd5b5061027d610945565b3480156102ba57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101059436949293602493928401919081908401838280828437509497506109549650505050505050565b34801561031357600080fd5b50610105600160a060020a036004351661097f565b34801561033457600080fd5b5061010560ff600435166109dd565b60015474010000000000000000000000000000000000000000900460ff1681565b600054600160a060020a0316331461037857fe5b6003805461ffff191661ffff92909216919091179055565b60035461ffff1681565b6002805460408051602060018416156101000260001901909316849004601f8101849004840282018401909252818152929183018282801561041d5780601f106103f25761010080835404028352916020019161041d565b820191906000526020600020905b81548152906001019060200180831161040057829003601f168201915b505050505081565b6001546000908190839060029060ff740100000000000000000000000000000000000000009091048116908316101561050b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526020600482019081528254600260001961010060018416150201909116046024830181905290918291604490910190849080156104fc5780601f106104d1576101008083540402835291602001916104fc565b820191906000526020600020905b8154815290600101906020018083116104df57829003601f168201915b50509250505060405180910390fd5b50600160a060020a03868116600090815260056020908152604080832081516060810183523381528084018b8152429382019390935281546001808201808555938752958590208251600390920201805473ffffffffffffffffffffffffffffffffffffffff1916919097161786559151805191959294929361059393850192910190610a33565b506040820151816002015550505033600160a060020a031686600160a060020a03167f6f0469247f8d3ff180b32699c6180d88f180aa89bfac248ac21dba5eb79aaa7987426040518080602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561061b578181015183820152602001610603565b50505050905090810190601f1680156106485780820380516001836020036101000a031916815260200191505b50935050505060405180910390a3600354600160a060020a038716600090815260056020526040902054600261ffff928316029091161161079f57600091505b60035461ffff9081169083161161075457600160a060020a0386166000908152600560205260409020805484919061ffff85169081106106c457fe5b600091825260208083208454600181810180885596865292909420600393840290910180549490930201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909416939093178355808201805492939261073e92848101929160029181161561010002600019011604610ab1565b5060029182015491015550600190910190610688565b600160a060020a03861660009081526005602052604081206107769082610b26565b50600160a060020a0386166000908152600560205260409020835461079d91908590610b57565b505b505050505050565b600054600160a060020a031633146107bb57fe5b82600160a060020a03811615156107d157600080fd5b82600160a060020a03811615156107e757600080fd5b83600160a060020a0381163014156107fe57600080fd5b85600160a060020a031663a9059cbb86866040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600160a060020a0316600160a060020a0316815260200182815260200192505050602060405180830381600087803b15801561087a57600080fd5b505af115801561088e573d6000803e3d6000fd5b505050506040513d60208110156108a457600080fd5b5051151561079f57fe5b600154600160a060020a031633146108c557600080fd5b60015460008054604051600160a060020a0393841693909116917f343765429aea5a34b3ff6a3785a98a5abb2597aca87bfbb58632c173d585373a91a3600180546000805473ffffffffffffffffffffffffffffffffffffffff19908116600160a060020a03841617909155169055565b600054600160a060020a031681565b600154600160a060020a031681565b600054600160a060020a0316331461096857fe5b805161097b906002906020840190610a33565b5050565b600054600160a060020a0316331461099357fe5b600054600160a060020a03828116911614156109ae57600080fd5b6001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600054600160a060020a031633146109f157fe5b6001805460ff909216740100000000000000000000000000000000000000000274ff000000000000000000000000000000000000000019909216919091179055565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a7457805160ff1916838001178555610aa1565b82800160010185558215610aa1579182015b82811115610aa1578251825591602001919060010190610a86565b50610aad929150610c08565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610aea5780548555610aa1565b82800160010185558215610aa157600052602060002091601f016020900482015b82811115610aa1578254825591600101919060010190610b0b565b815481835581811115610b5257600302816003028360005260206000209182019101610b529190610c25565b505050565b828054828255906000526020600020906003028101928215610bfc5760005260206000209160030282015b82811115610bfc578254825473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039091161782556001808401805485928592610be09282850192600260001991831615610100029190910190911604610ab1565b5060028201548160020155505091600301919060030190610b82565b50610aad929150610c25565b610c2291905b80821115610aad5760008155600101610c0e565b90565b610c2291905b80821115610aad57805473ffffffffffffffffffffffffffffffffffffffff191681556000610c5d6001830182610c6d565b5060006002820155600301610c2b565b50805460018160011615610100020316600290046000825580601f10610c935750610cb1565b601f016020900490600052602060002090810190610cb19190610c08565b505600a165627a7a72305820d67d00e3c97dec2942df0d1b095f0245b0bdcea0f90e2be3dfba57d110da5a740029\n";

    protected NickTalk(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NickTalk(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<NewMessageEventResponse> getNewMessageEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("newMessage", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<NewMessageEventResponse> responses = new ArrayList<NewMessageEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewMessageEventResponse typedResponse = new NewMessageEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._to = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._from = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._content = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewMessageEventResponse> newMessageEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("newMessage", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewMessageEventResponse>() {
            @Override
            public NewMessageEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                NewMessageEventResponse typedResponse = new NewMessageEventResponse();
                typedResponse.log = log;
                typedResponse._to = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._from = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._content = (Utf8String) eventValues.getNonIndexedValues().get(0);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(1);

                return typedResponse;
            }
        });
    }

    public List<OwnerUpdateEventResponse> getOwnerUpdateEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OwnerUpdate", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<OwnerUpdateEventResponse> responses = new ArrayList<OwnerUpdateEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnerUpdateEventResponse typedResponse = new OwnerUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._prevOwner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._newOwner = (Address) eventValues.getIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnerUpdateEventResponse> ownerUpdateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OwnerUpdate", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnerUpdateEventResponse>() {
            @Override
            public OwnerUpdateEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                OwnerUpdateEventResponse typedResponse = new OwnerUpdateEventResponse();
                typedResponse.log = log;
                typedResponse._prevOwner = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._newOwner = (Address) eventValues.getIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public RemoteCall<Uint8> clientVersion() {
        final Function function = new Function("clientVersion", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint16> maxPersonalMessageBoxLength() {
        final Function function = new Function("maxPersonalMessageBoxLength", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Utf8String> updateMsg() {
        final Function function = new Function("updateMsg", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> withdrawTokens(Address _token, Address _to, Uint256 _amount) {
        final Function function = new Function(
                "withdrawTokens", 
                Arrays.<Type>asList(_token, _to, _amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> acceptOwnership() {
        final Function function = new Function(
                "acceptOwnership", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> owner() {
        final Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> newOwner() {
        final Function function = new Function("newOwner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(Address _newOwner) {
        final Function function = new Function(
                "transferOwnership", 
                Arrays.<Type>asList(_newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setVersion(Uint8 _clientVersion) {
        final Function function = new Function(
                "setVersion", 
                Arrays.<Type>asList(_clientVersion), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setUpdateMsg(Utf8String _updateMsg) {
        final Function function = new Function(
                "setUpdateMsg", 
                Arrays.<Type>asList(_updateMsg), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setMaxPersonalMessageBoxLength(Uint16 _length) {
        final Function function = new Function(
                "setMaxPersonalMessageBoxLength", 
                Arrays.<Type>asList(_length), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> sendMsg(Address _toAddress, Utf8String _content, Uint8 _clientVersion) {
        final Function function = new Function(
                "sendMsg", 
                Arrays.<Type>asList(_toAddress, _content, _clientVersion), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<NickTalk> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NickTalk.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<NickTalk> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NickTalk.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static NickTalk load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NickTalk(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static NickTalk load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NickTalk(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class NewMessageEventResponse {
        public Log log;

        public Address _to;

        public Address _from;

        public Utf8String _content;

        public Uint256 _timestamp;
    }

    public static class OwnerUpdateEventResponse {
        public Log log;

        public Address _prevOwner;

        public Address _newOwner;
    }
}
