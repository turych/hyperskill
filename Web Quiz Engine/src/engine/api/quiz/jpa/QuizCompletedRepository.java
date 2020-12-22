package engine.api.quiz.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizCompletedRepository extends PagingAndSortingRepository<QuizCompleted, Integer> {
    public Slice<QuizCompleted> findByAccountId(Integer accountId, Pageable pageable);
}
