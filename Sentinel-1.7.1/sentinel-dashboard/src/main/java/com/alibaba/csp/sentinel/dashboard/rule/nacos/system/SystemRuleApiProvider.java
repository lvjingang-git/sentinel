package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gang
 * @Date: 2020/2/24 17:31
 * @Description:
 */
@Component("systemRuleNacosProvider")
public class SystemRuleApiProvider implements DynamicRuleProvider<List<SystemRuleEntity>> {


    @Autowired
    private ConfigService configService;

    @Override
    public List<SystemRuleEntity> getRules(String appName) throws Exception {

        return NacosConfigUtil.getRulesEntitiesFromNacos(configService,appName,
                NacosConfigUtil.SYSTEM_DATA_ID_POSTFIX,
                SystemRuleEntity.class);
    }

}
