/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.util.JSONUtils;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
public final class NacosConfigUtil {

    public static final String GROUP_ID = "SENTINEL_GROUP";
    
    public static final String FLOW_DATA_ID_POSTFIX = "-flow-rules";
    public static final String PARAM_FLOW_DATA_ID_POSTFIX = "-param-rules";
    public static final String CLUSTER_MAP_DATA_ID_POSTFIX = "-cluster-map";
    public static final String DEGRADE_MAP_DATA_ID_POSTFIX = "-degrade-rules";

    /**
     * cc for `cluster-client`
     */
    public static final String CLIENT_CONFIG_DATA_ID_POSTFIX = "-cc-config";
    /**
     * cs for `cluster-server`
     */
    public static final String SERVER_TRANSPORT_CONFIG_DATA_ID_POSTFIX = "-cs-transport-config";
    public static final String SERVER_FLOW_CONFIG_DATA_ID_POSTFIX = "-cs-flow-config";
    public static final String SERVER_NAMESPACE_SET_DATA_ID_POSTFIX = "-cs-namespace-set";
    public static final String SYSTEM_DATA_ID_POSTFIX ="-system-rules" ;
    public static final String AUTHORITY_DATA_ID_POSTFIX ="-authority-rules" ;

    private NacosConfigUtil() {}

    public static  <T>  void setRuleStringToNacos(ConfigService service,String appName,String postfix,List<T> rules) throws NacosException {
        AssertUtil.notEmpty(appName,"app name cannot be empty");

        service.publishConfig(getDataId(appName, postfix),
                GROUP_ID,
                JSONUtils.toJSONString(rules)
        );

    }

    public static <T> List<T> getRulesEntitiesFromNacos(ConfigService service,String appName,String postfix,Class<T> tClass) throws NacosException {
        if(StringUtil.isEmpty(appName)){
            return new ArrayList<>();
        }
        String config = service.getConfig(getDataId(appName, postfix),
                GROUP_ID, 3000);
        return JSONUtils.parseObject(tClass,config);
    }

    public static void deleteRule(ConfigService service,String appName,String postfix) throws NacosException {
        service.removeConfig(getDataId(appName,postfix),GROUP_ID);
    }

    private static String getDataId(String appName, String postfix) {
        return appName+postfix;
    }
}
