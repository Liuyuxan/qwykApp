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
import io.dcloud.uniapp.extapi.clearStorageSync as uni_clearStorageSync;
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch;
open class GenComponentsMyPageMyPage : VueComponent {
    constructor(instance: ComponentInternalInstance) : super(instance) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("scroll-view", utsMapOf("class" to "flex flex-column align-center", "direction" to "vertical", "style" to normalizeStyle(utsMapOf("flex" to "1", "padding-top" to "50px", "background-color" to "#f2eee9"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "top-icons flex justify-end"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "top-icon", "src" to "/static/image/icons/health.png", "mode" to "widthFix")),
                createElementVNode("image", utsMapOf("class" to "top-icon", "src" to "/static/image/icons/message_my.png", "mode" to "widthFix")),
                createElementVNode("image", utsMapOf("onClick" to _ctx.out, "class" to "top-icon", "src" to "/static/image/icons/setting.png", "mode" to "widthFix"), null, 8, utsArrayOf(
                    "onClick"
                ))
            )),
            createElementVNode("image", utsMapOf("class" to "avatar", "src" to "/static/image/icons/avatar.png", "mode" to "widthFix")),
            createElementVNode("view", utsMapOf("class" to "bg flex flex-column align-center"), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-top" to "60px", "font-size" to "16px", "color" to "#594532"))), "爱吃汉堡哥的卡皮巴拉", 4),
                createElementVNode("text", utsMapOf("class" to "mt-2", "style" to normalizeStyle(utsMapOf("font-size" to "10px", "color" to "#8A6913"))), "账号：210312", 4),
                createElementVNode("view", utsMapOf("class" to "tag-bar mt-2 flex justify-around"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.tagBars, fun(item, index, _, _): VNode {
                        return createElementVNode("view", utsMapOf("class" to "item flex flex-column align-center", "key" to index), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "mt-1", "style" to normalizeStyle(utsMapOf("font-size" to "12px", "color" to "#808080"))), toDisplayString(item.title), 5),
                            createElementVNode("text", utsMapOf("class" to "mt-1", "style" to normalizeStyle(utsMapOf("font-size" to "12px", "color" to "#000"))), toDisplayString(item.count) + "个", 5)
                        ));
                    }
                    ), 128)
                )),
                createElementVNode("view", utsMapOf("class" to "report mt-5"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "title flex"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "dot ml-1 mr-1")),
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "12px"))), "统计报告", 4)
                    )),
                    createElementVNode("view", utsMapOf("class" to "center-box flex flex-column align-center justify-center"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "box flex"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "left flex flex-column align-center justify-center"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "inner-left flex flex-column align-center justify-center"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "12px", "color" to "#8A6913"))), "连续坚持(天)", 4),
                                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "30px", "color" to "#594532"))), "07", 4),
                                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "14px", "color" to "#8A6913"))), "打卡率100%", 4)
                                ))
                            )),
                            createElementVNode("view", utsMapOf("class" to "right"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title"), "日期"),
                                createElementVNode("text", utsMapOf("class" to "text"), "2024.03.22-2024.04.22"),
                                createElementVNode("text", utsMapOf("class" to "title"), "计划种类"),
                                createElementVNode("text", utsMapOf("class" to "text"), "健康;运动;养发...")
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "box flex justify-center align-center"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "flex justify-center align-center", "style" to normalizeStyle(utsMapOf("width" to "50%"))), utsArrayOf(
                                createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "70%")), "src" to "/static/image/bg/circular_chart.webp", "mode" to "widthFix"), null, 4)
                            ), 4),
                            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50%"))), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "flex flex-wrap", "style" to normalizeStyle(utsMapOf("width" to "100%"))), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.planOptions, fun(item, index, _, _): VNode {
                                        return createElementVNode("view", utsMapOf("class" to "mt-1 flex justify-center align-center option-item", "key" to index), utsArrayOf(
                                            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsArrayOf(
                                                utsMapOf("width" to "6px", "height" to "6px", "border-radius" to "6px"),
                                                utsMapOf("background-color" to item.color)
                                            ))), null, 4),
                                            createElementVNode("text", utsMapOf("class" to "ml-1", "style" to normalizeStyle(utsMapOf("font-size" to "10px"))), toDisplayString(item.name), 5)
                                        ));
                                    }
                                    ), 128)
                                ), 4),
                                createElementVNode("text", utsMapOf("class" to "mt-2", "style" to normalizeStyle(utsMapOf("font-size" to "12px"))), "完成计划数：18", 4),
                                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "12px"))), "待完成计划数：10", 4)
                            ), 4)
                        )),
                        createElementVNode("text", utsMapOf("class" to "mt-2", "style" to normalizeStyle(utsMapOf("color" to "#8A6913"))), "更多打卡记录（生成我的打卡报告）", 4)
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "sign-in-box mt-5"), utsArrayOf(
                    createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%")), "src" to "/static/image/bg/bg_4.webp", "mode" to "widthFix"), null, 4),
                    createElementVNode("view", utsMapOf("class" to "texts flex flex-column align-center"), utsArrayOf(
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "20px", "line-height" to "20px", "font-size" to "14px", "color" to "#fff", "font-weight" to "700"))), utsArrayOf(
                            "累计获得",
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-weight" to "700", "font-size" to "16px", "color" to "#594532"))), " 1000 ", 4),
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "14px", "color" to "#fff", "font-weight" to "700"))), "能量", 4)
                        ), 4),
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "10px", "color" to "#fff", "font-weight" to "700"))), "快去解锁新的装扮吧！", 4)
                    )),
                    createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%")), "src" to "/static/image/bg/bg_5.png", "mode" to "widthFix"), null, 4),
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "40px", "background-color" to "#fffaf5", "line-height" to "40px", "text-align" to "center", "font-size" to "12px", "color" to "#C9B17F"))), "坚持养生打卡，植物和你都会健康成长哦！", 4)
                ))
            ))
        ), 4);
    }
    open var tagBars: UTSArray<tagType> by `$data`;
    open var planOptions: UTSArray<planType> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("tagBars" to utsArrayOf<tagType>(tagType(title = "计划", count = 16), tagType(title = "收藏膳食", count = 0), tagType(title = "好友", count = 8)), "planOptions" to utsArrayOf<planType>(planType(name = "健康（4/5）", color = "#c9b17f"), planType(name = "运动（3/4）", color = "#e09e10"), planType(name = "养发（2/6）", color = "#a5d63f"), planType(name = "健脾（1/3）", color = "#2a82e4")));
    }
    override fun `$initMethods`() {
        this.out = fun() {
            uni_clearStorageSync();
            uni_reLaunch(ReLaunchOptions(url = "/pages/login/login"));
        }
        ;
    }
    open lateinit var out: () -> Unit;
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>>
            get() {
                return normalizeCssStyles(utsArrayOf(
                    styles0
                ));
            }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("avatar" to padStyleMapOf(utsMapOf("width" to 96, "borderRadius" to 96, "marginBottom" to -48, "zIndex" to 99)), "top-icons" to padStyleMapOf(utsMapOf("width" to "100%")), "top-icon" to utsMapOf(".top-icons " to utsMapOf("width" to 24, "marginRight" to 18)), "bg" to padStyleMapOf(utsMapOf("width" to "100%", "backgroundColor" to "#fff5e0", "borderRadius" to 30, "paddingBottom" to 70)), "tag-bar" to utsMapOf(".bg " to utsMapOf("width" to "90%", "borderRadius" to 16, "backgroundColor" to "#fffaf5")), "item" to utsMapOf(".bg .tag-bar " to utsMapOf("width" to "33%")), "report" to utsMapOf(".bg " to utsMapOf("width" to "90%", "borderRadius" to 16, "backgroundColor" to "#fffaf5", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "dot" to utsMapOf(".bg .report .title " to utsMapOf("width" to 10, "height" to 10, "borderRadius" to 10, "backgroundColor" to "#DEB867")), "box" to utsMapOf(".bg .report " to utsMapOf("display" to "flex", "flexDirection" to "row", "width" to 300, "height" to 150, "paddingBottom" to 16, "borderBottomWidth" to 1, "borderBottomStyle" to "dashed", "borderBottomColor" to "#594532")), "left" to utsMapOf(".bg .report " to utsMapOf("width" to "50%")), "inner-left" to utsMapOf(".bg .report .left " to utsMapOf("width" to 100, "height" to 100, "marginTop" to 20, "backgroundColor" to "#fcf6e6", "borderRadius" to 12)), "right" to utsMapOf(".bg .report " to utsMapOf("width" to "50%", "height" to "100%", "display" to "flex", "justifyContent" to "space-between")), "title" to utsMapOf(".bg .report .right " to utsMapOf("fontSize" to 14, "color" to "#594532")), "text" to utsMapOf(".bg .report .right " to utsMapOf("width" to 158, "paddingTop" to 4, "paddingRight" to 12, "paddingBottom" to 4, "paddingLeft" to 12, "backgroundColor" to "#fcf6e6", "borderRadius" to 8, "fontSize" to 12, "color" to "#594532")), "sign-in-box" to utsMapOf(".bg " to utsMapOf("width" to "90%", "borderRadius" to 16, "position" to "relative")), "texts" to utsMapOf(".bg .sign-in-box " to utsMapOf("top" to "50%", "right" to "5%", "position" to "absolute")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
