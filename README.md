#代码架构
imooc-security：主模块
imooc-security-core：核心业务逻辑
imooc-security-browser：浏览器安全特定代码
imooc-security-app：app相关特定代码
imooc-security-demo：样例程序
#过滤器链
- SecurityContextPresistenceFilter
过滤连的最前面的一个，当请求进来的时候检查有没有SecurityContext，如果有从session里把SecurityContext拿出来放到线程里，最后快要出来的时候检查SecurityContext在不在线程里，如果在就放到session里
- UsernamePasswordAuthenticationFilter
用于表单请求
- BasicAuthenticationFilter
用于Http Basic
- FilterSecurityInterceptor
最后有个过滤器，根据代码里的配置来判断能不能认证通过
- ExceptionTranslationFilter
这个过滤器在FilterSecurityInterceptor过滤器之前，用来捕获它跑出来的异常
- RememberMeAuthenticationFilter 在绿色区域的倒数第二个
![image.png](https://upload-images.jianshu.io/upload_images/4021619-d60372c075c36a2b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


# OAuth协议简介
##OAuth要解决的问题
直接使用用户名密码进行授权
- 应用可以访问用户的所有数据
- 用户只有修改密码，才能收回授权
- 密码泄露的可能性大大提高
所以OAuth使用token机制来避免上述问题
##OAuth协议中的各中角色
- 服务提供商（Provider）
提供token
- 资源所有者（Resource Owner）
用户
- 第三方应用（Client）
- 认证服务器（Authorization Server）
认证用户的身份，并且产生token
- 资源服务器（Resource Server）
保存用户资源数据，验证token


##OAuth协议的运行流程
![image.png](https://upload-images.jianshu.io/upload_images/4021619-afc9652a9ee97019.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##授权模式
- 授权码模式（authorization code）
![image.png](https://upload-images.jianshu.io/upload_images/4021619-fbcb90eff0761eee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 密码模式（resource owner password credentials）
直接返回token而不是返回授权码
- 简化模式（implicit）
- 客户端模式（client credentials）