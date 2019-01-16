## 秒杀系统 PAWO
> 简介：pawo 是一个秒杀系统。用于模拟定时抢单。支持分布式。

Springboot+Redis+RabbitMQ+Dubbo


#### 项目服务模块：

- pawo-power 抢单服务
- pawo-server 提供定单处理服务
- pawo-common 定单处理接口
- response 页面部分

###### pawo-power 服务

- pawo-power-app 启用模块，包含常用配置
- pawo-power-common  公共模块，提供工具类及提供基础插件pom依赖
- pawo-power-dao  数据持久层模块
- pawo-power-service  service层模块
- pawo-power-web  接口模块，包含抢单，基础数据注册等接口

###### pawo-server 服务
pawo-server-dubbo dubbo服务模块，提供dubbo接口实现类


###### pawo-common 模块
pawo-common-basic 提供基础信息公共接口



- 联系作者：JackMing 邮箱2419475724@qq.com
