@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME")
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
open class GenPagesMyCollectionMyCollection : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        val _component_search = resolveEasyComponent("search", GenComponentsSearchSearchClass);
        return createElementVNode("scroll-view", utsMapOf("class" to "flex flex-column align-center pt", "style" to normalizeStyle(utsMapOf("flex" to "1", "background-color" to "#fffaf5"))), utsArrayOf(
            createElementVNode("text", utsMapOf("class" to "title mt-3"), "我的收藏"),
            createVNode(_component_search, utsMapOf("placeholder" to "输入主要食材,名称,功效搜索")),
            createElementVNode("view", utsMapOf("class" to "cards"), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.foods, fun(item, index, _, _): VNode {
                    return createElementVNode("view", utsMapOf("class" to "card mt-4 flex justify-center align-center", "key" to index, "style" to normalizeStyle(utsMapOf("background-color" to _ctx.colors[index % 5]))), utsArrayOf(
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "10rpx", "height" to "66%"))), utsArrayOf(
                            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "40rpx", "border-radius" to "10rpx", "background-color" to "rgba(255,255,255,0.5)"))), null, 4)
                        ), 4),
                        createElementVNode("view", utsMapOf("class" to "ml-3", "style" to normalizeStyle(utsMapOf("width" to "70%"))), utsArrayOf(
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "#fff", "font-size" to "12px", "font-weight" to "700"))), toDisplayString(item.name), 5),
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "#fff", "font-size" to "12px"))), toDisplayString(item.`function`), 5)
                        ), 4),
                        createElementVNode("image", utsMapOf("class" to "img ml-2", "src" to item.img, "mode" to "widthFix"), null, 8, utsArrayOf(
                            "src"
                        ))
                    ), 4);
                }
                ), 128)
            )),
            createElementVNode("text", utsMapOf("class" to "mt-5", "style" to normalizeStyle(utsMapOf("font-size" to "24rpx", "color" to "#B5A491"))), "膳食调理只可作为养生辅助，如若身体不适请线下就医", 4)
        ), 4);
    }
    open var colors: UTSArray<String> by `$data`;
    open var foods: UTSArray<foodType> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("colors" to utsArrayOf(
            "#a77e8e",
            "#7a927a",
            "#7a8d91",
            "#91897a",
            "#7e7a91"
        ), "foods" to utsArrayOf<foodType>(foodType(name = "膳食-四神粥", `function` = "主要功效：温补脾胃、补血益气、健脾养胃、滋阴补肝、益肾养肝、改善病态 ", img = "/static/image/pic/1.png"), foodType(name = "膳食-四神粥", `function` = "主要功效：温补脾胃、补血益气、健脾养胃、滋阴补肝、益肾养肝、改善病态 ", img = "/static/image/pic/2.png"), foodType(name = "膳食-四神粥", `function` = "主要功效：温补脾胃、补血益气、健脾养胃、滋阴补肝、益肾养肝、改善病态 ", img = "/static/image/pic/3.png"), foodType(name = "膳食-四神粥", `function` = "主要功效：温补脾胃、补血益气、健脾养胃、滋阴补肝、益肾养肝、改善病态 ", img = "/static/image/pic/4.png"), foodType(name = "膳食-四神粥", `function` = "主要功效：温补脾胃、补血益气、健脾养胃、滋阴补肝、益肾养肝、改善病态 ", img = "/static/image/pic/5.png")));
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
                return utsMapOf("title" to padStyleMapOf(utsMapOf("fontSize" to 20, "color" to "#594532", "paddingLeft" to "40rpx", "paddingBottom" to "20rpx")), "cards" to padStyleMapOf(utsMapOf("width" to "92%")), "card" to utsMapOf(".cards " to utsMapOf("width" to "100%", "height" to "156rpx", "borderRadius" to "20rpx")), "img" to utsMapOf(".cards " to utsMapOf("width" to "112rpx", "borderRadius" to "8rpx")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
