package com.fishingbooker.ftn.student3.integrationTests;


import com.fishingbooker.ftn.TestUtil;
import com.fishingbooker.ftn.dto.AdventureQuickReservationDto;
import com.fishingbooker.ftn.dto.InstructorNewReservationDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

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
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testAddInstructorReservation() throws Exception {
        InstructorNewReservationDto dto=new InstructorNewReservationDto();
        dto.setAdventureId(1l);
        dto.setClientId(1l);
        dto.setPrice(150.0);
        dto.setGuestNumber(4);
        dto.setStartDate(1642631033l);
        dto.setEndDate(1642717433l);

        String json = TestUtil.json(dto);
        String url= URL_PREFIX+"/add-reservation";
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isOk());

    }

    /*public void testCreateQuickReservation(){
        AdventureQuickReservationDto dto=new AdventureQuickReservationDto();
        dto.setAdventureId(2l);
        dto.setPrice(210.0);
        dto.setActionEnd(new LocalDateTime());
        dto.setActionStart(1642631033l);
    }*/

}
