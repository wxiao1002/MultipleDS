package com.github.multipleds.ssh;


import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.config.keys.loader.KeyPairResourceLoader;
import org.apache.sshd.common.util.security.SecurityUtils;
import org.springframework.util.StringUtils;

import java.security.KeyPair;
import java.util.Collection;
import java.util.Objects;

public class SSHUtils {

    private SSHUtils() {
        throw new IllegalStateException("no instance class");
    }

    public static ClientSession getSession(SshClient client, SSHConnectParam connectionParam) throws Exception {
        ClientSession session;
        session = client.connect(connectionParam.getUsername(), connectionParam.getAddress(), connectionParam.getPort())
                .verify(5000).getSession();
        // add password identity
        String password = connectionParam.getPassword();
        if (Objects.nonNull(password)) {
            session.addPasswordIdentity(password);
        }

        // add public key identity
        String publicKey = connectionParam.getPublicKey();
        if (Objects.nonNull(publicKey)) {
            try {
                KeyPairResourceLoader loader = SecurityUtils.getKeyPairResourceParser();
                Collection<KeyPair> keyPairCollection = loader.loadKeyPairs(null, null, null, publicKey);
                for (KeyPair keyPair : keyPairCollection) {
                    session.addPublicKeyIdentity(keyPair);
                }
            } catch (Exception e) {
                throw new Exception("Failed to add public key identity", e);
            }
        }
        return session;
    }
}
