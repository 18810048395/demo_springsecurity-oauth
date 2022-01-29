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
    根据需要，手动创建mysql数据库，并顺序执行以下sql文件
    src/main/resources/sql/rbac.sql  
    src/main/resources/sql/oauth.sql
    sql/oauth_client_details.sql
    
# oauth2.0
   本次demo分别支持密码模式、授权模式、刷新模式。
   可以通过下方地址，或者将src/main/resources/postman/aouth2.0.postman_collection.json导入postman进行测试
   
   密码模式：
            http://localhost:8080/oauth/token?grant_type=password&redirect_uri=http://localhost:8080/hello&client_id=client_yh&client_secret=123456&username=xiaohei&password=123456
            
   授权模式：
            1.http://localhost:8080/oauth/authorize?client_id=client_yh&response_type=code        
            2.http://localhost:8080/oauth/token?code=3Tw7Sj&grant_type=authorization_code&redirect_uri=http://localhost:8080/hello&scope=yh:scope&client_id=client_yh&client_secret=123456
            
   令牌检查：
            http://localhost:8080/oauth/check_token?token=eb51fcca-ef5d-4fc5-afb6-55c44dbf27dc
            
   令牌刷新：
            http://localhost:8080/oauth/token?grant_type=refresh_token&client_id=client_yh&client_secret=123456&refresh_token=ce95f0f4-8d2c-47b2-8620-aa6ec778485d                    

           