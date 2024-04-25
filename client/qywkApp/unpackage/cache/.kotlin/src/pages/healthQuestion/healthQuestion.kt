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
import io.dcloud.uniapp.extapi.getStorageSync as uni_getStorageSync;
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading;
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo;
import io.dcloud.uniapp.extapi.request as uni_request;
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading;
open class GenPagesHealthQuestionHealthQuestion : BasePage {
    constructor(instance: ComponentInternalInstance) : super(instance) {
        onLoad(fun(_: OnLoadOptions) {
            var token = uni_getStorageSync("token");
            console.log("token", token, " at pages/healthQuestion/healthQuestion.uvue:438");
            uni_request<Any>(RequestOptions(url = "/health/questionnaire/getAllProblem?page=" + 1 + "&size=" + 15, header = object : UTSJSONObject() {
                var Authorization = token
            }, success = fun(res){
                var r = res.data;
                if (r == null) {
                    return;
                }
                console.log("问卷数据", r, " at pages/healthQuestion/healthQuestion.uvue:447");
            }
            , fail = fun(err){
                console.log(err, " at pages/healthQuestion/healthQuestion.uvue:450");
            }
            ));
        }
        , instance);
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this;
        val _cache = this.`$`.renderCache;
        return createElementVNode("scroll-view", utsMapOf("class" to "question flex flex-column align-center", "style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("text", utsMapOf("class" to "top-text"), "体质测试以当下感受为准作答，人的体质有可能会随季节变化。"),
            createElementVNode("text", utsMapOf("class" to "name mt-2"), "体质检测"),
            createElementVNode("view", utsMapOf("class" to "content mt-2"), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.listData, fun(item, index, _, _): VNode {
                    return createElementVNode("view", utsMapOf("class" to "question-item mt-2", "key" to index), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title"), toDisplayString(index + 1 + "." + item.problem), 1),
                        createElementVNode("view", utsMapOf("class" to "options flex mt-2"), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(item.optionBox, fun(optionItem, indey, _, _): VNode {
                                return createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                                    "option-item",
                                    if (optionItem.isSelected == true) {
                                        "active-option";
                                    } else {
                                        "";
                                    }
                                )), "style" to normalizeStyle(utsMapOf("width" to (((100 as Number) / item.optionBox.length) + "%"))), "key" to indey, "onClick" to fun(){
                                    _ctx.selectOption(item, optionItem, index, indey);
                                }
                                ), toDisplayString(optionItem.option), 15, utsArrayOf(
                                    "onClick"
                                ));
                            }
                            ), 128)
                        ))
                    ));
                }
                ), 128),
                createElementVNode("button", utsMapOf("class" to "btn", "hover-class" to "btn-is-hover", "onClick" to _ctx.submit), "提交", 8, utsArrayOf(
                    "onClick"
                ))
            )),
            createElementVNode("image", utsMapOf("class" to "bg", "src" to "/static/image/bg/bg_3.png", "mode" to "widthFix"))
        ), 4);
    }
    open var currentIndex: Number by `$data`;
    open var listData: UTSArray<listDataType> by `$data`;
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("currentIndex" to 0, "listData" to utsArrayOf<listDataType>(listDataType(problem = "请选择您的性别:", optionBox = utsArrayOf(
            optionBoxType(option = "男", isSelected = false),
            optionBoxType(option = "女", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你喜欢安静懒得说话吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你面色晦暗或容易出现褐斑吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你容易有黑眼圈吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你口唇颜色偏暗吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你口唇的颜色比一般人红吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你皮肤或口唇干吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你面部两颧潮红或偏红嘛?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你两颧部有细微红丝吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你腹部肥满松软吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你有额部油脂分泌多的现象吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你面部或鼻部有油腻感或者油亮发光吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你精力充沛吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你容易生痤疮或疮疖吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>), listDataType(problem = "你的皮肤在不知不觉中会出现青紫瘀斑（皮下出血)吗?", optionBox = utsArrayOf(
            optionBoxType(option = "没有", isSelected = false),
            optionBoxType(option = "很少", isSelected = false),
            optionBoxType(option = "有时", isSelected = false),
            optionBoxType(option = "经常", isSelected = false),
            optionBoxType(option = "总是", isSelected = false)
        ) as UTSArray<optionBoxType>)));
    }
    override fun `$initMethods`() {
        this.selectOption = fun(item: Any, optionItem: Any, index: Number, indey: Number) {
            console.log(item, optionItem, index, indey, " at pages/healthQuestion/healthQuestion.uvue:416");
            run {
                var i: Number = 0;
                while(i < this.listData[index].optionBox.length){
                    this.listData[index].optionBox[i].isSelected = false;
                    i++;
                }
            }
            this.listData[index].optionBox[indey].isSelected = true;
        }
        ;
        this.submit = fun() {
            uni_showLoading(ShowLoadingOptions(title = "检测体质中"));
            setTimeout(fun(){
                uni_hideLoading();
                uni_navigateTo(NavigateToOptions(url = "/pages/resultHealth/resultHealth"));
            }
            , 2000);
        }
        ;
    }
    open lateinit var selectOption: (item: Any, optionItem: Any, index: Number, indey: Number) -> Unit;
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
                return utsMapOf("question" to padStyleMapOf(utsMapOf("paddingTop" to 50, "backgroundColor" to "#f2eee9")), "bg" to padStyleMapOf(utsMapOf("position" to "absolute", "zIndex" to 0, "width" to 350, "bottom" to "-10%", "left" to "50%", "transform" to "translateX(-50%)")), "top-text" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 18, "fontSize" to 10, "color" to "#ffffff", "backgroundColor" to "#B49B70", "textAlign" to "center", "lineHeight" to "18px")), "name" to padStyleMapOf(utsMapOf("fontSize" to 24, "color" to "#B49B70")), "content" to padStyleMapOf(utsMapOf("boxSizing" to "border-box", "width" to "670rpx", "position" to "absolute", "zIndex" to 9, "backgroundColor" to "rgba(255,255,255,0.3)", "paddingTop" to "50rpx", "paddingRight" to "50rpx", "paddingBottom" to "50rpx", "paddingLeft" to "50rpx", "borderRadius" to 24)), "title" to utsMapOf(".content " to utsMapOf("fontSize" to 16, "color" to "#B49B70")), "options" to utsMapOf(".content " to utsMapOf("width" to "100%")), "option-item" to utsMapOf(".content .options " to utsMapOf("height" to "64rpx", "lineHeight" to "64rpx", "textAlign" to "center", "borderWidth" to 1, "borderStyle" to "solid", "borderColor" to "#B49B70", "backgroundColor" to "#f2efeb", "fontSize" to 16, "color" to "#B49B70")), "active-option" to utsMapOf(".content .options " to utsMapOf("color" to "#ffffff", "backgroundColor" to "#b49b70")), "btn" to padStyleMapOf(utsMapOf("width" to 130, "height" to 40, "fontSize" to 24, "lineHeight" to "40px", "backgroundColor" to "#b49b70", "color" to "#ffffff", "marginTop" to 30, "marginRight" to "auto", "marginBottom" to 30, "marginLeft" to "auto")), "btn-is-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "#594532")));
            }
        var inheritAttrs = true;
        var inject: Map<String, Map<String, Any?>> = utsMapOf();
        var emits: Map<String, Any?> = utsMapOf();
        var props = normalizePropsOptions(utsMapOf());
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf();
        var components: Map<String, CreateVueComponent> = utsMapOf();
    }
}
