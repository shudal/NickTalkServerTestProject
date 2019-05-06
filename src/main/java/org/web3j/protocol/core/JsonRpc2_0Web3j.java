//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.web3j.protocol.core;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.methods.request.ShhFilter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.DbGetHex;
import org.web3j.protocol.core.methods.response.DbGetString;
import org.web3j.protocol.core.methods.response.DbPutHex;
import org.web3j.protocol.core.methods.response.DbPutString;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthCompileLLL;
import org.web3j.protocol.core.methods.response.EthCompileSerpent;
import org.web3j.protocol.core.methods.response.EthCompileSolidity;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthFilter;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByHash;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.core.methods.response.EthGetCompilers;
import org.web3j.protocol.core.methods.response.EthGetStorageAt;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthGetUncleCountByBlockHash;
import org.web3j.protocol.core.methods.response.EthGetUncleCountByBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetWork;
import org.web3j.protocol.core.methods.response.EthHashrate;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthMining;
import org.web3j.protocol.core.methods.response.EthProtocolVersion;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthSign;
import org.web3j.protocol.core.methods.response.EthSubmitHashrate;
import org.web3j.protocol.core.methods.response.EthSubmitWork;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.EthUninstallFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.NetListening;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.core.methods.response.ShhAddToGroup;
import org.web3j.protocol.core.methods.response.ShhHasIdentity;
import org.web3j.protocol.core.methods.response.ShhMessages;
import org.web3j.protocol.core.methods.response.ShhNewFilter;
import org.web3j.protocol.core.methods.response.ShhNewGroup;
import org.web3j.protocol.core.methods.response.ShhNewIdentity;
import org.web3j.protocol.core.methods.response.ShhPost;
import org.web3j.protocol.core.methods.response.ShhUninstallFilter;
import org.web3j.protocol.core.methods.response.ShhVersion;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.Web3Sha3;
import org.web3j.protocol.rx.JsonRpc2_0Rx;
import org.web3j.utils.Async;
import org.web3j.utils.Numeric;
import rx.Observable;

public class JsonRpc2_0Web3j implements Web3j {
    public static final int DEFAULT_BLOCK_TIME = 15000;
    protected final Web3jService web3jService;
    private final JsonRpc2_0Rx web3jRx;
    private final long blockTime;

    public JsonRpc2_0Web3j(Web3jService web3jService) {
        this(web3jService, 15000L, Async.defaultExecutorService());
    }

    public JsonRpc2_0Web3j(Web3jService web3jService, long pollingInterval, ScheduledExecutorService scheduledExecutorService) {
        this.web3jService = web3jService;
        this.web3jRx = new JsonRpc2_0Rx(this, scheduledExecutorService);
        this.blockTime = pollingInterval;
    }

    public Request<?, Web3ClientVersion> web3ClientVersion() {
        return new Request("web3_clientVersion", Collections.emptyList(), this.web3jService, Web3ClientVersion.class);
    }

    public Request<?, Web3Sha3> web3Sha3(String data) {
        return new Request("web3_sha3", Arrays.asList(data), this.web3jService, Web3Sha3.class);
    }

    public Request<?, NetVersion> netVersion() {
        return new Request("net_version", Collections.emptyList(), this.web3jService, NetVersion.class);
    }

    public Request<?, NetListening> netListening() {
        return new Request("net_listening", Collections.emptyList(), this.web3jService, NetListening.class);
    }

    public Request<?, NetPeerCount> netPeerCount() {
        return new Request("net_peerCount", Collections.emptyList(), this.web3jService, NetPeerCount.class);
    }

    public Request<?, EthProtocolVersion> ethProtocolVersion() {
        return new Request("vns_protocolVersion", Collections.emptyList(), this.web3jService, EthProtocolVersion.class);
    }

    public Request<?, EthCoinbase> ethCoinbase() {
        return new Request("vns_coinbase", Collections.emptyList(), this.web3jService, EthCoinbase.class);
    }

