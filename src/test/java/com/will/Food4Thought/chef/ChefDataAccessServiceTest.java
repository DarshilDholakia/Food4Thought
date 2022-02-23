package com.will.Food4Thought.chef;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChefDataAccessServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private ChefDataAccessService underTest;


    @Test
    void canInsertChef() {

        Chef expected = new Chef(2,"james", "james@gmail.com", "london",22.0);
        underTest.insertChef(expected);
        
        Chef actual = null;
        for (Chef selectAllChef : underTest.selectAllChefs()) {
            if(selectAllChef.getName().equals("james"));
            actual=selectAllChef;
        }
        assertThat(expected).isEqualTo(actual);

    }

    @Test
    void canDeleteChef() {
        List<Chef> expected=new ArrayList<>();
        underTest.deleteChefById(1);
        List<Chef> actual=underTest.selectAllChefs();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canSelectById(){
        Chef expected = new Chef(1,"Sue Lopez", "suelopez@gotmail.com","Grimsby", 75.00);
        Chef actual = underTest.selectChefById(1);
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void canSelectAll(){
        Chef chef = new Chef(1,"Sue Lopez", "suelopez@gotmail.com","Grimsby", 75.00);
       List<Chef> actual = underTest.selectAllChefs();
        List<Chef> expected = new ArrayList<>();
        expected.add(chef);
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void canUpdateById(){
        Chef updatedExpected= new Chef(1,"Jennifer Lopez", "suelopez@gotmail.com","Grimsby", 75.00);
        underTest.updateChefsById(1,updatedExpected);

        List<Chef> expected = new ArrayList<>();
        expected.add(updatedExpected);

        List<Chef> actual= underTest.selectAllChefs();

        assertThat(actual).isEqualTo(expected);

    }


}