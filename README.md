# binfeiGenerator
一个伟大的项目开始啦
一个你绝对可以看懂，一个你绝对可以看懂，一个你绝对可以看懂的 逆向工程  绝对可以二次开发 二次开发 二次开发的  逆向工程   重要的事说3遍
为了让你们又更好的体验  我特地留了几个BUG呢  快来吐槽我吧

本项目根据mybatisGenerator改造   采用velocity 实现
分页用 pagehelper   实体  用了  lombok @Data 注解

整套逆向工程  代码由本人加度娘一起开发的  代码简洁   相信是个java 程序员就能看得懂  且可以二次开发  更适合自己的项目的逆向工程

优点 各种查询条件 修改条件  采用规定式参数 详情可参考代码  相信你一定可以看懂  别指望我文档写太多  本人很苦逼的 

main 方法是程序入口   

算了  还是给你们说说核心吧   核心就是   对查询  修改 条件  进行了规定  直接传Map   进行查询   并且奉献 DataModel 类   

数字日期行匹配

属性_gt -------- greater than >   
 
属性_gte --------- gt equal >=   

属性_lt -------- less than <   

属性_lte --------- lt equal <=  

属性_ne ----------- not equal !=  

属性_s -----------   in ()  可传数组 或list  


字符串匹配
 
属性_la -------- like as all %XX%形式 

属性_ll -------- like as left XX%形式 

属性_lr -------- like as right %XX形式  

属性_s -----------   in ()  可传数组 或list  

配置文件 

driver:com.mysql.jdbc.Driver  
  
#user:nicai 

#pwd:nicai  

#url:jdbc:mysql://localhost/nicai 


userName:nicai  创建人 
    
table:nicai  表名 <br/>

packageName:com.binfei.XXX  包名  


最后  暂时只支持mysql  也可能这辈子 也就支支持mysql 了
