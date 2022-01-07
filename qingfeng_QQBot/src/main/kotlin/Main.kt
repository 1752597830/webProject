import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.utils.BotConfiguration.MiraiProtocol.ANDROID_PAD
object WithConfiguration {
    var pwd = "qinfeng.711527";

    @JvmStatic

    fun main(args: Array<String>): Unit = runBlocking {
// 使用自定义配置
        var bot = BotFactory.newBot(753046195, pwd) {
            fileBasedDeviceInfo() // 使用 device.json 存储设备信息
            protocol = ANDROID_PAD // 切换协议
        }.alsoLogin()

        globalEventChannel().subscribeAlways<FriendMessageEvent> {

        }
//    GetData.get()
        globalEventChannel().subscribeAlways<GroupMessageEvent> {
            var data = message.content
            var data1 = data.substring(1,3);
            if("天气".equals(data1)) {
                var data2 = GetData.getPinYin(data.substring(4,data.length))
                if(data2 != null) {
                    var data3 = GetData.getWeather(data2)
                    if(data3 == null)
                        group.sendMessage("城市不存在")
                    else {
                        group.sendMessage(data3)
                    }
                } else {
                    group.sendMessage("输入错误")
                }
            }
//            if ("#天气 西安".equals(message.content)) {
//                var s = JsoupDemo.URLLoader("http://www.weather.com.cn/weather/101110101.shtml");
//                group.sendMessage(s)
//            }
        }
    }
}