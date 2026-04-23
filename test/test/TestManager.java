package test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import levenshtein.LevenshteinTest;
import static levenshtein.StructuralLevenshtein.DetailLevel.ONE_PER_MEMBER_CATEGORY;
import static levenshtein.StructuralLevenshtein.structuralTestFactory;
import wrappers.MainWrapper;



@LevenshteinTest
public class TestManager {

    static MainWrapper<?> mainClz;

    public static MainWrapper<?> mainClz() {
        return mainClz;
    }

    @BeforeAll
    static void beforeAll() {
        mainClz = new MainWrapper<>();
    }

    void testCompilationAndSetup() {
        assertThat(mainClz).isNotNull();
        assertThat(mainClz).isInstanceOf(MainWrapper.class);

    }
    
    @TestFactory
    List<DynamicTest> strukturTests() {
        testCompilationAndSetup();
        return structuralTestFactory(
            ONE_PER_MEMBER_CATEGORY,
            mainClz
        );
    }

    @Test
    void testMain() {
        try {
            Tests.testMain();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }



}    

