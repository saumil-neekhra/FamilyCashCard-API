package example.cashcard;

import example.cashcard.service.CashCardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardService cashCardService;

    public CashCardController(CashCardService cashCardService) {
        this.cashCardService = cashCardService;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        return cashCardService.findById(requestedId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCashCard(
            @RequestBody CashCard newCashCardRequest,
            UriComponentsBuilder ucb) {

        CashCard savedCashCard = cashCardService.create(newCashCardRequest);

        URI locationOfNewCashCard = ucb
                .path("/cashcards/{id}")
                .buildAndExpand(savedCashCard.getId())
                .toUri();

        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @GetMapping
    public ResponseEntity<List<CashCard>> findAll(
            @PageableDefault(
                    size = 20,
                    sort = "amount",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        return ResponseEntity.ok(cashCardService.findAll(pageable));
    }
}
