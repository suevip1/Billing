package com.pingpongx.smb.fee.server;

import com.google.common.collect.Lists;
import com.pingpongx.smb.fee.common.constants.TemplateConstants;
import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationDocable;
import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationInfo;
import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationRun;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;

@SpringBootApplication
public class Application implements BaseApplicationDocable {

    @Override
    @Bean
    public BaseApplicationInfo getAppInfo() {
        ApiInfo apiInfo = new ApiInfo("Api", "Api", "1.0", null, null, null, null, Lists.newArrayList());
        return new BaseApplicationInfo(apiInfo, TemplateConstants.APP_NAME);
    }

    public static void main(String[] args) {
        BaseApplicationRun.run(args, Application.class);
    }

}
