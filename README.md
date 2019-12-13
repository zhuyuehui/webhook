# webhook
## 概要
- 对接gitLab webhook,然后通过钉钉自定义机器人api发送事件消息
- 目前只是简单地完成的push event 和tag event
- 钉钉@所有人功能有问题
- 增加mergeRequest通知 和 针对commit、mergeRequest评论的通知

## 流程
- 通过解析事件header X-Gitlab-Event,分配给不同的service处理
