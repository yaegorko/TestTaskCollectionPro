package testtask.collectionpro;

import org.junit.Test;

public class ReadFileTest {
    ReadFile readFile = new ReadFile();

    @Test
    public void test1() {
        readFile.readLines("C:/1/orders.xml");

    }
}