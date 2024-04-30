package library.generation;

import com.github.javafaker.Faker;
import library.entity.EducationalLiterature;
import library.entity.FictionLiterature;
import library.entity.Lang;
import library.entity.LiteratureLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class LiteratureGenerator {
    private Faker ruFaker = new Faker(new Locale("ru"));
    private Faker enFaker = new Faker(new Locale("en"));

    public List<FictionLiterature> generateFictionLiteratures(int numFiction) {
        List<FictionLiterature> fictionLiteratures = new ArrayList<>();
        int numEngLiterature = (int) (numFiction / 100.0 * 30);

        for (int i = 0; i < numFiction; i++) {
            if (i < numEngLiterature) {
                String title = enFaker.book().title();
                String author = enFaker.book().author();
                FictionLiterature fiction = new FictionLiterature(title, author, Lang.EN);
                fictionLiteratures.add(fiction);
            } else {
                String title = ruFaker.book().title();
                String author = ruFaker.book().author();
                FictionLiterature fiction = new FictionLiterature(title, author, Lang.RU);
                fictionLiteratures.add(fiction);
            }
        }
        return fictionLiteratures;
    }

    public List<EducationalLiterature> generateEducationalLiteratures(int numEducational) {
        List<EducationalLiterature> educationalLiteratures = new ArrayList<>();
        int numEngLiterature = (int) (numEducational / 100.0 * 30);

        for (int i = 0; i < numEducational; i++) {
            if (i < numEngLiterature) {
                String title = enFaker.book().title();
                String author = enFaker.book().author();
                String discipline = getEnglishDisciplines().get(new Random().nextInt(getEnglishDisciplines().size()));
                LiteratureLevel level = LiteratureLevel.values()[new Random().nextInt(1)];
                String university = getUniversity().get(new Random().nextInt(getUniversity().size()));
                EducationalLiterature edLiterature = new EducationalLiterature(title, author, Lang.EN, level, university, discipline);
                educationalLiteratures.add(edLiterature);
            } else {
                String title = ruFaker.book().title();
                String author = ruFaker.book().author();
                String discipline = getRussianDisciplines().get(new Random().nextInt(getRussianDisciplines().size()));
                String subject = getSubject().get(new Random().nextInt(getSubject().size()));
                EducationalLiterature edLiterature = new EducationalLiterature(title, author, Lang.RU, LiteratureLevel.NON, subject, discipline);
                educationalLiteratures.add(edLiterature);

            }
        }
        return educationalLiteratures;
    }

    private List<String> getSubject() {

        return List.of("Учебник",
                "Пособие",
                "Задачник",
                "Рабочая тетрадь"
        );
    }

    private List<String> getUniversity() {
        return List.of("Harvard University (USA)",
                "University of Oxford (UK)",
                "University of Stuttgart (Germany)",
                "University of Zurich (Switzerland)",
                "University of Melbourne (Australia)"
        );
    }

    private List<String> getRussianDisciplines() {

        return List.of("Сопротивление материалов",
                "Надежность технических систем",
                "Проектная практика"
        );
    }

    private List<String> getEnglishDisciplines() {

        return List.of("The Science of Well-Being",
                "Learning How to Learn",
                "Programming for Everybody"
        );
    }
}
