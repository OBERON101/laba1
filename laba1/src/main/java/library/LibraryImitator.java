package library;

import library.generation.LiteratureGenerator;
import library.generation.PeopleGenerator;
import library.entity.*;

import java.util.*;

public class LibraryImitator {
    private Set<Literature> literatures;
    private Map<People, Set<Literature>> accoutingMap = new HashMap<>();
    private LiteratureGenerator literatureGenerator;

    public LibraryImitator() {
        literatureGenerator = new LiteratureGenerator();
        literatures = new HashSet<>(generateAllLiterature());
    }

    public void randomFillingMap(List<People> peoples) {
        for (People people : peoples) {
            Random random = new Random();
            int numOfBooks = random.nextInt(1, 5);
            List<Literature> bookList = new ArrayList<>(literatures);
            Set<Literature> peopleLiteratures = new HashSet<>();

            for (int i = 0; i < numOfBooks; i++) {
                int index = random.nextInt(bookList.size());
                Literature book = bookList.get(index);
                peopleLiteratures.add(book);
                literatures.remove(book);
            }
            accoutingMap.put(people, peopleLiteratures);
        }
    }

    private List<Literature> generateAllLiterature() {
        List<FictionLiterature> fictionLiteratures = literatureGenerator.generateFictionLiteratures(100);
        List<EducationalLiterature> educationalLiteratures = literatureGenerator.generateEducationalLiteratures(100);

        List<Literature> allLiterature = new ArrayList<>();
        allLiterature.addAll(fictionLiteratures);
        allLiterature.addAll(educationalLiteratures);

        return allLiterature;
    }

    public void returnBook(People people, Literature literature) {
        Set<Literature> peopleBooks = accoutingMap.get(people);

        if (peopleBooks != null) {
            peopleBooks.remove(literature);
            literatures.add(literature);
        }
    }

    public void takeBook(People people, Literature literature) {
        if (literatures.contains(literature)) {
            Set<Literature> peopleBooks = accoutingMap.getOrDefault(people, new HashSet<>());
            peopleBooks.add(literature);
            accoutingMap.put(people, peopleBooks);
            literatures.remove(literature);
        }
    }

    public Set<Literature> getPeopleBooks(People people) {
        if (accoutingMap.containsKey(people)) {
            return accoutingMap.get(people);
        }
        return new HashSet<>();
    }


    private List<People> generateAllPeople() {
        PeopleGenerator peopleGenerator = new PeopleGenerator();
        List<Student> students = peopleGenerator.generateStudent(20);
        List<Teacher> teachers = peopleGenerator.generateTeacher(10);

        List<People> allPeople = new ArrayList<>();
        allPeople.addAll(students);
        allPeople.addAll(teachers);

        return allPeople;
    }


}
