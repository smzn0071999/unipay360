#!/usr/bin/env python
#coding: utf8

import requests
import json
import hashlib


def sign(*p):
    return hashlib.md5(u''.join(p).encode('utf8')).hexdigest().lower()


def pay():
    #像unipay360发起支付请求。
    #如果希望用 unipay360 的支付页面,接口会返回一个已经部署好的前端。如果希望自定义web页面，从GitHub上下载h5



    pay_data = {
        'order_sn': u'123',#订单编号
        'price': '50.00',#金额
        'user_phone': '12345678900',#平台注册手机号
        'remark': u'订单备注',#订单备注 用于显示在订单上
        'title': u'订单标题',#订单标题 用于显示在订单上
        'notify_url': 'http://xxxx/notify',#通知回调地址
        'return_url': 'http://xxxx',#支付完成返回地址

    }
    pay_data['sign'] = sign(
        pay_data['order_sn'],
        pay_data['price'],
        pay_data['user_phone'],
        pay_data['remark'],
        pay_data['title'],
        pay_data['notify_url'],
        pay_data['return_url'],
        'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'  #用户secret
    )
    resp = requests.post('https://unipay360.com/payform/api/query/pre_pay', data=pay_data)
    return json.loads(resp.text)



if __name__ == '__main__':
    resp = pay()
    print resp
