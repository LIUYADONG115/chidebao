Deque模拟队列先进先出        
Deque<Character> deque = new LinkedList<>();
        deque.add('a');
        deque.add('b');
        deque.add('c');

        System.out.println(deque.peek());

Deque模拟栈先进后出  
        Deque<Character> deque1 = new LinkedList<>();
        deque1.push('a');
        deque1.push('b');
        deque1.push('c');

        System.out.println(deque1.peek());


# paiyipai
heima code

# RocketMQ环境准备
以下载社区5.1.0的二进制包举例：(参考网址：https://rocketmq.apache.org/zh/docs/quickStart/01quickstart/)
<br>1：下载https://rocketmq.apache.org/download/
<br>2：解压
<br>3：启动NameServer：
<br> nohup sh bin/mqnamesrv &
<br>4：启动Broker：
<br> nohup sh bin/mqbroker -n localhost:9876 --enable-proxy &
<br>5：测试MQ
<br>    export NAMESRV_ADDR=localhost:9876
<br>    测试生产者：sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
<br>   测试消费者：sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
<br>6：测试完成后关闭服务器：
<br>    sh bin/mqshutdown broker
<br>    sh bin/mqshutdown namesrv
<br>7: MQ启动时遇到以下问题<br>
java.lang.IllegalAccessError: class org.apache.rocketmq.common.UtilAll....
需要runserver.sh最后一行修改以下内容
<br> $JAVA ${JAVA_OPT} --add-exports=java.base/sun.nio.ch=ALL-UNNAMED $@

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