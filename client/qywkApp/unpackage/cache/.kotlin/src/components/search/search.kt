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
open class GenComponentsSearchSearch : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("input", utsMapOf("class" to "search", "type" to "text", "placeholder" to _ctx.placeholder, "onConfirm" to _ctx.enter), null, 40, utsArrayOf(
                "placeholder",
                "onConfirm"
            ))
        ));
    }
    open var placeholder: Any? by `$props`;
    override fun `$initMethods`() {
        this.enter = fun(e: UniInputConfirmEvent) {
            console.log(e, " at components/search/search.uvue:22");
        }
        ;
    }
    open lateinit var enter: (e: UniInputConfirmEvent) -> Unit;
    companion object {
        var name = "search";
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("search" to padStyleMapOf(utsMapOf("width" to "600rpx", "height" to "76rpx", "borderRadius" to "50rpx", "borderWidth" to 1, "borderStyle" to "solid", "borderColor" to "#796856", "marginTop" to 0, "marginRight" to "auto", "marginBottom" to 0, "marginLeft" to "auto", "paddingLeft" to 10, "backgroundColor" to "#f2f2f2", "fontSize::placeholder" to "28rpx", "marginLeft::placeholder" to "100rpx")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf("placeholder" to utsMapOf("default" to "请输入搜索内容")));
        var propsNeedCastKeys = utsArrayOf(
            "placeholder"
        );
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
