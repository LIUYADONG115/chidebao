# chidebao
heima code

# RocketMQ环境准备
以下载社区5.1.0的二进制包举例：(参考网址：https://rocketmq.apache.org/zh/docs/quickStart/01quickstart/)
1：下载https://rocketmq.apache.org/download/
2：解压后执行：mvn -Prelease-all -DskipTests -Dspotbugs.skip=true clean install -U
3：启动NameServer：nohup sh bin/mqnamesrv &
4：启动Broker：nohup sh bin/mqbroker -n localhost:9876 --enable-proxy &
5：测试MQ
    export NAMESRV_ADDR=localhost:9876
    测试生产者：sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
    测试消费者：sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
6：测试完成后关闭服务器：
    sh bin/mqshutdown broker
    sh bin/mqshutdown namesrv
# sample
1:保证金支付接口（POST）：
http://localhost:8090/bidder-contracts/123/deposit-payment-request

请求参数样例：
{
"paymentAmount": 50.0,
"bankAccount": "zhongguoyinhang123"
}

返回样例：
{
"code": "200",
"message": "保证金支付成功"
}

注意：
    1.用户甲（协议ID：123）和用户乙（协议ID：456）都只有100元，根据题目要求同一拍品保证金支付成功一次以后，不可二次支付。
    2.如果在次需要验证账户余额不足场景请切换账户



2:订单签收确认接口（POST）：
http://localhost:8090/bidder-contracts/123/sign-deal-protocol-request

请求参数样例：
{
"signTime": "2023-3-19 12:33:00",
"signName":"小明"
}

返回样例：
{
"code": "200",
"message": "成交协议签约已受理"
}


3：查看成交协议签署结果接口（GET）:
http://localhost:8090/bidder-contracts/123/sign-deal-protocol-request

返回样例：
{
"code": "200",
"message": "成交协议签署成功"
}

注意：代码启动后会模拟成交协议自动消费MQ中的消息，因此查看成交协议签署结果会显示最终结果