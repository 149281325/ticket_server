# ticket_server
Ticket system server side

票务系统服务端Java RESTful WebService的实现。
该Repository是一个NetBeans工程。使用WebLogic 12.1.3应用服务器。更换服务器可能需要更换一些配置文件。
ticket.mysql.create.meta.sql 是元数据表建表脚本。
ticket.mysql.create.sql 是业务表建表脚本。
相应的表都有直接的REST接口进行增删改查，即org.ticket.entity.service包下的REST服务。
业务方法在org.ticket.business里。目前实现了读卡、检票。
简单测试用例见curl_test.cmd。

