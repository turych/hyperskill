package engine.api.quiz.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
    public Quiz getById(Integer id);
}
