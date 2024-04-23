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
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading;
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo;
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading;
import io.dcloud.uniapp.extapi.showToast as uni_showToast;
open class GenPagesDrawPlanDrawPlan : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("scroll-view", utsMapOf("class" to "flex flex-column align-center", "direction" to "vertical", "style" to normalizeStyle(utsMapOf("flex" to "1", "background-color" to "#f2eee9", "padding-top" to "50px"))), utsArrayOf(
            createElementVNode("text", utsMapOf("class" to "title"), "请选择本周打卡计划"),
            createElementVNode("text", utsMapOf("class" to "sub-title mt-3"), "------------------------- 根据体质检测结果智能推荐 -------------------------"),
            createElementVNode("button", utsMapOf("class" to "select-plan-btn mt-3", "style" to normalizeStyle(utsMapOf("color" to "#594532", "backgroundColor" to "#f2eee9", "borderColor" to "#594532", "border-radius" to "8px", "width" to "86%", "height" to "70rpx", "line-height" to "70rpx")), "hover-class" to "btn-is-hover"), "选择推荐计划", 4),
            createElementVNode("text", utsMapOf("class" to "sub-title mt-3"), "------------------------------ 创建自定义计划 ------------------------------"),
            createElementVNode("view", utsMapOf("class" to "menu mt-3 flex"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "left", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100%"))), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.menuOptions, fun(item, index, _, _): VNode {
                        return createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                            "flex align-center option",
                            utsArrayOf(
                                if (index == _ctx.currentOptionIndex) {
                                    "active-option";
                                } else {
                                    "";
                                }
                            )
                        )), "key" to index, "onClick" to fun(){
                            _ctx.clickMenuOptions(index);
                        }
                        ), utsArrayOf(
                            withDirectives(createElementVNode("view", utsMapOf("class" to "line"), null, 512), utsArrayOf(
                                utsArrayOf(
                                    vShow,
                                    index == _ctx.currentOptionIndex
                                )
                            )),
                            createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                                "option-text",
                                if (index == _ctx.currentOptionIndex) {
                                    "ac-text";
                                } else {
                                    "";
                                }
                            ))), toDisplayString(item), 3)
                        ), 10, utsArrayOf(
                            "onClick"
                        ));
                    }
                    ), 128)
                ), 4),
                createElementVNode("view", utsMapOf("class" to "right flex flex-column align-center", "style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.plans, fun(item, index, _, _): VNode {
                        return createElementVNode("view", utsMapOf("class" to "plan flex align-center mt-3", "key" to index), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "line mr-3", "style" to normalizeStyle(utsMapOf("background-color" to _ctx.colors[index % 9]))), null, 4),
                            createElementVNode("view", utsMapOf("class" to "texts"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("font-size" to "24rpx"))), toDisplayString(item.name), 5),
                                createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(utsMapOf("font-size" to "20rpx"))), toDisplayString(item.punchCycle), 5)
                            )),
                            createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                                if (item.isSelected) {
                                    "select";
                                } else {
                                    "unselect";
                                }
                            )), "style" to normalizeStyle(utsMapOf("backgroundColor" to if (item.isSelected) {
                                _ctx.colors[index % 9];
                            } else {
                                "#f2eee9";
                            }
                            )), "onClick" to fun(){
                                _ctx.select(index);
                            }
                            ), null, 14, utsArrayOf(
                                "onClick"
                            ))
                        ));
                    }
                    ), 128)
                ), 4)
            )),
            createElementVNode("button", utsMapOf("class" to "create-btn", "hover-class" to "create-btn-is-hover", "onClick" to _ctx.createPlan), "创建", 8, utsArrayOf(
                "onClick"
            ))
        ), 4);
    }
    open var currentOptionIndex: Number by `$data`;
    open var menuOptions: UTSArray<String> by `$data`;
    open var colors: UTSArray<String> by `$data`;
    open var plans: UTSArray<planType1> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("currentOptionIndex" to 0 as Number, "menuOptions" to utsArrayOf<String>("生活", "健康", "运动"), "colors" to utsArrayOf(
            "#3b361a",
            "#a27046",
            "#46a398",
            "#4679a3",
            "#a34646",
            "#83a346",
            "#a39746",
            "#a37246",
            "#46a353"
        ), "plans" to utsArrayOf<planType1>(planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "泡脚", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "艾灸", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "拔火罐", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "刮痧", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "针灸", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "中医熏蒸", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "推拿", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "打坐", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "深呼吸", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false), planType1(id = "00cf6a99-2b19-4a10-96f4-e5947eb7adfb", plantId = null, name = "冥想", subarea = "生活", punchCycle = "1;2;3;4;5;6;7", punchSize = 1, remindTime = "8:00:00", remindMusic = "小叮当.mp3", automatic = "1", startTime = null, endTime = null, isSelected = false)));
    }
    override fun `$initMethods`() {
        this.clickMenuOptions = fun(index: Number) {
            this.currentOptionIndex = index;
        }
        ;
        this.select = fun(index: Number) {
            this.plans[index].isSelected = !this.plans[index].isSelected;
        }
        ;
        this.createPlan = fun() {
            uni_showLoading(ShowLoadingOptions(title = "创建养生计划中"));
            setTimeout(fun(){
                uni_hideLoading();
                uni_showToast(ShowToastOptions(title = "创建成功，开始养生叭！"));
                uni_navigateTo(NavigateToOptions(url = "/pages/tabbar/tabbar"));
            }
            , 1000);
        }
        ;
    }
    open lateinit var clickMenuOptions: (index: Number) -> Unit;
    open lateinit var select: (index: Number) -> Unit;
    open lateinit var createPlan: () -> Unit;
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
                return utsMapOf("title" to padStyleMapOf(utsMapOf("fontSize" to 16, "color" to "#594532", "width" to "100%", "paddingLeft" to "40rpx", "paddingBottom" to "20rpx", "borderBottomWidth" to 2, "borderBottomStyle" to "solid", "borderBottomColor" to "#594532")), "sub-title" to padStyleMapOf(utsMapOf("fontSize" to 10, "color" to "#594532")), "btn-is-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "#594532", "color" to "#ffffff")), "create-btn" to padStyleMapOf(utsMapOf("position" to "absolute", "bottom" to "-3%", "width" to "80%", "height" to "100rpx", "borderRadius" to "16rpx", "lineHeight" to "100rpx", "backgroundColor" to "#b19983", "color" to "#ffffff")), "create-btn-is-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "#594532")), "menu" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%")), "left" to utsMapOf(".menu " to utsMapOf("backgroundColor" to "#eee1ce")), "option" to utsMapOf(".menu .left " to utsMapOf("width" to "100%", "height" to "128rpx", "display" to "flex", "flexDirection" to "row")), "line" to utsMapOf(".menu .left .option " to utsMapOf("width" to "12rpx", "height" to "80rpx", "borderRadius" to "12rpx", "backgroundColor" to "#faf4f0"), ".menu .right .plan " to utsMapOf("width" to "10rpx", "height" to "48rpx", "borderRadius" to "12rpx")), "option-text" to utsMapOf(".menu .left .option " to utsMapOf("width" to "100%", "height" to "100%", "lineHeight" to "128rpx", "textAlign" to "center", "fontSize" to 16, "color" to "#594532")), "ac-text" to utsMapOf(".menu .left .option " to utsMapOf("marginLeft" to -6)), "active-option" to utsMapOf(".menu .left " to utsMapOf("backgroundColor" to "#b19983")), "right" to utsMapOf(".menu " to utsMapOf("backgroundColor" to "#e3cead")), "plan" to utsMapOf(".menu .right " to utsMapOf("width" to "480rpx", "height" to "96rpx", "backgroundColor" to "#f2eee9", "borderRadius" to "16rpx")), "text" to utsMapOf(".menu .right .plan .line " to utsMapOf("color" to "#594532")), "texts" to utsMapOf(".menu .right .plan " to utsMapOf("width" to "380rpx")), "select" to utsMapOf(".menu .right .plan " to utsMapOf("width" to "32rpx", "height" to "32rpx", "borderRadius" to "32rpx")), "unselect" to utsMapOf(".menu .right .plan " to utsMapOf("width" to "32rpx", "height" to "32rpx", "borderRadius" to "32rpx", "borderWidth" to 1, "borderStyle" to "solid", "borderColor" to "#b19983")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
