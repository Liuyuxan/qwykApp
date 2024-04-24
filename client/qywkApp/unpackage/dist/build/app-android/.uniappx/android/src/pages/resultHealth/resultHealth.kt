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
import io.dcloud.uniapp.extapi.showToast as uni_showToast;
open class GenPagesResultHealthResultHealth : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onLoad(fun(_: OnLoadOptions) {}, instance);
        onMounted(fun() {
            uni_showToast(ShowToastOptions(title = "体质检测成功，报告已生成"));
            this.`$nextTick`(fun(){
                this.acHeight = "animate-ac-height";
            }
            );
        }
        , instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("scroll-view", utsMapOf("direction" to "vertical", "class" to "flex flex-column align-center", "style" to normalizeStyle(utsMapOf("flex" to "1", "background-color" to "#eeede4"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "top flex flex-column align-center", "style" to normalizeStyle(utsMapOf("margin-top" to "50px"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "18px", "color" to "#937152"))), "你的体质", 4),
                createElementVNode("view", utsMapOf("class" to "flex justify-between mt-2", "style" to normalizeStyle(utsMapOf("width" to "120px", "height" to "34px"))), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "body-text"), "特"),
                    createElementVNode("text", utsMapOf("class" to "body-text"), "禀"),
                    createElementVNode("text", utsMapOf("class" to "body-text"), "质")
                ), 4)
            ), 4),
            createElementVNode("view", utsMapOf("class" to "caritry flex"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "body", "style" to normalizeStyle(utsMapOf("width" to "58px", "border" to "1px solid #594532"))), "兼有体质", 4),
                createElementVNode("text", utsMapOf("class" to "body"), "阴虚质"),
                createElementVNode("text", utsMapOf("class" to "body"), "特禀质")
            )),
            createElementVNode("view", utsMapOf("class" to "charts"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "line"), "判定"),
                createElementVNode("view", utsMapOf("class" to "inner flex justify-around"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.chartData, fun(item, index, _, _): VNode {
                        return createElementVNode("view", utsMapOf("class" to "item flex flex-column align-center justify-around", "key" to index), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "top"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                                    "area animate-height",
                                    utsArrayOf(
                                        _ctx.acHeight,
                                        if (item.area > 50) {
                                            "ac-bgc";
                                        } else {
                                            "bgc";
                                        }
                                    )
                                )), "style" to normalizeStyle(utsMapOf("height" to (item.area + "%")))), null, 6)
                            )),
                            createElementVNode("text", utsMapOf("class" to "name"), toDisplayString(item.name), 1)
                        ));
                    }
                    ), 128)
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "result-info"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "top flex align-center"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "score"), utsArrayOf(
                        "46",
                        createElementVNode("text", utsMapOf("class" to "fen"), "分")
                    )),
                    createElementVNode("text", utsMapOf("class" to "ml-3", "style" to normalizeStyle(utsMapOf("font-size" to "24rpx", "width" to "100%"))), utsArrayOf(
                        "你的体质在20-24岁的女性中，优于他人",
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "28rpx", "color" to "#594532"))), "56%", 4)
                    ), 4)
                )),
                createElementVNode("view", utsMapOf("class" to "line mt-3 mb-3")),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "28rpx", "color" to "#937152"))), "特禀体质的成因主要是肺、肾、脾三脏的气血功能失调造成的", 4)
            )),
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-top" to "30px", "font-size" to "32rpx", "color" to "#937152"))), "根据您的体质，该中药材与您相匹配", 4),
            createElementVNode("view", utsMapOf("class" to "mt-5 flex justify-center align-center", "style" to normalizeStyle(utsMapOf("width" to "120rpx", "height" to "120rpx", "border-radius" to "16rpx", "background-color" to "#eee4cf", "border" to "1px solid #594532"))), utsArrayOf(
                createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "140rpx")), "src" to "/static/image/plant/plant_1.png", "mode" to "widthFix"), null, 4)
            ), 4),
            createElementVNode("text", utsMapOf("class" to "mt-2", "style" to normalizeStyle(utsMapOf("font-size" to "24rpx"))), "芍药", 4),
            createElementVNode("view", utsMapOf("class" to "plant-info mt-2"), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "12px", "color" to "#937152"))), "芍药是一种草本花卉，常被用在中药药方里，功效与作用很丰富。 \n1.养血柔肝，散郁祛瘀，改善面部黄褐斑，皮肤粗糙衰老。 \n2.养血调经、敛阴止汗、柔肝止痛、平抑肝阳，用于治疗血虚萎黄、月 经不调、自汗、盗汗、胁痛、腹痛、四肢拘挛疼痛以及头痛、眩晕等症状。 \n3.具有养血敛阴、平抑肝阳、柔肝止痛的功效，用于治疗肝血亏虚、月经 不调、痛经、崩漏、胎产诸证、体虚多汗、阴虚动风、肝阳上亢、胁肋脘 腹疼痛、四肢拘挛作痛等。 \n4. 白芍有广谱抗菌作用；对中枢性及末梢性的肌肉痉挛，以及因痉挛引起的 疼痛有效。 对胸腹痛、腓肠肌痉挛有止疼效果。", 4)
            )),
            createElementVNode("button", utsMapOf("class" to "btn mt-5", "hover-class" to "btn-is-hover", "onClick" to _ctx.submit), "种植并制定我的养生计划", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("image", utsMapOf("class" to "bg", "src" to "/static/image/bg/bg_1.png", "mode" to "widthFix"))
        ), 4);
    }
    open var acHeight: String by `$data`;
    open var chartData: UTSArray<chartType> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("acHeight" to "", "chartData" to utsArrayOf<chartType>(chartType(name = "气虚", area = 30), chartType(name = "阳虚", area = 20), chartType(name = "阴虚", area = 50), chartType(name = "痰湿", area = 26), chartType(name = "湿热", area = 28), chartType(name = "血瘀", area = 45), chartType(name = "气郁", area = 10), chartType(name = "特禀", area = 60)));
    }
    override fun `$initMethods`() {
        this.submit = fun() {
            uni_navigateTo(NavigateToOptions(url = "/pages/drawPlan/drawPlan"));
        }
        ;
    }
    open lateinit var submit: () -> Unit;
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
                return utsMapOf("bg" to padStyleMapOf(utsMapOf("position" to "absolute", "zIndex" to -1, "width" to "100%", "top" to -100, "left" to 0, "right" to 0)), "body-text" to padStyleMapOf(utsMapOf("width" to 32, "height" to 32, "lineHeight" to "32px", "textAlign" to "center", "backgroundColor" to "#f3efeb", "borderRadius" to 4, "boxShadow" to "0 4px #b79e86")), "caritry" to padStyleMapOf(utsMapOf("marginTop" to 35, "width" to "90%", "height" to 26, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#6B6A3D")), "body" to utsMapOf(".caritry " to utsMapOf("marginRight" to 6, "height" to 18, "lineHeight" to "18px", "textAlign" to "center", "color" to "#594532", "fontSize" to 12)), "charts" to padStyleMapOf(utsMapOf("marginTop" to 30, "width" to 375, "height" to 156, "position" to "relative")), "line" to utsMapOf(".charts " to utsMapOf("position" to "absolute", "zIndex" to 9, "top" to "44%", "width" to 375, "height" to 10, "lineHeight" to "10px", "fontSize" to 8, "textAlign" to "right", "borderTopWidth" to 2, "borderTopStyle" to "dashed", "borderTopColor" to "#594532"), ".result-info " to utsMapOf("width" to "100%", "height" to 1, "borderTopWidth" to 1, "borderTopStyle" to "dashed", "borderTopColor" to "#937152")), "inner" to utsMapOf(".charts " to utsMapOf("width" to "100%", "height" to "100%", "display" to "flex", "paddingTop" to 0, "paddingRight" to 10, "paddingBottom" to 0, "paddingLeft" to 10)), "item" to utsMapOf(".charts " to utsMapOf("width" to 26, "height" to "100%")), "top" to utsMapOf(".charts .item " to utsMapOf("width" to "100%", "height" to 136, "borderRadius" to 4, "position" to "relative", "backgroundColor" to "#f8f2e0")), "area" to utsMapOf(".charts .item .top " to utsMapOf("position" to "absolute", "bottom" to 0, "width" to "100%")), "animate-height" to utsMapOf(".charts .item .top " to utsMapOf("transform" to "scaleY(0.1)", "transitionProperty" to "transform", "transitionDuration" to "1.2s")), "animate-ac-height" to utsMapOf(".charts .item .top " to utsMapOf("transform" to "scaleY(1)", "transitionProperty" to "transform", "transitionDuration" to "1.2s")), "ac-bgc" to utsMapOf(".charts .item .top " to utsMapOf("backgroundColor" to "#a78b73")), "bgc" to utsMapOf(".charts .item .top " to utsMapOf("backgroundColor" to "#c8b584")), "name" to utsMapOf(".charts .item " to utsMapOf("fontSize" to 10, "color" to "#000000")), "result-info" to padStyleMapOf(utsMapOf("marginTop" to 30, "width" to "690rpx", "borderRadius" to 6, "backgroundColor" to "#f8f2e0", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "score" to utsMapOf(".result-info .top " to utsMapOf("width" to "124rpx", "height" to "60rpx", "lineHeight" to "-60rpx", "textAlign" to "center", "backgroundColor" to "#fdfbe3", "borderWidth" to 1, "borderStyle" to "solid", "borderColor" to "#d2bf97", "fontSize" to "32rpx", "color" to "#a78b73")), "fen" to utsMapOf(".result-info .top .score " to utsMapOf("fontSize" to "20rpx", "color" to "#a78b73")), "plant-info" to padStyleMapOf(utsMapOf("width" to "690rpx", "backgroundColor" to "#f8f2e0", "borderRadius" to 6, "paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20)), "btn" to padStyleMapOf(utsMapOf("width" to "496rpx", "height" to "84rpx", "lineHeight" to "84rpx", "backgroundColor" to "#d2bf97", "fontSize" to "28rpx", "color" to "#ffffff")), "btn-is-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "#594532")), "@TRANSITION" to utsMapOf("animate-height" to utsMapOf("property" to "transform", "duration" to "1.2s"), "animate-ac-height" to utsMapOf("property" to "transform", "duration" to "1.2s")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
