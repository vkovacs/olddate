package com.rafi.olddate;

import com.rafi.olddate.persistence.PersonEntity;
import com.rafi.olddate.persistence.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class PersonEntityRepoTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void shouldPersistByUsingGoodDate() {

        //given
        var sanyi = new PersonEntity();
        sanyi.setName("Sandor");
        sanyi.setBirthDate(LocalDate.of(1750, 1, 1));

        //when
        var persistedSanyi = personRepository.save(sanyi);

        //then
        assertThat(persistedSanyi.getId(), is(notNullValue()));
        assertThat(persistedSanyi.getName(), is("Sandor"));
        assertThat(persistedSanyi.getBirthDate(), is(LocalDate.of(1750, 1, 1)));
    }

    @Test
    void shouldPersistAndFindByUsingGoodDate() {

        //given
        var sanyi = new PersonEntity();
        sanyi.setName("Sandor");
        sanyi.setBirthDate(LocalDate.of(1750, 1, 1));
        var persistedSanyi = personRepository.save(sanyi);

        //when
        var foundSanyi = personRepository.findById(persistedSanyi.getId()).orElseThrow();

        //then
        assertThat(foundSanyi.getName(), is("Sandor"));
        assertThat(foundSanyi.getBirthDate(), is(LocalDate.of(1750, 1, 1)));
    }
}
