package ee.eljas.kontrolltoo.controller;

import ee.eljas.kontrolltoo.dto.WordSaveDto;
import ee.eljas.kontrolltoo.entity.Word;
import ee.eljas.kontrolltoo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordController {

    @Autowired
    private WordRepository wordRepository;

    @GetMapping("words")
    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    @PostMapping("words")
    public ResponseEntity<?> saveWord(@RequestBody WordSaveDto dto) {
        if (dto.text() == null || dto.text().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Sõna ei tohi olla tühi!");
        }
        if (dto.text().length() < 2) {
            return ResponseEntity.badRequest().body("Viga: Sõna peab olema vähemalt 2 tähte pikk!");
        }

        Word word = new Word();
        word.setText(dto.text());
        return ResponseEntity.ok(wordRepository.save(word));
    }

    @GetMapping("words/count-let")
    public int countThreeLetterWords() {
        List<Word> words = wordRepository.findAll();
        int count = 0;
        for (Word word : words) {
            if (word.getText().length() == 3) {
                count++;
            }
        }
        return count;
    }

    @GetMapping("words/count-div")
    public int countWordsDivisibleByThree() {
        List<Word> words = wordRepository.findAll();
        int count = 0;
        for (Word word : words) {
            if (word.getText().length() > 0 && word.getText().length() % 3 == 0) {
                count++;
            }
        }
        return count;
    }

    @GetMapping("words/{id}/prime")
    public boolean isWordLengthPrime(@PathVariable Long id) {
        Word word = wordRepository.findById(id).orElseThrow();
        int length = word.getText().length();
        return isPrime(length);
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
