## 秒杀购物系统 PAWO
> 简介：pawo 是一个秒杀购物系统。用于模拟抢单。采用mq处理普通下单信息，双十一抢单时采用分布式rpc+Nginx实现负载均衡。

技术实现：Springboot+Redis+RabbitMQ+Dubbo+Nginx

<div  align="center">  
 <img src="http://pawo.oss-cn-beijing.aliyuncs.com/E8%60K3W8%60%29ZWVVIQZRJT%7B%7BXF.png?Expires=1548758744&OSSAccessKeyId=TMP.AQEkHsurjvfdJQMApMA5WIjb5x_RBjIrEkE7bVncoq1hde8fQTuW_hkiCwc3ADAtAhRbSoqPcakm2uThxMWdzyf1A8uGjQIVAPdX9q2GiHw_0Us_FdmS61kkMRrb&Signature=aClvincEbgP1xqIxPA%2BE5ZYQZbc%3D" width = "680" height = "500" alt="pawo2"   align=center >
</div>

### 项目服务模块：

- pawo-power 抢单接口和基础服务
- pawo-server 提供订单处理服务
- pawo-common 订单处理接口
- response 页面部分

###### pawo-power 服务
```
 pawo-power-app 启动模块，包含常用配置
 pawo-power-common  公共模块，提供工具类及提供基础插件pom依赖
 pawo-power-dao  数据持久层模块
 pawo-power-service  service层模块
 pawo-power-web  接口模块，包含抢单，基础数据注册等接口
```
###### pawo-server 服务
```
pawo-server-dubbo dubbo服务模块，提供rpc接口实现类，处理抢单逻辑，支持负载均衡
```

###### pawo-common 模块
```
pawo-common-basic 提供基础rpc公共接口，包括抢单接口等
```

#### 其他实现支持：
- 跨域，全局异常处理
- 代理下单接口检查
