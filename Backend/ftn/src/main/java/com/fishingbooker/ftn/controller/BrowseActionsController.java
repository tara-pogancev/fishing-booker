package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.dto.ActionDto;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.service.interfaces.ActionReservationService;
import com.fishingbooker.ftn.service.interfaces.ActionService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/actions")
public class BrowseActionsController {

    final private ActionService actionService;

    @GetMapping
    public List<ActionDto> findAll() {
        List<ActionDto> actions = actionService.findAll();
        return actions;
    }


}
