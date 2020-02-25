package com.alibaba.csp.sentinel.dashboard.rule.nacos.authority;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gang
 * @Date: 2020/2/25 10:32
 * @Description:
 */
@Component("authorityRuleApiPublisher")
public class AuthorityRuleApiPublisher implements DynamicRulePublisher<List<AuthorityRuleEntity>> {

    @Autowired
    private ConfigService configService;


    @Override
    public void publish(String app, List<AuthorityRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app,"app name cannot be emty");
        if (rules == null||rules.size()<1) {
            NacosConfigUtil.deleteRule(configService,app,NacosConfigUtil.AUTHORITY_DATA_ID_POSTFIX);
            return;
        }
        NacosConfigUtil.setRuleStringToNacos(configService,
                app,
                NacosConfigUtil.AUTHORITY_DATA_ID_POSTFIX,
                rules);
    }
}
