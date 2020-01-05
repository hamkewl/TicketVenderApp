## advan2c-ticketVendingMachine

高度情報演習2CのテーマAのグループワーク「自動販売 (券売) 機」

### メンバー
+ Koki Nagahama (AL17083, @hervtea-dev)
+ Kensyo Nishizawa (AL17086, )

### 機能要件
+ 普通の購入と発券
+ 予約から発券
+ 払い戻し

### 必要なデータテーブル
詳細はドキュメント参照

+ トランザクションテーブル
    + 購入内容
    + 予約履歴
    + 顧客情報は予約から発見するときに必要だけど今回は実装しない
+ マスタテーブル
    + 販売内容

### JSON-Serverの起動方法
1. `$ sudo apt-get install nodejs npm` でNode.jsとパッケージ管理ツールのnpmをダウンロードする
2. `$ npm install -g json-server` でJSON-Serverをインストール
3. db.jsonがあるフォルダで `$ json-server --watch db.json`

### 各システムへのアクセス方法
+ 基本的にlocalhost上でGETとPOSTでがんばる
+ Apache Tomcatは使わない ~~(WEBアプリケーションではないので....)~~
+ JSON-Serverには `localhost:3000`
  + Javascript→JSON-Serverでは `localhost:3000/send` に対してPOST
  + JSON-Server→Javaでは `localhost:3000/send` に対してGET
  + Java→JSON-Serverでは `localhost:3000/receive` に対してPOST
  + JSON-Serverでは `localhost:3000/receive` に対してGET
+ DBには `localhost:5432`
  + Java⇔DBでは `localhost:5432` に対してJDBCでアクセス

### ドキュメント一覧
+ [データ項目と処理フローを簡単にまとめたやつ](https://drive.google.com/open?id=1wX9jG1sOWlThZQrf9eLW6ryLY6EWUO_9)

