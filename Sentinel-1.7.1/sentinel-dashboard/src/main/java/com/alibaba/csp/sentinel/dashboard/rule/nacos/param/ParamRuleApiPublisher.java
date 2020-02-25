package com.alibaba.csp.sentinel.dashboard.rule.nacos.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gang
 * @Date: 2020/2/25 14:47
 * @Description:
 */
@Component("paramRuleApiPublisher")
public class ParamRuleApiPublisher implements DynamicRulePublisher<List<ParamFlowRuleEntity>> {

    @Autowired
    private ConfigService configService;

    @Override
    public void publish(String app, List<ParamFlowRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app,"app name cannot be empty");
        if(rules ==null || rules.size()<1){
            NacosConfigUtil.deleteRule(configService,
                    app,NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX);
            return;
        }
        NacosConfigUtil.setRuleStringToNacos(configService,app,
                NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX,
                rules);
    }
}
