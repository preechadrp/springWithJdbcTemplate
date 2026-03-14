
== หลักการ ==
HTTP Request  //Client Request
   ↓
Controller,RestController   //HTTP
   ↓
Service (@Transactional)    //business logic ควรใช้ @Transactional(rollbackFor = Exception.class) เพื่อให้ rollbak ทุกกรณี
   ↓
Repository (JdbcTemplate)   //database access
   ↓
DataSource
   ↓
HikariCP
   ↓
Database
