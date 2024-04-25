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
open class GenPagesHealthDetailHealthDetail : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1", "padding-top" to "100rpx")), "class" to "align-center"), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "border-img", "src" to "/static/image/bg/border_2.png", "mode" to "widthFix")),
                createElementVNode("text", utsMapOf("class" to "text title center"), toDisplayString(_ctx.title), 1),
                createElementVNode("text", utsMapOf("class" to "text subtitle center"), toDisplayString(_ctx.subtitle), 1)
            )),
            createElementVNode("image", utsMapOf("class" to "mt-2", "src" to "/static/image/health/image_1.png", "mode" to "widthFix")),
            createElementVNode("view", utsMapOf("class" to "info mt-2"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "text-info"), toDisplayString(_ctx.info), 1)
            )),
            createElementVNode("view", utsMapOf("class" to "btn center"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "btn-img", "style" to normalizeStyle(utsMapOf("width" to "310rpx")), "src" to "/static/image/showWindow/unlocked_Pattern.png", "mode" to "widthFix"), null, 4),
                createElementVNode("text", utsMapOf("class" to "text"), "已收藏")
            )),
            createElementVNode("image", utsMapOf("class" to "bg", "src" to "/static/image/bg/bg_2.png", "mode" to "widthFix"))
        ), 4);
    }
    open var title: String by `$data`;
    open var subtitle: String by `$data`;
    open var info: String by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "膳食-四神粥", "subtitle" to "———— 健脾|除湿养心 ————", "info" to "\u539F\u6599\uFF1A\n							\u82A1\u5B9E15\u514B\uFF0C\u83B2\u5B5015\u514B,\u6DEE\u5C71\u836F\u5E7210\u514B\uFF0C\u832F\u82D310\u514B\uFF1B\u7CEF\u7C73\u534A\u7897\uFF0C\u767D\u82B8\u8C46\u3001\u7EA2\u67A3\u9002\u91CF\u3002\n\u505A\u6CD5\uFF1A\n							1\u3001\u82A1\u5B9E\u3001\u83B2\u5B50\u3001\u6DEE\u5C71\u836F\u3001\u832F\u82D3\u57FA\u672C\u4E0A\u7528\u7B49\u91CF\u7684\uFF0C\u98DF\u6750\u90FD\u653E\u5165\u6E05\u6C34\u4E2D\u6D78\u6CE130\u5206\u949F\u4EE5\u4E0A\u3002\n							2\u3001\u5C06\u9664\u4E86\u7EA2\u67A3\u4EE5\u5916\u7684\u6240\u6709\u98DF\u6750\uFF0C\u4E00\u540C\u653E\u5165\u9505\u4E2D\uFF0C\u52A0\u8DB3\u591F\u716E\u7CA5\u7684\u6C34\uFF0C\u5927\u706B\u716E\u5F00\u540E\uFF0C\u4E2D\u5C0F\u706B\u6162\u71AC45\u5206\u949F\u3002\n							3\u3001\u5C06\u7EA2\u67A3\u5207\u5F00\uFF0C\u653E\u5165\u9505\u4E2D\uFF0C\u76D6\u4E0A\u9505\u76D6\u7EE7\u7EED\u716E5\u5206\u949F\u540E\u5373\u53EF\u51FA\u9505\u3002");
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
                return utsMapOf("bg" to padStyleMapOf(utsMapOf("width" to "100%", "transform" to "scale(1.4)", "position" to "fixed", "zIndex" to -1, "top" to 0, "left" to 0, "right" to 0, "bottom" to 0)), "text" to utsMapOf("" to utsMapOf("position" to "absolute"), ".btn " to utsMapOf("position" to "absolute", "top" to "50%", "left" to "50%", "transform" to "translate(-50%)", "color" to "#7a927a", "fontSize" to "52rpx")), "title" to padStyleMapOf(utsMapOf("top" to "12%", "fontSize" to "56rpx", "fontWeight" to "700", "color" to "#937152")), "subtitle" to padStyleMapOf(utsMapOf("bottom" to "14%", "fontSize" to "32rpx", "color" to "#937152")), "info" to padStyleMapOf(utsMapOf("width" to "632rpx")), "text-info" to utsMapOf(".info " to utsMapOf("fontSize" to "32rpx", "color" to "#937152")), "btn" to padStyleMapOf(utsMapOf("position" to "absolute", "zIndex" to 9, "bottom" to "6%")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
