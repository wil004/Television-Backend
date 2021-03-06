package com.example.novi.controller;
import com.example.novi.model.dto.TelevisionDto;
import com.example.novi.model.dto.TelevisionInputDto;
import com.example.novi.model.Television;
import com.example.novi.services.TelevisionAndWallBracketsService;
import com.example.novi.services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {
    final List<Television> allTelevisions;

   private final TelevisionService televisionService;
    private final TelevisionAndWallBracketsService televisionAndWallBracketsService;

    @Autowired
    public TelevisionsController(List<Television> allTelevisions, TelevisionService televisionService, TelevisionAndWallBracketsService televisionAndWallBracketsService) {
        this.allTelevisions = allTelevisions;
        this.televisionService = televisionService;
        this.televisionAndWallBracketsService = televisionAndWallBracketsService;
    }


    @GetMapping()
    public ResponseEntity<List<TelevisionDto>> printAllTelevisions() {
        return ResponseEntity.ok(televisionAndWallBracketsService.allTelevisionsWithWallBrackets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevisionById(@PathVariable Long id) {
        return ResponseEntity.ok(televisionService.getTelevisionById(id));
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TelevisionDto> create(@RequestBody TelevisionInputDto televisionInputDto) {
        final TelevisionDto createdTelevision = televisionService.createTelevision(televisionInputDto);
        return ResponseEntity.ok(createdTelevision);
    }

    @PutMapping(value = "{televisionId}/remote/{remoteId}",
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TelevisionDto> addRemoteToTelevision(@PathVariable Long televisionId ,@PathVariable Long remoteId) {
        TelevisionDto televisionDto = televisionService.addRemoteToTelevision(televisionId, remoteId);
        return ResponseEntity.ok(televisionDto);
    }

    @PutMapping(value = "{televisionId}/CiModule/{ciModuleId}",
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TelevisionDto> addCiModuleToTelevision(@PathVariable Long televisionId ,@PathVariable Long ciModuleId) {
        TelevisionDto televisionDto = televisionService.addCiModuleToTelevision(televisionId, ciModuleId);
        return ResponseEntity.ok(televisionDto);
    }

    @PutMapping(value = "/{id}/{type}",
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable Long id ,@PathVariable String type, @RequestBody TelevisionInputDto televisionInputDto) {
        final TelevisionDto televisionDto = televisionService.changeTelevision(id, type, televisionInputDto);
        return ResponseEntity.ok(televisionDto);
    }


    @DeleteMapping("/{id}")
    public void deleteTelevision(@PathVariable Long id) {
            televisionService.deleteTelevision(id);
        }
}
