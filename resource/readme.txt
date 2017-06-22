restful 接口一览：
http://localhost:8089/zlwx/webapi/application.wadl

Mybatis 开发要点
namespace 命名空间，作用 对sql进行分类管理，理解sql隔离。
mapper代理开发方式：遵循  namespace 等于 Mapper接口地址  就会自动生成 接口的实现类的代理对象

com.sun.jersey 这个版本问题多多，有很多历史问题
现准备改用 glassfish.jersey
原项目已经备份到百度云盘：项目 文件夹中了

jersey 与struts 1.x 一样 会存在线程安全问题，因为都是继承了sevlet都是单例模式()，如果使用成员变量就会存在线程安全问题，建议使用局部变量。
struts1 使用actionForm 方法入参
如果一定要使用成员变量 ，则应该与数据操作无关的