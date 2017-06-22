jpa 多对多关系双向关系
1 学生 A
2 老师 B

选择学生作为关系维护端
A.getTeachers().add(Teacher);
  |
  V
向 采用中间表 insert


A.getTeachers().remove(Teacher);
  |
  V
向 采用中间表 delete
