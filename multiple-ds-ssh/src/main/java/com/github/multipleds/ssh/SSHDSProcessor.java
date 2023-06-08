package com.github.multipleds.ssh;

import com.github.multipleds.api.BaseConnectParam;
import com.github.multipleds.api.DbType;
import com.github.multipleds.api.datasource.BaseDSProcessor;
import com.github.multipleds.api.datasource.DSProcessor;
import com.google.auto.service.AutoService;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wang xiao
 * date 2023/6/8
 */
@AutoService(DSProcessor.class)
public class SSHDSProcessor extends BaseDSProcessor {

    @Override
    public String getDatasourceDriver() {
        return "";
    }

    @Override
    public String getValidateQuery() {
        return "";
    }

    @Override
    public String getJdbcUrl(BaseConnectParam connectParam) {
        return "";
    }

    @Override
    public boolean testConnection(BaseConnectParam connectionParam) {
        SSHConnectParam sshConnectParam = (SSHConnectParam) connectionParam;
        SshClient client = SshClient.setUpDefaultClient();
        client.start();
        try {
            ClientSession session = SSHUtils.getSession(client, sshConnectParam);
            return session.auth().verify().isSuccess();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Connection getConnection(BaseConnectParam connectParam) {
        return null;
    }

    @Override
    public DbType getDbType() {
        return DbType.SSH;
    }

    @Override
    public DSProcessor create() {
        return new SSHDSProcessor();
    }
}