    public Request<?, EthSyncing> ethSyncing() {
        return new Request("vns_syncing", Collections.emptyList(), this.web3jService, EthSyncing.class);
    }

    public Request<?, EthMining> ethMining() {
        return new Request("vns_mining", Collections.emptyList(), this.web3jService, EthMining.class);
    }

    public Request<?, EthHashrate> ethHashrate() {
        return new Request("vns_hashrate", Collections.emptyList(), this.web3jService, EthHashrate.class);
    }

    public Request<?, EthGasPrice> ethGasPrice() {
        return new Request("vns_gasPrice", Collections.emptyList(), this.web3jService, EthGasPrice.class);
    }

    public Request<?, EthAccounts> ethAccounts() {
        return new Request("vns_accounts", Collections.emptyList(), this.web3jService, EthAccounts.class);
    }

    public Request<?, EthBlockNumber> ethBlockNumber() {
        return new Request("vns_blockNumber", Collections.emptyList(), this.web3jService, EthBlockNumber.class);
    }

    public Request<?, EthGetBalance> ethGetBalance(String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request("vns_getBalance", Arrays.asList(address, defaultBlockParameter.getValue()), this.web3jService, EthGetBalance.class);
    }

    public Request<?, EthGetStorageAt> ethGetStorageAt(String address, BigInteger position, DefaultBlockParameter defaultBlockParameter) {
        return new Request("vns_getStorageAt", Arrays.asList(address, Numeric.encodeQuantity(position), defaultBlockParameter.getValue()), this.web3jService, EthGetStorageAt.class);
    }

    public Request<?, EthGetTransactionCount> ethGetTransactionCount(String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request("vns_getTransactionCount", Arrays.asList(address, defaultBlockParameter.getValue()), this.web3jService, EthGetTransactionCount.class);
    }

    public Request<?, EthGetBlockTransactionCountByHash> ethGetBlockTransactionCountByHash(String blockHash) {
        return new Request("vns_getBlockTransactionCountByHash", Arrays.asList(blockHash), this.web3jService, EthGetBlockTransactionCountByHash.class);
    }

    public Request<?, EthGetBlockTransactionCountByNumber> ethGetBlockTransactionCountByNumber(DefaultBlockParameter defaultBlockParameter) {
        return new Request("vns_getBlockTransactionCountByNumber", Arrays.asList(defaultBlockParameter.getValue()), this.web3jService, EthGetBlockTransactionCountByNumber.class);
    }

    public Request<?, EthGetUncleCountByBlockHash> ethGetUncleCountByBlockHash(String blockHash) {
        return new Request("vns_getUncleCountByBlockHash", Arrays.asList(blockHash), this.web3jService, EthGetUncleCountByBlockHash.class);
    }

    public Request<?, EthGetUncleCountByBlockNumber> ethGetUncleCountByBlockNumber(DefaultBlockParameter defaultBlockParameter) {
        return new Request("vns_getUncleCountByBlockNumber", Arrays.asList(defaultBlockParameter.getValue()), this.web3jService, EthGetUncleCountByBlockNumber.class);
    }

    public Request<?, EthGetCode> ethGetCode(String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request("vns_getCode", Arrays.asList(address, defaultBlockParameter.getValue()), this.web3jService, EthGetCode.class);
    }

    public Request<?, EthSign> ethSign(String address, String sha3HashOfDataToSign) {
        return new Request("vns_sign", Arrays.asList(address, sha3HashOfDataToSign), this.web3jService, EthSign.class);
    }

    public Request<?, EthSendTransaction> ethSendTransaction(Transaction transaction) {
        return new Request("vns_sendTransaction", Arrays.asList(transaction), this.web3jService, EthSendTransaction.class);
    }

