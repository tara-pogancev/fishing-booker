package com.fishingbooker.ftn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "APP_URL=https://localhost:8080",
        "EPASS=***",
        "EMAIL=fishing.booker.service@gmail.com",
        "PASS=2331"})
@AutoConfigureMockMvc
public class FtnApplicationTests {

    @Test
    public void contextLoads() {
    }

}
