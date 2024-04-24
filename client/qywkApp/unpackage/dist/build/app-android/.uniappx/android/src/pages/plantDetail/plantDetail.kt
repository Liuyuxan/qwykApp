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
open class GenPagesPlantDetailPlantDetail : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _cache = this.`$`.renderCache;
        return createElementVNode("view", utsMapOf("class" to "plant-detail"), utsArrayOf(
            createElementVNode("image", utsMapOf("class" to "plant-img", "src" to "/static/image/plant/hawthorn.png", "mode" to "widthFix")),
            createElementVNode("view", utsMapOf("class" to "info center flex justify-center align-center"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "plant-name"), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "img-border", "style" to normalizeStyle(utsMapOf("width" to "100rpx")), "src" to "/static/image/showWindow/Pattern.png", "mode" to "widthFix"), null, 4),
                    createElementVNode("text", utsMapOf("class" to "text text1"), "山"),
                    createElementVNode("text", utsMapOf("class" to "text text2"), "楂")
                )),
                createElementVNode("view", utsMapOf("class" to "info-content ml-3", "style" to normalizeStyle(utsMapOf("width" to "400rpx"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "tags flex justify-between"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "tag"), "酸"),
                        createElementVNode("text", utsMapOf("class" to "tag"), "味甘"),
                        createElementVNode("text", utsMapOf("class" to "tag"), "性温")
                    )),
                    createElementVNode("view", utsMapOf("class" to "mt-2"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "【用药部位】：干燥成熟的果实"),
                        createElementVNode("text", utsMapOf("class" to "text"), "【功能主治】：消食健脾，行气消淤，化浊降脂，用于肉食积滞，淤血闭经，产后瘀阻，岔气疼痛等。")
                    ))
                ), 4)
            )),
            createElementVNode("view", utsMapOf("class" to "btn center"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "btn-img", "style" to normalizeStyle(utsMapOf("width" to "310rpx")), "src" to "/static/image/showWindow/unlocked_Pattern.png", "mode" to "widthFix"), null, 4),
                createElementVNode("text", utsMapOf("class" to "text"), "已解锁")
            )),
            createElementVNode("image", utsMapOf("class" to "bg-img", "src" to "/static/image/showWindow/画卷.png.png", "mode" to "widthFix"))
        ));
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
                return utsMapOf("plant-detail" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "#f2eee9")), "plant-img" to padStyleMapOf(utsMapOf("width" to "460rpx", "position" to "fixed", "top" to "18%", "left" to "150rpx", "zIndex" to 1)), "bg-img" to padStyleMapOf(utsMapOf("width" to "96%", "position" to "absolute", "left" to "50%", "top" to "50%", "transform" to "translate(-50%)", "zIndex" to 0)), "info" to padStyleMapOf(utsMapOf("position" to "fixed", "top" to "56%", "zIndex" to 9)), "img-border" to utsMapOf(".info .plant-name " to utsMapOf("position" to "relative")), "text" to utsMapOf(".info .plant-name " to utsMapOf("position" to "absolute", "left" to "50%", "transform" to "translateX(-50%)", "fontSize" to "64rpx", "color" to "#594532"), ".info .info-content " to utsMapOf("color" to "#937152", "fontSize" to "24rpx"), ".btn " to utsMapOf("position" to "absolute", "top" to "50%", "left" to "50%", "transform" to "translate(-50%)", "color" to "#7a927a", "fontSize" to "52rpx")), "text1" to utsMapOf(".info .plant-name " to utsMapOf("top" to "30%")), "text2" to utsMapOf(".info .plant-name " to utsMapOf("top" to "60%")), "tag" to utsMapOf(".info .info-content .tags " to utsMapOf("width" to "100rpx", "height" to "56rpx", "lineHeight" to "56rpx", "backgroundColor" to "#7a927a", "borderRadius" to "12rpx", "textAlign" to "center", "color" to "#ffffff")), "btn" to padStyleMapOf(utsMapOf("position" to "fixed", "zIndex" to 9, "bottom" to "16%")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
