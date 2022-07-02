package com.pingpongx.smb.fee.server;

import com.google.common.collect.Lists;
import com.pingpongx.smb.fee.common.constants.FeeConstants;
import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationDocable;
import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationInfo;
import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationRun;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.service.ApiInfo;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.pingpongx.smb")
@EnableFeignClients(basePackages = {"com.pingpongx.smb.fee"} )
public class SmbFeeApplication implements BaseApplicationDocable {

    @Override
    @Bean
    public BaseApplicationInfo getAppInfo() {
        ApiInfo apiInfo = new ApiInfo("Api", "Api", "1.0", null, null, null, null, Lists.newArrayList());
        return new BaseApplicationInfo(apiInfo, FeeConstants.APP_NAME);
    }

    public static void main(String[] args) {
        BaseApplicationRun.run(args, SmbFeeApplication.class);
    }

}
