package codeit.cakeN.web.contest;

import codeit.cakeN.service.contest.ScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scrap")
public class ScrapController {

    private final ScrapService scrapService;

    @PostMapping
    public ResponseEntity<ScrapDto> scrap(@RequestBody @Valid ScrapDto scrapDto) throws IOException {
        scrapService.scrap(scrapDto);
        return new ResponseEntity<>(scrapDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ScrapDto> unScrap(@RequestBody @Valid ScrapDto scrapDto) throws IOException {
        scrapService.unscrap(scrapDto);
        return new ResponseEntity<>(scrapDto, HttpStatus.OK);
    }
}
