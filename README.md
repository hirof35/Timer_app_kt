Multi-Clock App (Kotlin/Swing)
KotlinとJava Swingを利用した、タブ切り替え式のデスクトップ時計ツールです。
1つのウィンドウで、現在時刻の確認、経過時間の計測、カウントダウンタイマーの3つの機能を利用できます。

🛠 機能紹介
デジタル時計: 1秒ごとに更新される正確な現在時刻（HH:mm:ss）を表示します。

ストップウォッチ: 0.1秒単位での計測が可能です。スタートおよびストップ機能を備えています。

タイマー: 30秒のカウントダウン機能を搭載。時間が終了するとダイアログでお知らせします。

タブUI: JTabbedPaneを採用し、限られたウィンドウスペースで機能をスムーズに切り替えられます。

🚀 実行方法
前提条件
JDK 8 以上

Kotlin コンパイラ

コンパイルと実行
プロジェクトのルートディレクトリで以下のコマンドを実行してください。

Bash
# コンパイル
kotlinc MultiClockApp.kt -include-runtime -d MultiClock.jar

# 実行
java -jar MultiClock.jar
📂 コード構成のポイント
JTabbedPane: 各機能（Clock, Stopwatch, Timer）を独立したパネルとして管理し、タブで整理しています。

javax.swing.Timer: Swingのイベントスレッドと親和性の高いタイマーを使用し、スレッドセーフにUIを更新しています。

Monospaced Font: 数字の幅が一定のフォントを使用することで、時刻更新時のテキストの揺れを抑えています。

🛠 使用技術
Language: Kotlin

GUI Framework: Java Swing

Update Engine: javax.swing.Timer

📝 ライセンス
このプロジェクトは MITライセンス の下で公開されています。
