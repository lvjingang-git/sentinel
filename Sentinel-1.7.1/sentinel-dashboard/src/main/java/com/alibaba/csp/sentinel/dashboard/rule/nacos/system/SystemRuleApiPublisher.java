package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gang
 * @Date: 2020/2/24 23:02
 * @Description:
 */
@Component("systemRuleApiPublisher")
public class SystemRuleApiPublisher implements DynamicRulePublisher<List<SystemRuleEntity>> {

    @Autowired
    private ConfigService configService;

    @Override
    public void publish(String app, List<SystemRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app," app name cannot be empty");
        if (rules == null||rules.size()<1) {
            NacosConfigUtil.deleteRule(configService,app,NacosConfigUtil.SYSTEM_DATA_ID_POSTFIX);
            return;
        }
        NacosConfigUtil.setRuleStringToNacos(configService,
                        app,NacosConfigUtil.SYSTEM_DATA_ID_POSTFIX,
                        rules);
    }
}
