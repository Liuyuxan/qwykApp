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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo;
open class GenPagesMyPlanMyPlan : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("scroll-view", utsMapOf("class" to "flex flex-column align-center", "style" to normalizeStyle(utsMapOf("flex" to "1", "background-color" to "#f2eee9", "padding-top" to "50px"))), utsArrayOf(
            createElementVNode("text", utsMapOf("class" to "title"), "我的计划（4个）"),
            createElementVNode("view", utsMapOf("class" to "options flex mt-2"), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.tabs, fun(item, index, _, _): VNode {
                    return createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "option mr-2 ml-1",
                        if (_ctx.currentOptionIndex == index) {
                            "ac-option";
                        } else {
                            "";
                        }
                    )), "key" to item.id, "onClick" to fun(){
                        _ctx.clickOption(index);
                    }
                    ), toDisplayString(item.name), 11, utsArrayOf(
                        "onClick"
                    ));
                }
                ), 128)
            )),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.plans, fun(item, index, _, _): VNode {
                return createElementVNode("view", utsMapOf("class" to "plan flex align-center mt-3", "key" to index), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "line mr-3", "style" to normalizeStyle(utsMapOf("background-color" to _ctx.colors[index % 9]))), null, 4),
                    createElementVNode("view", utsMapOf("class" to "texts"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("font-size" to "24rpx"))), toDisplayString(item.name), 5),
                        createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("font-size" to "20rpx"))), toDisplayString(item.punchCycle), 5)
                    ))
                ));
            }
            ), 128),
            createElementVNode("button", utsMapOf("class" to "add-btn mt-5", "hover-class" to "add-btn-is-hover", "onClick" to _ctx.addPlan), "添加计划", 8, utsArrayOf(
                "onClick"
            ))
        ), 4);
    }
    open var currentOptionIndex: Number by `$data`;
    open var colors: UTSArray<String> by `$data`;
    open var tabs: UTSArray<tabType> by `$data`;
    open var plans: UTSArray<planType2> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("currentOptionIndex" to 0, "colors" to utsArrayOf(
            "#3b361a",
            "#a27046",
            "#46a398",
            "#4679a3",
            "#a34646",
            "#83a346",
            "#a39746",
            "#a37246",
            "#46a353"
        ), "tabs" to utsArrayOf<tabType>(tabType(id = 1, name = "全部"), tabType(id = 2, name = "运动"), tabType(id = 3, name = "健康"), tabType(id = 4, name = "养发"), tabType(id = 5, name = "健脾"), tabType(id = 6, name = "已完成")), "plans" to utsArrayOf<planType2>(planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "泡脚", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "艾灸", subarea = "生活", punchCycle = "每周一次", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "拔火罐", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "刮痧", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "针灸", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "中医熏蒸", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "推拿", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "打坐", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "深呼吸", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType2(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "冥想", subarea = "生活", punchCycle = "每天", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false)));
    }
    override fun `$initMethods`() {
        this.clickOption = fun(index: Number) {
            this.currentOptionIndex = index;
        }
        ;
        this.addPlan = fun() {
            uni_navigateTo(NavigateToOptions(url = "/pages/drawPlan/drawPlan"));
        }
        ;
    }
    open lateinit var clickOption: (index: Number) -> Unit;
    open lateinit var addPlan: () -> Unit;
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
                return utsMapOf("title" to padStyleMapOf(utsMapOf("fontSize" to 20, "color" to "#594532", "width" to "100%", "paddingLeft" to "40rpx", "paddingBottom" to "20rpx", "borderBottomWidth" to 2, "borderBottomStyle" to "dashed", "borderBottomColor" to "#594532")), "options" to padStyleMapOf(utsMapOf("width" to "94%")), "option" to utsMapOf(".options " to utsMapOf("fontSize" to "28rpx", "color" to "#796856", "transform" to "scale(1)", "transitionProperty" to "transform", "transitionDuration" to "0.5s")), "ac-option" to utsMapOf(".options " to utsMapOf("transform" to "scale(1.2)", "transitionProperty" to "transform", "transitionDuration" to "0.5s")), "plan" to padStyleMapOf(utsMapOf("width" to "94%", "height" to "96rpx", "backgroundColor" to "#fcf6e6", "borderRadius" to "16rpx")), "line" to utsMapOf(".plan " to utsMapOf("width" to "10rpx", "height" to "48rpx", "borderRadius" to "12rpx")), "text" to utsMapOf(".plan .line " to utsMapOf("color" to "#594532")), "texts" to utsMapOf(".plan " to utsMapOf("width" to "380rpx")), "select" to utsMapOf(".plan " to utsMapOf("width" to "32rpx", "height" to "32rpx", "borderRadius" to "32rpx")), "unselect" to utsMapOf(".plan " to utsMapOf("width" to "32rpx", "height" to "32rpx", "borderRadius" to "32rpx", "borderWidth" to 1, "borderStyle" to "solid", "borderColor" to "#b19983")), "add-btn" to padStyleMapOf(utsMapOf("width" to "80%", "height" to "100rpx", "borderRadius" to "16rpx", "lineHeight" to "100rpx", "backgroundColor" to "#b19983", "color" to "#ffffff")), "add-btn-is-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "#594532")), "@TRANSITION" to utsMapOf("option" to utsMapOf("property" to "transform", "duration" to "0.5s"), "ac-option" to utsMapOf("property" to "transform", "duration" to "0.5s")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
