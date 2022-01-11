package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.dto.ActionDto;
import com.fishingbooker.ftn.service.interfaces.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
