- GlobalExceptionHandler
- @Bean Methods ใช้ใน @Configuration Classes
- ทดสอบเชื่อม 2 database
- link ทดสอบ
  http://localhost:8080/api/user  สำหรับเชื่อม db1
  http://localhost:8080/api/user2  สำหรับเชื่อม db2

== หลักการ ==
HTTP Request  //Client Request
   ↓
Controller,RestController   //HTTP
   ↓
Service (@Transactional)    //business logic ควรใช้ @Transactional(rollbackFor = Exception.class) เพื่อให้ rollbak ทุกกรณี
   ↓
Repository (JdbcTemplate)   //database access
   ↓
DataSource (แต่ในตัวอย่างนี้ใช้ HikariDataSource โดยตรง)
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
