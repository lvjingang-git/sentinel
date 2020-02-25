package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gang
 * @Date: 2020/2/25 15:09
 * @Description:
 */
@Component("degradeRuleApiPublisher")
public class DegradeRuleApiPublisher implements DynamicRulePublisher<List<DegradeRuleEntity>> {

    @Autowired
    private ConfigService configService;

    @Override
    public void publish(String app, List<DegradeRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app,"app name cannot be empty");
        if(rules==null||rules.size()<1){
            NacosConfigUtil.deleteRule(configService,app,NacosConfigUtil.DEGRADE_MAP_DATA_ID_POSTFIX);
            return;
        }
        NacosConfigUtil.setRuleStringToNacos(configService,app,
                NacosConfigUtil.DEGRADE_MAP_DATA_ID_POSTFIX,rules);
    }
}
