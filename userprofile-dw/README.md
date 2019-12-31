### userprofile-dw(数据服务层)
1. 数据输入：数仓的离线Hive表、Kafka流式数据、Mysql业务数据等
2. 数据输出：Hudi数据表
3. 处理逻辑：采用spark计算引擎实现数据整理，为下游的基础标签计算提供数据支撑
