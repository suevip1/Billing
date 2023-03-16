package com.pingpongx.smb.fee;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @Author xuyq
 * @create 2022/8/12 9:47 上午
 */

@Slf4j
@ExtendWith(MockitoExtension.class)
public  class MockedTest {
    @AfterEach
    void after(){
        Mockito.validateMockitoUsage();
    }
}
