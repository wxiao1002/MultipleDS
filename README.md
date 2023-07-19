# MultipleDS
multiple data source manager and Execute SQL ,手动管理多个数据源 并且能在多种数据源上执行sql

## 需求来源
最近想做一个可视化展示需求，用户配置数据源，并且查询sql，将查询sql 的结果作为可视化渲染源数据

## 功能
- 管理多种数据源（mysql,pgsql 等常见的数据源）
- 提供访问入口

## 开发
实现某种数据源 要经历三个步骤
1. 继承连接参数BaseConnectParam 实现自己的连接参数
2. 继承 BaseDSProcessor 实现改种数据源的处理,用于SPI 处理
3. 实现DSClient
4. 实现DsChannel 用于创建数据源
5. 实现 DSChannelFactory 用于SPI 服务的发现


## 使用
- 使用DSUtils
- DSClientProvider
- 参考example
```
        PgsqlConnectParam connectionParam =new PgsqlConnectParam();
        connectionParam.setAddress("123.249.117.198");
        connectionParam.setPort(5432);
        connectionParam.setDatabase("things_link");
        connectionParam.setUsername("xxx");
        connectionParam.setPassword("xxx@1xxx23!");
        connectionParam.setDriverClassName("org.postgresql.Driver");
        Connection connection =
                DSUtils.getConnection(DbType.POSTGRESQL, connectionParam);
        // connect 可以做任何事情
        // 或者扩展下 交给jdbctemplate 执行
                
```

<br/>
有缓存的使用（Map）
<br/>


```
DSClientProvider.getInstance().getConnection(DbType.POSTGRESQL, connectionParam)
```
