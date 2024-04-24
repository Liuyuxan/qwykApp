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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo;
open class GenComponentsShowWindowShowWindow : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("scroll-view", utsMapOf("class" to "show-window", "direction" to "vertical"), utsArrayOf(
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "#383838", "font-size" to "32rpx"))), "植物收集进度", 4),
            createElementVNode("text", utsMapOf("class" to "mt-1", "style" to normalizeStyle(utsMapOf("color" to "#3C6A5D", "font-weight" to "700", "font-size" to "40rpx"))), "3/16", 4),
            createElementVNode("text", utsMapOf("class" to "mt-2", "style" to normalizeStyle(utsMapOf("color" to "#a6a6a6", "font-size" to "32rpx"))), "轻松培养健康生活习惯", 4),
            createElementVNode("view", utsMapOf("class" to "list mt-3"), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.plantListData, fun(item, index, _, _): VNode {
                    return createElementVNode("view", utsMapOf("key" to index, "onClick" to fun(){
                        _ctx.clickPlant(item, index);
                    }
                    ), utsArrayOf(
                        createElementVNode("image", utsMapOf("class" to "img", "src" to item.imgUrl, "mode" to "widthFix"), null, 8, utsArrayOf(
                            "src"
                        )),
                        if (item.isUnlock === false) {
                            createElementVNode("text", utsMapOf("key" to 0, "class" to "text"), "未解锁");
                        } else {
                            createCommentVNode("v-if", true);
                        }
                    ), 8, utsArrayOf(
                        "onClick"
                    ));
                }
                ), 128)
            ))
        ));
    }
    open var plantListData: UTSArray<plantListType> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("plantListData" to utsArrayOf<plantListType>(plantListType(imgUrl = "/static/image/showWindow/Unlocked_hawthorn.png", isUnlock = true), plantListType(imgUrl = "/static/image/showWindow/Unlocked_ginseng.png", isUnlock = true), plantListType(imgUrl = "/static/image/showWindow/Unlocked_peony.png", isUnlock = true), plantListType(imgUrl = "/static/image/showWindow/Not_Unlocked_1.png", isUnlock = false), plantListType(imgUrl = "/static/image/showWindow/Not_Unlocked_2.png", isUnlock = false), plantListType(imgUrl = "/static/image/showWindow/Not_Unlocked_3.png", isUnlock = false), plantListType(imgUrl = "/static/image/showWindow/Not_Unlocked_1.png", isUnlock = false), plantListType(imgUrl = "/static/image/showWindow/Not_Unlocked_2.png", isUnlock = false)));
    }
    override fun `$initMethods`() {
        this.clickPlant = fun(item: Any, index: Number) {
            uni_navigateTo(NavigateToOptions(url = "/pages/plantDetail/plantDetail"));
        }
        ;
    }
    open lateinit var clickPlant: (item: Any, index: Number) -> Unit;
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("show-window" to padStyleMapOf(utsMapOf("boxSizing" to "border-box", "display" to "flex", "flexDirection" to "column", "alignItems" to "center", "backgroundColor" to "#f8f5ed", "paddingTop" to "100rpx", "paddingRight" to "36rpx", "paddingBottom" to 0, "paddingLeft" to "36rpx")), "list" to padStyleMapOf(utsMapOf("width" to "100%", "boxSizing" to "border-box", "display" to "flex", "justifyContent" to "space-around", "flexDirection" to "row", "flexWrap" to "wrap")), "img" to utsMapOf(".list " to utsMapOf("width" to "310rpx", "marginBottom" to 16, "position" to "relative")), "text" to utsMapOf(".list " to utsMapOf("position" to "absolute", "left" to 0, "top" to 0, "width" to "100rpx", "height" to "40rpx", "borderRadius" to "40rpx", "backgroundColor" to "#d6aaa7", "lineHeight" to "40rpx", "textAlign" to "center", "color" to "#ffffff", "fontSize" to "24rpx")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
