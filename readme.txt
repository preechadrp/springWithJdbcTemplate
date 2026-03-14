
== หลักการ ==
HTTP Request  //Client Request
   ↓
Controller,RestController   //HTTP
   ↓
Service (@Transactional)    //business logic
   ↓
Repository (JdbcTemplate)   //database access
   ↓
DataSource
   ↓
HikariCP
   ↓
Database
