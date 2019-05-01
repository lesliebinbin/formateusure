package au.edu.uq;

import au.edu.uq.services.StaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExcelServiceTest {
    @Autowired
    TestRestTemplate template;
    @Autowired
    StaffService service;

    @Test
    public void testGetAllSheetsFromExcel() {
        ResponseEntity<String> forEntity = template.getForEntity("/excel/seat_plan", String.class);
        System.out.println(forEntity.getBody());
        forEntity = template.getForEntity("/excel/seat_plan/Leslie RF", String.class);
        System.out.println(forEntity.getBody());
    }
}
