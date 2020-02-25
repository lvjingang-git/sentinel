package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.auth.AuthAction;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gang
 * @Date: 2020/2/25 15:09
 * @Description:
 */
@Component("degradeRuleApiProvider")
public class DegradeRuleApiProvider implements DynamicRuleProvider<List<DegradeRuleEntity>> {

    @Autowired
    private ConfigService configService;

    @Override
    public List<DegradeRuleEntity> getRules(String appName) throws Exception {
        return NacosConfigUtil.getRulesEntitiesFromNacos(configService,
                appName,NacosConfigUtil.DEGRADE_MAP_DATA_ID_POSTFIX,
                DegradeRuleEntity.class);
    }
}
