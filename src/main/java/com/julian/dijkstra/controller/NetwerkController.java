package com.julian.dijkstra.controller;

import com.julian.dijkstra.controller.dto.BerekenDto;
import com.julian.dijkstra.controller.dto.RouteDto;
import com.julian.dijkstra.controller.dto.StadDto;
import com.julian.dijkstra.domain.Stad;
import com.julian.dijkstra.service.NetwerkService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("netwerk")
public class NetwerkController {

    NetwerkService netwerkService;

    public NetwerkController() {
        netwerkService = new NetwerkService();
    }

    @PostMapping(path = "bereken", produces = "application/json")
    public RouteDto geefKortsteRoute(@RequestBody @Validated BerekenDto berekenDto) {
        return netwerkService.kortste_route(berekenDto.startpunt, berekenDto.eindpunt, berekenDto.soortGewicht);
    }
    @GetMapping(path = "steden", produces = "application/json")
    public List<StadDto> geefSteden() {
        List<Stad> steden = netwerkService.geefSteden();
        return IntStream.range(0, steden.size())
                .mapToObj(i -> new StadDto(i, steden.get(i).naam))
                .collect(Collectors.toList());
    }

}