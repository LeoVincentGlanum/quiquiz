package com.example.demo.seeder;

import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.model.Reponse;
import com.example.demo.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class QuestionDataLoader implements CommandLineRunner {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ReponseRepository reponseRepository;

    @Override
    public void run(String... args) throws Exception {
        loadQuestionData();
    }

    private void loadQuestionData() {
        if (questionRepository.count() == 0) {
            Question q1 = new Question("Complétez l'expression : 'Mettre du beurre dans les...' ", 1);
            Reponse r1 = new Reponse(q1,"Nouilles",false);
            Reponse r2 = new Reponse(q1,"Patates",false);
            Reponse r3 = new Reponse(q1,"Epinards",true);
            Reponse r4 = new Reponse(q1,"Poches",false);

            questionRepository.save(q1);

            reponseRepository.save(r1);
            reponseRepository.save(r2);
            reponseRepository.save(r3);
            reponseRepository.save(r4);

            Question q2 = new Question("Lorsque quelqu'un écrit avec aisance, on dit qu'il a... ", 1);

            questionRepository.save(q2);


            Reponse r5 = new Reponse(q2,"La plume facile",true);
            Reponse r6 = new Reponse(q2,"Le crayon léger",false);
            Reponse r7 = new Reponse(q2,"Le stylo evident",false);
            Reponse r8 = new Reponse(q2,"Le bic allègre",false);


            reponseRepository.save(r5);
            reponseRepository.save(r6);
            reponseRepository.save(r7);
            reponseRepository.save(r8);

        }
        System.out.println(questionRepository.count());
    }
}