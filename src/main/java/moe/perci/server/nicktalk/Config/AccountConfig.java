package moe.perci.server.nicktalk.Config;

import org.web3j.crypto.Credentials;

public class AccountConfig {
    // vns wallet 2
    public static String vnsWalletPk = "4e68fbe5aea6ac72bbcc58d609d16649cd43dfc4a5c7a0103ea5f6e23ebc8bf6";
    public static String vnsWalletAddress = "0x26e2dcb60a99e455b8320478ec16f443cf0f19f9";
    public static Credentials credentials = Credentials.create(vnsWalletPk);

    public AccountConfig() {
        credentials = Credentials.create(vnsWalletPk);
    }
}
