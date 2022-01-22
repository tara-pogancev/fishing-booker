package com.fishingbooker.ftn.student3.integrationTests;

import com.fishingbooker.ftn.TestUtil;
import com.fishingbooker.ftn.dto.AvailableInstructorTimePeriodDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "APP_URL=https://localhost:8080",
        "EPASS=***",
        "EMAIL=fishing.booker.service@gmail.com",
        "PASS=admin"})
@AutoConfigureMockMvc
public class InstructorAvailableTimeTest {

    private static final String URL_PREFIX = "/api/available-instructor-time";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }


    @WithMockUser(roles="FISHING_INSTRUCTOR")
    @Test
    public void createOrUpdateTest() throws Exception {
        AvailableInstructorTimePeriodDto dto=new AvailableInstructorTimePeriodDto();
        dto.setInstructorId(4l);
        dto.setId(-1l);
        dto.setStartDate(1643581433l);
        dto.setEndDate(1643591433l);

        String json = TestUtil.json(dto);
        String url= URL_PREFIX;
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isBadRequest());
    }
}
