## TicketVenderApp

授業でやったグループワーク「自動販売 (券売) 機」

### 選考企業の方へ
ペアプロ相手が担当しているフロントエンド側が未完成なため実際に動きませんが、
`frontend/main` をご参照いただき、私のコーディングがどのようなものになっているかの参考にしていただければ幸いです

### 開発形態
@herp-vtともう1名によるペアプログラミング

### 機能要件
+ 普通の購入と発券
+ 予約から発券
+ ~~払い戻し (時間的に間に合わなさそうなので実装はしません)~~

### 必要なデータテーブル
詳細はドキュメント参照

+ トランザクションテーブル
    + 購入内容
    + 予約履歴
    + 顧客情報は予約から発見するときに必要だけど今回は実装しない
+ マスタテーブル
    + 販売内容 (商品内容)

### JSON-Serverの起動方法
1. `$ sudo apt-get install nodejs npm` でNode.jsとパッケージ管理ツールのnpmをダウンロードする
2. `$ npm install -g json-server` でJSON-Serverをインストール
3. db.jsonがあるフォルダで `$ json-server --watch db.json`

### Javaでのコンパイルに関する注意
+ カレントディレクトリがmain/original/utilのときにコンパイラが `original.classes.*` および `original.util.*` 系でエラーを吐いたら `$ javac (Java file) -cp ../../` してください  
(というか非推奨行為なのでmainから直接パス指定した方が早いです)

### 各システムへのアクセス方法
+ 基本的にlocalhost上でGETとPOSTでがんばる
+ Apache Tomcatは使わない ~~(WEBアプリケーションではないので....)~~
+ JSON-Serverには `localhost:3000`
	+ アクセス先はmain/json-server/db.jsonを参照してください
+ DBには `localhost:5432`
  + Java⇔DBでは `localhost:5432` に対してJDBCでアクセス
+ 空白は+などにreplaceする必要はない

### ドキュメント一覧
+ [データ項目と処理フローを簡単にまとめたやつ](https://drive.google.com/open?id=1wX9jG1sOWlThZQrf9eLW6ryLY6EWUO_9)
