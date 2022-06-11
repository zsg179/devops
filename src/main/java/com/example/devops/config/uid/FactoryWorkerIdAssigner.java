package com.example.devops.config.uid;

import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 曙光
 */
public class FactoryWorkerIdAssigner implements WorkerIdAssigner {

    @Autowired
    private SysConfig sysConfig;

    @Override
    public long assignWorkerId() {
        return Long.valueOf(sysConfig.getWorkerId());
    }
}
