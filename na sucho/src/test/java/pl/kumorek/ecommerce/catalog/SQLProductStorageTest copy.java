package pl.kumorek.ecommerce.catalog;

import java.beans.Transient;

@SpringBootTest
public class SQLProductStorageTest {
    @Autowired
    JdbcTemplate jdbcTemplate

    @Test
    void helloWorldWithSql() {
        var sql = """
                select now();
                """;
                String result = jdbcTemplate....

                assertTrue();
    }
}
