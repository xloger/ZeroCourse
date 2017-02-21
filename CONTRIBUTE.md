# 贡献代码

## 配置开发环境

* 将项目根目录下的 `sample_signing.properties` 重命名为 `signing.properties`，并修改其中的 keystore 配置。
  * 你也可以将其放在 `~/.gradle/signing.properties` 下。
* 新建 `local.properties`，配置 `sdk.dir=Android SDK 目录`。
* 执行 `./gradlew clean assembleRelease`。产出应当在 `app/build/outputs/apk` 下。

