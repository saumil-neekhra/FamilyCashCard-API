package example.cashcard.service;

import example.cashcard.CashCard;
import example.cashcard.CashCardRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashCardService {

    private final CashCardRepository cashCardRepository;

    public CashCardService(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    public Optional<CashCard> findById(Long id) {
        return cashCardRepository.findById(id);
    }

    public CashCard create(CashCard cashCard) {
        return cashCardRepository.save(cashCard);
    }

    public List<CashCard> findAll(Pageable pageable) {
        return cashCardRepository.findAll(pageable).getContent();
    }
}
