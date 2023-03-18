订单支付接口：http://localhost:8080/contracts/11223344/private-order-pay
请求参数样例：
{
  "amount": 100.0,
  "orderStatus": "ACQUISITION"
}
返回样例：
{
    "code": "200",
    "message": "订单支付成功"
}

订单签收确认接口：
http://localhost:8080/purchase-orders/326118/sign/confirmation
请求参数样例：
{
  "signTime": "2023-3-19 12:33:00",
  "signName":"小明"
}
返回样例：
{
    "code": "200",
    "message": "餐品签收成功"
}