package com.fishingbooker.ftn.student3.integrationTests;


import com.fishingbooker.ftn.TestUtil;
import com.fishingbooker.ftn.dto.AdventureQuickReservationDto;
import com.fishingbooker.ftn.dto.CreateAdventureQuickReservationDto;
import com.fishingbooker.ftn.dto.InstructorNewReservationDto;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "APP_URL=https://localhost:8080",
        "EPASS=***",
        "EMAIL=fishing.booker.service@gmail.com",
        "PASS=admin"})
@AutoConfigureMockMvc
public class AdventureIntegrationTest {

    private static final String URL_PREFIX = "/api/adventures";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }



    @WithMockUser(roles="FISHING_INSTRUCTOR")
    @Test
    public void testAddInstructorReservation() throws Exception {
        InstructorNewReservationDto dto=new InstructorNewReservationDto();
        dto.setAdventureId(1l);
        dto.setClientId(1l);
        dto.setPrice(150.0);
        dto.setGuestNumber(4);
        dto.setStartDate( 1646135669l);
        dto.setEndDate( 1646222069l);

        String json = TestUtil.json(dto);
        String url= URL_PREFIX+"/add-reservation";
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @WithMockUser(roles="FISHING_INSTRUCTOR")
    @Test
    public void testCreateQuickReservation() throws Exception {
        CreateAdventureQuickReservationDto dto=new CreateAdventureQuickReservationDto();
        dto.setAdventureId(2l);
        dto.setPrice(210.0);

        dto.setActionEnd(1649964850l);
        dto.setActionEnd(1649994850l);
        dto.setGuestLimit(5);

        String json = TestUtil.json(dto);
        String url= URL_PREFIX+"/add-quick-reservation";
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isBadRequest());
    }

}
