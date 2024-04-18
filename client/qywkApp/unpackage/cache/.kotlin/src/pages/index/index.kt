package uni.UNIB7338A2;
import io.dcloud.uniapp.*;
import io.dcloud.uniapp.extapi.*;
import io.dcloud.uniapp.framework.*;
import io.dcloud.uniapp.runtime.*;
import io.dcloud.uniapp.vue.*;
import io.dcloud.uniapp.vue.shared.*;
import io.dcloud.unicloud.*;
import io.dcloud.uts.*;
import io.dcloud.uts.Map;
import io.dcloud.uts.Set;
import io.dcloud.uts.UTSAndroid;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.async;
import io.dcloud.uniapp.extapi.getStorageSync as uni_getStorageSync;
open class GenPagesIndexIndex : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onLoad(fun(_: OnLoadOptions) {
            val token = uni_getStorageSync("token");
            console.log("token", token, " at pages/index/index.uvue:131");
        }
        , instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): VNode? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_progress = resolveComponent("progress");
        return createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "style" to "flex:1", "show-scrollbar" to false), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "bg-img", "src" to "/static/image/bg/宣纸纹理背景.png", "mode" to "scaleToFill")),
                createElementVNode("view", utsMapOf("class" to "top-bar flex justify-center"), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "plant-avatar", "src" to "/static/image/bg/圆扇.png", "mode" to "heightFix", "style" to "height: 140rpx;")),
                    createElementVNode("view", utsMapOf("class" to "right"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "title-top flex"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "title"), toDisplayString(_ctx.plantTitle), 1),
                            createElementVNode("text", utsMapOf("class" to "level ml-1"), "Lv" + toDisplayString(_ctx.level), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "bottom flex align-center mt-1"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "today"), "今天"),
                            createVNode(_component_progress, utsMapOf("class" to "jindutiao", "percent" to _ctx.currentPercenter, "stroke-width" to 12, "border-radius" to 20, "activeColor" to "#BF9350", "backgroundColor" to "#c3c3c0"), null, 8, utsArrayOf(
                                "percent"
                            )),
                            createElementVNode("text", utsMapOf("class" to "jindutiao-count"), toDisplayString(_ctx.currentPercenter) + "%", 1)
                        ))
                    ))
                )),
                createElementVNode("image", utsMapOf("class" to "bg2-img center", "src" to "/static/image/bg/圆扇.png", "mode" to "widthFix")),
                createElementVNode("image", utsMapOf("class" to "desk center", "src" to "/static/image/bg/桌子.png", "mode" to "widthFix")),
                createElementVNode("image", utsMapOf("class" to "plant center", "src" to "/static/image/bg/植物.png", "mode" to "widthFix")),
                createElementVNode("view", utsMapOf("class" to "user-bar"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "user-info flex"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "avatar"), utsArrayOf(
                            createElementVNode("image", utsMapOf("class" to "hexagon-img", "src" to "/static/image/bg/圆扇.png", "mode" to "widthFix"))
                        )),
                        createElementVNode("view", utsMapOf("class" to "right ml-3"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "top-text"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "username"), toDisplayString(_ctx.username), 1)
                            )),
                            createElementVNode("view", utsMapOf("class" to "flex align-center mt-2"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "flex align-center"), utsArrayOf(
                                    createElementVNode("image", utsMapOf("class" to "icon", "src" to "/static/image/icons/挑战icon.png", "mode" to "widthFix")),
                                    createElementVNode("text", utsMapOf("class" to "challenge-days text"), "挑战" + toDisplayString(_ctx.challengeDays) + "天", 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "line")),
                                createElementVNode("view", utsMapOf("class" to "flex align-center"), utsArrayOf(
                                    createElementVNode("image", utsMapOf("class" to "icon", "src" to "/static/image/icons/植物状态icon.png", "mode" to "widthFix")),
                                    createElementVNode("text", utsMapOf("class" to "tree-height text"), "树高" + toDisplayString(_ctx.treeHeight) + "M", 1)
                                ))
                            ))
                        ))
                    )),
                    createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "class" to "sign-in-scroll", "show-scrollbar" to false), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.signInData, fun(item, index, _, _): VNode {
                            return createElementVNode("view", utsMapOf("class" to "item flex", "key" to index), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "money-icon"), utsArrayOf(
                                    createElementVNode("image", utsMapOf("style" to "width: 50px;height: 50px;", "src" to item.icon, "mode" to "widthFix"), null, 8, utsArrayOf(
                                        "src"
                                    ))
                                )),
                                createElementVNode("view", utsMapOf("class" to "line-time"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "top")),
                                    createElementVNode("view", utsMapOf("class" to "current"), utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "day"), toDisplayString(index + 1), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "bottom"))
                                )),
                                createElementVNode("text", utsMapOf("style" to "font-size: 24rpx;color: #584717;font-weight: 700;"), toDisplayString(item.text), 1)
                            ));
                        }
                        ), 128)
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "garden center flex flex-column justify-around align-center"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "title"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "circular")),
                        createElementVNode("text", utsMapOf("class" to "text"), "花圃")
                    )),
                    createElementVNode("image", utsMapOf("class" to "graden-bg", "src" to "/static/image/bg/花圃背景.png", "mode" to "widthFix"))
                ))
            ))
        ));
    }
    open var plantTitle: String by `$data`;
    open var level: Number by `$data`;
    open var schedule: Number by `$data`;
    open var username: String by `$data`;
    open var challengeDays: Number by `$data`;
    open var treeHeight: Number by `$data`;
    open var currentPercenter: Number by `$data`;
    open var signInData: UTSArray<signInDataType> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("plantTitle" to "人参" as String, "level" to 2 as Number, "schedule" to 25 as Number, "username" to "初心平原" as String, "challengeDays" to 2 as Number, "treeHeight" to 5 as Number, "currentPercenter" to 20 as Number, "signInData" to utsArrayOf<signInDataType>(signInDataType(icon = "/static/image/icons/money/money-box.png", text = "第七天：未知的惊喜"), signInDataType(icon = "/static/image/icons/money/money-chinese.png", text = "第六天：中国结*2"), signInDataType(icon = "/static/image/icons/money/money.png", text = "第五天：铜钱*4"), signInDataType(icon = "/static/image/icons/money/money.png", text = "第四天：铜钱*3"), signInDataType(icon = "/static/image/icons/money/money-box.png", text = "第三天：未知的惊喜"), signInDataType(icon = "/static/image/icons/money/money-chinese.png", text = "第二天：中国结*1"), signInDataType(icon = "/static/image/icons/money/money.png", text = "第一天：铜钱*2")));
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ), utsArrayOf(
                    GenApp.styles
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("content" to padStyleMapOf(utsMapOf("paddingBottom" to "150rpx")), "bg-img" to padStyleMapOf(utsMapOf("transform" to "scale(1.8)", "width" to "750rpx", "height" to "2000rpx", "position" to "absolute", "zIndex" to 0)), "top-bar" to padStyleMapOf(utsMapOf("boxSizing" to "border-box", "width" to "650rpx", "height" to "130rpx", "backgroundColor" to "#f2f2e9", "borderRadius" to 100, "borderWidth" to 1, "borderStyle" to "solid", "borderColor" to "#c2a587", "padding" to "20rpx", "zIndex" to 999, "top" to "3%", "left" to "50%", "transform" to "translateX(-50%)")), "right" to utsMapOf(".top-bar " to utsMapOf("marginLeft" to "28rpx")), "level" to utsMapOf(".top-bar .right .title-top " to utsMapOf("color" to "#965D30", "fontWeight" to "700", "fontSize" to "32rpx")), "today" to utsMapOf(".top-bar .right .bottom " to utsMapOf("color" to "#BF9350", "fontSize" to "28rpx")), "jindutiao" to utsMapOf(".top-bar .right .bottom " to utsMapOf("margin" to "0 14rpx", "width" to "340rpx")), "jindutiao-count" to utsMapOf(".top-bar .right .bottom " to utsMapOf("fontSize" to "28rpx", "color" to "#BF9350")), "bg2-img" to padStyleMapOf(utsMapOf("width" to "700rpx", "zIndex" to 99, "top" to "4%")), "desk" to padStyleMapOf(utsMapOf("position" to "absolute", "width" to "750rpx", "zIndex" to 3, "top" to "9%")), "plant" to padStyleMapOf(utsMapOf("position" to "absolute", "width" to "560rpx", "zIndex" to 999, "top" to "22%")), "user-bar" to padStyleMapOf(utsMapOf("boxSizing" to "border-box", "padding" to "20rpx", "zIndex" to 999, "bottom" to "-2%", "left" to "50%", "transform" to "translateX(-50%)", "width" to "680rpx", "height" to "780rpx", "backgroundColor" to "#f9f8f6", "borderRadius" to "46rpx")), "user-info" to utsMapOf(".user-bar " to utsMapOf("height" to "140rpx")), "hexagon-img" to utsMapOf(".user-bar .user-info " to utsMapOf("width" to "120rpx")), "line" to utsMapOf(".user-bar .user-info .right " to utsMapOf("width" to "6rpx", "height" to "26rpx", "backgroundColor" to "#e5e5e5", "borderRadius" to 10, "margin" to "0 40rpx")), "icon" to utsMapOf(".user-bar .user-info .right " to utsMapOf("width" to "32rpx", "marginRight" to "16rpx")), "text" to utsMapOf(".user-bar .user-info .right " to utsMapOf("fontSize" to "24rpx"), ".garden .title " to utsMapOf("backgroundColor" to "#ffeec7", "color" to "#7a4923", "borderRadius" to 50, "textAlign" to "center", "lineHeight" to "60rpx")), "sign-in-scroll" to utsMapOf(".user-bar " to utsMapOf("width" to "100%", "height" to "580rpx", "backgroundColor" to "#cccccc")), "item" to utsMapOf(".user-bar .sign-in-scroll " to utsMapOf("marginBottom" to "20rpx", "backgroundColor" to "#965D30", "overflow" to "visible")), "money-icon" to utsMapOf(".user-bar .sign-in-scroll .item " to utsMapOf("width" to "128rpx", "height" to "128rpx", "borderRadius" to "20rpx", "backgroundColor" to "#ededed")), "line-time" to utsMapOf(".user-bar .sign-in-scroll .item " to utsMapOf("overflow" to "visible", "display" to "flex", "justifyContent" to "center")), "top" to utsMapOf(".user-bar .sign-in-scroll .item .line-time " to utsMapOf("width" to 3, "height" to 80, "backgroundColor" to "#ededed")), "current" to utsMapOf(".user-bar .sign-in-scroll .item .line-time " to utsMapOf("width" to "32rpx", "height" to "32rpx", "borderRadius" to "32rpx", "borderWidth" to 1, "borderStyle" to "solid", "borderColor" to "#ededed")), "day" to utsMapOf(".user-bar .sign-in-scroll .item .line-time .current " to utsMapOf("textAlign" to "center", "lineHeight" to "32rpx")), "bottom" to utsMapOf(".user-bar .sign-in-scroll .item .line-time " to utsMapOf("width" to 3, "height" to 100, "backgroundColor" to "#ededed")), "garden" to padStyleMapOf(utsMapOf("overflow" to "visible", "width" to "680rpx", "height" to "400rpx", "backgroundColor" to "#FFEEC7", "borderRadius" to "46rpx", "top" to "4%", "position" to "relative")), "title" to utsMapOf(".garden " to utsMapOf("position" to "absolute", "top" to "-7%", "zIndex" to 99, "width" to "160rpx", "height" to "60rpx")), "circular" to utsMapOf(".garden .title " to utsMapOf("position" to "absolute", "width" to "22rpx", "height" to "22rpx", "backgroundColor" to "#7a4923", "borderRadius" to 50, "top" to "50%", "transform" to "translateY(-50%)", "left" to "8%", "zIndex" to 99)), "graden-bg" to utsMapOf(".garden " to utsMapOf("width" to "700rpx", "position" to "absolute", "left" to "2%", "top" to "-32%")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
