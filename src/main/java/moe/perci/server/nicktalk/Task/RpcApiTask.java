package moe.perci.server.nicktalk.Task;

import moe.perci.server.nicktalk.Config.AccountConfig;
import moe.perci.server.nicktalk.Contract.NickTalk;
import moe.perci.server.nicktalk.Vns.RpcApi;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RpcApiTask {
    @Scheduled(fixedDelay = 5 * 1000)
    public void updateVns() {

        try {
            RpcApi.getNonce(AccountConfig.vnsWalletAddress);
            RpcApi.getGasPrice();
        } catch (Exception e) {
            System.out.println("updateVns, error:" + e.getMessage());
            if (e.getMessage() == null) {
                RpcApi.init();
                NickTalk.init();
            }
        }
    }
}
