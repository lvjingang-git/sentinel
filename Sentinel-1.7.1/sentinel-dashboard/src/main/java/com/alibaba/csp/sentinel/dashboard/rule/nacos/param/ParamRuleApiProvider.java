package com.alibaba.csp.sentinel.dashboard.rule.nacos.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gang
 * @Date: 2020/2/25 14:46
 * @Description:
 */
@Component("paramRuleApiProvider")
public class ParamRuleApiProvider implements DynamicRuleProvider<List<ParamFlowRuleEntity>> {

    @Autowired
    private ConfigService configService;

    @Override
    public List<ParamFlowRuleEntity> getRules(String appName) throws Exception {
        return NacosConfigUtil.getRulesEntitiesFromNacos(configService,
                appName,
                NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX,
                ParamFlowRuleEntity.class);
    }
}
