package com.alibaba.csp.sentinel.dashboard.rule.nacos.authority;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: gang
 * @Date: 2020/2/25 10:25
 * @Description:
 */
@Component("authorityRuleApiProvider")
public class AuthorityRuleApiProvider implements DynamicRuleProvider<List<AuthorityRuleEntity>> {

    @Autowired
    private ConfigService configService;


    @Override
    public List<AuthorityRuleEntity> getRules(String appName) throws Exception {

        return NacosConfigUtil.getRulesEntitiesFromNacos(configService,appName,
                NacosConfigUtil.AUTHORITY_DATA_ID_POSTFIX,
                AuthorityRuleEntity.class);
    }
}