    public Request<?, EthSendTransaction> ethSendRawTransaction(String signedTransactionData) {
        return new Request("vns_sendRawTransaction", Arrays.asList(signedTransactionData), this.web3jService, EthSendTransaction.class);
    }

    public Request<?, EthCall> ethCall(Transaction transaction, DefaultBlockParameter defaultBlockParameter) {
        return new Request("vns_call", Arrays.asList(transaction, defaultBlockParameter), this.web3jService, EthCall.class);
    }

    public Request<?, EthEstimateGas> ethEstimateGas(Transaction transaction) {
        return new Request("vns_estimateGas", Arrays.asList(transaction), this.web3jService, EthEstimateGas.class);
    }

    public Request<?, EthBlock> ethGetBlockByHash(String blockHash, boolean returnFullTransactionObjects) {
        return new Request("vns_getBlockByHash", Arrays.asList(blockHash, returnFullTransactionObjects), this.web3jService, EthBlock.class);
    }

    public Request<?, EthBlock> ethGetBlockByNumber(DefaultBlockParameter defaultBlockParameter, boolean returnFullTransactionObjects) {
        return new Request("vns_getBlockByNumber", Arrays.asList(defaultBlockParameter.getValue(), returnFullTransactionObjects), this.web3jService, EthBlock.class);
    }

    public Request<?, EthTransaction> ethGetTransactionByHash(String transactionHash) {
        return new Request("vns_getTransactionByHash", Arrays.asList(transactionHash), this.web3jService, EthTransaction.class);
    }

    public Request<?, EthTransaction> ethGetTransactionByBlockHashAndIndex(String blockHash, BigInteger transactionIndex) {
        return new Request("vns_getTransactionByBlockHashAndIndex", Arrays.asList(blockHash, Numeric.encodeQuantity(transactionIndex)), this.web3jService, EthTransaction.class);
    }

    public Request<?, EthTransaction> ethGetTransactionByBlockNumberAndIndex(DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex) {
        return new Request("vns_getTransactionByBlockNumberAndIndex", Arrays.asList(defaultBlockParameter.getValue(), Numeric.encodeQuantity(transactionIndex)), this.web3jService, EthTransaction.class);
    }

    public Request<?, EthGetTransactionReceipt> ethGetTransactionReceipt(String transactionHash) {
        return new Request("vns_getTransactionReceipt", Arrays.asList(transactionHash), this.web3jService, EthGetTransactionReceipt.class);
    }

    public Request<?, EthBlock> ethGetUncleByBlockHashAndIndex(String blockHash, BigInteger transactionIndex) {
        return new Request("vns_getUncleByBlockHashAndIndex", Arrays.asList(blockHash, Numeric.encodeQuantity(transactionIndex)), this.web3jService, EthBlock.class);
    }

    public Request<?, EthBlock> ethGetUncleByBlockNumberAndIndex(DefaultBlockParameter defaultBlockParameter, BigInteger uncleIndex) {
        return new Request("vns_getUncleByBlockNumberAndIndex", Arrays.asList(defaultBlockParameter.getValue(), Numeric.encodeQuantity(uncleIndex)), this.web3jService, EthBlock.class);
    }

    public Request<?, EthGetCompilers> ethGetCompilers() {
        return new Request("vns_getCompilers", Collections.emptyList(), this.web3jService, EthGetCompilers.class);
    }

    public Request<?, EthCompileLLL> ethCompileLLL(String sourceCode) {
        return new Request("vns_compileLLL", Arrays.asList(sourceCode), this.web3jService, EthCompileLLL.class);
    }

    public Request<?, EthCompileSolidity> ethCompileSolidity(String sourceCode) {
        return new Request("vns_compileSolidity", Arrays.asList(sourceCode), this.web3jService, EthCompileSolidity.class);
    }

    public Request<?, EthCompileSerpent> ethCompileSerpent(String sourceCode) {
        return new Request("vns_compileSerpent", Arrays.asList(sourceCode), this.web3jService, EthCompileSerpent.class);
    }

