# demo_springsecurity-oauth2

##默认用户:
         yanghe/123456  
         xiaohei/123456

##用户接口权限：
         yanghe可调用接口    http://localhost:8080/addMember 
                            http://localhost:8080/delMember
                            http://localhost:8080/updateMember
                            http://localhost:8080/showMember
         xiaohei可调用接口   http://localhost:8080/showMember 
         通用接口            http://localhost:8080/hello
         登陆:               http://localhost:8080/
                             http://localhost:8080/toLogin
         登出:               http://localhost:8080/logout
         
         
#环境搭建数据库脚本: 
    src/main/resources/sql/rbac.sql  

           