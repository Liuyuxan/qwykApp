@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME")
package uni.UNIB7338A2;
import io.dcloud.uniapp.*;
import io.dcloud.uniapp.extapi.*;
import io.dcloud.uniapp.framework.*;
import io.dcloud.uniapp.runtime.*;
import io.dcloud.uniapp.vue.*;
import io.dcloud.uniapp.vue.shared.*;
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
            console.log("token", token, " at pages/index/index.uvue:15");
        }
        , instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
            createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "class" to "scroll-Y"), utsArrayOf(
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "bg-img", "src" to "/static/image/bg/宣纸纹理背景.png", "mode" to "widthFix")),
                    createElementVNode("view", utsMapOf("class" to "top-bar flex justify-center"), utsArrayOf(
                        createElementVNode("image", utsMapOf("class" to "plant-avatar", "src" to "/static/image/bg/圆扇.png", "mode" to "widthFix", "style" to normalizeStyle(utsMapOf("width" to "90rpx"))), null, 4),
                        createElementVNode("view", utsMapOf("class" to "right ml-1"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "title-top flex"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title"), toDisplayString(_ctx.plantTitle), 1),
                                createElementVNode("text", utsMapOf("class" to "level ml-1"), "Lv" + toDisplayString(_ctx.level), 1)
                            )),
                            createElementVNode("view", utsMapOf("class" to "bottom flex align-center mt-1"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "today"), "今天"),
                                createElementVNode("view", utsMapOf("class" to "jindutiao ml-1 mr-1")),
                                createElementVNode("text", utsMapOf("class" to "jindutiao-count"), toDisplayString(_ctx.schedule) + "%", 1)
                            ))
                        ))
                    )),
                    createElementVNode("image", utsMapOf("class" to "bg2-img", "src" to "/static/image/bg/圆扇.png", "mode" to "widthFix")),
                    createElementVNode("image", utsMapOf("class" to "desk", "src" to "/static/image/bg/桌子.png", "mode" to "widthFix")),
                    createElementVNode("image", utsMapOf("class" to "plant", "src" to "/static/image/bg/植物.png", "mode" to "widthFix")),
                    createElementVNode("view", utsMapOf("class" to "user-bar flex"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "hexagon-container"), utsArrayOf(
                            createElementVNode("image", utsMapOf("class" to "hexagon-img", "src" to "/static/image/bg/圆扇.png", "mode" to "widthFix"))
                        )),
                        createElementVNode("view", utsMapOf("class" to "right ml-2"), utsArrayOf(
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
                                )),
                                createElementVNode("scroll-view", utsMapOf("direction" to "vertical"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "20px", "height" to "100px", "background-color" to "skyblue"))), null, 4),
                                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "20px", "height" to "100px", "background-color" to "skyblue"))), null, 4),
                                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "20px", "height" to "100px", "background-color" to "skyblue"))), null, 4),
                                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "20px", "height" to "100px", "background-color" to "skyblue"))), null, 4),
                                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "20px", "height" to "100px", "background-color" to "skyblue"))), null, 4),
                                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "20px", "height" to "100px", "background-color" to "skyblue"))), null, 4)
                                ))
                            ))
                        ))
                    ))
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
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("plantTitle" to "人参", "level" to 2, "schedule" to 25, "username" to "初心平原", "challengeDays" to 2, "treeHeight" to 5);
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
                return utsMapOf("bg-img" to padStyleMapOf(utsMapOf("transform" to "scale(1.5)", "position" to "fixed", "top" to 0, "zIndex" to 2)), "bg2-img" to padStyleMapOf(utsMapOf("width" to "580rpx", "zIndex" to 99, "top" to "18%", "left" to "50%", "transform" to "translateX(-50%)")), "desk" to padStyleMapOf(utsMapOf("width" to "750rpx", "zIndex" to 3, "top" to "3%", "left" to "50%", "transform" to "translateX(-50%)")), "plant" to padStyleMapOf(utsMapOf("width" to "560rpx", "zIndex" to 4, "top" to "31%", "left" to "50%", "transform" to "translateX(-50%)")), "top-bar" to padStyleMapOf(utsMapOf("boxSizing" to "border-box", "width" to "650rpx", "height" to "130rpx", "backgroundColor" to "#f2f2e9", "borderRadius" to 100, "borderWidth" to 1, "borderStyle" to "solid", "borderColor" to "#c2a587", "paddingTop" to "20rpx", "paddingRight" to "20rpx", "paddingBottom" to "20rpx", "paddingLeft" to "20rpx", "zIndex" to 999, "top" to "5%", "left" to "50%", "transform" to "translateX(-50%)")), "right" to utsMapOf(".top-bar " to utsMapOf("marginLeft" to "28rpx")), "level" to utsMapOf(".top-bar .right .title-top " to utsMapOf("color" to "#965D30", "fontWeight" to "700", "fontSize" to "32rpx")), "today" to utsMapOf(".top-bar .right .bottom " to utsMapOf("color" to "#BF9350", "fontSize" to "28rpx")), "jindutiao" to utsMapOf(".top-bar .right .bottom " to utsMapOf("width" to "348rpx", "height" to "20rpx", "borderRadius" to 50, "backgroundColor" to "#c5c5c2")), "jindutiao-count" to utsMapOf(".top-bar .right .bottom " to utsMapOf("fontSize" to "28rpx", "color" to "#BF9350")), "user-bar" to padStyleMapOf(utsMapOf("boxSizing" to "border-box", "paddingTop" to "20rpx", "paddingRight" to "20rpx", "paddingBottom" to "20rpx", "paddingLeft" to "20rpx", "zIndex" to 999, "bottom" to "-1%", "left" to "50%", "transform" to "translateX(-50%)", "width" to "580rpx", "height" to "780rpx", "backgroundColor" to "#f9f8f6", "borderRadius" to "40rpx")), "hexagon-img" to utsMapOf(".user-bar " to utsMapOf("width" to "120rpx")), "line" to utsMapOf(".user-bar .right " to utsMapOf("width" to "6rpx", "height" to "26rpx", "backgroundColor" to "#e5e5e5", "borderRadius" to 10, "marginTop" to 0, "marginRight" to "30rpx", "marginBottom" to 0, "marginLeft" to "30rpx")), "icon" to utsMapOf(".user-bar .right " to utsMapOf("width" to "32rpx", "marginRight" to "16rpx")), "text" to utsMapOf(".user-bar .right " to utsMapOf("fontSize" to "24rpx")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
