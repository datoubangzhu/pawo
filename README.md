## 秒杀系统 PAWO
> 简介：pawo 是一个秒杀系统。用于模拟定时抢单。

Springboot+Redis+RabbitMQ+Dubbo


#### 项目模块：

- pawo-power 抢单服务
- pawo-server 定单处理服务
- response 页面部分

###### pawo-power 服务

- pawo-power-app 启用模块，包含常用配置
- pawo-power-common  公共模块
- pawo-power-dao  数据持久层模块
- pawo-power-service  service层模块
- pawo-power-web  接口模块，包含抢单，基础数据注册等接口


