# Fingerspelling Recognition using LeapMotion  
これはハンドトラッキングにLeapMotionを用いて、取得したパラメータをルールベースAIにより、日本のかな指文字を分類するプロジェクトです。  
※ 精度はあまり期待しないでください  
※ 「の」、「も」、「り」、「を」、「ん」には対応していません  

## 実行方法
### Leap Motion SDK のインストール  
1. [ultraleap releases](https://developer.leapmotion.com/releases/?category=orion)から`LEAP MOTION ORION 3.2.1`をダウンロード  
2. `Leap_Motion_Orion_Setup_win_3.2.1.exe`を実行  


### ビルド
1. `LeapDeveloperKit_3.2.1+45911_win\LeapSDK\lib`フォルダから、プロジェクトのlibsフォルダに`LeapJava.jar`をコピー  
   `LeapDeveloperKit_3.2.1+45911_win\LeapSDK\lib\x64`フォルダから、プロジェクトのlibsフォルダに`LeapJava.dll`と`Leap.dll`をコピー 
2. ターミナルで`gradle build`  

### 実行
1. Leap MotionとコンピュータをUSBで繋げる  
2. ターミナルで`gradle run`  
   - この時点で`LeapJava`が見つかりませんとエラーが出たら`LeapJava.dll`と`Leap.dll`を置いているフォルダにPathを通す  
    ※ gradleで`*.dll`ファイルとリンクする方法が分かる方は教えて頂けると幸いです
3. `Diganostic Visualizer`タブのスタートボタンをクリックで、ハンドトラッキング＆指文字認識がスタート  
4. LeapMotionに向かって指文字表現すると、`テキスト`タブに認識結果が表示される

## Author  
Redgum775  

## License  
[Apache v2 license](LICENSE)