    public Request<?, EthFilter> ethNewFilter(org.web3j.protocol.core.methods.request.EthFilter ethFilter) {
        return new Request("vns_newFilter", Arrays.asList(ethFilter), this.web3jService, EthFilter.class);
    }

    public Request<?, EthFilter> ethNewBlockFilter() {
        return new Request("vns_newBlockFilter", Collections.emptyList(), this.web3jService, EthFilter.class);
    }

    public Request<?, EthFilter> ethNewPendingTransactionFilter() {
        return new Request("vns_newPendingTransactionFilter", Collections.emptyList(), this.web3jService, EthFilter.class);
    }

    public Request<?, EthUninstallFilter> ethUninstallFilter(BigInteger filterId) {
        return new Request("vns_uninstallFilter", Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)), this.web3jService, EthUninstallFilter.class);
    }

    public Request<?, EthLog> ethGetFilterChanges(BigInteger filterId) {
        return new Request("vns_getFilterChanges", Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)), this.web3jService, EthLog.class);
    }

    public Request<?, EthLog> ethGetFilterLogs(BigInteger filterId) {
        return new Request("vns_getFilterLogs", Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)), this.web3jService, EthLog.class);
    }

    public Request<?, EthLog> ethGetLogs(org.web3j.protocol.core.methods.request.EthFilter ethFilter) {
        return new Request("vns_getLogs", Arrays.asList(ethFilter), this.web3jService, EthLog.class);
    }

    public Request<?, EthGetWork> ethGetWork() {
        return new Request("vns_getWork", Collections.emptyList(), this.web3jService, EthGetWork.class);
    }

    public Request<?, EthSubmitWork> ethSubmitWork(String nonce, String headerPowHash, String mixDigest) {
        return new Request("vns_submitWork", Arrays.asList(nonce, headerPowHash, mixDigest), this.web3jService, EthSubmitWork.class);
    }

    public Request<?, EthSubmitHashrate> ethSubmitHashrate(String hashrate, String clientId) {
        return new Request("vns_submitHashrate", Arrays.asList(hashrate, clientId), this.web3jService, EthSubmitHashrate.class);
    }

    public Request<?, DbPutString> dbPutString(String databaseName, String keyName, String stringToStore) {
        return new Request("db_putString", Arrays.asList(databaseName, keyName, stringToStore), this.web3jService, DbPutString.class);
    }

    public Request<?, DbGetString> dbGetString(String databaseName, String keyName) {
        return new Request("db_getString", Arrays.asList(databaseName, keyName), this.web3jService, DbGetString.class);
    }

    public Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore) {
        return new Request("db_putHex", Arrays.asList(databaseName, keyName, dataToStore), this.web3jService, DbPutHex.class);
    }

    public Request<?, DbGetHex> dbGetHex(String databaseName, String keyName) {
        return new Request("db_getHex", Arrays.asList(databaseName, keyName), this.web3jService, DbGetHex.class);
    }

    public Request<?, ShhPost> shhPost(org.web3j.protocol.core.methods.request.ShhPost shhPost) {
        return new Request("shh_post", Arrays.asList(shhPost), this.web3jService, ShhPost.class);
    }

    public Request<?, ShhVersion> shhVersion() {
        return new Request("shh_version", Collections.emptyList(), this.web3jService, ShhVersion.class);
    }

    public Request<?, ShhNewIdentity> shhNewIdentity() {
        return new Request("shh_newIdentity", Collections.emptyList(), this.web3jService, ShhNewIdentity.class);
    }

    public Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress) {
        return new Request("shh_hasIdentity", Arrays.asList(identityAddress), this.web3jService, ShhHasIdentity.class);
    }

    public Request<?, ShhNewGroup> shhNewGroup() {
        return new Request("shh_newGroup", Collections.emptyList(), this.web3jService, ShhNewGroup.class);
    }

    public Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress) {
        return new Request("shh_addToGroup", Arrays.asList(identityAddress), this.web3jService, ShhAddToGroup.class);
    }

    public Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter) {
        return new Request("shh_newFilter", Arrays.asList(shhFilter), this.web3jService, ShhNewFilter.class);
    }

    public Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId) {
        return new Request("shh_uninstallFilter", Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)), this.web3jService, ShhUninstallFilter.class);
    }

    public Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId) {
        return new Request("shh_getFilterChanges", Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)), this.web3jService, ShhMessages.class);
    }

    public Request<?, ShhMessages> shhGetMessages(BigInteger filterId) {
        return new Request("shh_getMessages", Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)), this.web3jService, ShhMessages.class);
    }

    public Observable<String> ethBlockHashObservable() {
        return this.web3jRx.ethBlockHashObservable(this.blockTime);
    }

    public Observable<String> ethPendingTransactionHashObservable() {
        return this.web3jRx.ethPendingTransactionHashObservable(this.blockTime);
    }

    public Observable<Log> ethLogObservable(org.web3j.protocol.core.methods.request.EthFilter ethFilter) {
        return this.web3jRx.ethLogObservable(ethFilter, this.blockTime);
    }

    public Observable<org.web3j.protocol.core.methods.response.Transaction> transactionObservable() {
        return this.web3jRx.transactionObservable(this.blockTime);
    }

    public Observable<org.web3j.protocol.core.methods.response.Transaction> pendingTransactionObservable() {
        return this.web3jRx.pendingTransactionObservable(this.blockTime);
    }

    public Observable<EthBlock> blockObservable(boolean fullTransactionObjects) {
        return this.web3jRx.blockObservable(fullTransactionObjects, this.blockTime);
    }

    public Observable<EthBlock> replayBlocksObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock, boolean fullTransactionObjects) {
        return this.web3jRx.replayBlocksObservable(startBlock, endBlock, fullTransactionObjects);
    }

    public Observable<EthBlock> replayBlocksObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock, boolean fullTransactionObjects, boolean ascending) {
        return this.web3jRx.replayBlocksObservable(startBlock, endBlock, fullTransactionObjects, ascending);
    }

    public Observable<org.web3j.protocol.core.methods.response.Transaction> replayTransactionsObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        return this.web3jRx.replayTransactionsObservable(startBlock, endBlock);
    }

    public Observable<EthBlock> catchUpToLatestBlockObservable(DefaultBlockParameter startBlock, boolean fullTransactionObjects, Observable<EthBlock> onCompleteObservable) {
        return this.web3jRx.catchUpToLatestBlockObservable(startBlock, fullTransactionObjects, onCompleteObservable);
    }

    public Observable<EthBlock> catchUpToLatestBlockObservable(DefaultBlockParameter startBlock, boolean fullTransactionObjects) {
        return this.web3jRx.catchUpToLatestBlockObservable(startBlock, fullTransactionObjects);
    }

    public Observable<org.web3j.protocol.core.methods.response.Transaction> catchUpToLatestTransactionObservable(DefaultBlockParameter startBlock) {
        return this.web3jRx.catchUpToLatestTransactionObservable(startBlock);
    }

    public Observable<EthBlock> catchUpToLatestAndSubscribeToNewBlocksObservable(DefaultBlockParameter startBlock, boolean fullTransactionObjects) {
        return this.web3jRx.catchUpToLatestAndSubscribeToNewBlocksObservable(startBlock, fullTransactionObjects, this.blockTime);
    }

    public Observable<org.web3j.protocol.core.methods.response.Transaction> catchUpToLatestAndSubscribeToNewTransactionsObservable(DefaultBlockParameter startBlock) {
        return this.web3jRx.catchUpToLatestAndSubscribeToNewTransactionsObservable(startBlock, this.blockTime);
    }
}

