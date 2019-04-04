# webhook
## 概要
- 对接gitLab webhook,然后通过钉钉api发送事件消息
- 目前只是简单地完成的push event 和tag event
- 钉钉@所有人功能有问题
## 流程
- 通过解析事件header X-Gitlab-Event,分配给不同的service处理
