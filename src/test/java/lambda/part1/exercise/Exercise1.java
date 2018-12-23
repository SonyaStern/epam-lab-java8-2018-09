package lambda.part1.exercise;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lambda.data.Person;
import org.junit.jupiter.api.Test;

class Exercise1 {

  @Test
  void sortPersonsByAgeUsingArraysSortLocalComparator() {
    Person[] persons = getPersons();

    // TODO use Arrays.sort
    Arrays.sort(persons, Comparator.comparing(Person::getAge));


    assertThat(persons, is(arrayContaining(
        new Person("Иван", "Мельников", 20),
        new Person("Николай", "Зимов", 30),
        new Person("Алексей", "Доренко", 40),
        new Person("Артем", "Зимов", 45)
    )));
  }

  @Test
  void sortPersonsByAgeUsingArraysSortAnonymousComparator() {
    Person[] persons = getPersons();

    // TODO use Arrays.sort
    Arrays.sort(persons, Comparator.comparingInt(Person::getAge));

    assertThat(persons, is(arrayContaining(
        new Person("Иван", "Мельников", 20),
        new Person("Николай", "Зимов", 30),
        new Person("Алексей", "Доренко", 40),
        new Person("Артем", "Зимов", 45)
    )));
  }

  @Test
  void sortPersonsByLastNameThenFirstNameUsingArraysSortAnonymousComparator() {
    Person[] persons = getPersons();

    // TODO use Arrays.sort
    Arrays.sort(persons,
        Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));

    assertThat(persons, is(arrayContaining(
        new Person("Алексей", "Доренко", 40),
        new Person("Артем", "Зимов", 45),
        new Person("Николай", "Зимов", 30),
        new Person("Иван", "Мельников", 20)
    )));
  }

  @Test
  void findFirstWithAge30UsingGuavaPredicate() {
    List<Person> persons = Arrays.asList(getPersons());

    // TODO use FluentIterable
    Optional<Person> person = FluentIterable.from(persons)
        .firstMatch(person1 -> Integer.valueOf(30).equals(person1.getAge()));

    assertThat(person.get(), is(new Person("Николай", "Зимов", 30)));
  }

  @Test
  void findFirstWithAge30UsingGuavaAnonymousPredicate() {
    List<Person> persons = Arrays.asList(getPersons());

    // TODO use FluentIterable
    Optional<Person> person = FluentIterable.from(persons).firstMatch(new Predicate<Person>() {
      @Override
      public boolean apply(Person person) {
        return Integer.valueOf(30).equals(person.getAge());
      }
    });

    assertThat(person.get(), is(new Person("Николай", "Зимов", 30)));
  }

  private Person[] getPersons() {
    return new Person[]{
        new Person("Иван", "Мельников", 20),
        new Person("Алексей", "Доренко", 40),
        new Person("Николай", "Зимов", 30),
        new Person("Артем", "Зимов", 45)
    };
  }
}
