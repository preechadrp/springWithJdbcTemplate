
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

=== ลำดับการ override config Spring จะอ่านตามลำดับ

application.properties
↓
application-{profile}.properties
↓
environment variable
↓
command line

ค่าล่างจะ override ค่าบน
